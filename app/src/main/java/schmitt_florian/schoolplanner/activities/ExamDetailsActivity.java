package schmitt_florian.schoolplanner.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.Exam;

/**
 * bound class to activity_exam_details.xml to show, change attributes of a choose {@link Exam}, delete a choose {@link Exam} or add a new one
 */
public class ExamDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
