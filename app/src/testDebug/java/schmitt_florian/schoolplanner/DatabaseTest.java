package schmitt_florian.schoolplanner;


import android.test.mock.MockContext;

import org.junit.Test;

import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;
import schmitt_florian.schoolplanner.logic.Subject;
import schmitt_florian.schoolplanner.logic.Teacher;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {
    private DatabaseHelperImpl databaseHelper = new DatabaseHelperImpl(new MockContext());

    @Test
    public void addGetSubject0() throws Exception {
        Subject testExpectedValue = new Subject(0,
                new Teacher(0, "Braeuer", "BRAE", 'm'),
                "informatics",
                "B103"
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
                new Teacher(0, "Dickens", "DIC", 'm'),
                "german",
                "C301"
        );

        int id = databaseHelper.insertIntoDB(testExpectedValue);

        Subject testResultValue = databaseHelper.getSubjectAtId(id);
        databaseHelper.resetDatabase();

        assertTrue(testExpectedValue.equals(testResultValue));

    }
}
