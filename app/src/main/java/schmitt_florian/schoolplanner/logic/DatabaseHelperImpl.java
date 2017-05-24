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


    //region handling Methods
    //region getObjectAtId

    /**
     * gets the {@link Subject} at a specific id from database.
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Subject}, or null if not existing
     */
    @Override
    public Subject getSubjectAtId(int id) {
        try {
            return getSubjectAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Subject", context);
            return null;
        }
    }

    /**
     * gets the {@link Teacher} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Teacher}, or null if not existing
     */
    @Override
    public Teacher getTeacherAtId(int id) {
        try {
            return getTeacherAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Teacher", context);
            return null;
        }
    }

    /**
     * gets the {@link Homework} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Homework}, or null if not existing
     */
    @Override
    public Homework getHomeworkAtId(int id) {
        try {
            return getHomeworkAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Homework", context);
            return null;
        }
    }

    /**
     * gets the {@link Exam} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Exam}, or null if not existing
     */
    @Override
    public Exam getExamAtId(int id) {
        try {
            return getExamAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Exam", context);
            return null;
        }
    }

    /**
     * gets the {@link Grade} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}, or null if not existing
     */
    @Override
    public Grade getGradeAtId(int id) {
        try {
            return getGradeAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Grade", context);
            return null;
        }
    }

    /**
     * gets the {@link Period} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}, or null if not existing
     */
    @Override
    public Period getPeriodAtId(int id) {
        try {
            return getPeriodAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Period", context);
            return null;
        }
    }

    /**
     * gets the {@link Lesson} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Lesson}, or null if not existing
     */
    @Override
    public Lesson getLessonAtId(int id) {
        try {
            return getLessonAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Lesson", context);
            return null;
        }
    }

    /**
     * gets the {@link Weekday} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}, or null if not existing
     */
    @Override
    public Weekday getWeekdayAtId(int id) {
        try {
            return getWeekdayAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Weekday", context);
            return null;
        }
    }

    /**
     * gets the {@link Schedule} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}, or null if not existing
     */
    @Override
    public Schedule getScheduleAtId(int id) {
        try {
            return getScheduleAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForGettingANotExistingObject("Schedule", context);
            return null;
        }
    }
    //endregion

    //region updateObjectAtId

    /**
     * updates {@link Subject} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newSubject the new {@link Subject}
     */
    @Override
    public void updateSubjectAtId(Subject newSubject) {
        try {
            updateSubjectAtIdOrThrow(newSubject);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Subject", context);
        }
    }

    /**
     * updates {@link Teacher} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newTeacher the new {@link Teacher}
     */
    @Override
    public void updateTeacherAtId(Teacher newTeacher) {
        try {
            updateTeacherAtIdOrThrow(newTeacher);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Teacher", context);
        }
    }

    /**
     * updates {@link Homework} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newHomework the new {@link Homework}
     */
    @Override
    public void updateHomeworkAtId(Homework newHomework) {
        try {
            updateHomeworkAtIdOrThrow(newHomework);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Homework", context);
        }
    }

    /**
     * updates {@link Exam} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newExam the new {@link Exam}
     */
    @Override
    public void updateExamAtId(Exam newExam) {
        try {
            updateExamAtIdOrThrow(newExam);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Exam", context);
        }
    }

    /**
     * updates {@link Grade} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newGrade the new {@link Grade}
     */
    @Override
    public void updateGradeAtId(Grade newGrade) {
        try {
            updateGradeAtIdOrThrow(newGrade);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Grade", context);
        }
    }

    /**
     * updates {@link Period} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newPeriod the new {@link Period}
     */
    @Override
    public void updatePeriodAtId(Period newPeriod) {
        try {
            updatePeriodAtIdOrThrow(newPeriod);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Period", context);
        }
    }

    /**
     * updates {@link Lesson} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newLesson the new {@link Lesson}
     */
    @Override
    public void updateLessonAtId(Lesson newLesson) {
        try {
            updateLessonAtIdOrThrow(newLesson);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Lesson", context);
        }
    }

    /**
     * updates {@link Weekday} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newWeekday the new {@link Weekday}
     */
    @Override
    public void updateWeekdayAtId(Weekday newWeekday) {
        try {
            updateWeekdayAtIdOrThrow(newWeekday);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Weekday", context);
        }
    }

    /**
     * updates {@link Schedule} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newSchedule the new {@link Schedule}
     */
    @Override
    public void updateScheduleAtId(Schedule newSchedule) {
        try {
            updateScheduleAtIdOrThrow(newSchedule);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject("Schedule", context);
        }
    }
    //endregion

    //region insertIntoDB

    /**
     * inserts {@link Subject} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param subject {@link Subject} to be inserted
     * @return the id in the database the {@link Subject} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Subject subject) {
        try {
            return insertIntoDBOrThrow(subject);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(subject, context);
            return -1;
        }
    }

    /**
     * inserts {@link Teacher} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param teacher {@link Teacher} to be inserted
     * @return the id in the database the {@link Teacher} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Teacher teacher) {
        try {
            return insertIntoDBOrThrow(teacher);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(teacher, context);
            return -1;
        }
    }

    /**
     * inserts {@link Homework} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param homework {@link Homework} to be inserted
     * @return the id in the database the {@link Homework} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Homework homework) {
        try {
            return insertIntoDBOrThrow(homework);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(homework, context);
            return -1;
        }
    }

    /**
     * inserts {@link Exam} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param exam {@link Exam} to be inserted
     * @return the id in the database the {@link Exam} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Exam exam) {
        try {
            return insertIntoDBOrThrow(exam);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(exam, context);
            return -1;
        }
    }

    /**
     * inserts {@link Grade} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param grade {@link Grade} to be inserted
     * @return the id in the database the {@link Grade} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Grade grade) {
        try {
            return insertIntoDBOrThrow(grade);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(grade, context);
            return -1;
        }
    }

    /**
     * inserts {@link Period} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param period {@link Period} to be inserted
     * @return the id in the database the {@link Period} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Period period) {
        try {
            return insertIntoDBOrThrow(period);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(period, context);
            return -1;
        }
    }

    /**
     * inserts {@link Lesson} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param lesson {@link Lesson} to be inserted
     * @return the id in the database the {@link Lesson} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Lesson lesson) {
        try {
            return insertIntoDBOrThrow(lesson);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(lesson, context);
            return -1;
        }
    }

    /**
     * inserts {@link Weekday} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param weekday {@link Weekday} to be inserted
     * @return the id in the database the {@link Weekday} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Weekday weekday) {
        try {
            return insertIntoDBOrThrow(weekday);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(weekday, context);
            return -1;
        }
    }

    /**
     * inserts {@link Schedule} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param schedule {@link Schedule} to be inserted
     * @return the id in the database the {@link Schedule} was inserted or -1 if action could not be performed
     */
    @Override
    public int insertIntoDB(Schedule schedule) {
        try {
            return insertIntoDBOrThrow(schedule);
        } catch (IllegalAccessException e) {
            ExceptionHandler.handleDatabaseExceptionForAddingAAlreadyExistingObject(schedule, context);
            return -1;
        }
    }
    //endregion

    //region deleteObjectAtId

    /**
     * deletes the {@link Subject} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Subject} to delete has
     */
    @Override
    public void deleteSubjectAtId(int id) {
        try {
            deleteSubjectAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Teacher} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Teacher} to delete has
     */
    @Override
    public void deleteTeacherAtId(int id) {
        try {
            deleteTeacherAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Homework} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Homework} to delete has
     */
    @Override
    public void deleteHomeworkAtId(int id) {
        try {
            deleteHomeworkAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Exam} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Exam} to delete has
     */
    @Override
    public void deleteExamAtId(int id) {
        try {
            deleteExamAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Grade} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Grade} to delete has
     */
    @Override
    public void deleteGradeAtId(int id) {
        try {
            deleteGradeAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Period} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Period} to delete has
     */
    @Override
    public void deletePeriodAtId(int id) {
        try {
            deletePeriodAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Lesson} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Lesson} to delete has
     */
    @Override
    public void deleteLessonAtId(int id) {
        try {
            deleteLessonAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Weekday} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Weekday} to delete has
     */
    @Override
    public void deleteWeekdayAtId(int id) {
        try {
            deleteWeekdayAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    /**
     * deletes the {@link Schedule} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Schedule} to delete has
     */
    @Override
    public void deleteScheduleAtId(int id) {
        try {
            deleteScheduleAtIdOrThrow(id);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }
    //endregion
    //endregion

    //region orThrow Methods
    //region getObjectAtId

    /**
     * gets the {@link Subject} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Subject}
     * @throws NoSuchFieldException if there is no {@link Subject} at the given id in the Database
     */
    @Override
    public Subject getSubjectAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_SUBJECT, SUBJECT_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Subject(
                    cursor.getInt(0),
                    getTeacherAtIdOrThrow(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Teacher} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Teacher}
     * @throws NoSuchFieldException if there is no {@link Teacher} at the given id in the Database
     */
    @Override
    public Teacher getTeacherAtIdOrThrow(int id) throws NoSuchFieldException {
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
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Homework} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Homework}
     * @throws NoSuchFieldException if there is no {@link Homework} at the given id in the Database
     */
    @Override
    public Homework getHomeworkAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_HOMEWORK, HOMEWORK_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            if (cursor.getInt(4) == 0) {
                return new Homework(
                        cursor.getInt(0),
                        getSubjectAtIdOrThrow(cursor.getInt(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        false
                );
            } else {
                return new Homework(
                        cursor.getInt(0),
                        getSubjectAtIdOrThrow(cursor.getInt(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        true
                );
            }

        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Exam} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Exam}
     * @throws NoSuchFieldException if there is no {@link Exam} at the given id in the Database
     */
    @Override
    public Exam getExamAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_EXAM, EXAM_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Exam(
                    cursor.getInt(0),
                    getSubjectAtIdOrThrow(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Grade} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}
     * @throws NoSuchFieldException if there is no {@link Grade} at the given id in the Database
     */
    @Override
    public Grade getGradeAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_GRADE, GRADE_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Grade(
                    cursor.getInt(0),
                    getSubjectAtIdOrThrow(cursor.getInt(1)),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Period} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}
     * @throws NoSuchFieldException if there is no {@link Period} at the given id in the Database
     */
    @Override
    public Period getPeriodAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_PERIOD, PERIOD_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Period(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Lesson} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Lesson}
     * @throws NoSuchFieldException if there is no {@link Lesson} at the given id in the Database
     */
    @Override
    public Lesson getLessonAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_LESSON, LESSON_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Lesson(
                    cursor.getInt(0),
                    getSubjectAtIdOrThrow(cursor.getInt(1)),
                    getPeriodAtIdOrThrow(cursor.getInt(2))
            );
        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Weekday} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}
     * @throws NoSuchFieldException if there is no {@link Weekday} at the given id in the Database
     */
    @Override
    public Weekday getWeekdayAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String query = buildQueryToGetRowAtId(TABLE_WEEKDAY, WEEKDAY_COLUMN_ID, id);

        try {
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            return new Weekday(
                    cursor.getInt(0),
                    cursor.getString(2),
                    getLessonsAtWeekday(id)
            );
        } catch (Exception e) {
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    /**
     * gets the {@link Schedule} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}
     * @throws NoSuchFieldException if there is no {@link Schedule} at the given id in the Database
     */
    @Override
    public Schedule getScheduleAtIdOrThrow(int id) throws NoSuchFieldException {
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
            throw new NoSuchFieldException();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    //endregion

    //region updateObjectAtId

    /**
     * updates {@link Subject} at the given id in database
     *
     * @param newSubject the new {@link Subject}
     * @throws NoSuchFieldException if there is no {@link Subject} at the given id in the Database
     */
    @Override
    public void updateSubjectAtIdOrThrow(Subject newSubject) throws NoSuchFieldException {
        deleteSubjectAtIdOrThrow(newSubject.getId());
        try {
            insertIntoDBOrThrow(newSubject);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Teacher} at the given id in database
     *
     * @param newTeacher the new {@link Teacher}
     * @throws NoSuchFieldException if there is no {@link Teacher} at the given id in the Database
     */
    @Override
    public void updateTeacherAtIdOrThrow(Teacher newTeacher) throws NoSuchFieldException {
        deleteTeacherAtIdOrThrow(newTeacher.getId());
        try {
            insertIntoDBOrThrow(newTeacher);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Homework} at the given id in database
     *
     * @param newHomework the new {@link Homework}
     * @throws NoSuchFieldException if there is no {@link Homework} at the given id in the Database
     */
    @Override
    public void updateHomeworkAtIdOrThrow(Homework newHomework) throws NoSuchFieldException {
        deleteHomeworkAtIdOrThrow(newHomework.getId());
        try {
            insertIntoDBOrThrow(newHomework);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Exam} at the given id in database
     *
     * @param newExam the new {@link Exam}
     * @throws NoSuchFieldException if there is no {@link Exam} at the given id in the Database
     */
    @Override
    public void updateExamAtIdOrThrow(Exam newExam) throws NoSuchFieldException {
        deleteExamAtIdOrThrow(newExam.getId());
        try {
            insertIntoDBOrThrow(newExam);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Grade} at the given id in database
     *
     * @param newGrade the new {@link Grade}
     * @throws NoSuchFieldException if there is no {@link Grade} at the given id in the Database
     */
    @Override
    public void updateGradeAtIdOrThrow(Grade newGrade) throws NoSuchFieldException {
        deleteGradeAtIdOrThrow(newGrade.getId());
        try {
            insertIntoDBOrThrow(newGrade);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Period} at the given id in database
     *
     * @param newPeriod the new {@link Period}
     * @throws NoSuchFieldException if there is no {@link Period} at the given id in the Database
     */
    @Override
    public void updatePeriodAtIdOrThrow(Period newPeriod) throws NoSuchFieldException {
        deletePeriodAtIdOrThrow(newPeriod.getId());
        try {
            insertIntoDBOrThrow(newPeriod);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Lesson} at the given id in database
     *
     * @param newLesson the new {@link Lesson}
     * @throws NoSuchFieldException if there is no {@link Lesson} at the given id in the Database
     */
    @Override
    public void updateLessonAtIdOrThrow(Lesson newLesson) throws NoSuchFieldException {
        deleteLessonAtIdOrThrow(newLesson.getId());
        try {
            insertIntoDBOrThrow(newLesson);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Weekday} at the given id in database
     *
     * @param newWeekday the new {@link Weekday}
     * @throws NoSuchFieldException if there is no {@link Weekday} at the given id in the Database
     */
    @Override
    public void updateWeekdayAtIdOrThrow(Weekday newWeekday) throws NoSuchFieldException {
        deleteWeekdayAtIdOrThrow(newWeekday.getId());
        try {
            insertIntoDBOrThrow(newWeekday);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }

    /**
     * updates {@link Schedule} at the given id in database
     *
     * @param newSchedule the new {@link Schedule}
     * @throws NoSuchFieldException if there is no {@link Schedule} at the given id in the Database
     */
    @Override
    public void updateScheduleAtIdOrThrow(Schedule newSchedule) throws NoSuchFieldException {
        deleteScheduleAtIdOrThrow(newSchedule.getId());
        try {
            insertIntoDBOrThrow(newSchedule);
        } catch (IllegalAccessException e) {
            //never reach this point
        }
    }
    //endregion

    //region insertIntoDB

    /**
     * inserts {@link Subject} into database at a given id, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param subject {@link Subject} to be inserted
     * @return the id in the database the {@link Subject} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Subject subject) throws IllegalAccessException {
        try {
            getTeacherAtIdOrThrow(subject.getTeacher().getId());
        } catch (NoSuchFieldException e) {
            insertIntoDBOrThrow(subject.getTeacher());
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (subject.getId() <= 0) {
            query = "INSERT INTO " + TABLE_SUBJECT + " VALUES ( " + getNewID(TABLE_SUBJECT, SUBJECT_COLUMN_ID) + ", " + subject.getTeacher().getId() + ", \"" + subject.getName() + "\", \"" + subject.getRoom() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_SUBJECT + " VALUES ( " + subject.getId() + ", " + subject.getTeacher().getId() + ", \"" + subject.getName() + "\", \"" + subject.getRoom() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return subject.getId();
    }

    /**
     * inserts {@link Teacher} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param teacher {@link Teacher} to be inserted
     * @return the id in the database the {@link Teacher} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Teacher teacher) throws IllegalAccessException {
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (teacher.getId() <= 0) {
            query = "INSERT INTO " + TABLE_TEACHER + " VALUES ( " + getNewID(TABLE_TEACHER, TEACHER_COLUMN_ID) + ", \"" + teacher.getName() + "\", \"" + teacher.getAbbreviation() + "\", \"" + teacher.getGender() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_TEACHER + " VALUES ( " + teacher.getId() + ", \"" + teacher.getName() + "\", \"" + teacher.getAbbreviation() + "\", \"" + teacher.getGender() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return teacher.getId();
    }

    /**
     * inserts {@link Homework} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param homework {@link Homework} to be inserted
     * @return the id in the database the {@link Homework} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Homework homework) throws IllegalAccessException {
        try {
            getSubjectAtIdOrThrow(homework.getSubject().getId());
        } catch (NoSuchFieldException e) {
            insertIntoDBOrThrow(homework.getSubject());
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (homework.getId() <= 0) {
            query = "INSERT INTO " + TABLE_HOMEWORK + " VALUES ( " + getNewID(TABLE_HOMEWORK, HOMEWORK_COLUMN_ID) + ", " + homework.getSubject().getId() + ", \"" + homework.getDescription() + "\", \"" + homework.getDeadlineAsString() + "\", " + homework.getDone() + ")";
        } else {
            query = "INSERT INTO " + TABLE_HOMEWORK + " VALUES ( " + homework.getId() + ", " + homework.getSubject().getId() + ", \"" + homework.getDescription() + "\", \"" + homework.getDeadlineAsString() + "\", " + homework.getDone() + ")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }

        return homework.getId();
    }

    /**
     * inserts {@link Exam} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param exam {@link Exam} to be inserted
     * @return the id in the database the {@link Exam} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Exam exam) throws IllegalAccessException {
        try {
            getSubjectAtIdOrThrow(exam.getSubject().getId());
        } catch (NoSuchFieldException e) {
            insertIntoDBOrThrow(exam.getSubject());
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (exam.getId() <= 0) {
            query = "INSERT INTO " + TABLE_EXAM + " VALUES ( " + getNewID(TABLE_EXAM, EXAM_COLUMN_ID) + ", " + exam.getSubject().getId() + ", \"" + exam.getDescription() + "\", \"" + exam.getDeadlineAsString() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_EXAM + " VALUES ( " + exam.getId() + ", " + exam.getSubject().getId() + ", \"" + exam.getDescription() + "\", \"" + exam.getDeadlineAsString() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return exam.getId();
    }

    /**
     * inserts {@link Grade} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param grade {@link Grade} to be inserted
     * @return the id in the database the {@link Grade} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Grade grade) throws IllegalAccessException {
        try {
            getSubjectAtIdOrThrow(grade.getSubject().getId());
        } catch (NoSuchFieldException e) {
            insertIntoDBOrThrow(grade.getSubject());
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (grade.getId() <= 0) {
            query = "INSERT INTO " + TABLE_GRADE + " VALUES ( " + getNewID(TABLE_GRADE, GRADE_COLUMN_ID) + ", " + grade.getSubject().getId() + ", \"" + grade.getName() + "\", \"" + grade.getGrade() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_GRADE + " VALUES ( " + grade.getId() + ", " + grade.getSubject().getId() + ", \"" + grade.getName() + "\", \"" + grade.getGrade() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return grade.getId();
    }

    /**
     * inserts {@link Period} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param period {@link Period} to be inserted
     * @return the id in the database the {@link Period} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Period period) throws IllegalAccessException {
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (period.getId() <= 0) {
            query = "INSERT INTO " + TABLE_PERIOD + " VALUES ( " + getNewID(TABLE_PERIOD, PERIOD_COLUMN_ID) + ", " + period.getSchoolHourNo() + ", \"" + period.getStartTimeAsString() + "\", \"" + period.getEndTimeAsString() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_PERIOD + " VALUES ( " + period.getId() + ", " + period.getSchoolHourNo() + ", \"" + period.getStartTimeAsString() + "\", \"" + period.getEndTimeAsString() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return period.getId();
    }

    /**
     * inserts {@link Lesson} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param lesson {@link Lesson} to be inserted
     * @return the id in the database the {@link Lesson} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Lesson lesson) throws IllegalAccessException {
        try {
            getSubjectAtIdOrThrow(lesson.getSubject().getId());
        } catch (NoSuchFieldException e) {
            insertIntoDBOrThrow(lesson.getSubject());
        }

        try {
            getPeriodAtIdOrThrow(lesson.getPeriod().getId());
        } catch (NoSuchFieldException e) {
            insertIntoDBOrThrow(lesson.getPeriod());
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (lesson.getId() <= 0) {
            query = "INSERT INTO " + TABLE_LESSON + " VALUES ( " + getNewID(TABLE_LESSON, LESSON_COLUMN_ID) + ", " + lesson.getSubject().getId() + ", " + lesson.getPeriod().getId() + ", NULL)";
        } else {
            query = "INSERT INTO " + TABLE_LESSON + " VALUES ( " + lesson.getId() + ", " + lesson.getSubject().getId() + ", " + lesson.getPeriod().getId() + ", NULL)";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return lesson.getId();
    }

    /**
     * inserts {@link Weekday} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param weekday {@link Weekday} to be inserted
     * @return the id in the database the {@link Weekday} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Weekday weekday) throws IllegalAccessException {
        for (int i = 0; i < weekday.getLessons().length; i++) {
            if (weekday.getLessons()[i] != null) {
                try {
                    if ((getLessonAtIdOrThrow(weekday.getLessons()[i].getId()).match(weekday.getLessons()[i]))) {
                        updateLessonWeekdayIdAtId(weekday.getLessons()[i].getId(), weekday.getId());
                    }
                } catch (NoSuchFieldException e) {
                    insertIntoDBOrThrow(weekday.getLessons()[i]);
                    updateLessonWeekdayIdAtId(weekday.getLessons()[i].getId(), weekday.getId());
                }
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (weekday.getId() <= 0) {
            query = "INSERT INTO " + TABLE_WEEKDAY + " VALUES ( " + getNewID(TABLE_WEEKDAY, WEEKDAY_COLUMN_ID) + ",NULL, \"" + weekday.getName() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_WEEKDAY + " VALUES ( " + weekday.getId() + ",NULL, \"" + weekday.getName() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return weekday.getId();
    }

    /**
     * inserts {@link Schedule} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param schedule {@link Schedule} to be inserted
     * @return the id in the database the {@link Schedule} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    @Override
    public int insertIntoDBOrThrow(Schedule schedule) throws IllegalAccessException {
        for (int i = 0; i < schedule.getDays().length; i++) {
            if (schedule.getDays()[i] != null) {
                try {
                    if ((getWeekdayAtIdOrThrow(schedule.getDays()[i].getId()).match(schedule.getDays()[i]))) {
                        updateWeekdayScheduleIdAtId(schedule.getDays()[i].getId(), schedule.getId());
                    }
                } catch (NoSuchFieldException e) {
                    insertIntoDBOrThrow(schedule.getDays()[i]);
                    updateWeekdayScheduleIdAtId(schedule.getDays()[i].getId(), schedule.getId());
                }
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if (schedule.getId() <= 0) {
            query = "INSERT INTO " + TABLE_SCHEDULE + " VALUES ( " + getNewID(TABLE_SCHEDULE, SCHEDULE_COLUMN_ID) + ", \"" + schedule.getName() + "\")";
        } else {
            query = "INSERT INTO " + TABLE_SCHEDULE + " VALUES ( " + schedule.getId() + ", \"" + schedule.getName() + "\")";
        }

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new IllegalAccessException();
        }
        return schedule.getId();
    }
    //endregion

    //region deleteObjectAtId

    /**
     * deletes the {@link Subject} at the given id from database
     *
     * @param id the id the {@link Subject} to delete has
     * @throws NoSuchFieldException if there is no {@link Subject} at the given id in the Database
     */
    @Override
    public void deleteSubjectAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_SUBJECT + " WHERE " + SUBJECT_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Teacher} at the given id from database
     *
     * @param id the id the {@link Teacher} to delete has
     * @throws NoSuchFieldException if there is no {@link Teacher} at the given id in the Database
     */
    @Override
    public void deleteTeacherAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_TEACHER + " WHERE " + TEACHER_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Homework} at the given id from database
     *
     * @param id the id the {@link Homework} to delete has
     * @throws NoSuchFieldException if there is no {@link Homework} at the given id in the Database
     */
    @Override
    public void deleteHomeworkAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_HOMEWORK + " WHERE " + HOMEWORK_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Exam} at the given id from database
     *
     * @param id the id the {@link Exam} to delete has
     * @throws NoSuchFieldException if there is no {@link Exam} at the given id in the Database
     */
    @Override
    public void deleteExamAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_EXAM + " WHERE " + EXAM_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Grade} at the given id from database
     *
     * @param id the id the {@link Grade} to delete has
     * @throws NoSuchFieldException if there is no {@link Grade} at the given id in the Database
     */
    @Override
    public void deleteGradeAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_GRADE + " WHERE " + GRADE_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Period} at the given id from database
     *
     * @param id the id the {@link Period} to delete has
     * @throws NoSuchFieldException if there is no {@link Period} at the given id in the Database
     */
    @Override
    public void deletePeriodAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_PERIOD + " WHERE " + PERIOD_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Lesson} at the given id from database
     *
     * @param id the id the {@link Lesson} to delete has
     * @throws NoSuchFieldException if there is no {@link Lesson} at the given id in the Database
     */
    @Override
    public void deleteLessonAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_LESSON + " WHERE " + LESSON_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Weekday} at the given id from database
     *
     * @param id the id the {@link Weekday} to delete has
     * @throws NoSuchFieldException if there is no {@link Weekday} at the given id in the Database
     */
    @Override
    public void deleteWeekdayAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_WEEKDAY + " WHERE " + WEEKDAY_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            throw new NoSuchFieldException();
        }
    }

    /**
     * deletes the {@link Schedule} at the given id from database
     *
     * @param id the id the {@link Schedule} to delete has
     * @throws NoSuchFieldException if there is no {@link Schedule} at the given id in the Database
     */
    @Override
    public void deleteScheduleAtIdOrThrow(int id) throws NoSuchFieldException {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_SCHEDULE + " WHERE " + SCHEDULE_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForDeletingAnNotExistingObject(id, context);
        }
    }

    //endregion
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
                toString(TABLE_LESSON) + "\n \n" +
                toString(TABLE_WEEKDAY) + "\n \n" +
                toString(TABLE_SCHEDULE);
    }

    /**
     * represents the given Table from the database as String
     *
     * @param tableName name of the table to convert tzo String, choose from the TABLE_XXX constants in {@link DatabaseHelper}
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
     * returns the size of the given Table in the Database
     *
     * @param tableName name of the table, choose from the TABLE_XXX constants in {@link DatabaseHelper}
     * @return the size of the table. 0 if table has no elements, 1 if table has one element and so on
     */
    @Override
    public int size(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT COUNT(*) " + "FROM " + tableName;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int size = cursor.getInt(0);

        cursor.close();
        db.close();

        return size;
    }

    /**
     * returns all indices of the given Table in the Database
     *
     * @param tableName name of the table, choose from the TABLE_XXX constants in {@link DatabaseHelper}
     * @return all indices of the given Table in the Database as array
     */
    @Override
    public int[] getIndices(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * " + "FROM " + tableName;

        ArrayList<Integer> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }

        cursor.close();
        db.close();


        int[] returningArray = new int[arrayList.size()];
        for (int i = 0; i < returningArray.length; i++) {
            returningArray[i] = arrayList.get(i);
        }

        return returningArray;
    }

    /**
     * resets the database, onUpgrade can also called instead
     */
    public void resetDatabase() {
        onUpgrade(this.getWritableDatabase(), 1, 1);
    }

    //todo remove
    public void fillDatabaseWithExamples() {
        Teacher teacher1 = new Teacher(1, "Bräuer", "BRÄ", Teacher.MALE);
        Teacher teacher2 = new Teacher(2, "Dickens", "DICK", Teacher.FEMALE);

        Subject subject1 = new Subject(1, teacher1, "Math", "B213");
        Subject subject2 = new Subject(2, teacher2, "German", "B308");

        Exam exam1 = new Exam(1, subject1, "A simple Test in Math", "2017-06-13");
        Exam exam2 = new Exam(2, subject2, "German Test", "2017-05-03");

        Grade grade1 = new Grade(1, subject1, "2nd test", "13");
        Grade grade2 = new Grade(2, subject2, "3rd test", "4");
        Grade grade3 = new Grade(3, subject1, "5th test", "14");
        Grade grade4 = new Grade(4, subject2, "4th test", "6");

        Homework homework1 = new Homework(1, subject1, "Geometry - draw a rectangle", "2017-05-06", false);
        Homework homework2 = new Homework(2, subject2, "Characterisation Goethe", "2017-06-03", false);
        Homework homework3 = new Homework(3, subject1, "The calculation of probabilities", "2017-05-07", true);
        Homework homework4 = new Homework(4, subject2, "Literature during WW2", "2017-05-05", false);

        Period period1 = new Period(1, 1, "07-45-00", "08-30-00");
        Period period2 = new Period(2, 2, "08-35-00", "09-20-00");
        Period period3 = new Period(3, 3, "09-35-00", "10-20-00");
        Period period4 = new Period(4, 4, "10-25-00", "11-25-00");

        Lesson lesson1 = new Lesson(1, subject1, period1);
        Lesson lesson2 = new Lesson(2, subject2, period2);
        Lesson lesson3 = new Lesson(3, subject1, period3);
        Lesson lesson4 = new Lesson(4, subject2, period4);

        Weekday weekday1 = new Weekday(1, "Monday", new Lesson[]{lesson1, lesson2, lesson3, lesson4});

        Schedule schedule1 = new Schedule(1, "a", new Weekday[]{weekday1, null, null, null, null, null});

        //###################################Insert###############################################

        insertIntoDB(teacher1);
        insertIntoDB(teacher2);

        insertIntoDB(subject1);
        insertIntoDB(subject2);

        insertIntoDB(exam1);
        insertIntoDB(exam2);

        insertIntoDB(grade1);
        insertIntoDB(grade2);
        insertIntoDB(grade3);
        insertIntoDB(grade4);

        insertIntoDB(homework1);
        insertIntoDB(homework2);
        insertIntoDB(homework3);
        insertIntoDB(homework4);

        insertIntoDB(period1);
        insertIntoDB(period2);
        insertIntoDB(period3);
        insertIntoDB(period4);

        insertIntoDB(lesson1);
        insertIntoDB(lesson2);
        insertIntoDB(lesson3);
        insertIntoDB(lesson4);

        insertIntoDB(weekday1);

        insertIntoDB(schedule1);

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
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_LESSON);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_WEEKDAY);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SCHEDULE);
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
        createLessonTable(sqLiteDatabase);
        createWeekdayTable(sqLiteDatabase);
        createScheduleTable(sqLiteDatabase);
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
                HOMEWORK_COLUMN_DEADLINE + " DATE, " +
                HOMEWORK_COLUMN_DONE + "INTEGER )"
        );
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
    }

    /**
     * create period table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createPeriodTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERIOD + "(" +
                PERIOD_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                PERIOD_COLUMN_SCHOOL_HOUR_NO + "INTEGER NOT NULL, " +
                PERIOD_COLUMN_STARTTIME + " TIME NOT NULL, " +
                PERIOD_COLUMN_ENDTIME + " TIME NOT NULL )"
        );
    }

    /**
     * create lesson table in the schoolPlanner Database
     *
     * @param sqLiteDatabase the schoolPlanner Database
     */
    private void createLessonTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_LESSON + "(" +
                LESSON_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                LESSON_COLUMN_SUBJECT_ID + "INTEGER NOT NULL, " +
                LESSON_COLUMN_PERIOD_ID + " INTEGER NOT NULL, " +
                LESSON_COLUMN_WEEKDAY_ID + " INTEGER )"
        );
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
    }
    //endregion

    /**
     * returns the highest id in the given Table in the Database + 1
     *
     * @param tableName    name of the table, choose from the TABLE_XXX constants of {@link DatabaseHelper}
     * @param idColumnName name of the column containing the id's, choose from the XXX_COLUMN_ID constants in {@link DatabaseHelper}
     * @return the highest id in the Table + 1
     */
    private int getNewID(String tableName, String idColumnName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(" + idColumnName + ") FROM " + tableName;

        try (Cursor cursor = db.rawQuery(query, null)) {
            cursor.moveToFirst();

            return cursor.getInt(0) + 1;
        }
    }

    /**
     * gets the {@link Lesson}s as array at a specific {@link Weekday}
     *
     * @param weekdayID id of the {@link Weekday}
     * @return Periods at the given Weekday as Array
     * @throws Exception if an error occurs
     */
    private Lesson[] getLessonsAtWeekday(int weekdayID) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = buildQueryToGetRowAtId(TABLE_LESSON, LESSON_COLUMN_WEEKDAY_ID, weekdayID);

        ArrayList<Lesson> lessonArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            lessonArrayList.add(getLessonAtIdOrThrow(cursor.getInt(0)));
        }

        cursor.close();
        db.close();

        return lessonArrayList.toArray(new Lesson[0]);
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

        return weekdayArrayList.toArray(new Weekday[0]);
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
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject(WEEKDAY_COLUMN_SCHEDULE_ID + " in WEEKDAY", context);
        }
    }

    /**
     * updates the LESSON_COLUMN_WEEKDAY_ID column in the TABLE_LESSON with the new value for at a given id
     *
     * @param id        id of the object to update
     * @param weekdayId id of the new schedule
     */
    private void updateLessonWeekdayIdAtId(int id, int weekdayId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_LESSON + " SET " + LESSON_COLUMN_WEEKDAY_ID + " = " + weekdayId + " WHERE " + LESSON_COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (Exception e) {
            ExceptionHandler.handleDatabaseExceptionForUpdatingAnNotExistingObject(WEEKDAY_COLUMN_SCHEDULE_ID + " in WEEKDAY", context);
        }
    }
    //endregion

}
