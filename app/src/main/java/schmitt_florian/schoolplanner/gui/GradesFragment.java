package schmitt_florian.schoolplanner.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.objects.Grade;
import schmitt_florian.schoolplanner.logic.objects.Subject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GradesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class GradesFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private Grade[] gradesCurrentlyShowing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
//        ToggleButton editButton = (ToggleButton) view.findViewById(R.id.toggleEditSchedule);
//        editButton.setVisibility(View.INVISIBLE);

        initGui(view);
        initToolbarTitle();
        return view;
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
        initGui(getView());
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
            case R.id.grades_floatingActionButton_add:
                startActivity(new Intent(getContext(), GradeDetailsActivity.class));
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
    private void initGui(final View view) {
        defineSubjectListOnClick(view, fillSubjectListView(view));
        defineGridViewOnClick(view);

        GuiHelper.defineFloatingActionButtonOnClickListener(view, R.id.grades_floatingActionButton_add, this);
    }

    /**
     * method to handle Clicks on the ListView, which shows the {@link Subject}s at the grades screen
     *
     * @param view              the view of the fragment
     * @param allSubjectsInList a array of all {@link Subject}s shown in the listView ordered by their position in the listView
     */
    private void defineSubjectListOnClick(final View view, final Subject[] allSubjectsInList) {
        ListView subjectList = (ListView) view.findViewById(R.id.grades_listSubjects);

        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                gradesCurrentlyShowing = fillGridView(view, allSubjectsInList[position]);
            }
        });
    }

    /**
     * method to handle Clicks on the GridView, which shows the {@link Grade}s at the grades screen
     *
     * @param view the view of the fragment
     */
    private void defineGridViewOnClick(View view) {
        final GridView gridView = (GridView) view.findViewById(R.id.grades_gradesTable);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent intent = new Intent(getContext(), GradeDetailsActivity.class);
                if (position % 2 == 0) {
                    intent.putExtra("GradeID", gradesCurrentlyShowing[position / 2].getId());

                } else {
                    intent.putExtra("GradeID", gradesCurrentlyShowing[(position - 1) / 2].getId());
                }
                startActivity(intent);
            }
        });
    }

    /**
     * method to fill the ListView, which shows the {@link Subject}s at the grades screen
     *
     * @param view the view of the fragment
     * @return returns a array of all {@link Subject}s shown in the listView ordered by their position in the listView
     */
    private Subject[] fillSubjectListView(View view) {
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
            GuiHelper.fillListViewFromArray(view, R.id.grades_listSubjects, subjectStrings.toArray(new String[0]));
        }
        return subjectArrayList.toArray(new Subject[0]);
    }

    /**
     * method to fill the GridView, which shows the {@link Grade}s at the grades screen
     *
     * @param view    the view of the fragment
     * @param subject the subjects the grades to be shown are in
     * @return returns a array of all {@link Grade}s shown in the gridView ordered by their position in the gridView
     */
    private Grade[] fillGridView(View view, Subject subject) {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> gridStrings = new ArrayList<>();
        ArrayList<Grade> gradeArrayList = new ArrayList<>();
        int[] gradeIndices = dbHelper.getIndices(DatabaseHelper.TABLE_GRADE);

        for (int gradeIndex : gradeIndices) {
            Grade grade = dbHelper.getGradeAtId(gradeIndex);

            if (grade.getSubject().match(subject)) {
                gridStrings.add(grade.getName());
                gridStrings.add("\t" + "\t" + "\t" + "\t" + grade.getGrade());

                gradeArrayList.add(grade);
            }
        }

        //if (gridStrings.size() != 0) {
        GuiHelper.fillGridViewFromArray(view, R.id.grades_gradesTable, gridStrings.toArray(new String[0]));
//        }
        return gradeArrayList.toArray(new Grade[0]);
    }

    /**
     * method to adjust appbar title for selected fragment
     */

    private void initToolbarTitle() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.string_grades);
    }
    //endregion
}
