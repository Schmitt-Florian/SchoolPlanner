package schmitt_florian.schoolplanner.logic;


import android.support.annotation.Nullable;

import java.util.GregorianCalendar;

/**
 * The Exam Class represents an Object in the Exam SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Exam {
    /**
     * numeric id of the exam (unique)
     */
    private int id; //test
    /**
     * {@link Subject} the exam is in
     */
    private Subject subject;
    /**
     * description/details of the exam, can be null if not available
     */
    private String description;
    /**
     * due date / deadline for the exam, can be null if not available
     */
    private GregorianCalendar deadline;

    /**
     * standard c'tor for Exam class
     *
     * @param id          numeric id of the exam (unique)
     * @param subject     {@link Subject} the exam is in
     * @param description description/details of the exam, can be null if not available
     * @param deadline    due date / deadline for the exam, can be null if not available
     */
    public Exam(int id, Subject subject, @Nullable String description, @Nullable GregorianCalendar deadline) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * gets id of the exam
     *
     * @return unique numeric id of the exam
     */
    public int getId() {
        return id;
    }

    /**
     * gets {@link Subject} the exam is in
     *
     * @return Subject the exam is in
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * gets description/details of the exam as String
     *
     * @return description/details of the exam, returns null if not available
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets deadline for the exam as String
     *
     * @return deadline for the exam, returns null if not available
     */
    public GregorianCalendar getDeadline() {
        return deadline;
    }
}
