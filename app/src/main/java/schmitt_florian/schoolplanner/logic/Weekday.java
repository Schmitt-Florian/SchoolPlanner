package schmitt_florian.schoolplanner.logic;

import java.util.Arrays;

/**
 * The Weekday Class represents an Object in the Weekday SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Weekday {
    /**
     * numeric id of the Weekday (unique)
     */
    private int id;

    /**
     * name of the Weekday as string e.g "Monday"
     */
    private String name;

    /**
     * {@link Period}s on that day, sorted by the schoolHourNo
     */
    private Period[] periods;

    /**
     * standard c'tor for Weekday class
     *
     * @param id      unique numeric id of the Weekday
     * @param name    name of the Weekday as string e.g "Monday"
     * @param periods {@link Period} on that day, sorted by the schoolHourNo
     */
    public Weekday(int id, String name, Period[] periods) {
        this.id = id;
        this.name = name;
        Arrays.sort(periods);
        this.periods = periods;
    }

    /**
     * gets id of the Weekday
     *
     * @return unique numeric id of the Weekday
     */
    public int getId() {
        return id;
    }

    /**
     * gets name of the Weekday
     *
     * @return name of the Weekday as string e.g "Monday"
     */
    public String getName() {
        return name;
    }

    /**
     * gets {@link Period} on that day
     *
     * @return Period on that day, sorted by the schoolHourNo, as array
     */
    public Period[] getPeriods() {
        return periods;
    }
}
