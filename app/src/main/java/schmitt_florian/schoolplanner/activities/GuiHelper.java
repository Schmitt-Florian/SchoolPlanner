package schmitt_florian.schoolplanner.activities;


import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

import schmitt_florian.schoolplanner.logic.Exam;
import schmitt_florian.schoolplanner.logic.Homework;


/**
 * A basic auxiliary class containing simple methods for GUI interaction
 */
class GuiHelper {

    /**
     * method to set the text of a {@link TextView}
     *
     * @param view the view the {@link TextView} is in
     * @param id   Resource ID of the {@link TextView}
     * @param text The text to set to the {@link TextView}
     */
    void setTextToTextView(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        textView.setText(text);
    }

    /**
     * method to set the content of a {@link ListView}
     *
     * @param view    the view the {@link ListView} is in
     * @param id      Resource ID of the {@link ListView}
     * @param content The content to fill the {@link ListView} with as string array
     */
    void fillListViewFromArray(View view, int id, String[] content) {
        ListView listView = (ListView) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, content);

        listView.setAdapter(adapter);
    }

    /**
     * extracts a GUI displayable String from the given {@link Homework}
     *
     * @param homework {@link Homework} to extract from
     * @return the Name of the {@link schmitt_florian.schoolplanner.logic.Subject} the {@link Homework} was given in and the deadline as String. E.g "Math - 26.03.2017"
     */
    String extractGuiString(Homework homework) {
        String subjectName = homework.getSubject().getName();
        String dateString;

        Calendar deadline = homework.getDeadline();
        //TODO support different date formats
        dateString = deadline.get(Calendar.DAY_OF_MONTH) + "." + String.valueOf(deadline.get(Calendar.MONTH) + 1) + "." + String.valueOf(deadline.get(Calendar.YEAR));

        return subjectName + " - " + dateString;
    }

    /**
     * extracts a GUI displayable String from the given {@link Exam}
     *
     * @param exam {@link Exam} to extract from
     * @return the Name of the {@link schmitt_florian.schoolplanner.logic.Subject} the {@link Exam} is in and the deadline as String. E.g "Math - 26.03.2017"
     */
    String extractGuiString(Exam exam) {
        String subjectName = exam.getSubject().getName();
        String dateString;

        Calendar deadline = exam.getDeadline();
        //TODO support different date formats
        dateString = deadline.get(Calendar.DAY_OF_MONTH) + "." + String.valueOf(deadline.get(Calendar.MONTH) + 1) + "." + String.valueOf(deadline.get(Calendar.YEAR));

        return subjectName + " - " + dateString;
    }


    /**
     * method used to set the {@link View.OnClickListener} of a {@link Button} at a given id
     *
     * @param view            the view the {@link Button} is in
     * @param id              Resource ID of the {@link Button}
     * @param onClickListener the {@link View.OnClickListener} to set the {@link Button} to
     */
    void defineButtonOnClickListener(View view, int id, View.OnClickListener onClickListener) {
        Button b = (Button) view.findViewById(id);
        b.setOnClickListener(onClickListener);
    }

    /**
     * method used to set the {@link View.OnClickListener} of a {@link FloatingActionButton} at a given id
     *
     * @param view            the view the {@link FloatingActionButton} is in
     * @param id              Resource ID of the {@link FloatingActionButton}
     * @param onClickListener the {@link View.OnClickListener} to set the {@link FloatingActionButton} to
     */
    void defineFloatingActionButtonOnClickListener(View view, int id, View.OnClickListener onClickListener) {
        FloatingActionButton b = (FloatingActionButton) view.findViewById(id);
        b.setOnClickListener(onClickListener);
    }
}
