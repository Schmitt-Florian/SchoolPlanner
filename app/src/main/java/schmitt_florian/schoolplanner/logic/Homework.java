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
     * indicates whether the homework is done or not
     */
    private boolean done;


    /**
     * standard c'tor for Homework class
     *
     * @param id          numeric id of the homework (unique)
     * @param subject     {@link Subject} the homework is for
     * @param description description/details of the homework, can be null if not available
     * @param deadline    due date / deadline for the homework, can be null if not available
     * @param done        indicates whether the homework is done or not
     */
    public Homework(int id, Subject subject, @Nullable String description, @Nullable GregorianCalendar deadline, boolean done) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    /**
     * c'tor for Homework class using Sting for deadline field
     *
     * @param id          numeric id of the homework (unique)
     * @param subject     {@link Subject} the homework is for
     * @param description description/details of the homework, can be null if not available
     * @param deadline    due date / deadline for the homework as String in YYYY-MM-DD format, can be null if not available
     * @param done        indicates whether the homework is done or not
     */
    public Homework(int id, Subject subject, @Nullable String description, @Nullable String deadline, boolean done) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.deadline = convertDateStringToGregorianCalendar(deadline);
        this.done = done;
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
     *
     * @return Deadline as String
     */
    public String getDeadlineAsString() {
        return deadline.get(Calendar.YEAR) + "-" + String.valueOf(deadline.get(Calendar.MONTH) + 1) + "-" + deadline.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * indicates whether the homework is done or not
     *
     * @return true if the homework is done, else false
     */
    public boolean isDone() {
        return done;
    }

    /**
     * gets an int representation of the done-boolean
     *
     * @return 1 if done is true, else 0
     */
    public int getDone() {
        if (done) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * mark a homework is done.
     * <br> </br>
     * Note: this action is irreversible
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * method to indicate if one Homework matches another one by the values of their fields
     *
     * @param otherHomework the other Homework
     * @return true if all fields are the same in both Homework, else false
     */
    public boolean match(Homework otherHomework) {
        return this.id == otherHomework.id && this.subject.match(otherHomework.subject) &&
                this.description.equals(otherHomework.description) &&
                this.deadline.equals(otherHomework.deadline) && this.done == otherHomework.done
                ;
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
                "Deadline: \t" + getDeadlineAsString() + "\n" +
                "Done: \t" + done + "\n" +
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
                Integer.parseInt(date[1]) - 1,
                Integer.parseInt(date[2])
        );
    }
    //endregion
}
