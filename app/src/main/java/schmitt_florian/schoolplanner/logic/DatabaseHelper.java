package schmitt_florian.schoolplanner.logic;

/**
 * storage for table & column names and other information's for Database,
 * as well as overview for public methods of DatabaseHelperImpl class
 */
@SuppressWarnings({"FieldNever", "unused"})
interface DatabaseHelper {

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
     * integer, primary key, autoincrement, not null
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
     * integer, primary key, autoincrement, not null
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
     * integer, primary key, autoincrement, not null
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
     * text
     * <br> </br>
     * name of the description column in the homework table as String
     */
    String HOMEWORK_COLUMN_DESCRIPTION = "homework_description";

    /**
     * date
     * <br> </br>
     * YYYY-MM-DD
     * <br> </br>
     * name of the deadline column in the homework table as String
     */
    String HOMEWORK_COLUMN_DEADLINE = "homework_deadline";
    //endregion

    //region exam table
    /**
     * name of the exam table as String
     */
    String TABLE_EXAM = "exam";


    /**
     * integer, primary key, autoincrement, not null
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
     * text
     * <br> </br>
     * name of the description column in the exam table as String
     */
    String EXAM_COLUMN_DESCRIPTION = "exam_description";

    /**
     * date
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
     * integer, primary key, autoincrement, not null
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
     * integer, primary key, autoincrement, not null
     * <br> </br>
     * name of the id column in the period table as String
     */
    String PERIOD_COLUMN_ID = "period_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the subjectId column in the period table as String
     */
    String PERIOD_COLUMN_SUBJECT_ID = "period_column_subject_id";

    /**
     * integer, foreign key, not null
     * <br> </br>
     * name of the weekdayId column in the period table as String
     */
    String PERIOD_COLUMN_WEEKDAY_ID = "period_column_weekday_id";

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

    //region weekday table
    /**
     * name of the weekday table as String
     */
    String TABLE_WEEKDAY = "weekday";


    /**
     * integer, primary key, autoincrement, not null
     * <br> </br>
     * name of the id column in the weekday table as String
     */
    String WEEKDAY_COLUMN_ID = "weekday_id";

    /**
     * integer, foreign key, not null
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
     * integer, primary key, autoincrement, not null
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

    //region getObjectAtId methods

    /**
     * gets the {@link Subject} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Subject}, or null if not existing
     */
    Subject getSubjectAtId(int id);

    /**
     * gets the {@link Teacher} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Teacher}, or null if not existing
     */
    Teacher getTeacherAtId(int id);

    /**
     * gets the {@link Homework} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Homework}, or null if not existing
     */
    Homework getHomeworkAtId(int id);

    /**
     * gets the {@link Exam} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Exam}, or null if not existing
     */
    Exam getExamAtId(int id);

    /**
     * gets the {@link Grade} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Grade}, or null if not existing
     */
    Grade getGradeAtId(int id);

    /**
     * gets the {@link Period} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Period}, or null if not existing
     */
    Period getPeriodAtId(int id);

    /**
     * gets the {@link Weekday} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Weekday}, or null if not existing
     */
    Weekday getWeekdayAtId(int id);

    /**
     * gets the {@link Schedule} at a specific id from database
     *
     * @param id id in database
     * @return row with given id from db as {@link Schedule}, or null if not existing
     */
    Schedule getScheduleAtId(int id);
    //endregion

    //region updateObjectAtId

    /**
     * updates {@link Subject} at the given id in database
     *
     * @param id         the id the {@link Subject} to update has
     * @param newSubject the new {@link Subject}
     */
    void updateSubjectAtId(int id, Subject newSubject);

    /**
     * updates {@link Teacher} at the given id in database
     *
     * @param id         the id the {@link Teacher} to update has
     * @param newTeacher the new {@link Teacher}
     */
    void updateTeacherAtId(int id, Teacher newTeacher);

