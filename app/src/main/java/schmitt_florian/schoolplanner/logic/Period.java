package schmitt_florian.schoolplanner.logic;


import java.util.GregorianCalendar;

/**
 * The Period Class represents an Object in the Period SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Period {
    /**
     * numeric id of the period (unique)
     */
    private int id;

    /**
     * the {@link Subject} taught in this period
     */
    private Subject subject;

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
     * @param id        numeric id of the period (unique)
     * @param subject   the {@link Subject} taught in this period
     * @param startTime the time the period starts as GregorianCalendar
     * @param endTime   the time the period ends as GregorianCalendar
     */
    public Period(int id, Subject subject, GregorianCalendar startTime, GregorianCalendar endTime) {
        this.id = id;
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * constructs a Period from Id as int and times as Strings,
     * note, that if this c'tor is used the Year, month, dayOfMonth fields in the GregorianCalendars will be initialized with 0 value
     *
     * @param id        numeric id of the period (unique)
     * @param subject   the {@link Subject} taught in this period
     * @param startTime the time the period starts as String separated by '-' to look like HH-MM-SS
     * @param endTime   the time the period ends as String separated by '-' to look like HH-MM-SS
     */
    public Period(int id, Subject subject, String startTime, String endTime) {
        this.id = id;
        this.startTime = getTimeFromString(startTime);
        this.endTime = getTimeFromString(endTime);
    }
    //endregion

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
