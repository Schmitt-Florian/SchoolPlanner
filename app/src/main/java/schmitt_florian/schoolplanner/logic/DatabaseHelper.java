package schmitt_florian.schoolplanner.logic;

import android.content.Context;

/**
 * storage for table & column names and other information's for Database,
 * as well as overview for public methods of DatabaseHelperImpl class
 */
@SuppressWarnings({"FieldNever", "unused"})
public interface DatabaseHelper {

    /**
     * Versionnumber of the database as int
     */
    int DATABASE_VERSION = 1;

    /**
     * Name of the database file as String
     */
    String DATABASE_NAME = "SchoolPlaner.db";


    //region tables
    //region subject table
    /**
     * name of the subject table as String
     */
    String TABLE_SUBJECT = "subject";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the subject table as String
     */
    String SUBJECT_COLUMN_ID = "subject_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the teacherID column in the subject table as String
     */
    String SUBJECT_COLUMN_TEACHER_ID = "subject_teacher_id";

    /**
     * varchar, not null
     * <br> </br>
     * name of the name column in the subject table as String
     */
    String SUBJECT_COLUMN_NAME = "subject_name";

    /**
     * varchar, not null
     * <br> </br>
     * name of the room column in the subject table as String
     */
    String SUBJECT_COLUMN_ROOM = "subject_room";
    //endregion

    //region teacher table
    /**
     * name of the teacher table as String
     */
    String TABLE_TEACHER = "teacher";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the teacher table as String
     */
    String TEACHER_COLUMN_ID = "teacher_id";

    /**
     * varchar, not null
     * <br> </br>
     * name of the name column in the teacher table as String
     */
    String TEACHER_COLUMN_NAME = "teacher_name";

    /**
     * varchar, unique
     * <br> </br>
     * name of the abbreviation column in the teacher table as String
     */
    String TEACHER_COLUMN_ABBREVIATION = "teacher_abbreviation";

    /**
     * char, not null
     * <br> </br>
     * 'f' = female , 'm' = male
     * <br> </br>
     * name of the gender column in the teacher table as String
     */
    String TEACHER_COLUMN_GENDER = "teacher_gender";
    //endregion

    //region homework table
    /**
     * name of the homework table as String
     */
    String TABLE_HOMEWORK = "homework";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the homework table as String
     */
    String HOMEWORK_COLUMN_ID = "homework_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the subjectId column in the homework table as String
     */
    String HOMEWORK_COLUMN_SUBJECT_ID = "homework_subject_id";

    /**
     * text, not null
     * <br> </br>
     * name of the description column in the homework table as String
     */
    String HOMEWORK_COLUMN_DESCRIPTION = "homework_description";

    /**
     * date, not null
     * <br> </br>
     * YYYY-MM-DD
     * <br> </br>
     * name of the deadline column in the homework table as String
     */
    String HOMEWORK_COLUMN_DEADLINE = "homework_deadline";

    /**
     * boolean, not null
     * <br> </br>
     * name of the description column in the homework table as String
     */
    String HOMEWORK_COLUMN_DONE = "homework_done";
    //endregion

    //region exam table
    /**
     * name of the exam table as String
     */
    String TABLE_EXAM = "exam";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the exam table as String
     */
    String EXAM_COLUMN_ID = "exam_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the subjectId column in the exam table as String
     */
    String EXAM_COLUMN_SUBJECT_ID = "exam_subject_id";

    /**
     * text, not null
     * <br> </br>
     * name of the description column in the exam table as String
     */
    String EXAM_COLUMN_DESCRIPTION = "exam_description";

    /**
     * date, not null
     * <br> </br>
     * YYYY-MM-DD
     * <br> </br>
     * name of the deadline column in the exam table as String
     */
    String EXAM_COLUMN_DEADLINE = "exam_deadline";
    //endregion

    //region grade table
    /**
     * name of the grade table as String
     */
    String TABLE_GRADE = "grade";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the grade table as String
     */
    String GRADE_COLUMN_ID = "grade_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the subjectId column in the grade table as String
     */
    String GRADE_COLUMN_SUBJECT_ID = "grade_subject_id";

    /**
     * varchar, not null
     * <br> </br>
     * name of the name column in the grade table as String
     */
    String GRADE_COLUMN_NAME = "grade_name";

    /**
     * varchar, not null
     * <br> </br>
     * name of the grade column in the grade table as String
     */
    String GRADE_COLUMN_GRADE = "grade_grade";
    //endregion

