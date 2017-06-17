package schmitt_florian.schoolplanner.gui;


import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.Settings;
import schmitt_florian.schoolplanner.logic.objects.Exam;
import schmitt_florian.schoolplanner.logic.objects.Homework;
import schmitt_florian.schoolplanner.logic.objects.Subject;
import schmitt_florian.schoolplanner.logic.objects.Teacher;


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
     * @throws IllegalArgumentException if input is empty and calls {@link GuiHelper#handleEmptyMandatoryEditText;} method to do things to the text field
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
     * @return the input of a {@link EditText} as String or "NULL" if the input was empty
     */
    static String getInputFromEditText(View view, int id) {
        EditText editText = (EditText) view.findViewById(id);
        String input = editText.getText().toString();

        if (input.matches("")) {
            return "NULL";
        } else {
            return input;
        }
    }

    /**
     * gets the date input of a mandatory {@link Button} as String
     *
     * @param view the view the {@link Button} is in
     * @param id   Resource ID of the {@link Button}
     * @return the input of a {@link Button} as {@link GregorianCalendar}
     * @throws IllegalArgumentException if input is invalid date and calls {@link GuiHelper#handleEmptyMandatoryEditText;} method to do things to the text field
     */
    static GregorianCalendar getDateFromMandatoryButton(View view, int id) throws IllegalArgumentException {
        Button button = (Button) view.findViewById(id);

        String str = button.getText().toString();
        str = str.replaceAll(":", "-");
        str = str.replaceAll("\\.", "-");
        str = str.replaceAll(",", "-");
        str = str.replaceAll("/", "-");

        String date[];
        if (str.contains("-")) {
            date = str.split("-");

            if (date.length != 3) {
                handleEmptyMandatoryButton(view, id);
                throw new IllegalArgumentException("date must be contain three elements");
            }
        } else {
            handleEmptyMandatoryButton(view, id);
            throw new IllegalArgumentException("unknown date divider");
        }

        try {
            return (GregorianCalendar) parseCalendarFromStringArray(date, view.getContext());
        } catch (IllegalArgumentException ex) {
            handleEmptyMandatoryButton(view, id,
                    view.getContext().getResources().getString(R.string.string_date_must_comply_with) +
                            " " + Settings.getInstance(view.getContext()).getActiveDateFormat());
            throw ex;
        }

    }

    /**
     * gets the time input of a mandatory {@link EditText} as String
     *
     * @param editText the editText
     * @return the input of a {@link EditText} as {@link GregorianCalendar} initialises with "0" for year, month and day
     * @throws IllegalArgumentException if input is invalid date and calls {@link GuiHelper#handleEmptyMandatoryEditText;} method to do things to the text field
     */
    static GregorianCalendar getTimeFromMandatoryEditText(EditText editText) throws IllegalArgumentException {
        String str = editText.getText().toString();
        str = str.replaceAll(":", "-");
        str = str.replaceAll("\\.", "-");
        str = str.replaceAll(",", "-");
        str = str.replaceAll("/", "-");

        String time[];
        if (str.contains("-")) {
            time = str.split("-");

            if (time.length != 2) {
                handleEmptyMandatoryEditText(editText);
                throw new IllegalArgumentException("time must be contain two elements");
            }
        } else {
            handleEmptyMandatoryEditText(editText);
            throw new IllegalArgumentException("unknown time divider");
        }

        try {
            if ((time[0].length() <= 2 && time[1].length() <= 2) && (Integer.parseInt(time[0]) <= 24 && Integer.parseInt(time[1]) <= 60)) {
                return new GregorianCalendar(0, 0, 0, Integer.parseInt(time[0]), Integer.parseInt(time[1]));
            } else {
                throw new IllegalArgumentException((Arrays.toString(time) + " is no valid array for TIME_FORMAT_HHMM"));
            }
        } catch (IllegalArgumentException ex) {
            handleEmptyMandatoryEditText(editText,
                    editText.getContext().getResources().getString(R.string.string_time_must_comply_with) +
                            " " + Settings.TIME_FORMAT_HHMM);
            throw ex;
        }
    }

    /**
     * method to set the text of a {@link TextView}
     *
     * @param view the view the {@link TextView} is in
     * @param id   Resource ID of the {@link TextView}
     * @param text The text to set to the {@link TextView}
     * @return the updated {@link TextView}
     */
    static TextView setTextToTextView(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        textView.setText(text);
        return textView;
    }

    /**
     * method to set the background color of a {@link Button} with the resource id of a color
     *
     * @param view    the view the {@link Button} is in
     * @param id      Resource ID of the {@link Button}
     * @param colorId Resource ID of the color
     * @return the updated {@link Button}
     */
    static Button setColorToButton(View view, int id, int colorId) {
        Button b = (Button) view.findViewById(id);
        b.setBackgroundResource(colorId);
        return b;
    }

    /**
     * method to set the visibility of a {@link View}
     *
     * @param view       the view the {@link View} is in
     * @param id         Resource ID of the {@link View}
     * @param visibility The visibility to set to the {@link View},
     *                   must be one of {@link View#VISIBLE} , {@link View#INVISIBLE} , {@link View#GONE}
     * @return the updated {@link View}
     */
    static View setVisibility(View view, int id, int visibility) {
        View v = view.findViewById(id);
        v.setVisibility(visibility);
        return v;
    }

    /**
     * method to set the content of a {@link ListView}
     *
     * @param view    the view the {@link ListView} is in
     * @param id      Resource ID of the {@link ListView}
     * @param content The content to fill the {@link ListView} with as string array
     * @return the updated {@link ListView}
     */
    static ListView fillListViewFromArray(View view, int id, String[] content) {
        ListView listView = (ListView) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, content);

        listView.setAdapter(adapter);
        return listView;
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
     * @return the updated {@link GridView}
     */
    static GridView fillGridViewFromArray(View view, int id, String[] content) {
        GridView gridView = (GridView) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, content);

        gridView.setAdapter(adapter);
        return gridView;
    }

    /**
     * method to set the content of a {@link Spinner}
     *
     * @param view    the view the {@link Spinner} is in
     * @param id      Resource ID of the {@link Spinner}
     * @param content The content to fill the {@link Spinner} with as string array
     * @return the updated {@link Spinner}
     */
    static Spinner fillSpinnerFromArray(View view, int id, String[] content) {
        Spinner spinner = (Spinner) view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, content);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        return spinner;
    }

    //region GUI string

    /**
     * extracts a GUI displayable String from the given {@link Exam}
     *
     * @param exam    {@link Exam} to extract from
     * @param context context of the application
     * @return the Name of the {@link Subject} the {@link Exam} is in and the deadline as String. E.g "Math - 26.03.2017"
     */
    static String extractGuiString(Exam exam, Context context) {
        String dateString = extractGuiString(exam.getDeadline(), false, context);

        return exam.getSubject().getName() + " - " + dateString;
    }

    /**
     * extracts a GUI displayable String from the given {@link Homework}
     *
     * @param homework {@link Homework} to extract from
     * @param context  the context of the application
     * @return the Name of the {@link Subject} the {@link Homework} was given in and the deadline as String. E.g "Math - 26.03.2017"
     */
    static String extractGuiString(Homework homework, Context context) {
        String dateString = extractGuiString(homework.getDeadline(), false, context);

        return homework.getSubject().getName() + " - " + dateString;
    }

    /**
     * extracts a GUI displayable String from the given {@link Subject}
     *
     * @param subject {@link Subject} to extract from
     * @return the Name of the {@link Subject} and the abbreviation of the {@link Teacher} as String. E.g "Math - SMT"
     * or if the abbreviation wasn't provided the name of the {@link Teacher}
     */
    static String extractGuiString(Subject subject) {
        if (subject.getTeacher().getAbbreviation().matches("NULL")) {
            return subject.getName() + " - " + subject.getTeacher().getName();
        } else {
            return subject.getName() + " - " + subject.getTeacher().getAbbreviation();
        }
    }

    /**
     * extracts a GUI displayable String from the given {@link Teacher}
     *
     * @param teacher {@link Teacher} to extract from
     * @param context the context of the application
     * @return the Name of the {@link Teacher} and if provided the abbreviation of the {@link Teacher} as String. E.g "Mr. Smith - SMT"
     */
    static String extractGuiString(Teacher teacher, Context context) {
        StringBuilder stringBuilder = new StringBuilder();

        if (teacher.getGender() == Teacher.MALE) {
            stringBuilder.append(context.getResources().getString(R.string.string_mr));
        } else {
            stringBuilder.append(context.getResources().getString(R.string.string_mrs));
        }

        stringBuilder.append(" ").append(teacher.getName());
        if (!teacher.getAbbreviation().matches("NULL")) {
            stringBuilder.append(" - ").append(teacher.getAbbreviation());
        }

        return stringBuilder.toString();
    }

    /**
     * extracts a GUI displayable String from the given {@link Calendar}
     *
     * @param calendar   {@link Calendar} to extract from
     * @param isTimeOnly indicates if {@link Calendar} stores only time values (false if it's a date)
     * @param context    context of the application
     * @return if isTimeOnly == false: returns the date as string formatted like {@link Settings#getActiveDateFormat()} but separated by '.'-s
     * <br></br>
     * if isTimeOnly == true: returns the time as string e.g. HH:MM (24h-Format)
     */
    static String extractGuiString(Calendar calendar, boolean isTimeOnly, Context context) {
        String res = "";
        if (isTimeOnly) {
            if (calendar.get(Calendar.HOUR_OF_DAY) > 9 && calendar.get(Calendar.MINUTE) > 9) {
                res = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
            } else if (calendar.get(Calendar.HOUR_OF_DAY) <= 9 && calendar.get(Calendar.MINUTE) > 9) {
                res = "0" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
            } else if (calendar.get(Calendar.HOUR_OF_DAY) > 9 && calendar.get(Calendar.MINUTE) <= 9) {
                res = calendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + calendar.get(Calendar.MINUTE);
            } else {
                res = "0" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + calendar.get(Calendar.MINUTE);
            }
        } else {
            switch (Settings.getInstance(context).getActiveDateFormat()) {
                case Settings.DATE_FORMAT_DDMMYYYY:
                    res = calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." +
                            calendar.get(Calendar.YEAR);
                    break;
                case Settings.DATE_FORMAT_MMDDYYYY:
                    res = (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH) + "." +
                            calendar.get(Calendar.YEAR);
                    break;
                case Settings.DATE_FORMAT_YYYYMMDD:
                    res = calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." +
                            calendar.get(Calendar.DAY_OF_MONTH);
                    break;
            }
        }
        return res;
    }
    //endregion

    /**
     * method used to set the {@link View.OnClickListener} of a {@link Button} at a given id
     *
     * @param view            the view the {@link Button} is in
     * @param id              Resource ID of the {@link Button}
     * @param onClickListener the {@link View.OnClickListener} to set the {@link Button} to
     * @return the updated {@link Button}
     */
    static Button defineButtonOnClickListener(View view, int id, View.OnClickListener onClickListener) {
        Button b = (Button) view.findViewById(id);
        b.setOnClickListener(onClickListener);
        return b;
    }

    /**
     * method used to set the {@link View.OnClickListener} of a {@link FloatingActionButton} at a given id
     *
     * @param view            the view the {@link FloatingActionButton} is in
     * @param id              Resource ID of the {@link FloatingActionButton}
     * @param onClickListener the {@link View.OnClickListener} to set the {@link FloatingActionButton} to
     * @return the updated {@link FloatingActionButton}
     */
    static FloatingActionButton defineFloatingActionButtonOnClickListener(View view, int id, View.OnClickListener onClickListener) {
        FloatingActionButton b = (FloatingActionButton) view.findViewById(id);
        b.setOnClickListener(onClickListener);
        return b;
    }

    /**
     * method to set the {@link SeekBar.OnSeekBarChangeListener} of a {@link SeekBar} at a given id
     *
     * @param view                    the view the {@link SeekBar} is in
     * @param id                      Resource ID of the {@link SeekBar}
     * @param onSeekBarChangeListener The {@link SeekBar.OnSeekBarChangeListener} to set to the {@link SeekBar}
     * @return the updated {@link SeekBar}
     */
    static SeekBar defineSeekBarOnChangeListener(View view, int id, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        SeekBar seekBar = (SeekBar) view.findViewById(id);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        return seekBar;
    }

    //region private methods

    /**
     * can be used to indicate for the user that a {@link EditText} in the GUI must not be empty by displaying a Red hint "Mandatory Field"
     *
     * @param view the view the {@link EditText} is in
     * @param id   the ResourceID of the EditText
     */
    private static void handleEmptyMandatoryEditText(View view, int id) {
        handleEmptyMandatoryEditText(view, id, view.getContext().getResources().getString(R.string.string_mandatory_field));
    }

    /**
     * can be used to indicate for the user that a {@link EditText} in the GUI must not be empty by displaying a Red hint with your message
     *
     * @param view    the view the {@link EditText} is in
     * @param id      the ResourceID of the EditText
     * @param message the message to display in the hint
     */
    private static void handleEmptyMandatoryEditText(View view, int id, String message) {
        EditText editText = (EditText) view.findViewById(id);
        handleEmptyMandatoryEditText(editText, message);
    }

    /**
     * can be used to indicate for the user that a {@link EditText} in the GUI must not be empty by displaying a Red hint "Mandatory Field"
     *
     * @param editText the editText
     */
    private static void handleEmptyMandatoryEditText(EditText editText) {
        handleEmptyMandatoryEditText(editText, editText.getContext().getResources().getString(R.string.string_mandatory_field));
    }

    /**
     * can be used to indicate for the user that a {@link EditText} in the GUI must not be empty by displaying a Red hint with your message
     *
     * @param editText the editText
     * @param message  the message to display in the hint
     */
    private static void handleEmptyMandatoryEditText(EditText editText, String message) {
        editText.setText("");
        editText.setHint(message);
        editText.setHintTextColor(Color.RED);
    }


    /**
     * can be used to indicate for the user that a {@link Button} in the GUI must not be empty by displaying a Red hint "Mandatory Field"
     *
     * @param view the view the {@link Button} is in
     * @param id   the ResourceID of the Button
     */
    private static void handleEmptyMandatoryButton(View view, int id) {
        handleEmptyMandatoryButton(view, id, view.getContext().getResources().getString(R.string.string_mandatory_field));
    }

    /**
     * can be used to indicate for the user that a {@link Button} in the GUI must not be empty by displaying a Red hint with your message
     *
     * @param view    the view the {@link Button} is in
     * @param id      the ResourceID of the Button
     * @param message the message to display in the hint
     */
    private static void handleEmptyMandatoryButton(View view, int id, String message) {
        Button button = (Button) view.findViewById(id);
        handleEmptyMandatoryButton(button, message);
    }

    /**
     * can be used to indicate for the user that a {@link Button} in the GUI must not be empty by displaying a Red hint "Mandatory Field"
     *
     * @param button the button
     */
    private static void handleEmptyMandatoryButton(Button button) {
        handleEmptyMandatoryButton(button, button.getContext().getResources().getString(R.string.string_mandatory_field));
    }

    /**
     * can be used to indicate for the user that a {@link Button} in the GUI must not be empty by displaying a Red hint with your message
     *
     * @param button  the button
     * @param message the message to display in the hint
     */
    private static void handleEmptyMandatoryButton(Button button, String message) {
        button.setText("");
        button.setHint(message);
        button.setHintTextColor(Color.RED);
    }


    /**
     * method to parse a {@link Calendar} from a ordered {@link String}[]
     *
     * @param date    {@link Settings#getActiveDateFormat()} ordered {@link String}[]
     * @param context the context of the application
     * @return the parsed Calendar
     * @throws IllegalArgumentException if the given array don't meets the {@link Settings#getActiveDateFormat()} standard
     */
    private static Calendar parseCalendarFromStringArray(String[] date, Context context) throws IllegalArgumentException {
        Calendar calendar;
        String activeDateFormat = Settings.getInstance(context).getActiveDateFormat();
        switch (activeDateFormat) {
            case Settings.DATE_FORMAT_DDMMYYYY:
                if (date[0].length() <= 2 && date[1].length() <= 2 && date[2].length() == 4) {
                    calendar = new GregorianCalendar(
                            Integer.parseInt(date[2]),
                            Integer.parseInt(date[1]) - 1,
                            Integer.parseInt(date[0])
                    );
                } else {
                    throw new IllegalArgumentException(Arrays.toString(date) + " is no valid array for DATE_FORMAT_DDMMYYYY");
                }
                break;
            case Settings.DATE_FORMAT_MMDDYYYY:
                if (date[0].length() <= 2 && date[1].length() <= 2 && date[2].length() == 4) {
                    calendar = new GregorianCalendar(
                            Integer.parseInt(date[2]),
                            Integer.parseInt(date[0]) - 1,
                            Integer.parseInt(date[1])
                    );
                } else {
                    throw new IllegalArgumentException(Arrays.toString(date) + " is no valid array for DATE_FORMAT_MMDDYYYY");
                }
                break;
            case Settings.DATE_FORMAT_YYYYMMDD:
                if (date[0].length() == 4 && date[1].length() <= 2 && date[2].length() <= 2) {
                    calendar = new GregorianCalendar(
                            Integer.parseInt(date[0]),
                            Integer.parseInt(date[1]) - 1,
                            Integer.parseInt(date[2])
                    );
                } else {
                    throw new IllegalArgumentException(Arrays.toString(date) + " is no valid array for DATE_FORMAT_YYYYMMDD");
                }
                break;
            default:
                throw new IllegalArgumentException(Settings.getInstance(context).getActiveDateFormat() + " is not supported");
        }
        return calendar;
    }
    //endregion


}
