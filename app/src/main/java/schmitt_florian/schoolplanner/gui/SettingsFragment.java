package schmitt_florian.schoolplanner.gui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelper;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Settings;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    @SuppressWarnings({"FieldNever", "unused"})
    private OnFragmentInteractionListener mListener;
    private Settings settings;
    private View view;
    private boolean weekdaySetMon;
    private boolean weekdaySetTue;
    private boolean weekdaySetWed;
    private boolean weekdaySetThur;
    private boolean weekdaySetFri;
    private boolean weekdaySetSat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        settings = Settings.getInstance(view.getContext());
        initGui();
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
            case R.id.settings_buttonSave:
                readGui();
                settings.saveSettings();
                Toast.makeText(getContext(), R.string.string_settings_saved, Toast.LENGTH_SHORT).show();
                getActivatedWeekdays();
                break;
            case R.id.settings_buttonResetDB:
                System.out.println("pressed");
                DatabaseHelper dbHelper = new DatabaseHelperImpl(getContext());
                System.out.println(dbHelper.toString());
                dbHelper.resetDatabase();
                System.out.println(dbHelper.toString());
        }
    }

    private void getActivatedWeekdays() {
    /*    CheckBox mon = (CheckBox) getActivity().findViewById(R.id.settings_checkBox_for_monday);
        CheckBox tue = (CheckBox) getActivity().findViewById(R.id.settings_checkBox_for_tuesday);
        CheckBox wed = (CheckBox) getActivity().findViewById(R.id.settings_checkBox_for_wednesday);
        CheckBox thur = (CheckBox) getActivity().findViewById(R.id.settings_checkBox_for_thursday);
        CheckBox fri = (CheckBox) getActivity().findViewById(R.id.settings_checkBox_for_friday);
        CheckBox sat = (CheckBox) getActivity().findViewById(R.id.settings_checkBox_for_saturday);

        weekdaySetMon = mon.isChecked();

        weekdaySetTue = tue.isChecked();

        weekdaySetWed = wed.isChecked();

        weekdaySetThur = thur.isChecked();

        weekdaySetFri = fri.isChecked();

        weekdaySetSat = sat.isChecked();

//        System.out.println(
//                "mon: " + weekdaySetMon + "\n" +
//                        "tue: " + weekdaySetTue + "\n" +
//                        "wed: " + weekdaySetWed + "\n" +
//                        "thur: " + weekdaySetThur + "\n" +
//                        "fri: " + weekdaySetFri + "\n" +
//                        "sat: " + weekdaySetSat + "\n"
//        );
*/
    }

    public boolean isWeekdaySetMon() {
        return weekdaySetMon;
    }

    public boolean isWeekdaySetTue() {
        return weekdaySetTue;
    }

    public boolean isWeekdaySetWed() {
        return weekdaySetWed;
    }

    public boolean isWeekdaySetThur() {
        return weekdaySetThur;
    }

    public boolean isWeekdaySetFri() {
        return weekdaySetFri;
    }

    public boolean isWeekdaySetSat() {
        return weekdaySetSat;
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
        initSeekBar();
        initDateFormatSpinner();
        GuiHelper.defineButtonOnClickListener(view, R.id.settings_buttonSave, this);

    }

    /**
     * initialises the {@link SeekBar} which displays the {@link Settings#periodsAtDay}
     */
    private void initSeekBar() {
        GuiHelper.defineSeekBarOnChangeListener(view, R.id.settings_seekbarPeriods,
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        GuiHelper.setTextToTextView(view, R.id.settings_textviewSeekbarPeriodsPos, String.valueOf(progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //ignore
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //ignore
                    }
                }
        ).setProgress(settings.getPeriodsAtDay());
    }

    /**
     * initialises the {@link Settings#DATE_FORMAT} {@link Spinner}
     */
    private void initDateFormatSpinner() {
        Spinner spinner = GuiHelper.fillSpinnerFromArray(view, R.id.settings_spinnerDate,
                new String[]{Settings.DATE_FORMAT_DDMMYYYY, Settings.DATE_FORMAT_MMDDYYYY, Settings.DATE_FORMAT_YYYYMMDD});
        switch (settings.getActiveDateFormat()) {
            case Settings.DATE_FORMAT_DDMMYYYY:
                spinner.setSelection(0);
                break;
            case Settings.DATE_FORMAT_MMDDYYYY:
                spinner.setSelection(1);
                break;
            case Settings.DATE_FORMAT_YYYYMMDD:
                spinner.setSelection(2);
        }
    }

    /**
     * updates {@link SettingsFragment#settings} with values in GUI
     */
    private void readGui() {
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.settings_seekbarPeriods);
        settings.setPeriodsAtDay(seekBar.getProgress());

        Spinner spinner = (Spinner) view.findViewById(R.id.settings_spinnerDate);
        settings.setActiveDateFormat((String) spinner.getSelectedItem());
    }

    /**
     * method to adjust appbar title for selected fragment
     */

    private void initToolbarTitle() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.string_settings);
    }
    //endregion
}
