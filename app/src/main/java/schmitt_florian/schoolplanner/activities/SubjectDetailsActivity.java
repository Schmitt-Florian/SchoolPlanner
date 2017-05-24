package schmitt_florian.schoolplanner.activities;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Subject;
import schmitt_florian.schoolplanner.logic.Teacher;

/**
 * bound class to activity_subject_details.xml to show, change attributes of a choose {@link Subject}, delete a choose {@link Subject} or add a new one
 */
public class SubjectDetailsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private View rootView;
    private Subject showingSubject;
    private boolean addMode;
    private Teacher[] teachersInSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbHelper = new DatabaseHelperImpl(this);
        int subjectId = getIntent().getIntExtra("SubjectID", -1);
        if (subjectId <= 0) {
            addMode = true;
        } else {
            addMode = false;
            showingSubject = dbHelper.getSubjectAtId(subjectId);
        }

        rootView = findViewById(R.id.subjectDetails_main);
        initGUI();
    }

    /**
     * saves changes to database
     *
     * @param view the button
     */
    public void onSaveClick(View view) {
        try {
            if (addMode) {
                dbHelper.insertIntoDB(readSubjectFromGUI());
            } else {
                dbHelper.updateSubjectAtId(readSubjectFromGUI());
            }
            finish();
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * deletes grade from database
     *
     * @param view the button
     */
    public void onDeleteClick(View view) {
        dbHelper.deleteSubjectAtId(showingSubject.getId());
        finish();
    }

    /**
     * closes the activity
     *
     * @param view the button
     */
    public void onCloseClick(View view) {
        finish();
    }


    //region private methods

    /**
     * method to initialise components of the GUI
     */
    private void initGUI() {
        if (!addMode) {
            GuiHelper.setTextToTextView(rootView, R.id.subjectDetails_textName, showingSubject.getName());
            GuiHelper.setTextToTextView(rootView, R.id.subjectDetails_textRoom, showingSubject.getRoom());

            GuiHelper.setVisibility(rootView, R.id.subjectDetails_buttonDelete, View.VISIBLE);
        } else {
            GuiHelper.setVisibility(rootView, R.id.subjectDetails_buttonDelete, View.GONE);
        }

        teachersInSpinner = fillSpinner();

        //preselect spinner
        if (!addMode) {
            for (int i = 0; i < teachersInSpinner.length; i++) {
                if (teachersInSpinner[i].match(showingSubject.getTeacher())) {
                    Spinner spinner = (Spinner) findViewById(R.id.subjectDetails_spinnerTeacher);
                    spinner.setSelection(i);
                }
            }
        }
    }

    /**
     * method to fill the Spinner, which shows the {@link Teacher}s at the SubjectDetails screen
     *
     * @return returns a array of all {@link Teacher}s shown in the spinner ordered by their position in the spinner
     */
    private Teacher[] fillSpinner() {
        ArrayList<String> teacherStrings = new ArrayList<>();
        ArrayList<Teacher> teacherArrayList = new ArrayList<>();

        int[] teacherIndices = dbHelper.getIndices(DatabaseHelper.TABLE_TEACHER);

        for (int teacherIndex : teacherIndices) {
            Teacher teacher = dbHelper.getTeacherAtId(teacherIndex);

            teacherStrings.add(GuiHelper.extractGuiString(teacher, getBaseContext()));
            teacherArrayList.add(teacher);
        }

        if (teacherStrings.size() != 0) {
            GuiHelper.fillSpinnerFromArray(rootView, R.id.subjectDetails_spinnerTeacher, teacherStrings.toArray(new String[0]));
        } else {
            GuiHelper.setVisibility(rootView, R.id.subjectDetails_labelSpinnerError, View.VISIBLE);
            findViewById(R.id.subjectDetails_buttonSave).setEnabled(false);
        }
        return teacherArrayList.toArray(new Teacher[0]);
    }

    /**
     * read the values in the Gui and builds a {@link Subject} from it
     *
     * @return the generated {@link Subject}
     * @throws IllegalArgumentException if input is empty or illegal
     **/
    private Subject readSubjectFromGUI() throws IllegalArgumentException {
        Spinner spinner = (Spinner) findViewById(R.id.subjectDetails_spinnerTeacher);

        if (addMode) {
            return new Subject(
                    -1,
                    teachersInSpinner[spinner.getSelectedItemPosition()],
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.subjectDetails_textName),
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.subjectDetails_textRoom)
            );
        } else {
            return new Subject(
                    showingSubject.getId(),
                    teachersInSpinner[spinner.getSelectedItemPosition()],
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.subjectDetails_textName),
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.subjectDetails_textRoom)
            );
        }
    }
    //endregion
}
