package schmitt_florian.schoolplanner.logic;


import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Homework Class represents an Object in the Homework SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Homework {
    /**
     * numeric id of the homework (unique)
     */
    private int id;

    /**
     * {@link Subject} the homework is for
     */
    private Subject subject;

    /**
     * description/details of the homework, can be null if not available
     */
    private String description;

    /**
     * due date / deadline for the homework, can be null if not available
     */
    private GregorianCalendar deadline;


    /**
     * standard c'tor for Homework class
     *
     * @param id          numeric id of the homework (unique)
     * @param subject     {@link Subject} the homework is for
     * @param description description/details of the homework, can be null if not available
     * @param deadline    due date / deadline for the homework, can be null if not available
     */
    public Homework(int id, Subject subject, @Nullable String description, @Nullable GregorianCalendar deadline) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * c'tor for Homework class using Sting for deadline field
     *
     * @param id          numeric id of the homework (unique)
     * @param subject     {@link Subject} the homework is for
     * @param description description/details of the homework, can be null if not available
     * @param deadline    due date / deadline for the homework as String in YYYY-MM-DD format, can be null if not available
     */
    public Homework(int id, Subject subject, @Nullable String description, @Nullable String deadline) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.deadline = convertDateStringToGregorianCalendar(deadline);
    }

    /**
     * gets id of the homework
     *
     * @return unique numeric id of the homework
     */
    public int getId() {
        return id;
    }

    /**
     * gets {@link Subject} the homework is for
     *
     * @return subject the homework is for
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * gets description/details of the homework
     *
     * @return description/details of the homework, returns null if not available
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets deadline for the homework
     *
     * @return deadline for the homework, returns null if not available
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
     * builds a string from Homework's values
     *
     * @return Homework as String
     */
    @Override
    public String toString() {
        return "---Homework--- \n" +
                "Id: \t" + id + "\n" +
                subject.toString() + "\n" +
                "Description: \t" + description + "\n" +
                "Deadline: \t" + deadline.get(Calendar.YEAR) + "-" + deadline.get(Calendar.MONTH) + "-" + deadline.get(Calendar.DAY_OF_MONTH) + "\n" +
                "---####---";
    }

    //region private methods

    /**
     * converts a date-string to a GregorianCalendar
     *
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
