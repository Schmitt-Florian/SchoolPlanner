package schmitt_florian.schoolplanner.logic;


import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Period Class represents an Object in the Period SQL table and is usually returned by methods from the DatabaseHelper Interface
 * <br><br/>
 * Note: this class has a natural ordering that is inconsistent with equals
 */
public class Period implements Comparable<Period> {
    /**
     * numeric id of the period (unique)
     */
    private int id;

    /**
     * the {@link Subject} taught in this period
     */
    private Subject subject;

    /**
     * the {@link Weekday} the Subject is on
     */
    private Weekday weekday;

    /**
     * the school hour number the period is, e.g. '1' for the first school hour
     */
    private int schoolHourNo;

    /**
     * the time the period starts as GregorianCalendar
     */
    private GregorianCalendar startTime;

    /**
     * the time the period ends as GregorianCalendar
     */
    private GregorianCalendar endTime;

    //region c'tors

    /**
     * standard c'tor for Period class
     *
     * @param id           numeric id of the period (unique)
     * @param subject      the {@link Subject} taught in this period
     * @param weekday      the {@link Weekday} the Subject is on
     * @param schoolHourNo the school hour number the period is, e.g. '1' for the first school hour
     * @param startTime    the time the period starts as GregorianCalendar
     * @param endTime      the time the period ends as GregorianCalendar
     */
    public Period(int id, Subject subject, Weekday weekday, int schoolHourNo, GregorianCalendar startTime, GregorianCalendar endTime) {
        this.id = id;
        this.subject = subject;
        this.weekday = weekday;
        this.schoolHourNo = schoolHourNo;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * constructs a Period like the standard c'tor but start and end times given as Strings,
     * note, that if this c'tor is used the Year, month, dayOfMonth fields in the GregorianCalendars will be initialized with 0 value
     *
     * @param id           numeric id of the period (unique)
     * @param subject      the {@link Subject} taught in this period
     * @param weekday      the {@link Weekday} the Subject is on
     * @param schoolHourNo the school hour number the period is, e.g. '1' for the first school hour
     * @param startTime    the time the period starts as String separated by '-' to look like HH-MM-SS
     * @param endTime      the time the period ends as String separated by '-' to look like HH-MM-SS
     */
    public Period(int id, Subject subject, Weekday weekday, int schoolHourNo, String startTime, String endTime) {
        this.id = id;
        this.subject = subject;
        this.weekday = weekday;
        this.schoolHourNo = schoolHourNo;
        this.startTime = convertTimeStringToGregorianCalendar(startTime);
        this.endTime = convertTimeStringToGregorianCalendar(endTime);
    }
    //endregion

    //region getters

    /**
     * gets id of the period
     *
     * @return numeric id of the period (unique)
     */
    public int getId() {
        return id;
    }

    /**
     * gets the {@link Subject} taught in this period
     *
     * @return {@link Subject} taught in this period
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * gets the {@link Weekday} the Subject is on
     *
     * @return the {@link Weekday} the Subject is on
     */
    public Weekday getWeekday() {
        return weekday;
    }

    /**
     * the school hour number the period is
     *
     * @return school hour number the period is, e.g. '1' for the first school hour
     */
    public int getSchoolHourNo() {
        return schoolHourNo;
    }


    /**
     * gets the time the period starts
     *
     * @return the time the period starts as GregorianCalendar
     */
    public GregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * gets the time the period ends
     *
     * @return the time the period ends as GregorianCalendar
     */
    public GregorianCalendar getEndTime() {
        return endTime;
    }
    //endregion

    /**
     * builds a string from Period's values
     *
     * @return Period as String
     */
    @Override
    public String toString() {
        return "---Period--- \n" +
                "Id: \t" + id + "\n" +
                subject.toString() + "\n" +
                weekday.toString() + "\n" +
                "SchoolHourNo: \t" + schoolHourNo + "\n" +
                "StartTime: 	" + startTime.get(Calendar.HOUR_OF_DAY) + "-" + startTime.get(Calendar.MINUTE) + "-" + startTime.get(Calendar.SECOND) + "\n" +
                "EndTime: \t" + endTime.get(Calendar.HOUR_OF_DAY) + "-" + endTime.get(Calendar.MINUTE) + "-" + endTime.get(Calendar.SECOND) + "\n" +
                "---####---";
    }

    /**
     * compares the schoolHourNo's of this and the given period, it can be used to sort a Period[] by the schoolHourNo's,
     * e.g. by using the Arrays.sort(Period[] periods) method to get a sorted Periods[]
     *
     * @param period the period to compare to
     * @return -1,0,1 if the schoolHourNo of this period is less, equal, greater than the schoolHourNo of the given period
     */
    @Override
    public int compareTo(@NonNull Period period) {
        if (this.schoolHourNo < period.schoolHourNo) {
            return -1;
        } else if (this.schoolHourNo == period.schoolHourNo) {
            return 0;
        } else {
            return 1;
        }
    }

    //region private methods

    /**
     * method to transfer time data as Sting to a GregorianCalendar
     *
     * @param source string with time information separated by '-' HH-MM-SS
     * @return GregorianCalendar with time information's. initialized year, month, dayOfMonth with 0
     */
    private GregorianCalendar convertTimeStringToGregorianCalendar(String source) {
        return new GregorianCalendar(
                0,
                0,
                0,
                Integer.parseInt(source.split("-")[0]),
                Integer.parseInt(source.split("-")[1]),
                Integer.parseInt(source.split("-")[2])
        );
    }
    //endregion
}
