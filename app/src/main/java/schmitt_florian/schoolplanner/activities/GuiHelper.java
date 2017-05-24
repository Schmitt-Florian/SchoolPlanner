package schmitt_florian.schoolplanner.activities;


import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.Exam;
import schmitt_florian.schoolplanner.logic.Homework;
import schmitt_florian.schoolplanner.logic.Subject;
import schmitt_florian.schoolplanner.logic.Teacher;


/**
 * A basic auxiliary class containing simple methods for GUI interaction
 */
class GuiHelper {

    /**
     * gets the input of a mandatory {@link EditText} as String
     *
     * @param view the view the {@link EditText} is in
     * @param id   Resource ID of the {@link EditText}
     * @return the input of a {@link EditText} as String
     * @throws IllegalArgumentException if input is empty
     */
    static String getInputFromMandatoryEditText(View view, int id) throws IllegalArgumentException {
        EditText editText = (EditText) view.findViewById(id);

        String input = editText.getText().toString();
        if (input.replaceAll("\\s+", "").replaceAll("\\s", "").isEmpty()) {
            handleEmptyMandatoryEditText(view, id);
            throw new IllegalArgumentException();
        } else {
            return input;
        }
    }

    /**
     * gets the input of a {@link EditText} as String
     *
     * @param view the view the {@link EditText} is in
     * @param id   Resource ID of the {@link EditText}
     * @return the input of a {@link EditText} as String
     */
    static String getInputFromEditText(View view, int id) {
        EditText editText = (EditText) view.findViewById(id);
        return editText.getText().toString();
    }

    /**
     * gets the date input of a mandatory {@link EditText} as String
     *
     * @param view the view the {@link EditText} is in
     * @param id   Resource ID of the {@link EditText}
     * @return the input of a {@link EditText} as {@link GregorianCalendar}
     * @throws IllegalArgumentException if input is invalid date
     */
    static GregorianCalendar getDateFromMandatoryEditText(View view, int id) throws IllegalArgumentException {
        //todo date formats
        EditText editText = (EditText) view.findViewById(id);
        String str = editText.getText().toString();
        str = str.replaceAll("\\.", "-");
        String date[];
        if (str.contains("-")) {
            date = str.split("-");
        } else if (str.contains(",")) {
            date = str.split(",");
        } else if (str.contains(":")) {
            date = str.split(":");
        } else {
            handleEmptyMandatoryEditText(view, id);
            throw new IllegalArgumentException();
        }
        return new GregorianCalendar(
                Integer.parseInt(date[2]),
                Integer.parseInt(date[1]) - 1,
                Integer.parseInt(date[0])
        );
    }

    /**
     * method to set the text of a {@link TextView}
     *
     * @param view the view the {@link TextView} is in
     * @param id   Resource ID of the {@link TextView}
     * @param text The text to set to the {@link TextView}
     */
    static void setTextToTextView(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        textView.setText(text);
    }

    /**
     * method to set the background color of a {@link Button} with the resource id of a color
     *
     * @param view    the view the {@link Button} is in
     * @param id      Resource ID of the {@link Button}
     * @param colorId Resource ID of the color
     */
    static void setColorToButton(View view, int id, int colorId) {
        Button b = (Button) view.findViewById(id);
        b.setBackgroundResource(colorId);
    }

    /**
     * method to set the visibility of a {@link View}
     *
     * @param view       the view the {@link View} is in
     * @param id         Resource ID of the {@link View}
     * @param visibility The visibility to set to the {@link View},
     *                   must be one of {@link View#VISIBLE} , {@link View#INVISIBLE} , {@link View#GONE}
     */
    static void setVisibility(View view, int id, int visibility) {
        View v = view.findViewById(id);
        v.setVisibility(visibility);
    }

    /**
     * method to set the content of a {@link ListView}
     *
     * @param view    the view the {@link ListView} is in
     * @param id      Resource ID of the {@link ListView}
     * @param content The content to fill the {@link ListView} with as string array
     */
    static void fillListViewFromArray(View view, int id, String[] content) {
        ListView listView = (ListView) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, content);

