package schmitt_florian.schoolplanner.logic;

import android.support.annotation.NonNull;

/**
 * The Lesson Class represents an Object in the Period SQL table and is usually returned by methods from the DatabaseHelper Interface
 * <br><br/>
 * Note: this class has a natural ordering that is inconsistent with equals
 */
public class Lesson implements Comparable<Lesson> {
    /**
     * numeric id of the lesson (unique)
     */
    private int id;

    /**
     * the {@link Subject} taught in this Lesson
     */
    private Subject subject;

    /**
     * the {@link Period} the Lesson is taught in
     */
    private Period period;

    /**
     * standard c'tor for Lesson class
     *
     * @param id      numeric id of the lesson (unique)
     * @param subject the {@link Subject} taught in this Lesson
     * @param period  the {@link Period} the Lesson is taught in
     */
    public Lesson(int id, Subject subject, Period period) {
        this.id = id;
        this.subject = subject;
        this.period = period;
    }

    /**
     * gets the id of the lesson
     *
     * @return numeric id of the lesson (unique)
     */
    public int getId() {
        return id;
    }

    /**
     * gets the {@link Subject} taught in this Lesson
     *
     * @return the {@link Subject} taught in this Lesson
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * gets the {@link Period} the Lesson is taught in
     *
     * @return the {@link Period} the Lesson is taught in
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * method to indicate if one Lesson matches another one by the values of their fields
     *
     * @param otherLesson the other Lesson
     * @return true if all fields are the same in both Lessons, else false
     */
    public boolean match(Lesson otherLesson) {
        return this.id == otherLesson.id && this.subject.match(otherLesson.subject) &&
                this.period.match(otherLesson.period)
                ;
    }

    /**
     * method to indicate if one Lesson[] matches another one by the values of their fields
     *
     * @param lessons      one Lesson[]
     * @param otherLessons the other Lesson[]
     * @return true if all fields are the same in both Lesson[]s, else false
     */
    public static boolean match(Lesson[] lessons, Lesson[] otherLessons) {
        if (lessons.length != otherLessons.length) {
            return false;
        }

        for (int i = 0; i < lessons.length; i++) {
            if (!lessons[i].match(otherLessons[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * builds a string from Lesson's values
     *
     * @return Lesson as String
     */
    @Override
    public String toString() {
        return "---Period--- \n" +
                "Id: \t" + id + "\n" +
                subject.toString() + "\n" +
                period.toString() + "\n" +
                "---####---"
                ;
    }

    /**
     * compares the schoolHourNo's of this and the given period, it can be used to sort a Lesson[] by the schoolHourNo's of their {@link Period}s,
     * e.g. by using the Arrays.sort(Lesson[] lessons) method to get a sorted Lesson[]
     *
     * @param lesson the period to compare to
     * @return -1,0,1 if the schoolHourNo of the {@link Period} of this lesson is less, equal, greater then the schoolHourNo of the {@link Period} of the given lesson
     */
    @Override
    public int compareTo(@NonNull Lesson lesson) {
        return this.period.compareTo(lesson.period);
    }
}
