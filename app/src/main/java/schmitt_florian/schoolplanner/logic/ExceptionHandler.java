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
     *
     * @param objectTypeName name of the object type you was requesting
     * @param context        the context of the app
     */
    static void handleDatabaseExceptionForGettingANotExistingObject(String objectTypeName, Context context) {
        Toast.makeText(context, "Could not get " + objectTypeName + " from Database. Maybe you have not created this" + objectTypeName + " before.", Toast.LENGTH_LONG).show();
    }

    /**
     * handles an exception thrown by implementing classes of {@link DatabaseHelper}
     * when trying to add an object to the database that is already existing
     * by showing a Toast with an error message
     *
     * @param newObject the object which failed to add to the database
     * @param context   the context of the app
     */
    static void handleDatabaseExceptionForAddingAAlreadyExistingObject(Object newObject, Context context) {
        Toast.makeText(context, "Could not add \n" + newObject.toString() + "\nto Database. Maybe you are trying to add an already existing Object", Toast.LENGTH_LONG).show();
    }

    /**
     * handles an exception thrown by implementing classes of {@link DatabaseHelper}
     * when trying to update an object in the database that isn't existing
     * by showing a Toast with an error message
     *
     * @param objectTypeName name of the object type you was updating
     * @param context        the context of the app
     */
    static void handleDatabaseExceptionForUpdatingAnExistingObject(String objectTypeName, Context context) {
        Toast.makeText(context, "Could not update " + objectTypeName + " in Database. Maybe you have not created this " + objectTypeName + " before.", Toast.LENGTH_LONG).show();
    }
}
