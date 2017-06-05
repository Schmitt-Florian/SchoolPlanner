package schmitt_florian.schoolplanner.logic;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import schmitt_florian.schoolplanner.R;

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
     * when trying to add an object to the database that does already exist
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
     * when trying to update an object in the database that does not exist
     * by showing a Toast with an error message
     *
     * @param objectTypeName name of the object type you were updating
     * @param context        the context of the app
     */
    static void handleDatabaseExceptionForUpdatingAnNotExistingObject(String objectTypeName, Context context) {
        Toast.makeText(context, "Could not update " + objectTypeName + " in Database. Maybe you have not created this " + objectTypeName + " before.", Toast.LENGTH_LONG).show();
    }

    /**
     * handles an exception thrown by implementing classes of {@link DatabaseHelper}
     * when trying to delete an object in the database that does not exist
     * by showing a Toast with an error message
     *
     * @param objectId id of the object type you were deleting
     * @param context  the context of the app
     */
    static void handleDatabaseExceptionForDeletingAnNotExistingObject(int objectId, Context context) {
        Toast.makeText(context, "Could not delete " + objectId + " in Database. Maybe you have not created this " + objectId + " before.", Toast.LENGTH_LONG).show();
    }

    /**
     * displays alert dialog to ask the user to confirm deletion
     * @param numberOfObjects number of affected objects
     * @param context the context of the app
     * @return true if deletion confirmed, else false
     */
    static boolean askForConfirmationToDeleteObjectsRecursive(int numberOfObjects, Context context) {
        final boolean[] bool = new boolean[1];

        new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.string_confirm_delete))
                .setMessage(context.getResources().getString(R.string.string_do_you_really_want_to_delete_this_object_this_will_also_lead_to_the_deletion_of) +
                        " " + numberOfObjects + " " + context.getResources().getString(R.string.string_related_objects))
                .setIcon(android.R.drawable.ic_menu_delete)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        bool[0] = true;
                    }
                })

                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        bool[0] = false;
                    }
                })
                .setCancelable(false)
                .show()
                .setCanceledOnTouchOutside(false);

        return bool[0];
    }

}
