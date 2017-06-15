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
import schmitt_florian.schoolplanner.logic.objects.Subject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubjectsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SubjectsFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private Subject[] allSubjectsInList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

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
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subjects_floatingActionButton_add:
                startActivity(new Intent(getContext(), SubjectDetailsActivity.class));
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
        allSubjectsInList = fillListView(view);
        GuiHelper.defineFloatingActionButtonOnClickListener(view, R.id.subjects_floatingActionButton_add, this);
        defineSubjectListOnClick(view);
    }

    /**
     * method to fill the ListView, which shows the {@link Subject}s at the subject screen
     *
     * @param view the view of the fragment
     * @return returns a array of all {@link Subject}s shown in the listView ordered by their position in the listView
     */
    private Subject[] fillListView(View view) {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> subjectStrings = new ArrayList<>();
        ArrayList<Subject> subjectArrayList = new ArrayList<>();
        int[] subjectIndices = dbHelper.getIndices(DatabaseHelper.TABLE_SUBJECT);

        for (int subjectIndex : subjectIndices) {
            Subject subject = dbHelper.getSubjectAtId(subjectIndex);

            subjectStrings.add(GuiHelper.extractGuiString(subject));
            subjectArrayList.add(subject);
        }

        if (subjectStrings.size() != 0) {
            GuiHelper.fillListViewFromArray(view, R.id.subjects_listSubjects, subjectStrings.toArray(new String[0]));
        }
        return subjectArrayList.toArray(new Subject[0]);
    }

    /**
     * method to handle Clicks on the ListView, which shows the {@link Subject}s at the subjects screen
     *
     * @param view              the view of the fragment
     */
    private void defineSubjectListOnClick(final View view) {
        ListView subjectList = (ListView) view.findViewById(R.id.subjects_listSubjects);

        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent intent = new Intent(getContext(), SubjectDetailsActivity.class);
                intent.putExtra("SubjectID", allSubjectsInList[position].getId());
                startActivity(intent);
            }
        });
    }
//endregion
}
