package schmitt_florian.schoolplanner.logic;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Implementation of DatabaseHelper interface to create and interact with the schoolPlanner SQLite Database.
 */
public class DatabaseHelperImpl extends SQLiteOpenHelper implements DatabaseHelper {

    private Context context = null;

    /**
     * standard c'tor for DatabaseHelperImpl
     *
     * @param context context of the application
     */
    public DatabaseHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * method inherited from SQLiteOpenHelper to create and setup the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
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
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_SUBJECT, SUBJECT_COLUMN_ID, id);

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
     * @return row with given id from db as {@link Teacher}, or null if not existing
     */
    @Override
    public Teacher getTeacherAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_TEACHER, TEACHER_COLUMN_ID, id);

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
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Teacher", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * gets the {@link Homework} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Homework}, or null if not existing
     */
    @Override
    public Homework getHomeworkAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_EXAM, EXAM_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Homework(
                    cursor.getInt(0),
                    getSubjectAtId(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Exam", context);
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
     * @return row with given id from db as {@link Exam}, or null if not existing
     */
    @Override
    public Exam getExamAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_EXAM, EXAM_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Exam(
                    cursor.getInt(0),
                    getSubjectAtId(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Exam", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * gets the {@link Grade} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}, or null if not existing
     */
    @Override
    public Grade getGradeAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_GRADE, GRADE_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Grade(
                    cursor.getInt(0),
                    getSubjectAtId(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Grade", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * gets the {@link Period} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}, or null if not existing
     */
    @Override
    public Period getPeriodAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_PERIOD, PERIOD_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Period(
                    cursor.getInt(0),
                    getSubjectAtId(cursor.getInt(1)),
                    getWeekdayAtId(cursor.getInt(2)),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Period", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * gets the {@link Weekday} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}, or null if not existing
     */
    @Override
    public Weekday getWeekdayAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_WEEKDAY, WEEKDAY_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Weekday(
                    cursor.getInt(0),
                    cursor.getString(2),
                    getPeriodsAtWeekday(id)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Weekday", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return null;
    }

    /**
     * gets the {@link Schedule} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}, or null if not existing
     */
    @Override
    public Schedule getScheduleAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_SCHEDULE, SCHEDULE_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Schedule(
                    cursor.getInt(0),
                    cursor.getString(1),
                    getWeekdaysAtSchedule(id)
            );
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Schedule", context);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
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
     * updates {@link Homework} at the given id in database
     *
     * @param id          the id the {@link Homework} to update has
     * @param newHomework the new {@link Homework}
     */
    @Override
    public void updateHomeworkAtId(int id, Homework newHomework) {

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
     * @return the id in the database the {@link Subject} was inserted
     */
    @Override
    public int insertIntoDB(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_SUBJECT + " VALUES ( " + subject.getId() + ", \"" + subject.getTeacher().getId() + "\", \"" + subject.getName() + "\", \"" + subject.getRoom() + "\")";

        insertIntoDB(subject.getTeacher());
        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(subject, context);
            return -1;
        }
        return subject.getId();

    }

    /**
     * inserts {@link Teacher} into database
     *
     * @param teacher {@link Teacher} to be inserted
     * @return the id in the database the {@link Teacher} was inserted
     */
    @Override
    public int insertIntoDB(Teacher teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_TEACHER + " VALUES ( " + teacher.getId() + ", \"" + teacher.getName() + "\", \"" + teacher.getAbbreviation() + "\", \"" + teacher.getGender() + "\")";

        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(teacher, context);
            return -1;
        }
        return teacher.getId();
    }

    /**
     * inserts {@link Homework} into database
     *
     * @param homework {@link Homework} to be inserted
     * @return the id in the database the {@link Homework} was inserted
     */
    @Override
    public int insertIntoDB(Homework homework) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_HOMEWORK + " VALUES ( " + homework.getId() + ", \"" + homework.getSubject().getId() + "\", \"" + homework.getDescription() + "\", \"" + homework.getDeadlineAsString() + "\")";

        insertIntoDB(homework.getSubject());
        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(homework, context);
            return -1;
        }
        return homework.getId();
    }

    /**
     * inserts {@link Exam} into database
     *
     * @param exam {@link Exam} to be inserted
     * @return the id in the database the {@link Exam} was inserted
     */
    @Override
    public int insertIntoDB(Exam exam) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_EXAM + " VALUES ( " + exam.getId() + ", \"" + exam.getSubject().getId() + "\", \"" + exam.getDescription() + "\", \"" + exam.getDeadlineAsString() + "\")";

        insertIntoDB(exam.getSubject());
        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(exam, context);
            return -1;
        }
        return exam.getId();
    }

    /**
     * inserts {@link Grade} into database
     *
     * @param grade {@link Grade} to be inserted
     * @return the id in the database the {@link Grade} was inserted
     */
    @Override
    public int insertIntoDB(Grade grade) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_GRADE + " VALUES ( " + grade.getId() + ", \"" + grade.getSubject().getId() + "\", \"" + grade.getName() + "\", \"" + grade.getGrade() + "\")";

        insertIntoDB(grade.getSubject());
        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(grade, context);
            return -1;
        }
        return grade.getId();
    }

    /**
     * inserts {@link Period} into database
     *
     * @param period {@link Period} to be inserted
     * @return the id in the database the {@link Grade} was inserted
     */
    @Override
    public int insertIntoDB(Period period) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_PERIOD + " VALUES ( " + period.getId() + ", \"" + period.getSubject().getId() + "\", \"" + period.getWeekday().getId() + "\", \"" + period.getSchoolHourNo() + "\", \"" + period.getStartTimeAsString() + "\", \"" + period.getEndTimeAsString() + "\")";

        insertIntoDB(period.getSubject());
        insertIntoDB(period.getWeekday());
        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(period, context);
            return -1;
        }
        return period.getId();
    }

    /**
     * inserts {@link Weekday} into database
     *
     * @param weekday {@link Weekday} to be inserted
     * @return the id in the database the {@link Weekday} was inserted
     */
    @Override
    public int insertIntoDB(Weekday weekday) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_WEEKDAY + " VALUES ( " + weekday.getId() + ",NULL, \"" + weekday.getName() + "\")";

        for (int i = 0; i < weekday.getPeriods().length; i++) {
            insertIntoDB(weekday.getPeriods()[i]);
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(weekday, context);
            return -1;
        }
        return weekday.getId();
    }

    /**
     * inserts {@link Schedule} into database
     *
     * @param schedule {@link Schedule} to be inserted
     * @return the id in the database the {@link Schedule} was inserted
     */
    @Override
    public int insertIntoDB(Schedule schedule) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO " + TABLE_SCHEDULE + " VALUES ( " + schedule.getId() + ", \"" + schedule.getName() + "\")";

        for (int i = 0; i < schedule.getDays().length; i++) {
            insertIntoDB(schedule.getDays()[i]);
            updateWeekdayScheduleIdAtId(schedule.getDays()[i].getId(), schedule.getId());
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(schedule, context);
            return -1;
        }
        return schedule.getId();
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
     * deletes the {@link Homework} at the given id from database
     *
     * @param id the id the {@link Homework} to delete has
     */
    @Override
    public void deleteHomeworkAtId(int id) {

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


    /**
     * represents the whole database to a String
     *
     * @return database as String
     */
    @Override
    public String toString() {
        return toString(TABLE_SUBJECT) + "\n \n" +
                toString(TABLE_TEACHER) + "\n \n" +
                toString(TABLE_HOMEWORK) + "\n \n" +
                toString(TABLE_EXAM) + "\n \n" +
                toString(TABLE_GRADE) + "\n \n" +
                toString(TABLE_PERIOD) + "\n \n" +
                toString(TABLE_WEEKDAY) + "\n \n" +
                toString(TABLE_SCHEDULE);
    }

    /**
     * represents the given Table from the database as String
     *
     * @param tableName name of the table to convert tzo String, choose from the TABLE_XXX constants of {@link DatabaseHelper}
     * @return database as String
     */
    @Override
    public String toString(String tableName) {
        StringBuilder returnString = new StringBuilder("############################### \n" + tableName + "\n-------------------------------\n");

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableName;


        try (Cursor cursor = db.rawQuery(query, null)) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    returnString.append(cursor.getColumnName(i)).append(": \t").append(cursor.getString(i)).append(" || ");
                }
                returnString.append("\n");
            }
        }
        returnString.append("############################### ");

        return returnString.toString();
    }

    /**
     * resets the database, onUpgrade can also called instead
     */
    public void resetDatabase() {
        onUpgrade(this.getWritableDatabase(), 1, 1);
    }

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


        // sqLiteDatabase.close();
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

        //  sqLiteDatabase.close();
    }

    //region table creation

    /**
     * create subject table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createSubjectTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SUBJECT + "(" +
                SUBJECT_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                SUBJECT_COLUMN_TEACHER_ID + " INTEGER NOT NULL, " +
                SUBJECT_COLUMN_NAME + " VARCHAR NOT NULL, " +
                SUBJECT_COLUMN_ROOM + " VARCHAR NOT NULL )"
        );
        // sqLiteDatabase.close();
    }

    /**
     * create teacher table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createTeacherTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TEACHER + "(" +
                TEACHER_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                TEACHER_COLUMN_NAME + " VARCHAR NOT NULL, " +
                TEACHER_COLUMN_ABBREVIATION + " VARCHAR UNIQUE, " +
                TEACHER_COLUMN_GENDER + " CHAR NOT NULL )"
        );
        //  sqLiteDatabase.close();
    }

    /**
     * create homework table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createHomeworkTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_HOMEWORK + "(" +
                HOMEWORK_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                HOMEWORK_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                HOMEWORK_COLUMN_DESCRIPTION + " TEXT, " +
                HOMEWORK_COLUMN_DEADLINE + " DATE )"
        );
        // sqLiteDatabase.close();
    }

    /**
     * create exam table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createExamTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EXAM + "(" +
                EXAM_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                EXAM_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                EXAM_COLUMN_DESCRIPTION + " TEXT, " +
                EXAM_COLUMN_DEADLINE + " DATE )"
        );
        //  sqLiteDatabase.close();
    }

    /**
     * create grade table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createGradeTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GRADE + "(" +
                GRADE_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                GRADE_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                GRADE_COLUMN_NAME + " VARCHAR NOT NULL, " +
                GRADE_COLUMN_GRADE + " VARCHAR NOT NULL )"
        );
        //   sqLiteDatabase.close();
    }

    /**
     * create period table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createPeriodTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERIOD + "(" +
                PERIOD_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                PERIOD_COLUMN_SUBJECT_ID + " INTEGER NOT NULL, " +
                PERIOD_COLUMN_WEEKDAY_ID + " INTEGER NOT NULL, " +
                PERIOD_COLUMN_SCHOOL_HOUR_NO + "INTEGER NOT NULL, " +
                PERIOD_COLUMN_STARTTIME + " TIME NOT NULL, " +
                PERIOD_COLUMN_ENDTIME + " TIME NOT NULL )"
        );
        //  sqLiteDatabase.close();
    }

    /**
     * create weekday table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createWeekdayTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_WEEKDAY + "(" +
                WEEKDAY_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                WEEKDAY_COLUMN_SCHEDULE_ID + " INTEGER, " +
                WEEKDAY_COLUMN_NAME + " VARCHAR NOT NULL )"
        );
        //   sqLiteDatabase.close();
    }

    /**
     * create schedule table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createScheduleTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SCHEDULE + "(" +
                SCHEDULE_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                SCHEDULE_COLUMN_NAME + " VARCHAR NOT NULL )"
        );
        //  sqLiteDatabase.close();
    }
    //endregion

    /**
     * gets the {@link Period}s as array at a specific {@link Weekday}
     *
     * @param weekdayID id of the {@link Weekday}
     * @return Periods at the given Weekday as Array
     * @throws Exception if an error occurs
     */
    private Period[] getPeriodsAtWeekday(int weekdayID) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = buildQueryToGetRowAtId(TABLE_PERIOD, PERIOD_COLUMN_WEEKDAY_ID, weekdayID);

        ArrayList<Period> periodArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            periodArrayList.add(getPeriodAtId(cursor.getInt(0)));
        }

        cursor.close();
        db.close();

        return (Period[]) periodArrayList.toArray();
    }


    /**
     * gets the {@link Weekday}s as array at a specific {@link Schedule}
     *
     * @param scheduleID id of the {@link Schedule}
     * @return Weekdays at the given schedule as Array
     * @throws Exception if an error occurs
     */
    private Weekday[] getWeekdaysAtSchedule(int scheduleID) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = buildQueryToGetRowAtId(TABLE_WEEKDAY, WEEKDAY_COLUMN_SCHEDULE_ID, scheduleID);

        ArrayList<Weekday> weekdayArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            weekdayArrayList.add(getWeekdayAtId(cursor.getInt(0)));
        }

        cursor.close();
        db.close();

        return (Weekday[]) weekdayArrayList.toArray();
    }

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

    /**
     * updates the WEEKDAY_COLUMN_SCHEDULE_ID column in the TABLE_WEEKDAY with the new value for at a given id
     *
     * @param id         id of the object to update
     * @param scheduleId id of the new schedule
     */
    private void updateWeekdayScheduleIdAtId(int id, int scheduleId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_WEEKDAY + " SET " + WEEKDAY_COLUMN_SCHEDULE_ID + " = " + scheduleId + " WHERE " + WEEKDAY_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnExistingObject(WEEKDAY_COLUMN_SCHEDULE_ID + " in WEEKDAY", context);
        }
    }

    //endregion

}
