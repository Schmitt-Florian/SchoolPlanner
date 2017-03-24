package schmitt_florian.schoolplanner.logic;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Implementation of DatabaseHelper interface to create and interact with the schoolPlanner SQLite Database
 */
public class DatabaseHelperImpl extends SQLiteOpenHelper implements DatabaseHelper {

    private Context context = null;

    /**
     * method inherited from SQLiteOpenHelper to create and setup the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);

        sqLiteDatabase.close();
    }

    /**
     * method inherited from SQLiteOpenHelper called to reset the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     * @param i              old db version number
     * @param i1             new db version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropAllTables(sqLiteDatabase);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.close();
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


    //region getObjectAtId

    /**
     * gets the {@link Subject} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Subject}, or null if not existing
     */
    @Override
    public Subject getSubjectAtId(int id) {
        //Open Database
        SQLiteDatabase db = this.getReadableDatabase();
        //Build cursor
        Cursor cursor = null;

        //Query for commands
        String query = buildQueryToGetRowAtId(TABLE_SUBJECT, SUBJECT_COLUMN_ID, id);

        //Return object
        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Subject(
                    cursor.getInt(0),
                    getTeacherAtId(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Subject", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }


    /**
     * gets the {@link Teacher} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Teacher}
     */
    @Override
    public Teacher getTeacherAtId(int id) {
        //Open Database
        SQLiteDatabase db = this.getReadableDatabase();
        //Build cursor
        Cursor cursor = null;
        //Query for commands
        String query = buildQueryToGetRowAtId(TABLE_TEACHER, TEACHER_COLUMN_ID, id);

        //Return object
        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            return new Teacher(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3).charAt(0)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Subject", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * gets the {@link Exam} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Exam}
     */
    @Override
    public Exam getExamAtId(int id) {
        return null;
    }

    /**
     * gets the {@link Grade} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}
     */
    @Override
    public Grade getGradeAtId(int id) {
        return null;
    }

    /**
     * gets the {@link Period} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}
     */
    @Override
    public Period getPeriodAtId(int id) {
        return null;
    }

    /**
     * gets the {@link Weekday} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}
     */
    @Override
    public Weekday getWeekdayAtId(int id) {
        return null;
    }

    /**
     * gets the {@link Schedule} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}
     */
    @Override
    public Schedule getScheduleAtId(int id) {
        return null;
    }
//endregion


    //region updateObjectAtId

    /**
     * updates {@link Subject} at the given id in database
     *
     * @param id         the id the {@link Subject} to update has
     * @param newSubject the new {@link Subject}
     */
    @Override
    public void updateSubjectAtId(int id, Subject newSubject) {

    }

    /**
     * updates {@link Teacher} at the given id in database
     *
     * @param id         the id the {@link Teacher} to update has
     * @param newTeacher the new {@link Teacher}
     */
    @Override
    public void updateTeacherAtId(int id, Teacher newTeacher) {

    }

    /**
     * updates {@link Exam} at the given id in database
     *
     * @param id      the id the {@link Exam} to update has
     * @param newExam the new {@link Exam}
     */
    @Override
    public void updateExamAtId(int id, Exam newExam) {

    }

    /**
     * updates {@link Grade} at the given id in database
     *
     * @param id       the id the {@link Grade} to update has
     * @param newGrade the new {@link Grade}
     */
    @Override
    public void updateGradeAtId(int id, Grade newGrade) {

    }

    /**
     * updates {@link Period} at the given id in database
     *
     * @param id        the id the {@link Period} to update has
     * @param newPeriod the new {@link Period}
     */
    @Override
    public void updatePeriodAtId(int id, Period newPeriod) {

    }

    /**
     * updates {@link Weekday} at the given id in database
     *
     * @param id         the id the {@link Weekday} to update has
     * @param newWeekday the new {@link Weekday}
     */
    @Override
    public void updateWeekdayAtId(int id, Weekday newWeekday) {

    }

    /**
     * updates {@link Schedule} at the given id in database
     *
     * @param id          the id the {@link Schedule} to update has
     * @param newSchedule the new {@link Schedule}
     */
    @Override
    public void updateScheduleAtId(int id, Schedule newSchedule) {

    }
    //endregion


    //region insertIntoDB

    /**
     * inserts {@link Subject} into database
     *
     * @param subject {@link Subject} to be inserted
     */
    @Override
    public void insertIntoDB(Subject subject) {

    }

    /**
     * inserts {@link Teacher} into database
     *
     * @param teacher {@link Teacher} to be inserted
     */
    @Override
    public void insertIntoDB(Teacher teacher) {

    }

    /**
     * inserts {@link Exam} into database
     *
     * @param exam {@link Exam} to be inserted
     */
    @Override
    public void insertIntoDB(Exam exam) {

    }

    /**
     * inserts {@link Grade} into database
     *
     * @param grade {@link Grade} to be inserted
     */
    @Override
    public void insertIntoDB(Grade grade) {

    }

    /**
     * inserts {@link Period} into database
     *
     * @param period {@link Period} to be inserted
     */
    @Override
    public void insertIntoDB(Period period) {

    }

    /**
     * inserts {@link Weekday} into database
     *
     * @param weekday {@link Weekday} to be inserted
     */
    @Override
    public void insertIntoDB(Weekday weekday) {

    }

    /**
     * inserts {@link Schedule} into database
     *
     * @param schedule {@link Schedule} to be inserted
     */
    @Override
    public void insertIntoDB(Schedule schedule) {

    }
//endregion


    //region deleteObjectAtId

    /**
     * deletes the {@link Subject} at the given id from database
     *
     * @param id the id the {@link Subject} to delete has
     */
    @Override
    public void deleteSubjectAtId(int id) {

    }

    /**
     * deletes the {@link Teacher} at the given id from database
     *
     * @param id the id the {@link Teacher} to delete has
     */
    @Override
    public void deleteTeacherAtId(int id) {

    }

    /**
     * deletes the {@link Exam} at the given id from database
     *
     * @param id the id the {@link Exam} to delete has
     */
    @Override
    public void deleteExamAtId(int id) {

    }

    /**
     * deletes the {@link Grade} at the given id from database
     *
     * @param id the id the {@link Grade} to delete has
     */
    @Override
    public void deleteGradeAtId(int id) {

    }

    /**
     * deletes the {@link Period} at the given id from database
     *
     * @param id the id the {@link Period} to delete has
     */
    @Override
    public void deletePeriodAtId(int id) {

    }

    /**
     * deletes the {@link Weekday} at the given id from database
     *
     * @param id the id the {@link Weekday} to delete has
     */
    @Override
    public void deleteWeekdayAtId(int id) {

    }

    /**
     * deletes the {@link Schedule} at the given id from database
     *
     * @param id the id the {@link Schedule} to delete has
     */
    @Override
    public void deleteScheduleAtId(int id) {

    }
    //endregion


    //region private methods

    /**
     * deletes all tables from the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void dropAllTables(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SUBJECT);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TEACHER);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_HOMEWORK);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EXAM);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_GRADE);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PERIOD);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_WEEKDAY);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SCHEDULE);


        sqLiteDatabase.close();
    }

    /**
     * create all tables in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createTables(SQLiteDatabase sqLiteDatabase) {
        createSubjectTable(sqLiteDatabase);
        createTeacherTable(sqLiteDatabase);
        createHomeworkTable(sqLiteDatabase);
        createExamTable(sqLiteDatabase);
        createGradeTable(sqLiteDatabase);
        createPeriodTable(sqLiteDatabase);
        createWeekdayTable(sqLiteDatabase);
        createScheduleTable(sqLiteDatabase);

        sqLiteDatabase.close();
    }

    //region table creation

    /**
     * create subject table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createSubjectTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SUBJECT + "(" +
                SUBJECT_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                SUBJECT_COLUMN_TEACHER_ID + " INTEGER NOT NULL, " +
                SUBJECT_COLUMN_NAME + " VARCHAR NOT NULL, " +
                SUBJECT_COLUMN_ROOM + " VARCHAR NOT NULL "
        );
        sqLiteDatabase.close();
    }

    /**
     * create teacher table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createTeacherTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TEACHER + "(" +
                TEACHER_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                TEACHER_COLUMN_NAME + " VARCHAR NOT NULL, " +
                TEACHER_COLUMN_ABBREVIATION + " VARCHAR UNIQUE, " +
                TEACHER_COLUMN_GENDER + " CHAR NOT NULL "
        );
        sqLiteDatabase.close();
    }

    /**
     * create homework table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createHomeworkTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_HOMEWORK + "(" +
                HOMEWORK_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                HOMEWORK_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                HOMEWORK_COLUMN_DESCRIPTION + " TEXT, " +
                HOMEWORK_COLUMN_DEADLINE + " DATE "
        );
        sqLiteDatabase.close();
    }

    /**
     * create exam table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createExamTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EXAM + "(" +
                EXAM_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                EXAM_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                EXAM_COLUMN_DESCRIPTION + " TEXT, " +
                EXAM_COLUMN_DEADLINE + " DATE "
        );
        sqLiteDatabase.close();
    }

    /**
     * create grade table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createGradeTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GRADE + "(" +
                GRADE_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                GRADE_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                GRADE_COLUMN_NAME + " VARCHAR NOT NULL, " +
                GRADE_COLUMN_GRADE + " INTEGER NOT NULL "
        );
        sqLiteDatabase.close();
    }

    /**
     * create period table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createPeriodTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERIOD + "(" +
                PERIOD_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                PERIOD_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                PERIOD_COLUMN_WEEKDAY_ID + " INTEGER NOT NULL, " +
                PERIOD_COLUMN_SCHOOL_HOUR_NO + "INTEGER NOT NULL, " +
                PERIOD_COLUMN_STARTTIME + " TIME NOT NULL, " +
                PERIOD_COLUMN_ENDTIME + " TIME NOT NULL "
        );
        sqLiteDatabase.close();
    }

    /**
     * create weekday table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createWeekdayTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_WEEKDAY + "(" +
                WEEKDAY_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                WEEKDAY_COLUMN_SCHEDULE_ID + " INTEGER NOT NULL, " +
                WEEKDAY_COLUMN_NAME + " VARCHAR NOT NULL "
        );
        sqLiteDatabase.close();
    }

    /**
     * create schedule table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createScheduleTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SCHEDULE + "(" +
                SCHEDULE_COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                SCHEDULE_COLUMN_NAME + " VARCHAR NOT NULL "
        );
        sqLiteDatabase.close();
    }
    //endregion

    /**
     * method to build a SQLite query to get a row in a specific table at a specific id from schoolPlaner database
     *
     * @param table         name of the table to get the row from as String
     * @param tableColumnID name of the id column in the given table as String
     * @param id            id of the row to get
     * @return a SQLite query  as String
     */
    private String buildQueryToGetRowAtId(String table, String tableColumnID, int id) {
        return "SELECT * " +
                "FROM " + table +
                " WHERE " + tableColumnID +
                " = " + id;
    }
    //endregion

}
