//package schmitt_florian.schoolplanner.logic;
//
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Implementation of DatabaseHelper interface to create and interact with the schoolPlanner SQLite Database
// */
//public class DatabaseHelperImpl extends SQLiteOpenHelper implements DatabaseHelper {
//    private Context context = null;
//
//    /**
//     * method inherited from SQLiteOpenHelper to create and setup the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        createTables(sqLiteDatabase);
//
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * method inherited from SQLiteOpenHelper called to reset the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     * @param i              old db version number
//     * @param i1             new db version number
//     */
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        dropAllTables(sqLiteDatabase);
//        onCreate(sqLiteDatabase);
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * standard c'tor for DatabaseHelperImpl
//     *
//     * @param context context of the application
//     */
//    public DatabaseHelperImpl(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
//    }
//
//
//    //region private methods
//
//    /**
//     * deletes all tables from the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void dropAllTables(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SUBJECT);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TEACHER);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_HOMEWORK);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EXAM);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_GRADE);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PERIOD);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_WEEKDAY);
//        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SCHEDULE);
//
//
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create all tables in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createTables(SQLiteDatabase sqLiteDatabase) {
//        createSubjectTable(sqLiteDatabase);
//        createTeacherTable(sqLiteDatabase);
//        createHomeworkTable(sqLiteDatabase);
//        createExamTable(sqLiteDatabase);
//        createGradeTable(sqLiteDatabase);
//        createPeriodTable(sqLiteDatabase);
//        createWeekdayTable(sqLiteDatabase);
//        createScheduleTable(sqLiteDatabase);
//
//        sqLiteDatabase.close();
//    }
//
//    //region table creation
//
//    /**
//     * create subject table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createSubjectTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SUBJECT + "(" +
//                SUBJECT_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                SUBJECT_COLUMN_TEACHER_ID + " INTEGER NOT NULL, " +
//                SUBJECT_COLUMN_NAME + " VARCHAR NOT NULL, " +
//                SUBJECT_COLUMN_ROOM + " VARCHAR NOT NULL "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create teacher table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createTeacherTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TEACHER + "(" +
//                TEACHER_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                TEACHER_COLUMN_NAME + " VARCHAR NOT NULL, " +
//                TEACHER_COLUMN_ABBREVIATION + " VARCHAR UNIQUE, " +
//                TEACHER_COLUMN_GENDER + " CHAR NOT NULL "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create homework table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createHomeworkTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_HOMEWORK + "(" +
//                HOMEWORK_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                HOMEWORK_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
//                HOMEWORK_COLUMN_DESCRIPTION + " TEXT, " +
//                HOMEWORK_COLUMN_DEADLINE + " DATE "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create exam table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createExamTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EXAM + "(" +
//                EXAM_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                EXAM_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
//                EXAM_COLUMN_DESCRIPTION + " TEXT, " +
//                EXAM_COLUMN_DEADLINE + " DATE "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create grade table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createGradeTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GRADE + "(" +
//                GRADE_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                GRADE_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
//                GRADE_COLUMN_NAME + " VARCHAR NOT NULL, " +
//                GRADE_COLUMN_GRADE + " INTEGER NOT NULL "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create period table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createPeriodTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERIOD + "(" +
//                PERIOD_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                PERIOD_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
//                PERIOD_COLUMN_WEEKDAY_ID + " INTEGER NOT NULL, " +
//                PERIOD_COLUMN_SCHOOL_HOUR_NO + "INTEGER NOT NULL, " +
//                PERIOD_COLUMN_STARTTIME + " TIME NOT NULL, " +
//                PERIOD_COLUMN_ENDTIME + " TIME NOT NULL "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create weekday table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createWeekdayTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_WEEKDAY + "(" +
//                WEEKDAY_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                WEEKDAY_COLUMN_SCHEDULE_ID + " INTEGER NOT NULL, " +
//                WEEKDAY_COLUMN_NAME + " VARCHAR NOT NULL "
//        );
//        sqLiteDatabase.close();
//    }
//
//    /**
//     * create schedule table in the schoolPlanner Database
//     *
//     * @param sqLiteDatabase the schoolPlanner Database
//     */
//    private void createScheduleTable(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SCHEDULE + "(" +
//                SCHEDULE_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                SCHEDULE_COLUMN_NAME + " VARCHAR NOT NULL "
//        );
//        sqLiteDatabase.close();
//    }
//    //endregion
//
//    //endregion
//
//}
