package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.Settings;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ScheduleFragment extends Fragment {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private View rootView;
    private TableRow[] rows = new TableRow[15];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        initGui();
//        ToggleButton editButton = (ToggleButton) view.findViewById(R.id.toggleEditSchedule);
//        editButton.setVisibility(View.VISIBLE);
        return rootView;

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
    public interface OnFragmentInteractionListener {
        @SuppressWarnings({"FieldNever", "unused"})
        void onFragmentInteraction(Uri uri);
    }


    //region private methods

    /**
     * method to initialise components of the GUI
     */
    private void initGui() {
        rows = getScheduleRowsInArray();
        initVisibilityForSchedule();
    }

    /**
     * method to get all {@link TableRow} in the schedule {@link TableLayout}
     *
     * @return all {@link TableRow} in the schedule {@link TableLayout}
     */
    private TableRow[] getScheduleRowsInArray() {
        TableLayout table = (TableLayout) rootView.findViewById(R.id.schedule_table);
        ArrayList<TableRow> rowArrayList = new ArrayList<>();

        for (int i = 0; i < table.getChildCount(); i++) {
            rowArrayList.add((TableRow) table.getChildAt(i));
        }

        return rowArrayList.toArray(new TableRow[0]);
    }

    /**
     * method to initialise visibility of the schedule rows based on {@link Settings#getPeriodsAtDay()}
     */
    private void initVisibilityForSchedule() {
        int visibleRowCount = Settings.getInstance(getContext()).getPeriodsAtDay() + 1;
        for (int i = 0; i < visibleRowCount; i++) {
            rows[i].setVisibility(View.VISIBLE);
        }
        for (int i = rows.length; i > visibleRowCount; i--) {
            rows[i - 1].setVisibility(View.GONE);
        }
    }
    //endregion

}
