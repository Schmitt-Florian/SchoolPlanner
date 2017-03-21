package schmitt_florian.schoolplanner.logic;

/**
 * storage for table & column names and other information's for Database,
 * as well as overview for public methods of DatabaseHelperImpl class
 */
interface DatabaseHelper {

    /**
     * Versionnumber of the database as int
     */
    int DATABASE_VERSION = 1;

    /**
     * Name of the database file as String
     */
    String DATABASE_NAME = "SchoolPlaner.db";


    //region classic tables
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
     * varchar, unique, not null
     * <br> </br>
     * name of the name column in the grade table as String
     */
    String GRADE_COLUMN_NAME = "grade_name";

    /**
     * integer, not null
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




}
