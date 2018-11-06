package schmitt_florian.schoolplanner.logic.objects;


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
    private final int id;

    /**
     * the school hour number the period is, e.g. '1' for the first school hour
     */
    private final int schoolHourNo;

    /**
     * the time the period starts as GregorianCalendar
     */
    private final GregorianCalendar startTime;

    /**
     * the time the period ends as GregorianCalendar
     */
    private final GregorianCalendar endTime;

    //region c'tors

    /**
     * standard c'tor for Period class
     *
     * @param id           numeric id of the period (unique)
     * @param schoolHourNo the school hour number the period is, e.g. '1' for the first school hour
     * @param startTime    the time the period starts as GregorianCalendar
     * @param endTime      the time the period ends as GregorianCalendar
     */
    public Period(int id, int schoolHourNo, GregorianCalendar startTime, GregorianCalendar endTime) {
        this.id = id;
        this.schoolHourNo = schoolHourNo;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * constructs a Period like the standard c'tor but start and end times given as Strings,
     * note, that if this c'tor is used the Year, month, dayOfMonth fields in the GregorianCalendars will be initialized with 0 value
     *
     * @param id           numeric id of the period (unique)
     * @param schoolHourNo the school hour number the period is, e.g. '1' for the first school hour
     * @param startTime    the time the period starts as String separated by '-' to look like HH-MM-SS
     * @param endTime      the time the period ends as String separated by '-' to look like HH-MM-SS
     */
    public Period(int id, int schoolHourNo, String startTime, String endTime) {
        this.id = id;
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

    public String getStartTimeAsString() {
        return startTime.get(Calendar.HOUR_OF_DAY) + "-" + startTime.get(Calendar.MINUTE) + "-" + startTime.get(Calendar.SECOND);
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

    public String getEndTimeAsString() {
        return endTime.get(Calendar.HOUR_OF_DAY) + "-" + endTime.get(Calendar.MINUTE) + "-" + endTime.get(Calendar.SECOND);
    }

    /**
     * method to indicate if one Period matches another one by the values of their fields
     *
     * @param otherPeriod the other Period
     * @return true if all fields are the same in both Periods, else false
     */
    public boolean match(Period otherPeriod) {
        return this.id == otherPeriod.id && this.schoolHourNo == otherPeriod.schoolHourNo &&
                this.startTime.equals(otherPeriod.startTime) && this.endTime.equals(otherPeriod.endTime)
                ;
    }

    /**
     * builds a string from Period's values
     *
     * @return Period as String
     */
    @Override
    public String toString() {
        return "---Period--- \n" +
                "Id: \t" + id + "\n" +
                "SchoolHourNo: \t" + schoolHourNo + "\n" +
                "StartTime: \t" + startTime.get(Calendar.HOUR_OF_DAY) + "-" + startTime.get(Calendar.MINUTE) + "-" + startTime.get(Calendar.SECOND) + "\n" +
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
        return Integer.compare(this.schoolHourNo, period.schoolHourNo);
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
