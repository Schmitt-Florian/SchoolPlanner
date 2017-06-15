package schmitt_florian.schoolplanner.gui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.objects.Exam;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExamsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ExamsFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private View view;
    private Exam[] allExamsInList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_exams, container, false);

        initGUI();
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
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exams_floatingActionButton_add:
                startActivity(new Intent(getContext(), ExamDetailsActivity.class));
                break;
        }
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
    private void initGUI() {
        GuiHelper.defineFloatingActionButtonOnClickListener(view, R.id.exams_floatingActionButton_add, this);

        allExamsInList = fillListView();
        defineExamListOnClick(view);
    }

    /**
     * method to fill the ListView, which shows the {@link Exam}s at the exams screen
     *
     * @return returns a array of all {@link Exam}s shown in the listView ordered by their position in the listView
     */
    private Exam[] fillListView() {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> examStrings = new ArrayList<>();
        ArrayList<Exam> examArrayList = new ArrayList<>();
        int[] examIndices = dbHelper.getIndices(DatabaseHelper.TABLE_EXAM);

        for (int examIndex : examIndices) {
            Exam exam = dbHelper.getExamAtId(examIndex);

            examStrings.add(GuiHelper.extractGuiString(exam, getContext()));
            examArrayList.add(exam);
        }

        if (examStrings.size() != 0) {
            GuiHelper.fillListViewFromArray(view, R.id.exams_listExams, examStrings.toArray(new String[0]));
        }

        return examArrayList.toArray(new Exam[0]);
    }

    /**
     * method to handle Clicks on the ListView, which shows the {@link Exam}s at the exams screen
     *
     * @param view the view of the fragment
     */
    private void defineExamListOnClick(final View view) {
        ListView subjectList = (ListView) view.findViewById(R.id.exams_listExams);

        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent intent = new Intent(getContext(), ExamDetailsActivity.class);
                intent.putExtra("ExamID", allExamsInList[position].getId());
                startActivity(intent);
            }
        });
    }
    //endregion
}
