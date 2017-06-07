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
import schmitt_florian.schoolplanner.logic.objects.Exam;
import schmitt_florian.schoolplanner.logic.objects.Subject;

/**
 * bound class to activity_exam_details.xml to show, change attributes of a choose {@link Exam}, delete a choose {@link Exam} or add a new one
 */
public class ExamDetailsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private View rootView;
    private Exam showingExam;
    private Subject[] subjectsInSpinner;
    private boolean addMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbHelper = new DatabaseHelperImpl(this);
        int examID = getIntent().getIntExtra("ExamID", -1);
        if (examID <= 0) {
            addMode = true;
        } else {
            addMode = false;
            showingExam = dbHelper.getExamAtId(examID);
        }

        rootView = findViewById(R.id.examDetails_main);
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
                dbHelper.insertIntoDB(readHomeworkFromGUI());
            } else {
                dbHelper.updateExamAtId(readHomeworkFromGUI());
            }
            finish();
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * deletes homework from database & finishes the activity if deletion successful
     *
     * @param view the button
     */
    public void onDeleteClick(View view) {
        dbHelper.deleteExamAtId(showingExam.getId());
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
            GuiHelper.setTextToTextView(rootView, R.id.examDetails_textDescription, showingExam.getDescription());
            GuiHelper.setTextToTextView(rootView, R.id.examDetails_textDate,
                    GuiHelper.extractGuiString(showingExam.getDeadline(), false, rootView.getContext()));

            GuiHelper.setVisibility(rootView, R.id.examDetails_buttonDelete, View.VISIBLE);
        } else {
            GuiHelper.setVisibility(rootView, R.id.examDetails_buttonDelete, View.GONE);
        }

        subjectsInSpinner = fillSpinner();

        //preselect spinner
        if (!addMode) {
            for (int i = 0; i < subjectsInSpinner.length; i++) {
                if (subjectsInSpinner[i].match(showingExam.getSubject())) {
                    Spinner spinner = (Spinner) findViewById(R.id.examDetails_spinnerSubject);
                    spinner.setSelection(i);
                }
            }
        }
    }

    /**
     * method to fill the Spinner, which shows the {@link Subject}s at the examDetails screen
     *
     * @return returns a array of all {@link Subject}s shown in the spinner ordered by their position in the spinner
     */
    private Subject[] fillSpinner() {
        ArrayList<String> subjectStrings = new ArrayList<>();
        ArrayList<Subject> subjectArrayList = new ArrayList<>();

        int[] subjectIndices = dbHelper.getIndices(DatabaseHelper.TABLE_SUBJECT);

        for (int subjectIndex : subjectIndices) {
            Subject subject = dbHelper.getSubjectAtId(subjectIndex);

            subjectStrings.add(GuiHelper.extractGuiString(subject));
            subjectArrayList.add(subject);
        }

        if (subjectStrings.size() != 0) {
            GuiHelper.fillSpinnerFromArray(rootView, R.id.examDetails_spinnerSubject, subjectStrings.toArray(new String[0]));
        } else {
            GuiHelper.setVisibility(rootView, R.id.examDetails_labelSpinnerError, View.VISIBLE);
            findViewById(R.id.examDetails_buttonSave).setEnabled(false);
        }
        return subjectArrayList.toArray(new Subject[0]);
    }

    /**
     * read the values in the Gui and builds a {@link Exam} from it
     *
     * @return the generated {@link Exam}
     * @throws IllegalArgumentException if input is empty or illegal
     **/
    private Exam readHomeworkFromGUI() throws IllegalArgumentException {
        Spinner spinner = (Spinner) findViewById(R.id.examDetails_spinnerSubject);

        if (addMode) {
            return new Exam(
                    -1,
                    subjectsInSpinner[spinner.getSelectedItemPosition()],
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.examDetails_textDescription),
                    GuiHelper.getDateFromMandatoryEditText(rootView, R.id.examDetails_textDate)
            );
        } else {
            return new Exam(
                    showingExam.getId(),
                    subjectsInSpinner[spinner.getSelectedItemPosition()],
                    GuiHelper.getInputFromMandatoryEditText(rootView, R.id.examDetails_textDescription),
                    GuiHelper.getDateFromMandatoryEditText(rootView, R.id.examDetails_textDate)
            );
        }
    }
    //endregion
}
