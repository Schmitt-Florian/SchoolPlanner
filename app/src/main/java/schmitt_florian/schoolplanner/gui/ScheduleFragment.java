package schmitt_florian.schoolplanner.gui;

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
import java.util.Arrays;
import java.util.GregorianCalendar;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Settings;
import schmitt_florian.schoolplanner.logic.objects.Lesson;
import schmitt_florian.schoolplanner.logic.objects.Period;
import schmitt_florian.schoolplanner.logic.objects.Schedule;
import schmitt_florian.schoolplanner.logic.objects.Subject;
import schmitt_florian.schoolplanner.logic.objects.Weekday;

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

    private DatabaseHelperImpl databaseHelper;
    private boolean editMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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
        updateValues();

        rows = getScheduleRowsInArray();
        initVisibilityForSchedule();

        buttons = getButtonsAsArray();
        initButtons();

        initAppbarEditSwitch();
    }

    /**
     * a little method to refresh the local variables: {@link ScheduleFragment#databaseHelper}, {@link ScheduleFragment#table}, {@link ScheduleFragment#schedule}
     */
    private void updateValues() {
        databaseHelper = new DatabaseHelperImpl(getContext());
        table = (TableLayout) rootView.findViewById(R.id.schedule_table);
        schedule = databaseHelper.getScheduleAtId(1);
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
                buttons[x][y].setOnClickListener(new OnScheduleButtonClickListener(x, y));
                buttons[x][y].setClickable(editMode);

                if (editMode) {
                    buttons[x][y].setText("+");
                } else {
                    buttons[x][y].setText("");
                }
            }
        }
        loadSubjectButtons();
        initPeriodButtons();
    }

    /**
     * loads the {@link Subject} at their specific slot to the given Button
     */
    private void loadSubjectButtons() {
        for (int i = 0; i < schedule.getDays().length; i++) {
            for (Lesson lesson : schedule.getDays()[i].getLessons()) {
                switch (schedule.getDays()[i].getName()) {
                    case Weekday.MONDAY:
                        buttons[2][lesson.getPeriod().getSchoolHourNo()].setText(lesson.getSubject().getName());
                        break;
                    case Weekday.TUESDAY:
                        buttons[3][lesson.getPeriod().getSchoolHourNo()].setText(lesson.getSubject().getName());
                        break;
                    case Weekday.WEDNESDAY:
                        buttons[4][lesson.getPeriod().getSchoolHourNo()].setText(lesson.getSubject().getName());
                        break;
                    case Weekday.THURSDAY:
                        buttons[5][lesson.getPeriod().getSchoolHourNo()].setText(lesson.getSubject().getName());
                        break;
                    case Weekday.FRIDAY:
                        buttons[6][lesson.getPeriod().getSchoolHourNo()].setText(lesson.getSubject().getName());
                        break;
                    case Weekday.SATURDAY:
                        buttons[7][lesson.getPeriod().getSchoolHourNo()].setText(lesson.getSubject().getName());
                        break;
                }
            }
        }
    }

    /**
     * loads the {@link Period} at their specific slot to the given Button
     */
    private void initPeriodButtons() {
        Period[] periods = getAllPeriodsInDb();
        for (Period p : periods) {
            buttons[1][p.getSchoolHourNo()].setText(GuiHelper.extractGuiString(p.getStartTime(), true, getContext()) + " - " +
                    GuiHelper.extractGuiString(p.getEndTime(), true, getContext()));
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
    //endregion


    /**
     * this implementation of the {@link View.OnClickListener} is used to handle clicks on the
     * buttons in the {@link ScheduleFragment} which show the {@link Lesson}s and those showing the {@link Period}s
     */
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
            final InsertPeriodTimesDialog timeDialog = new InsertPeriodTimesDialog(getContext());
            timeDialog.positiveButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleOnPositiveButtonInTimeDialogClick(timeDialog);
                }
            });

            timeDialog.show();
        }

        /**
         * method tho handle clicks at the {@link InsertPeriodTimesDialog} shown by {@link OnScheduleButtonClickListener#showTimeAlertDialog()}
         * by updating the {@link Period} with the entered times or inserting a new one, if it isn't existing
         *
         * @param timesDialog the {@link InsertPeriodTimesDialog}
         */
        private void handleOnPositiveButtonInTimeDialogClick(InsertPeriodTimesDialog timesDialog) {
            try {
                try {
                    Period period = databaseHelper.getPeriodAtIdOrThrow(y);

                    databaseHelper.updatePeriodAtId(
                            new Period(period.getId(), period.getSchoolHourNo(), timesDialog.getStartTime(), timesDialog.getEndTime()));

                    timeHasChanged = true;
                } catch (NoSuchFieldException e) {
                    databaseHelper.insertIntoDB(
                            new Period(y, y, timesDialog.getStartTime(), timesDialog.getEndTime()));

                    timeHasChanged = true;
                }
            } catch (IllegalArgumentException ex) {
                timeHasChanged = false;
            }
            initGui();
        }

        /**
         * method to show the select subject dialog in {@link ScheduleFragment} to let
         * the user select a {@link Subject} or none to be taught during the clicked {@link Period}
         */
        private void showSubjectAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.string_select_subject);

            builder.setItems(getSubjectSelectorContent(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handleOnSubjectInSubjectDialogClick(dialog, which);
                }
            });

            builder.setNegativeButton(R.string.string_cancel, null);

            builder.show();
        }

        /**
         * method to handle clicks at the SubjectAlertDialog shown by {@link OnScheduleButtonClickListener#showSubjectAlertDialog()}
         * by deleting the {@link Lesson} from the database if "none" was selected as {@link Subject} or
         * inserting or updating the clicked Lesson with the selected {@link Subject}
         *
         * @param dialog the SubjectAlertDialog
         * @param which  the clicked Subject in the displayed list
         */
        private void handleOnSubjectInSubjectDialogClick(DialogInterface dialog, int which) {
            Weekday weekday = schedule.getDay(getClickedWeekdayName());

            if (which == 0) {
                try {
                    Lesson lesson = databaseHelper.getLessonOrThrowAtDate(weekday, getAllPeriodsInDb()[y - 1]);
                    databaseHelper.deleteLessonAtId(lesson.getId());
                } catch (NoSuchFieldException | ArrayIndexOutOfBoundsException ignore) {
                    dialog.dismiss();
                }
            } else {
                try {
                    insertOrUpdateLesson(getAllSubjectsInDb()[which - 1]);
                } catch (NullPointerException ex) {
                    insertNewWeekdayInDb();
                    insertOrUpdateLesson(getAllSubjectsInDb()[which - 1]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    handleMissingPeriod();
                }
            }
            initGui();
        }

        /**
         * inserts the clicked {@link Lesson} with the given {@link Subject} in db
         *
         * @param subject the {@link Subject} in clicked {@link Lesson}
         * @throws ArrayIndexOutOfBoundsException if the {@link Weekday} or the {@link Period} the {@link Lesson} is on isn't initialised
         */
        private void insertOrUpdateLesson(Subject subject) throws ArrayIndexOutOfBoundsException {
            Weekday weekday = schedule.getDay(getClickedWeekdayName());

            try {
                Lesson lesson = databaseHelper.getLessonOrThrowAtDate(weekday, getAllPeriodsInDb()[y - 1]);
                Lesson newLesson = new Lesson(lesson.getId(), subject, lesson.getPeriod());

                databaseHelper.updateLessonAtId(newLesson);

            } catch (NoSuchFieldException e) {
                Lesson newLesson = new Lesson(-1, subject, getAllPeriodsInDb()[y - 1]);

                int newLessonId = databaseHelper.insertIntoDB(newLesson);
                newLesson = new Lesson(newLessonId, newLesson.getSubject(), newLesson.getPeriod());

                ArrayList<Lesson> lessons = new ArrayList<>(Arrays.asList(weekday.getLessons()));
                lessons.add(newLesson);
                databaseHelper.updateWeekdayAtId(new Weekday(
                        weekday.getId(),
                        weekday.getName(),
                        lessons.toArray(new Lesson[0])
                ));
            }
        }

        /**
         * inserts clicked {@link Weekday} in database
         */
        private void insertNewWeekdayInDb() {
            Weekday newWeekday = new Weekday(-1, getClickedWeekdayName(), new Lesson[0]);

            int newWeekdayID = databaseHelper.insertIntoDB(newWeekday);
            newWeekday = new Weekday(newWeekdayID, newWeekday.getName(), newWeekday.getLessons());

            ArrayList<Weekday> weekdays = new ArrayList<>(Arrays.asList(schedule.getDays()));
            weekdays.add(newWeekday);

            databaseHelper.updateScheduleAtId(new Schedule(
                    schedule.getId(),
                    schedule.getName(),
                    weekdays.toArray(new Weekday[0])
            ));

            updateValues();
        }

        /**
         * returns the name of the clicked Weekday
         *
         * @return name of the clicked Weekday one of {@link Weekday#MONDAY}, {@link Weekday#TUESDAY}, {@link Weekday#WEDNESDAY},
         * {@link Weekday#THURSDAY}, {@link Weekday#FRIDAY}, {@link Weekday#SATURDAY}
         */
        private String getClickedWeekdayName() {
            switch (x - 1) {
                case 1:
                    return Weekday.MONDAY;
                case 2:
                    return Weekday.TUESDAY;
                case 3:
                    return Weekday.WEDNESDAY;
                case 4:
                    return Weekday.THURSDAY;
                case 5:
                    return Weekday.FRIDAY;
                case 6:
                    return Weekday.SATURDAY;
                default:
                    return null;
            }
        }

        /**
         * method to query all {@link Subject}s from the SchoolPlanner's Database as {@link GuiHelper#extractGuiString(Subject)} array
         * with {@link schmitt_florian.schoolplanner.R.string#string_none} at array[0] position
         *
         * @return the array
         */
        private String[] getSubjectSelectorContent() {
            ArrayList<String> guiStrings = new ArrayList<>();

            guiStrings.add(getResources().getString(R.string.string_none));

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

        /**
         * method to handle the case the user wants to insert a {@link Lesson} at a not present {@link Period}
         * by displaying a {@link AlertDialog} which tells the user to insert the {@link Period} first
         */
        private void handleMissingPeriod() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setIconAttribute(android.R.attr.alertDialogIcon);
            builder.setTitle(R.string.string_missing_period);

            builder.setMessage(R.string.text_dialog_schedule_missing_period);
            builder.setNegativeButton(R.string.string_cancel, null);

            builder.show();
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
