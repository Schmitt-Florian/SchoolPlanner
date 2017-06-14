package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Settings;
import schmitt_florian.schoolplanner.logic.objects.Period;
import schmitt_florian.schoolplanner.logic.objects.Schedule;
import schmitt_florian.schoolplanner.logic.objects.Subject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ScheduleFragment extends Fragment {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private View rootView;

    private TableLayout table;
    private Schedule schedule;
    private TableRow[] rows;
    private Button[][] buttons;

    private DatabaseHelper databaseHelper;
    private boolean editMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        databaseHelper = new DatabaseHelperImpl(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        initGui();
        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        @SuppressWarnings({"FieldNever", "unused"})
        void onFragmentInteraction(Uri uri);
    }


    //region private methods

    /**
     * method to initialise components of the GUI
     */
    private void initGui() {
        table = (TableLayout) rootView.findViewById(R.id.schedule_table);
        schedule = databaseHelper.getScheduleAtId(1);

        rows = getScheduleRowsInArray();
        initVisibilityForSchedule();

        buttons = getButtonsAsArray();
        initButtons();

        initAppbarEditSwitch();
    }

    /**
     * method to get all {@link TableRow} in the schedule {@link TableLayout}
     *
     * @return all {@link TableRow} in the schedule {@link TableLayout}
     */
    private TableRow[] getScheduleRowsInArray() {
        ArrayList<TableRow> rowArrayList = new ArrayList<>();

        for (int i = 0; i < table.getChildCount(); i++) {
            rowArrayList.add((TableRow) table.getChildAt(i));
        }

        return rowArrayList.toArray(new TableRow[0]);
    }

    /**
     * method to get all {@link Button}s in the schedule {@link TableLayout}
     *
     * @return all {@link Button}s in the schedule {@link TableLayout}
     */
    private Button[][] getButtonsAsArray() {
        Button[][] buttons = new Button[((LinearLayout) rows[0].getChildAt(0)).getChildCount()][table.getChildCount()];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < ((LinearLayout) rows[i].getChildAt(0)).getChildCount(); j++) {
                buttons[j][i] = (Button) ((LinearLayout) rows[i].getChildAt(0)).getChildAt(j);
            }
        }
        return buttons;
    }

    /**
     * method to initialise visibility of the schedule rows based on {@link Settings#getPeriodsAtDay()}
     */
    private void initVisibilityForSchedule() {
        int visibleRowCount = Settings.getInstance(getContext()).getPeriodsAtDay() + 1;
        for (int i = 0; i < visibleRowCount; i++) {
            rows[i].setVisibility(View.VISIBLE);
        }
        for (int i = rows.length; i > visibleRowCount; i--) {
            rows[i - 1].setVisibility(View.GONE);
        }
    }

    /**
     * method to initialise the Edit {@link SwitchCompat} in the appbar and define {@link ScheduleFragment#editMode}
     */
    private void initAppbarEditSwitch() {
        SwitchCompat editSwitch = (SwitchCompat) getActivity().findViewById(R.id.appbar_switch);

        editSwitch.setVisibility(View.VISIBLE);
        editMode = editSwitch.isChecked();

        editSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editMode = !editMode;
                initGui();
            }
        });
    }

    /**
     * method to initialise all {@link Button}s in the {@link ScheduleFragment}
     */
    private void initButtons() {
        for (int x = 1; x < buttons.length; x++) {
            for (int y = 1; y < buttons[x].length; y++) {
                // if (editMode) {
                buttons[x][y].setOnClickListener(new OnScheduleButtonClickListener(x, y));
                //  }
                buttons[x][y].setClickable(editMode);

                if (x > 1) {
                    loadSubjectToButtonAt(x, y);
                }

            }
        }
        initPeriodButtons();
    }

    /**
     * loads the {@link schmitt_florian.schoolplanner.logic.objects.Period} at there specific slot to the given Button
     */
    private void initPeriodButtons() {
        Period[] periods = getAllPeriodsInDb();
        for (Period p : periods) {
            buttons[1][p.getSchoolHourNo()].setText(GuiHelper.extractGuiString(p.getStartTime(), true, getContext()) + " - " +
                    GuiHelper.extractGuiString(p.getEndTime(), true, getContext()));
        }
        for (int i = 1; i < buttons[1].length; i++) {
            if (buttons[1][i].getText().equals("+") && !editMode) {
                buttons[1][i].setText("");
            } else if (buttons[1][i].getText().equals("") && editMode) {
                buttons[1][i].setText("+");
            }
        }
    }

    /**
     * method to query all {@link Period}s from the SchoolPlanner's Database
     *
     * @return all {@link Period}s as Array
     */
    private Period[] getAllPeriodsInDb() {
        ArrayList<Period> periodArrayList = new ArrayList<>();

        int[] periodIndices = databaseHelper.getIndices(DatabaseHelper.TABLE_PERIOD);

        for (int periodIndex : periodIndices) {
            Period period = databaseHelper.getPeriodAtId(periodIndex);

            periodArrayList.add(period);
        }

        return periodArrayList.toArray(new Period[0]);
    }

    /**
     * loads the {@link Subject} at the specific slot to the given Button
     *
     * @param x {@link ScheduleFragment#buttons} first value
     * @param y {@link ScheduleFragment#buttons} second value
     */
    private void loadSubjectToButtonAt(int x, int y) {
        try {
            buttons[x][y].setText(schedule.getDays()[x - 2].getLessons()[y - 1].getSubject().getName());
        } catch (ArrayIndexOutOfBoundsException ex) {
            if (editMode) {
                buttons[x][y].setText("+");
            } else {
                buttons[x][y].setText("");
            }
        }
    }

    //endregion

    //// TODO: 13.06.2017 save changes
    private class OnScheduleButtonClickListener implements View.OnClickListener {
        private boolean timeHasChanged;
        private boolean isTimeButton;
        private int x;
        private int y;


        /**
         * standard c'tor
         *
         * @param xPos x position in the schedule
         * @param yPos y position in the schedule
         */
        private OnScheduleButtonClickListener(int xPos, int yPos) {
            this.isTimeButton = xPos <= 1;
            this.x = xPos;
            this.y = yPos;
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (isTimeButton) {
                showTimeAlertDialog();
            } else {
                showSubjectAlertDialog();
            }

        }

        //region private methods

        /**
         * method to show the select start and end time dialog in {@link ScheduleFragment}
         */
        private void showTimeAlertDialog() {
            final InsertPeriodTimesDialog timesDialog = new InsertPeriodTimesDialog(getContext());
            timesDialog.positiveButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        try {
                            Period period = databaseHelper.getPeriodAtIdOrThrow(y);

                            databaseHelper.updatePeriodAtId(
                                    new Period(period.getId(), period.getSchoolHourNo(), timesDialog.getStartTime(), timesDialog.getEndTime())
                            );
                            timeHasChanged = true;
                        } catch (NoSuchFieldException e) {
                            databaseHelper.insertIntoDB(
                                    new Period(y, y, timesDialog.getStartTime(), timesDialog.getEndTime())
                            );
                            timeHasChanged = true;
                        }
                    } catch (IllegalArgumentException ex) {
                        timeHasChanged = false;
                    }
                    initGui();
                }
            });

            timesDialog.show();
        }

        /**
         * method to show the select subject dialog in {@link ScheduleFragment}
         */
        private void showSubjectAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.string_select_subject);

            builder.setItems(getAllSubjectsInDbAsGuiString(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //todo save subject
                }
            });

            builder.setNeutralButton(R.string.string_none, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //todo save empty
                }
            });

            builder.show();
        }

        /**
         * method to query all {@link Subject}s from the SchoolPlanner's Database as {@link GuiHelper#extractGuiString(Subject)} array
         *
         * @return all {@link Subject}s as String Array
         */
        private String[] getAllSubjectsInDbAsGuiString() {
            ArrayList<String> guiStrings = new ArrayList<>();

            for (Subject subject : getAllSubjectsInDb()) {
                guiStrings.add(GuiHelper.extractGuiString(subject));
            }

            return guiStrings.toArray(new String[0]);
        }

        /**
         * method to query all {@link Subject}s from the SchoolPlanner's Database
         *
         * @return all {@link Subject}s as Array
         */
        private Subject[] getAllSubjectsInDb() {
            ArrayList<Subject> subjectArrayList = new ArrayList<>();

            int[] subjectIndices = databaseHelper.getIndices(DatabaseHelper.TABLE_SUBJECT);

            for (int subjectIndex : subjectIndices) {
                Subject subject = databaseHelper.getSubjectAtId(subjectIndex);

                subjectArrayList.add(subject);
            }

            return subjectArrayList.toArray(new Subject[0]);
        }
        //endregion


        /**
         * subclass of {@link AlertDialog} for a Input dialog used to insert a start time and a endtime
         * <br></br>
         * <b>Usage:</b>
         * <br></br>
         * - call {@link InsertPeriodTimesDialog#InsertPeriodTimesDialog(Context)}
         * <br></br>
         * - set positiveButton onClickListener {@link InsertPeriodTimesDialog#positiveButton(View.OnClickListener)}
         * <br></br>
         * - call {@link InsertPeriodTimesDialog#show()}
         */
        private class InsertPeriodTimesDialog extends AlertDialog {
            private EditText startTime;
            private EditText endTime;

            /**
             * prepare dialog for use
             *
             * @param context context to display dialog in
             */
            InsertPeriodTimesDialog(@NonNull Context context) {
                super(context);
                setTitle(R.string.string_select_time);
                setView(getLayoutForTimeDialog());
                setCancelable(false);

                setButton(BUTTON_NEGATIVE, context.getResources().getString(R.string.string_cancel), new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
            }

            /**
             * adds positive button and sets onClickListener for it
             *
             * @param onClickListener onClickListener
             */
            void positiveButton(final View.OnClickListener onClickListener) {
                setButton(BUTTON_POSITIVE, getContext().getResources().getString(R.string.string_save), (OnClickListener) null);

                setOnShowListener(new OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener.onClick(v);

                                if (timeHasChanged) {
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });
            }

            /**
             * gets inserted start time as {@link GregorianCalendar}
             *
             * @return inserted start time
             */
            GregorianCalendar getStartTime() {
                return GuiHelper.getTimeFromMandatoryEditText(startTime);
            }

            /**
             * gets inserted end time as {@link GregorianCalendar}
             *
             * @return inserted end time
             */
            GregorianCalendar getEndTime() {
                return GuiHelper.getTimeFromMandatoryEditText(endTime);
            }

            //region private methods

            /**
             * returns a specific {@link LinearLayout} for use in {@link InsertPeriodTimesDialog}
             *
             * @return {@link LinearLayout} for use in {@link InsertPeriodTimesDialog}
             */
            @NonNull
            private LinearLayout getLayoutForTimeDialog() {
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                startTime = new EditText(getContext());
                startTime.setHint(R.string.string_start_time);
                startTime.setInputType(InputType.TYPE_CLASS_DATETIME);
                layout.addView(startTime);

                endTime = new EditText(getContext());
                endTime.setHint(R.string.string_end_time);
                endTime.setInputType(InputType.TYPE_CLASS_DATETIME);
                layout.addView(endTime);

                return layout;
            }
            //endregion

        }
    }

}
