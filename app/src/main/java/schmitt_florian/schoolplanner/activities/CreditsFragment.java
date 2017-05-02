package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.content.Intent;
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
 * {@link CreditsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreditsFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
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
        View view = inflater.inflate(R.layout.fragment_credits, container, false);

        initGUI(view);

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
            case R.id.credits_buttonEmail:
                onEmailClick();
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
    private void initGUI(View view) {
        guiHelper.defineFloatingActionButtonOnClickListener(view, R.id.credits_buttonEmail, this);
    }

    /**
     * method to handle a click at the email button
     */
    private void onEmailClick() {
        sendEmail("Bug Report");
    }

    /**
     * method to send open a choosing dialog of email services to send a email to the dev team with the given subject
     *
     * @param subject the subject of the email
     */
    private void sendEmail(String subject) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:" + getString(R.string.project_email)));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        startActivity(Intent.createChooser(emailIntent, getString(R.string.choose_email_service)));
    }
    //endregion
}