    //region period table
    /**
     * name of the period table as String
     */
    String TABLE_PERIOD = "period";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the period table as String
     */
    String PERIOD_COLUMN_ID = "period_id";

    /**
     * integer, not null
     * <br> </br>
     * name of the schoolHourNo column in the period table as String
     */
    String PERIOD_COLUMN_SCHOOL_HOUR_NO = "period_column_school_hour_no";

    /**
     * time, not null
     * <br> </br>
     * HH-MM-SS
     * <br> </br>
     * name of the startTime column in the exam table as String
     */
    String PERIOD_COLUMN_STARTTIME = "period_starttime";

    /**
     * time, not null
     * <br> </br>
     * HH-MM-SS
     * <br> </br>
     * name of the endTime column in the exam table as String
     */
    String PERIOD_COLUMN_ENDTIME = "period_endtime";
    //endregion

    //region lesson table
    String TABLE_LESSON = "lesson";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the lesson table as String
     */
    String LESSON_COLUMN_ID = "lesson_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the subjectId column in the lesson table as String
     */
    String LESSON_COLUMN_SUBJECT_ID = "lesson_column_subject_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the subjectId column in the lesson table as String
     */
    String LESSON_COLUMN_PERIOD_ID = "lesson_column_period_id";

    /**
     * integer, foreign key
     * <br> </br>
     * name of the weekdayId column in the lesson table as String
     */
    String LESSON_COLUMN_WEEKDAY_ID = "lesson_column_weekday_id";
    //endregion

    //region weekday table
    /**
     * name of the weekday table as String
     */
    String TABLE_WEEKDAY = "weekday";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the weekday table as String
     */
    String WEEKDAY_COLUMN_ID = "weekday_id";

    /**
     * integer, foreign key
     * <br> </br>
     * name of the subjectId column in the period table as String
     */
    String WEEKDAY_COLUMN_SCHEDULE_ID = "weekday_schedule_id";

    /**
     * varchar, not null
     * <br> </br>
     * name of the name column in the weekday table as String
     */
    String WEEKDAY_COLUMN_NAME = "weekday_name";
    //endregion

    //region schedule table
    /**
     * name of the schedule table as String
     */
    String TABLE_SCHEDULE = "schedule";


    /**
     * integer, primary key, not null
     * <br> </br>
     * name of the id column in the schedule table as String
     */
    String SCHEDULE_COLUMN_ID = "schedule_id";

    /**
     * varchar, unique, not null
     * <br> </br>
     * name of the name column in the schedule table as String
     */
    String SCHEDULE_COLUMN_NAME = "schedule_name";
    //endregion
    //endregion

    //region handling Methods
    //region getObjectAtId methods

    /**
     * gets the {@link Subject} at a specific id from database.
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Subject}, or null if not existing
     */
    Subject getSubjectAtId(int id);

    /**
     * gets the {@link Teacher} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Teacher}, or null if not existing
     */
    Teacher getTeacherAtId(int id);

    /**
     * gets the {@link Homework} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Homework}, or null if not existing
     */
    Homework getHomeworkAtId(int id);

    /**
     * gets the {@link Exam} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Exam}, or null if not existing
     */
    Exam getExamAtId(int id);

    /**
     * gets the {@link Grade} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}, or null if not existing
     */
    Grade getGradeAtId(int id);

    /**
     * gets the {@link Period} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}, or null if not existing
     */
    Period getPeriodAtId(int id);

    /**
     * gets the {@link Lesson} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Lesson}, or null if not existing
     */
    Lesson getLessonAtId(int id);

    /**
     * gets the {@link Weekday} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}, or null if not existing
     */
    Weekday getWeekdayAtId(int id);

    /**
     * gets the {@link Schedule} at a specific id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForGettingANotExistingObject(String, Context)} to handle exceptions
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}, or null if not existing
     */
    Schedule getScheduleAtId(int id);
    //endregion

    //region updateObjectAtId

    /**
     * updates {@link Subject} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newSubject the new {@link Subject}
     */
    void updateSubjectAtId(Subject newSubject);

    /**
     * updates {@link Teacher} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newTeacher the new {@link Teacher}
     */
    void updateTeacherAtId(Teacher newTeacher);

    /**
     * updates {@link Homework} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newHomework the new {@link Homework}
     */
    void updateHomeworkAtId(Homework newHomework);

