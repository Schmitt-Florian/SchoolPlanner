package schmitt_florian.schoolplanner.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.objects.Exam;
import schmitt_florian.schoolplanner.logic.objects.Homework;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initGui(view);
        return view;
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
    interface OnFragmentInteractionListener {
        @SuppressWarnings({"FieldNever", "unused"})
        void onFragmentInteraction(Uri uri);
    }

    //region private methods
    /**
     * method to initialise components of the GUI
     *
     * @param view the view of the fragment
     */
    private void initGui(View view) {
        setDateToLabels(view);

        fillHomeworkListView(view);
        fillExamListView(view);
    }

    /**
     * method to initialise the Labels, which show the Date at the home screen
     *
     * @param view the view of the fragment
     */
    @SuppressLint("SwitchIntDef")
    private void setDateToLabels(View view) {
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_monday));
                break;
            case Calendar.TUESDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_tuesday));
                break;
            case Calendar.WEDNESDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_wednesday));
                break;
            case Calendar.THURSDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_thursday));
                break;
            case Calendar.FRIDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_friday));
                break;
            case Calendar.SATURDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_saturday));
                break;
            case Calendar.SUNDAY:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_sunday));
                break;
            default:
                GuiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_error));
                break;
        }
        //TODO support different date formats
        GuiHelper.setTextToTextView(view, R.id.home_labelDate, calendar.get(Calendar.DAY_OF_MONTH) + "." + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR));
    }

    /**
     * method to fill the ListView, which shows the {@link Homework}s at the home screen
     *
     * @param view the view of the fragment
     */
    private void fillHomeworkListView(View view) {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> homeworkStrings = new ArrayList<>();
        int[] homeworkIndices = dbHelper.getIndices(DatabaseHelper.TABLE_HOMEWORK);

        for (int homeworkIndex : homeworkIndices) {
            Homework homework = dbHelper.getHomeworkAtId(homeworkIndex);

            if (isDateInThisWeek(homework.getDeadline()) && !homework.isDone()) {
                homeworkStrings.add(GuiHelper.extractGuiString(homework));
            }
        }

        if (homeworkStrings.size() != 0) {
            GuiHelper.fillListViewFromArray(view, R.id.home_listHomework, homeworkStrings.toArray(new String[0]));
        }
    }

    /**
     * method to fill the ListView, which shows the {@link Exam}s at the home screen
     *
     * @param view the view of the fragment
     */
    private void fillExamListView(View view) {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> examStrings = new ArrayList<>();
        int[] examIndices = dbHelper.getIndices(DatabaseHelper.TABLE_EXAM);

        for (int examIndex : examIndices) {
            Exam exam = dbHelper.getExamAtId(examIndex);

            if (isDateInThisWeek(exam.getDeadline())) {
                examStrings.add(GuiHelper.extractGuiString(exam));
            }
        }

        if (examStrings.size() != 0) {
            GuiHelper.fillListViewFromArray(view, R.id.home_listExams, examStrings.toArray(new String[0]));
        }
    }

    /**
     * Indicates whether the given Date is in the current week.
     * <br><br/>
     * Note: that this method may not work properly in the last week of this and
     * the first week of the next year.
     * <br><br/>
     * Note: this method uses a that week goes from Monday to Sunday
     *
     * @param date the date
     * @return true if the date is in the current week, else false
     */
    private boolean isDateInThisWeek(GregorianCalendar date) {
        Calendar calendar = GregorianCalendar.getInstance();

        return date.get(Calendar.WEEK_OF_YEAR) == calendar.get(Calendar.WEEK_OF_YEAR) &&
                date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
    }

    //endregion

}
