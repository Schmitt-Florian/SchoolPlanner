package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Settings;
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

        buttons = getScheduleButtonsAsArray();
        initScheduleButtons();

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
    private Button[][] getScheduleButtonsAsArray() {
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
                initScheduleButtons();
            }
        });
    }

    /**
     * method to initialise these {@link Button}s in the {@link ScheduleFragment} which displays the {@link schmitt_florian.schoolplanner.logic.objects.Lesson}s
     */
    private void initScheduleButtons() {
        for (int x = 1; x < buttons.length; x++) {
            for (int y = 1; y < buttons[x].length; y++) {
                buttons[x][y].setClickable(editMode);
                if (editMode) {
                    if (x > 1) {
                        buttons[x][y].setOnClickListener(new OnScheduleButtonClickListener(false, x, y));
                    } else {
                        buttons[x][y].setOnClickListener(new OnScheduleButtonClickListener(true, x, y));
                    }
                }
            }
        }
    }

    //endregion

    private class OnScheduleButtonClickListener implements View.OnClickListener {
        private boolean isTimeButton;
        private int x;
        private int y;


        private OnScheduleButtonClickListener(boolean isTimeButton, int xPos, int yPos) {
            this.isTimeButton = isTimeButton;
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

            } else {
                showSubjectAlertDialog();
            }

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
                    //save subject
                }
            });

            builder.setNeutralButton(R.string.string_none, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //save empty
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
    }

}
