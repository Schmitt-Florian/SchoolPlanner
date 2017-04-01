package schmitt_florian.schoolplanner.logic;


import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Exam Class represents an Object in the Exam SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Exam {
    /**
     * numeric id of the exam (unique)
     */
    private int id;

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
     * c'tor for Exam class using Sting for deadline field
     *
     * @param id          numeric id of the exam (unique)
     * @param subject     {@link Subject} the exam is in
     * @param description description/details of the exam, can be null if not available
     * @param deadline    due date / deadline for the exam as String in YYYY-MM-DD format, can be null if not available
     */
    public Exam(int id, Subject subject, @Nullable String description, @Nullable String deadline) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        if (deadline != null) {
            this.deadline = convertDateStringToGregorianCalendar(deadline);
        } else {
            this.deadline = null;
        }

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

    /**
     * gets the Deadline as String in YYYY-MM-DD format
     * @return Deadline as String
     */
    public String getDeadlineAsString() {
        return deadline.get(Calendar.YEAR) + "-" + deadline.get(Calendar.MONTH) + "-" + deadline.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * builds a string from Exam's values
     *
     * @return Exam as String
     */
    @Override
    public String toString() {
        return "---Exam--- \n" +
                "Id: \t" + id + "\n" +
                subject.toString() + "\n" +
                "Description: \t" + description + "\n" +
                "Deadline: \t" + deadline.get(Calendar.YEAR) + "-" + deadline.get(Calendar.MONTH) + "-" + deadline.get(Calendar.DAY_OF_MONTH) + "\n" +
                "---####---";
    }

    //region private methods
    /**
     * converts a date-string to a GregorianCalendar
     * @param source date as string in YYYY-MM-DD format
     * @return date as GregorianCalendar
     */
    private GregorianCalendar convertDateStringToGregorianCalendar(String source) {
        String[] date = source.split("-");
        return new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
    }
    //endregion
}
