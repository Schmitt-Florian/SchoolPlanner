package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import schmitt_florian.schoolplanner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private GuiHelper guiHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guiHelper = new GuiHelper();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setDateToLabels(view);

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //region private methods

    /**
     * method to initialise the Labels, which show the the Date at the home screen
     *
     * @param view the view of the fragment
     */
    private void setDateToLabels(View view) {
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_monday));
                break;
            case Calendar.TUESDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_tuesday));
                break;
            case Calendar.WEDNESDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_wednesday));
                break;
            case Calendar.THURSDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_thursday));
                break;
            case Calendar.FRIDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_friday));
                break;
            case Calendar.SATURDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_saturday));
                break;
            case Calendar.SUNDAY:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, getString(R.string.string_day_sunday));
                break;
            default:
                guiHelper.setTextToTextView(view, R.id.home_labelWeekday, "Error");
                break;
        }
        //Todo support different date formats
        guiHelper.setTextToTextView(view, R.id.home_lableDate, calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR));
    }
    //endregion

}
