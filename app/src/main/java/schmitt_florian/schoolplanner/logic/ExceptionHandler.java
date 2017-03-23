package schmitt_florian.schoolplanner.logic;


import android.content.Context;
import android.widget.Toast;

/**
 * class to handle Exceptions thrown by classes in the logic package
 */
class ExceptionHandler {

    /**
     * handles an exception thrown by implementing classes of {@link DatabaseHelper}
     * when trying to get an object from the database that isn't existing
     * by showing a Toast with an error message
     * @param objectTypeName name of the object type you was requesting
     * @param context the context of the app
     */
    static void handleDatabaseExceptionForGettingANotExistingObject(String objectTypeName, Context context) {
        Toast.makeText(context, "Could not get " + objectTypeName + " from Database. Maybe you have not created the asked " + objectTypeName + " before.", Toast.LENGTH_LONG).show();
    }
}