        listView.setAdapter(adapter);
    }

    /**
     * method to set the content of a {@link GridView}
     *
     * @param view    the view the {@link GridView} is in
     * @param id      Resource ID of the {@link GridView}
     * @param content The content to fill the {@link GridView} with as string array.
     *                Fills the grid from left to right, so if you have a {@link GridView}
     *                with the {@link GridView#getNumColumns()} == 2 the content[] indices 0 & 1
     *                will form the first row in the grid, 2 & 3 the second row and so on.
     */
    static void fillGridViewFromArray(View view, int id, String[] content) {
        GridView gridView = (GridView) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, content);

        gridView.setAdapter(adapter);
    }

    /**
     * method to set the content of a {@link Spinner}
     *
     * @param view    the view the {@link Spinner} is in
     * @param id      Resource ID of the {@link Spinner}
     * @param content The content to fill the {@link Spinner} with as string array
     */
    static void fillSpinnerFromArray(View view, int id, String[] content) {
        Spinner spinner = (Spinner) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, content);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    //region GUI string

    /**
     * extracts a GUI displayable String from the given {@link Exam}
     *
     * @param exam {@link Exam} to extract from
     * @return the Name of the {@link Subject} the {@link Exam} is in and the deadline as String. E.g "Math - 26.03.2017"
     */
    static String extractGuiString(Exam exam) {
        Calendar deadline = exam.getDeadline();
        String dateString;

        //TODO support different date formats
        dateString = deadline.get(Calendar.DAY_OF_MONTH) + "." + String.valueOf(deadline.get(Calendar.MONTH) + 1) + "." + String.valueOf(deadline.get(Calendar.YEAR));

        return exam.getSubject().getName() + " - " + dateString;
    }

    /**
     * extracts a GUI displayable String from the given {@link Homework}
     *
     * @param homework {@link Homework} to extract from
     * @return the Name of the {@link Subject} the {@link Homework} was given in and the deadline as String. E.g "Math - 26.03.2017"
     */
    static String extractGuiString(Homework homework) {
        Calendar deadline = homework.getDeadline();
        String dateString;

        //TODO support different date formats
        dateString = deadline.get(Calendar.DAY_OF_MONTH) + "." + String.valueOf(deadline.get(Calendar.MONTH) + 1) + "." + String.valueOf(deadline.get(Calendar.YEAR));

        return homework.getSubject().getName() + " - " + dateString;
    }

    /**
     * extracts a GUI displayable String from the given {@link Subject}
     *
     * @param subject {@link Subject} to extract from
     * @return the Name of the {@link Subject} and the abbreviation of the {@link Teacher} as String. E.g "Math - SMT"
     */
    static String extractGuiString(Subject subject) {
        return subject.getName() + " - " + subject.getTeacher().getAbbreviation();
    }

    /**
     * extracts a GUI displayable String from the given {@link Teacher}
     *
     * @param teacher {@link Teacher} to extract from
     * @return the Name of the {@link Teacher} and the abbreviation of the {@link Teacher} as String. E.g "Mr. Smith - SMT"
     */
    static String extractGuiString(Teacher teacher, Context context) {
        StringBuilder stringBuilder = new StringBuilder();

        if (teacher.getGender() == Teacher.MALE) {
            stringBuilder.append(context.getResources().getString(R.string.string_mr));
        } else {
            stringBuilder.append(context.getResources().getString(R.string.string_mrs));
        }

        stringBuilder.append(" ").append(teacher.getName());
        stringBuilder.append(" - ").append(teacher.getAbbreviation());

        return stringBuilder.toString();
    }


    //endregion

    /**
     * method used to set the {@link View.OnClickListener} of a {@link Button} at a given id
     *
     * @param view            the view the {@link Button} is in
     * @param id              Resource ID of the {@link Button}
     * @param onClickListener the {@link View.OnClickListener} to set the {@link Button} to
     */
    static void defineButtonOnClickListener(View view, int id, View.OnClickListener onClickListener) {
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
    static void defineFloatingActionButtonOnClickListener(View view, int id, View.OnClickListener onClickListener) {
        FloatingActionButton b = (FloatingActionButton) view.findViewById(id);
        b.setOnClickListener(onClickListener);
    }

    /**
     * can be used to indicate that a {@link EditText} in the GUI must not be empty
     *
     * @param view the view the {@link EditText} is in
     * @param id   the ResourceID of the EditText
     */
    static void handleEmptyMandatoryEditText(View view, int id) {
        EditText editText = (EditText) view.findViewById(id);
        editText.setHint(view.getContext().getResources().getString(R.string.string_mandatory_field));
        editText.setHintTextColor(Color.RED);
    }


}
