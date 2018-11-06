package schmitt_florian.schoolplanner.gui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.objects.Teacher;

/**
 * bound class to activity_teacher_details.xml to show, change attributes of a choose {@link Teacher}, delete a choose {@link Teacher} or add a new one
 */
public class TeacherDetailsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private View rootView;
    private Teacher showingTeacher;
    private boolean addMode;
    private char[] gendersInSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbHelper = new DatabaseHelperImpl(this);
        int teacherID = getIntent().getIntExtra("TeacherID", -1);
        if (teacherID <= 0) {
            addMode = true;
        } else {
            addMode = false;
            showingTeacher = dbHelper.getTeacherAtId(teacherID);
        }

        rootView = findViewById(R.id.teacherDetails_main);
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
                dbHelper.insertIntoDB(readTeacherFromGUI());
            } else {
                dbHelper.updateTeacherAtId(readTeacherFromGUI());
            }
            finish();
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * deletes grade from database & finishes the activity if deletion successful
     *
     * @param view the button
     */
    public void onDeleteClick(View view) {
        dbHelper.deleteTeacherAtId(showingTeacher.getId());
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
            GuiHelper.setTextToTextView(rootView, R.id.teacherDetails_textName, showingTeacher.getName());
            GuiHelper.setTextToTextView(rootView, R.id.teacherDetails_textAbbreviation, showingTeacher.getAbbreviation());

            GuiHelper.setVisibility(rootView, R.id.teacherDetails_buttonDelete, View.VISIBLE);
        } else {
            GuiHelper.setVisibility(rootView, R.id.teacherDetails_buttonDelete, View.GONE);
        }

        gendersInSpinner = fillSpinner();

        //preselect spinner
        if (!addMode) {
            for (int i = 0; i < gendersInSpinner.length; i++) {
                if (gendersInSpinner[i] == showingTeacher.getGender()) {
                    Spinner spinner = findViewById(R.id.teacherDetails_spinnerGender);
                    spinner.setSelection(i);
                }
            }
        }
    }

    /**
     * method to fill the Spinner, which shows the genders at the TeacherDetails screen
     *
     * @return returns a array of all genders shown in the spinner ordered by their position in the spinner as chars
     */
    private char[] fillSpinner() {
        char[] gendersAsChar = {
                Teacher.MALE,
                Teacher.FEMALE
        };
        String[] genders = {
                getResources().getString(R.string.string_male),
                getResources().getString(R.string.string_female)
        };

        GuiHelper.fillSpinnerFromArray(rootView, R.id.teacherDetails_spinnerGender, genders);

        return gendersAsChar;
    }

    /**
     * read the values in the Gui and builds a {@link Teacher} from it
     *
     * @return the generated {@link Teacher}
     * @throws IllegalArgumentException if input is empty or illegal
     **/
    private Teacher readTeacherFromGUI() throws IllegalArgumentException {
        Spinner spinner = findViewById(R.id.teacherDetails_spinnerGender);

        if (addMode) {
            return new Teacher(
                    -1,
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.teacherDetails_textName),
                    GuiHelper.getInputFromEditText(rootView),
                    gendersInSpinner[spinner.getSelectedItemPosition()]
            );
        } else {
            return new Teacher(
                    showingTeacher.getId(),
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.teacherDetails_textName),
                    GuiHelper.getInputFromEditText(rootView),
                    gendersInSpinner[spinner.getSelectedItemPosition()]
            );
        }
    }
    //endregion
}
