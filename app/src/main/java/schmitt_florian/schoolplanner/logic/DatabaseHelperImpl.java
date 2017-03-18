package schmitt_florian.schoolplanner.logic;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Implementation of DatabaseHelper interface to create and interact with SQLite Database
 */
public class DatabaseHelperImpl extends SQLiteOpenHelper implements DatabaseHelper {
    private Context context = null;

    /**
     * method inherited from SQLiteOpenHelper called when created database
     *
     * @param sqLiteDatabase SQLiteDatabase to be created
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    /**
     *
     * method inherited from SQLiteOpenHelper called when upgrading database
     * @param sqLiteDatabase SQLiteDatabase to be created
     * @param i old db version number
     * @param i1 new db version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * standard c'tor for DatabaseHelperImpl
     *
     * @param context context of the application
     */
    public DatabaseHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


}
