package schmitt_florian.schoolplanner.gui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.objects.Teacher;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeachersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TeachersFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private Teacher[] allTeachersInList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        initGui(view);
        initToolbarTitle();
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
            case R.id.teachers_floatingActionButton_add:
                startActivity(new Intent(getContext(), TeacherDetailsActivity.class));
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
     *
     * @param view the view of the fragment
     */
    private void initGui(View view) {
        allTeachersInList = fillListView(view);
        GuiHelper.defineFloatingActionButtonOnClickListener(view, R.id.teachers_floatingActionButton_add, this);
        defineTeacherListOnClick(view);
    }

    /**
     * method to fill the ListView, which shows the {@link Teacher}s at the teachers screen
     *
     * @param view the view of the fragment
     * @return returns a array of all {@link Teacher}s shown in the listView ordered by their position in the listView
     */
    private Teacher[] fillListView(View view) {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> teacherStrings = new ArrayList<>();
        ArrayList<Teacher> teacherArrayList = new ArrayList<>();
        int[] teacherIndices = dbHelper.getIndices(DatabaseHelper.TABLE_TEACHER);

        for (int teacherIndex : teacherIndices) {
            Teacher teacher = dbHelper.getTeacherAtId(teacherIndex);

            teacherStrings.add(GuiHelper.extractGuiString(teacher, getContext()));
            teacherArrayList.add(teacher);
        }

        if (teacherStrings.size() != 0) {
            GuiHelper.fillListViewFromArray(view, R.id.teachers_listTeachers, teacherStrings.toArray(new String[0]));
        }
        return teacherArrayList.toArray(new Teacher[0]);
    }

    /**
     * method to handle Clicks on the ListView, which shows the {@link Teacher}s at the teachers screen
     *
     * @param view the view of the fragment
     */
    private void defineTeacherListOnClick(final View view) {
        ListView teacherList = view.findViewById(R.id.teachers_listTeachers);

        teacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent intent = new Intent(getContext(), TeacherDetailsActivity.class);
                intent.putExtra("TeacherID", allTeachersInList[position].getId());
                startActivity(intent);
            }
        });
    }

    /**
     * method to adjust appbar title for selected fragment
     */

    private void initToolbarTitle() {
        Toolbar toolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.string_teachers);
    }
//endregion

}
