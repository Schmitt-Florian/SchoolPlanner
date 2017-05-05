package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schmitt_florian.schoolplanner.R;

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
    private void initGUI() {
        GuiHelper.defineButtonOnClickListener(view, R.id.homework_buttonToDo, this);
        GuiHelper.defineButtonOnClickListener(view, R.id.homework_buttonDone, this);
        changeTab();
    }

    private void changeTab() {
        if (tabIsToDo) {
            GuiHelper.setColorToButton(view, R.id.homework_buttonToDo, R.color.button_active);
            GuiHelper.setColorToButton(view, R.id.homework_buttonDone, R.color.button_passive);
        } else {
            GuiHelper.setColorToButton(view, R.id.homework_buttonToDo, R.color.button_passive);
            GuiHelper.setColorToButton(view, R.id.homework_buttonDone, R.color.button_active);
        }
    }
    //endregion
}
