package schmitt_florian.schoolplanner.activities;


import android.view.View;
import android.widget.ArrayAdapter;
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
     * method to set the text of a TextView
     *
     * @param view the view the TextView is in
     * @param id   Resource ID of the TextView
     * @param text The text to set to the TextView
     */
    void setTextToTextView(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        textView.setText(text);
    }

    /**
     * method to set the content of a ListView
     *
     * @param view    the view the ListView is in
     * @param id      Resource ID of the ListView
     * @param content The content to fill the ListView with
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
}
