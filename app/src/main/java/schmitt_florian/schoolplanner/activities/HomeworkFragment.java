package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Homework;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeworkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeworkFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private View view;
    private boolean tabIsToDo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabIsToDo = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_homework, container, false);

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
            case R.id.homework_buttonToDo:
                tabIsToDo = true;
                changeTab();
                break;
            case R.id.homework_buttonDone:
                tabIsToDo = false;
                changeTab();
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
     */
    private void initGUI() {
        GuiHelper.defineButtonOnClickListener(view, R.id.homework_buttonToDo, this);
        GuiHelper.defineButtonOnClickListener(view, R.id.homework_buttonDone, this);
        changeTab();
    }

    /**
     * method to change between the to-do tab and the done tab
     */
    private void changeTab() {
        if (tabIsToDo) {
            GuiHelper.setColorToButton(view, R.id.homework_buttonToDo, R.color.button_active);
            GuiHelper.setColorToButton(view, R.id.homework_buttonDone, R.color.button_passive);
            fillListView();
        } else {
            GuiHelper.setColorToButton(view, R.id.homework_buttonToDo, R.color.button_passive);
            GuiHelper.setColorToButton(view, R.id.homework_buttonDone, R.color.button_active);
            fillListView();
        }
    }

    /**
     * method to fill the ListView, which shows the {@link Homework}s at the homework screen, depending on the activated tab
     */
    private void fillListView() {
        DatabaseHelper dbHelper = new DatabaseHelperImpl(view.getContext());

        ArrayList<String> homeworkStrings = new ArrayList<>();
        int[] homeworkIndices = dbHelper.getIndices(DatabaseHelper.TABLE_HOMEWORK);

        for (int homeworkIndex : homeworkIndices) {
            Homework homework = dbHelper.getHomeworkAtId(homeworkIndex);

            if (tabIsToDo && !homework.isDone()) {
                homeworkStrings.add(GuiHelper.extractGuiString(homework));
            } else if (!tabIsToDo && homework.isDone()) {
                homeworkStrings.add(GuiHelper.extractGuiString(homework));
            }
        }

        if (homeworkStrings.size() != 0) {
            GuiHelper.fillListViewFromArray(view, R.id.homework_listHomework, homeworkStrings.toArray(new String[0]));
        }
    }
    //endregion
}
