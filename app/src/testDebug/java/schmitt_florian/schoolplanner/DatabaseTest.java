package schmitt_florian.schoolplanner;


import android.test.mock.MockContext;

import org.junit.Test;

import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Exam;
import schmitt_florian.schoolplanner.logic.Grade;
import schmitt_florian.schoolplanner.logic.Homework;
import schmitt_florian.schoolplanner.logic.Period;
import schmitt_florian.schoolplanner.logic.Schedule;
import schmitt_florian.schoolplanner.logic.Subject;
import schmitt_florian.schoolplanner.logic.Teacher;
import schmitt_florian.schoolplanner.logic.Weekday;

import static org.junit.Assert.assertTrue;

public class DatabaseTest {
    private DatabaseHelperImpl databaseHelper = new DatabaseHelperImpl(new MockContext());

    //Launch test area: subject
    @Test
    public void addGetSubject0() throws Exception {
        Subject testExpectedValue = new Subject(0,
                new Teacher(0, "Braeuer", "BRAE", 'm'),
                "computer science",
                "B106"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Subject testResultValue = databaseHelper.getSubjectAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetSubject1() throws Exception {
        Subject testExpectedValue = new Subject(0,
                new Teacher(0, "Meyer", "MEY", 'f'),
                "maths",
                "A210"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Subject testResultValue = databaseHelper.getSubjectAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetSubject2() throws Exception {
        Subject testExpectedValue = new Subject(0,
                new Teacher(0, "Dickens", "DICK", 'm'),
                "german",
                "C301"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Subject testResultValue = databaseHelper.getSubjectAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: subject

    //Launch test area: teacher
    @Test
    public void addGetTeacher() throws Exception {
        Teacher testExpectedValue = new Teacher(0,
                "Braeuer",
                "BRAE",
                'm'
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Teacher testResultValue = databaseHelper.getTeacherAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetTeacher1() throws Exception {
        Teacher testExpectedValue = new Teacher(0,
                "Meyer",
                "MEY",
                'f'
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Teacher testResultValue = databaseHelper.getTeacherAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetTeacher2() throws Exception {
        Teacher testExpectedValue = new Teacher(0,
                "Dickens",
                "DICK",
                'm'
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Teacher testResultValue = databaseHelper.getTeacherAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: teacher

    //Launch test area: Weekday
    @Test
    public void addGetWeekday() throws Exception {
        Weekday testExpectedValue = new Weekday(0,
                "Monday",
                new Period[6]
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Weekday testResultValue = databaseHelper.getWeekdayAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetWeekday1() throws Exception {
        Weekday testExpectedValue = new Weekday(0,
                "Tuesday",
                new Period[10]
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Weekday testResultValue = databaseHelper.getWeekdayAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetWeekday2() throws Exception {
        Weekday testExpectedValue = new Weekday(0,
                "Friday",
                new Period[5]
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Weekday testResultValue = databaseHelper.getWeekdayAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: Weekday

    //Launch test area: Schedule
    @Test
    public void addGetSchedule() throws Exception {
        Schedule testExpectedValue = new Schedule (0,
                "A Schedule",
                new Weekday[5]
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Schedule testResultValue = databaseHelper.getScheduleAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetSchedule1() throws Exception {
        Schedule testExpectedValue = new Schedule (0,
                "B Schedule",
                new Weekday[3]
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Schedule testResultValue = databaseHelper.getScheduleAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetSchedule2() throws Exception {
        Schedule testExpectedValue = new Schedule (0,
                "c Schedule",
                new Weekday[7]
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Schedule testResultValue = databaseHelper.getScheduleAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: Schedule

    //Launch test area: Period
    @Test
    public void addGetPeriod() throws Exception {
        Period testExpectedValue = new Period (0,
                new Subject(0, new Teacher(0,"Braeuer","BRAE",'m'),"computer science","B106"),
                new Weekday(0,"Monday",new Period[6]),
                1,
                "7.45",
                "8.35"

        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Period testResultValue = databaseHelper.getPeriodAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetPeriod1() throws Exception {
        Period testExpectedValue = new Period (0,
                new Subject(0, new Teacher(0, "Meyer", "MEY", 'f'),"maths","A210"),
                new Weekday(0,"Tuesday",new Period[10]),
                3,
                "9.35",
                "10.20"

        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Period testResultValue = databaseHelper.getPeriodAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetPeriod2() throws Exception {
        Period testExpectedValue = new Period (0,
                new Subject(0, new Teacher(0, "Dickens", "DICK", 'm'),"german","C301"),
                new Weekday(0,"Friday",new Period[5]),
                5,
                "11.25",
                "12.10"

        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Period testResultValue = databaseHelper.getPeriodAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: Period

    //Launch test area: Homework
    @Test
    public void addGetHomework() throws Exception {
        Homework testExpectedValue = new Homework (0,
                new Subject(0, new Teacher(0,"Braeuer","BRAE",'m'),"computer science","B106"),
                "Database commands",
                "03.27.2017"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Homework testResultValue = databaseHelper.getHomeworkAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    @Test
    public void addGetHomework1() throws Exception {
        Homework testExpectedValue = new Homework (0,
                new Subject(0, new Teacher(0, "Meyer", "MEY", 'f'),"maths","A210"),
                "page 298 No.3, linear combination",
                "03.28.2017"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Homework testResultValue = databaseHelper.getHomeworkAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetHomework2() throws Exception {
        Homework testExpectedValue = new Homework (0,
                new Subject(0, new Teacher(0, "Dickens", "DICK", 'm'),"german","C301"),
                "read Buechner's Lenz chapter 1-5",
                "03.31.2017"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Homework testResultValue = databaseHelper.getHomeworkAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: Homework

    //Launch test area: Grade
    @Test
    public void addGetGrade() throws Exception {
        Grade testExpectedValue = new Grade (0,
                new Subject(0, new Teacher(0,"Braeuer","BRAE",'m'),"computer science","B106"),
                "oral mark",
                "13 Points"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Grade testResultValue = databaseHelper.getGradeAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetGrade1() throws Exception {
        Grade testExpectedValue = new Grade (0,
                new Subject(0, new Teacher(0, "Meyer", "MEY", 'f'),"maths","A210"),
                "exam",
                "7 Points"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Grade testResultValue = databaseHelper.getGradeAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetGrade2() throws Exception {
        Grade testExpectedValue = new Grade (0,
                new Subject(0, new Teacher(0, "Dickens", "DICK", 'm'),"german","C301"),
                "presentation",
                "14 Points"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Grade testResultValue = databaseHelper.getGradeAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: Grade

    //Launch test area: Exam
    @Test
    public void addGetExam() throws Exception {
        Exam testExpectedValue = new Exam (0,
                new Subject(0, new Teacher(0,"Braeuer","BRAE",'m'),"computer science","B106"),
                "databases:ERM,RM,SQL-Lite",
                "03.29.2017"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Exam testResultValue = databaseHelper.getExamAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }

    @Test
    public void addGetExam1() throws Exception {
        Exam testExpectedValue = new Exam (0,
                new Subject(0, new Teacher(0, "Meyer", "MEY", 'f'),"maths","A210"),
                "vectors:" +
                        "- linear combination" +
                        "- draw vectors",
                "04.01.2017"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Exam testResultValue = databaseHelper.getExamAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    @Test
    public void addGetExam2() throws Exception {
        Exam testExpectedValue = new Exam (0,
                new Subject(0, new Teacher(0, "Dickens", "DICK", 'm'),"german","C301"),
                "Lenz:" +
                        "- Lenz' feelings" +
                        "- fiction and reality" +
                        "- Lenz' mental disorders",
                "03.28.2017"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Exam testResultValue = databaseHelper.getExamAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
    //End test area: Exam

}