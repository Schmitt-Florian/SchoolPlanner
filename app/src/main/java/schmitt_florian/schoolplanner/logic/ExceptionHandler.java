package schmitt_florian.schoolplanner.logic;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
        Toast.makeText(context, "Could not get " + objectTypeName + " from Database. Maybe you have not created the asked " + objectTypeName + " before.", Toast.LENGTH_LONG).show();
    }

    //Todo Jdoc
    static void handleDatabaseExceptionForAddingAAlreadyExistingObject(/*Object existingObject,*/  Object newObject,  Context context) throws IllegalArgumentException{
        Toast.makeText(context, "Could not add \n" + newObject.toString() + "\nto Database. Maybe you are trying to add an already existing Object", Toast.LENGTH_LONG).show();


// if (existingObject.getClass().equals(newObject.getClass())) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//            builder.setTitle("Confirm overwriting existing data");
//            builder.setMessage("The object you were trying to add would overwrite an already existing object. Please choose what to do." +
//                    "The old Object : \n" + existingObject.toString() + "\n The new Object: \n" + newObject.toString()
//            );
//
//            builder.setPositiveButton("Overwrite", new DialogInterface.OnClickListener() {
//
//                public void onClick(DialogInterface dialog, int which) {
//                    DatabaseHelper dbHelper =new DatabaseHelperImpl(context);
//                    String s = newObject.getClass().getCanonicalName();
//
//                    if (s.equals(Subject.class.getCanonicalName())) {
//                        dbHelper.updateSubjectAtId(((Subject) newObject).getId(), (Subject) newObject);
//                    }else if (s.equals(Teacher.class.getCanonicalName())){
//                        dbHelper.updateTeacherAtId(((Teacher) newObject).getId(), (Teacher) newObject);
//                    }else if (s.equals(Homework.class.getCanonicalName())){
//                        dbHelper.updateHomeworkAtId(((Homework) newObject).getId(),(Homework) newObject);
//                    }else if (s.equals(Exam.class.getCanonicalName()))
//                        //stopped here
//                    dialog.dismiss();
//                }
//            }).setNegativeButton("Keep old", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    // Do nothing
//                    dialog.dismiss();
//                }
//            });
//
//            AlertDialog alert = builder.create();
//            alert.show();
//        } else {
//            throw new IllegalArgumentException("the given Objects are not of the same class, the new object's class is " +
//                    newObject.getClass() + "while the existing Objects class is " + existingObject.getClass());
//        }
    }
}
