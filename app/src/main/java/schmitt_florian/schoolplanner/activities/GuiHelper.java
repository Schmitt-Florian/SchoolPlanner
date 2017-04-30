package schmitt_florian.schoolplanner.activities;



import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;



/**
 * A basic auxiliary class containing simple methods for GUI interaction
 */
class GuiHelper {

    /**
     * method to set the Text of a TextView
     * @param view the view the TextView is in
     * @param id Resource ID of the TextView
     * @param text The text to set to the TextView
     */
    void setTextToTextView(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        textView.setText(text);
    }
}