    /**
     * updates {@link Homework} at the given id in database
     *
     * @param id          the id the {@link Homework} to update has
     * @param newHomework the new {@link Homework}
     */
    void updateHomeworkAtId(int id, Homework newHomework);

    /**
     * updates {@link Exam} at the given id in database
     *
     * @param id      the id the {@link Exam} to update has
     * @param newExam the new {@link Exam}
     */
    void updateExamAtId(int id, Exam newExam);

    /**
     * updates {@link Grade} at the given id in database
     *
     * @param id       the id the {@link Grade} to update has
     * @param newGrade the new {@link Grade}
     */
    void updateGradeAtId(int id, Grade newGrade);

    /**
     * updates {@link Period} at the given id in database
     *
     * @param id        the id the {@link Period} to update has
     * @param newPeriod the new {@link Period}
     */
    void updatePeriodAtId(int id, Period newPeriod);

    /**
     * updates {@link Weekday} at the given id in database
     *
     * @param id         the id the {@link Weekday} to update has
     * @param newWeekday the new {@link Weekday}
     */
    void updateWeekdayAtId(int id, Weekday newWeekday);

    /**
     * updates {@link Schedule} at the given id in database
     *
     * @param id          the id the {@link Schedule} to update has
     * @param newSchedule the new {@link Schedule}
     */
    void updateScheduleAtId(int id, Schedule newSchedule);
    //endregion

    //region insertIntoDB methods

    /**
     * inserts {@link Subject} into database
     *
     * @param subject {@link Subject} to be inserted
     */
    void insertIntoDB(Subject subject);

    /**
     * inserts {@link Teacher} into database
     *
     * @param teacher {@link Teacher} to be inserted
     */
    void insertIntoDB(Teacher teacher);

    /**
     * inserts {@link Homework} into database
     *
     * @param homework {@link Homework} to be inserted
     */
    void insertIntoDB(Homework homework);

    /**
     * inserts {@link Exam} into database
     *
     * @param exam {@link Exam} to be inserted
     */
    void insertIntoDB(Exam exam);

    /**
     * inserts {@link Grade} into database
     *
     * @param grade {@link Grade} to be inserted
     */
    void insertIntoDB(Grade grade);

    /**
     * inserts {@link Period} into database
     *
     * @param period {@link Period} to be inserted
     */
    void insertIntoDB(Period period);

    /**
     * inserts {@link Weekday} into database
     *
     * @param weekday {@link Weekday} to be inserted
     */
    void insertIntoDB(Weekday weekday);

    /**
     * inserts {@link Schedule} into database
     *
     * @param schedule {@link Schedule} to be inserted
     */
    void insertIntoDB(Schedule schedule);
//endregion

    //region deleteObjectAtId methods

    /**
     * deletes the {@link Subject} at the given id from database
     *
     * @param id the id the {@link Subject} to delete has
     */
    void deleteSubjectAtId(int id);

    /**
     * deletes the {@link Teacher} at the given id from database
     *
     * @param id the id the {@link Teacher} to delete has
     */
    void deleteTeacherAtId(int id);

    /**
     * deletes the {@link Homework} at the given id from database
     *
     * @param id the id the {@link Homework} to delete has
     */
    void deleteHomeworkAtId(int id);

    /**
     * deletes the {@link Exam} at the given id from database
     *
     * @param id the id the {@link Exam} to delete has
     */
    void deleteExamAtId(int id);

    /**
     * deletes the {@link Grade} at the given id from database
     *
     * @param id the id the {@link Grade} to delete has
     */
    void deleteGradeAtId(int id);

    /**
     * deletes the {@link Period} at the given id from database
     *
     * @param id the id the {@link Period} to delete has
     */
    void deletePeriodAtId(int id);

    /**
     * deletes the {@link Weekday} at the given id from database
     *
     * @param id the id the {@link Weekday} to delete has
     */
    void deleteWeekdayAtId(int id);

    /**
     * deletes the {@link Schedule} at the given id from database
     *
     * @param id the id the {@link Schedule} to delete has
     */
    void deleteScheduleAtId(int id);
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
}
