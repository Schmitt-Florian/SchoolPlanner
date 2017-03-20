package schmitt_florian.schoolplanner.logic;


import android.support.annotation.NonNull;

import java.util.GregorianCalendar;

/**
 * The Period Class represents an Object in the Period SQL table and is usually returned by methods from the DatabaseHelper Interface
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
     * @param schoolHourNo the school hour number the period is, e.g. '1' for the first school hour
     * @param startTime    the time the period starts as GregorianCalendar
     * @param endTime      the time the period ends as GregorianCalendar
     */
    public Period(int id, Subject subject, int schoolHourNo, GregorianCalendar startTime, GregorianCalendar endTime) {
        this.id = id;
        this.subject = subject;
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
     * @param schoolHourNo the school hour number the period is, e.g. '1' for the first school hour
     * @param startTime    the time the period starts as String separated by '-' to look like HH-MM-SS
     * @param endTime      the time the period ends as String separated by '-' to look like HH-MM-SS
     */
    public Period(int id, Subject subject, int schoolHourNo, String startTime, String endTime) {
        this.id = id;
        this.subject = subject;
        this.schoolHourNo = schoolHourNo;
        this.startTime = getTimeFromString(startTime);
        this.endTime = getTimeFromString(endTime);
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
     * compares the schoolHourNo's of this and the given period, it can be used to sort a Period[] by the schoolHourNo's,
     * e.g. by using the Arrays.sort(Period[] periods)
     * method to get a sorted Periods[]
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
     * @return GregorianCalendar with time information's. Year, month, dayOfMonth = 0
     */
    private GregorianCalendar getTimeFromString(String source) {
        return new GregorianCalendar(0,
                0,
                0,
                Integer.parseInt(source.split("-")[0]),
                Integer.parseInt(source.split("-")[1]),
                Integer.parseInt(source.split("-")[2])
        );
    }
    //endregion
}