    /**
     * updates {@link Exam} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newExam the new {@link Exam}
     */
    void updateExamAtId(Exam newExam);

    /**
     * updates {@link Grade} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newGrade the new {@link Grade}
     */
    void updateGradeAtId(Grade newGrade);

    /**
     * updates {@link Lesson} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newLesson the new {@link Lesson}
     */
    void updateLessonAtId(Lesson newLesson);

    /**
     * updates {@link Period} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newPeriod the new {@link Period}
     */
    void updatePeriodAtId(Period newPeriod);

    /**
     * updates {@link Weekday} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newWeekday the new {@link Weekday}
     */
    void updateWeekdayAtId(Weekday newWeekday);

    /**
     * updates {@link Schedule} at the given id in database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForUpdatingAnNotExistingObject(String, Context)} to handle exceptions
     *
     * @param newSchedule the new {@link Schedule}
     */
    void updateScheduleAtId(Schedule newSchedule);
    //endregion

    //region insertIntoDB methods

    /**
     * inserts {@link Subject} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param subject {@link Subject} to be inserted
     * @return the id in the database the {@link Subject} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Subject subject);

    /**
     * inserts {@link Teacher} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param teacher {@link Teacher} to be inserted
     * @return the id in the database the {@link Teacher} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Teacher teacher);

    /**
     * inserts {@link Homework} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param homework {@link Homework} to be inserted
     * @return the id in the database the {@link Homework} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Homework homework);

    /**
     * inserts {@link Exam} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param exam {@link Exam} to be inserted
     * @return the id in the database the {@link Exam} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Exam exam);

    /**
     * inserts {@link Grade} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param grade {@link Grade} to be inserted
     * @return the id in the database the {@link Grade} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Grade grade);

    /**
     * inserts {@link Period} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param period {@link Period} to be inserted
     * @return the id in the database the {@link Period} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Period period);

    /**
     * inserts {@link Lesson} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param lesson {@link Lesson} to be inserted
     * @return the id in the database the {@link Lesson} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Lesson lesson);

    /**
     * inserts {@link Weekday} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param weekday {@link Weekday} to be inserted
     * @return the id in the database the {@link Weekday} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Weekday weekday);

    /**
     * inserts {@link Schedule} into database, use an ID <= 0 to insert at next unoccupied ID
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForAddingAAlreadyExistingObject(Object, Context)} to handle exceptions
     *
     * @param schedule {@link Schedule} to be inserted
     * @return the id in the database the {@link Schedule} was inserted or -1 if action could not be performed
     */
    int insertIntoDB(Schedule schedule);
    //endregion

    //region deleteObjectAtId methods

    /**
     * deletes the {@link Subject} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Subject} to delete has
     */
    void deleteSubjectAtId(int id);

    /**
     * deletes the {@link Teacher} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Teacher} to delete has
     */
    void deleteTeacherAtId(int id);

    /**
     * deletes the {@link Homework} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Homework} to delete has
     */
    void deleteHomeworkAtId(int id);

    /**
     * deletes the {@link Exam} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Exam} to delete has
     */
    void deleteExamAtId(int id);

    /**
     * deletes the {@link Grade} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Grade} to delete has
     */
    void deleteGradeAtId(int id);

    /**
     * deletes the {@link Period} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Period} to delete has
     */
    void deletePeriodAtId(int id);

    /**
     * deletes the {@link Lesson} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Lesson} to delete has
     */
    void deleteLessonAtId(int id);

    /**
     * deletes the {@link Weekday} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Weekday} to delete has
     */
    void deleteWeekdayAtId(int id);

    /**
     * deletes the {@link Schedule} at the given id from database
     * <br> </br>
     * Note: Method naturally uses {@link ExceptionHandler#handleDatabaseExceptionForDeletingAnNotExistingObject(int, Context)} to handle exceptions
     *
     * @param id the id the {@link Schedule} to delete has
     */
    void deleteScheduleAtId(int id);
    //endregion
    //endregion

    //region OrThrow Methods
    //region getObjectAtIdOrThrow methods

    /**
     * gets the {@link Subject} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Subject}
     * @throws NoSuchFieldException if there is no {@link Subject} at the given id in the Database
     */
    Subject getSubjectAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Teacher} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Teacher}
     * @throws NoSuchFieldException if there is no {@link Teacher} at the given id in the Database
     */
    Teacher getTeacherAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Homework} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Homework}
     * @throws NoSuchFieldException if there is no {@link Homework} at the given id in the Database
     */
    Homework getHomeworkAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Exam} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Exam}
     * @throws NoSuchFieldException if there is no {@link Exam} at the given id in the Database
     */
    Exam getExamAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Grade} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}
     * @throws NoSuchFieldException if there is no {@link Grade} at the given id in the Database
     */
    Grade getGradeAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Period} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}
     * @throws NoSuchFieldException if there is no {@link Period} at the given id in the Database
     */
    Period getPeriodAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Lesson} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Lesson}
     * @throws NoSuchFieldException if there is no {@link Lesson} at the given id in the Database
     */
    Lesson getLessonAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Weekday} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}
     * @throws NoSuchFieldException if there is no {@link Weekday} at the given id in the Database
     */
    Weekday getWeekdayAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * gets the {@link Schedule} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}
     * @throws NoSuchFieldException if there is no {@link Schedule} at the given id in the Database
     */
    Schedule getScheduleAtIdOrThrow(int id) throws NoSuchFieldException;
    //endregion

    //region updateObjectAtIdOrThrow

    /**
     * updates {@link Subject} at the given id in database
     *
     * @param newSubject the new {@link Subject}
     * @throws NoSuchFieldException if there is no {@link Subject} at the given id in the Database
     */
    void updateSubjectAtIdOrThrow(Subject newSubject) throws NoSuchFieldException;

    /**
     * updates {@link Teacher} at the given id in database
     *
     * @param newTeacher the new {@link Teacher}
     * @throws NoSuchFieldException if there is no {@link Teacher} at the given id in the Database
     */
    void updateTeacherAtIdOrThrow(Teacher newTeacher) throws NoSuchFieldException;

    /**
     * updates {@link Homework} at the given id in database
     *
     * @param newHomework the new {@link Homework}
     * @throws NoSuchFieldException if there is no {@link Homework} at the given id in the Database
     */
    void updateHomeworkAtIdOrThrow(Homework newHomework) throws NoSuchFieldException;

    /**
     * updates {@link Exam} at the given id in database
     *
     * @param newExam the new {@link Exam}
     * @throws NoSuchFieldException if there is no {@link Exam} at the given id in the Database
     */
    void updateExamAtIdOrThrow(Exam newExam) throws NoSuchFieldException;

    /**
     * updates {@link Grade} at the given id in database
     *
     * @param newGrade the new {@link Grade}
     * @throws NoSuchFieldException if there is no {@link Grade} at the given id in the Database
     */
    void updateGradeAtIdOrThrow(Grade newGrade) throws NoSuchFieldException;

    /**
     * updates {@link Period} at the given id in database
     *
     * @param newPeriod the new {@link Period}
     * @throws NoSuchFieldException if there is no {@link Period} at the given id in the Database
     */
    void updatePeriodAtIdOrThrow(Period newPeriod) throws NoSuchFieldException;

    /**
     * updates {@link Lesson} at the given id in database
     *
     * @param newLesson the new {@link Lesson}
     * @throws NoSuchFieldException if there is no {@link Lesson} at the given id in the Database
     */
    void updateLessonAtIdOrThrow(Lesson newLesson) throws NoSuchFieldException;

    /**
     * updates {@link Weekday} at the given id in database
     *
     * @param newWeekday the new {@link Weekday}
     * @throws NoSuchFieldException if there is no {@link Weekday} at the given id in the Database
     */
    void updateWeekdayAtIdOrThrow(Weekday newWeekday) throws NoSuchFieldException;

    /**
     * updates {@link Schedule} at the given id in database
     *
     * @param newSchedule the new {@link Schedule}
     * @throws NoSuchFieldException if there is no {@link Schedule} at the given id in the Database
     */
    void updateScheduleAtIdOrThrow(Schedule newSchedule) throws NoSuchFieldException;
    //endregion

    //region insertIntoDBOrThrow methods

    /**
     * inserts {@link Subject} into database at a given id, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param subject {@link Subject} to be inserted
     * @return the id in the database the {@link Subject} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Subject subject) throws IllegalAccessException;

    /**
     * inserts {@link Teacher} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param teacher {@link Teacher} to be inserted
     * @return the id in the database the {@link Teacher} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Teacher teacher) throws IllegalAccessException;

    /**
     * inserts {@link Homework} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param homework {@link Homework} to be inserted
     * @return the id in the database the {@link Homework} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Homework homework) throws IllegalAccessException;

    /**
     * inserts {@link Exam} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param exam {@link Exam} to be inserted
     * @return the id in the database the {@link Exam} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Exam exam) throws IllegalAccessException;

    /**
     * inserts {@link Grade} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param grade {@link Grade} to be inserted
     * @return the id in the database the {@link Grade} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Grade grade) throws IllegalAccessException;

    /**
     * inserts {@link Period} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param period {@link Period} to be inserted
     * @return the id in the database the {@link Period} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Period period) throws IllegalAccessException;

    /**
     * inserts {@link Lesson} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param lesson {@link Lesson} to be inserted
     * @return the id in the database the {@link Lesson} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Lesson lesson) throws IllegalAccessException;

    /**
     * inserts {@link Weekday} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param weekday {@link Weekday} to be inserted
     * @return the id in the database the {@link Weekday} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Weekday weekday) throws IllegalAccessException;

    /**
     * inserts {@link Schedule} into database, use an ID <= 0 to insert at next unoccupied ID
     *
     * @param schedule {@link Schedule} to be inserted
     * @return the id in the database the {@link Schedule} was inserted
     * @throws IllegalAccessException if the given ID is already occupied
     */
    int insertIntoDBOrThrow(Schedule schedule) throws IllegalAccessException;
    //endregion

    //region deleteObjectAtIdOrThrow methods

    /**
     * deletes the {@link Subject} at the given id from database
     *
     * @param id the id the {@link Subject} to delete has
     * @throws NoSuchFieldException if there is no {@link Subject} at the given id in the Database
     */
    void deleteSubjectAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Teacher} at the given id from database
     *
     * @param id the id the {@link Teacher} to delete has
     * @throws NoSuchFieldException if there is no {@link Teacher} at the given id in the Database
     */
    void deleteTeacherAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Homework} at the given id from database
     *
     * @param id the id the {@link Homework} to delete has
     * @throws NoSuchFieldException if there is no {@link Homework} at the given id in the Database
     */
    void deleteHomeworkAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Exam} at the given id from database
     *
     * @param id the id the {@link Exam} to delete has
     * @throws NoSuchFieldException if there is no {@link Exam} at the given id in the Database
     */
    void deleteExamAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Grade} at the given id from database
     *
     * @param id the id the {@link Grade} to delete has
     * @throws NoSuchFieldException if there is no {@link Grade} at the given id in the Database
     */
    void deleteGradeAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Period} at the given id from database
     *
     * @param id the id the {@link Period} to delete has
     * @throws NoSuchFieldException if there is no {@link Period} at the given id in the Database
     */
    void deletePeriodAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Lesson} at the given id from database
     *
     * @param id the id the {@link Lesson} to delete has
     * @throws NoSuchFieldException if there is no {@link Lesson} at the given id in the Database
     */
    void deleteLessonAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Weekday} at the given id from database
     *
     * @param id the id the {@link Weekday} to delete has
     * @throws NoSuchFieldException if there is no {@link Weekday} at the given id in the Database
     */
    void deleteWeekdayAtIdOrThrow(int id) throws NoSuchFieldException;

    /**
     * deletes the {@link Schedule} at the given id from database
     *
     * @param id the id the {@link Schedule} to delete has
     * @throws NoSuchFieldException if there is no {@link Schedule} at the given id in the Database
     */
    void deleteScheduleAtIdOrThrow(int id) throws NoSuchFieldException;
    //endregion
    //endregion

    /**
     * represents the whole database as String
     *
     * @return database as String
     */
    @Override
    String toString();

    /**
     * represents the given Table from the database as String
     *
     * @param tableName name of the table to convert tzo String, choose from the TABLE_XXX constants of {@link DatabaseHelper}
     * @return database as String
     */
    String toString(String tableName);

    /**
     * returns the size of the given Table in the Database
     *
     * @param tableName name of the table, choose from the TABLE_XXX constants of {@link DatabaseHelper}
     * @return the size of the table. 0 if table has no elements, 0 if table has one element and so on
     */
    int size(String tableName);

    /**
     * returns all indices of the given Table in the Database
     *
     * @param tableName name of the table, choose from the TABLE_XXX constants of {@link DatabaseHelper}
     * @return all indices of the given Table in the Database as array
     */
    int[] getIndices(String tableName);

    /**
     * resets the database
     */
    void resetDatabase();
}
