package schmitt_florian.schoolplanner.logic;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Settings class represents all settings in the app. Settings are saved in {@link SharedPreferences}.
 * Use Factory Method {@link Settings#getInstance(Context)} to get current valid {@link Settings}.
 * Use {@link Settings#saveSettings()} to save this {@link Settings}-Object. Note that their is only
 * one {@link Settings}-Object saved in {@link SharedPreferences} at the same time.
 */
public class Settings {
    /**
     * represents DD.MM.YYYY date format
     */
    public static final String DATE_FORMAT_DDMMYYYY = "DD.MM.YYYY";
    /**
     * represents MM.DD.YYYY date format
     */
    public static final String DATE_FORMAT_MMDDYYYY = "MM.DD.YYYY";
    /**
     * represents YYYY.MM.DD date format
     */
    public static final String DATE_FORMAT_YYYYMMDD = "YYYY.MM.DD";

    /**
     * represents HH:MM date format
     */
    public static final String TIME_FORMAT_HHMM = "HH:MM";


    /**
     * the key under which date format is saved to {@link SharedPreferences}
     */
    private static final String DATE_FORMAT = "dateFormat";
    /**
     * the key under which periodsAtDay are saved to {@link SharedPreferences}
     */
    private static final String PERIODS_AT_DAY = "periodsAtDay";


    /**
     * the context of the application
     */
    private Context context;
    /**
     * the date format used in the app. One of {@link Settings#DATE_FORMAT_DDMMYYYY} ,
     * {@link Settings#DATE_FORMAT_MMDDYYYY} , {@link Settings#DATE_FORMAT_YYYYMMDD}
     */
    private String activeDateFormat;

    /**
     * number of periods at each day
     */
    private int periodsAtDay;


    /**
     * Factory method used to create a new {@link Settings} Object with current valid settings
     *
     * @param context the context of the application
     * @return {@link Settings} Object with current valid settings
     */
    public static Settings getInstance(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(context.getApplicationContext().toString(), Context.MODE_PRIVATE);

        return new Settings(context,
                preferences.getString(DATE_FORMAT, DATE_FORMAT_DDMMYYYY),
                preferences.getInt(PERIODS_AT_DAY, 6)
        );
    }

    /**
     * standard c'tor, consider using of {@link Settings#getInstance(Context)} for current app settings
     *
     * @param context          context of application
     * @param activeDateFormat One of {@link Settings#DATE_FORMAT_DDMMYYYY} ,
     *                         {@link Settings#DATE_FORMAT_MMDDYYYY} , {@link Settings#DATE_FORMAT_YYYYMMDD}
     * @param periodsAtDay     number of periods at each day
     */
    public Settings(Context context, String activeDateFormat, int periodsAtDay) {
        this.context = context;
        this.activeDateFormat = activeDateFormat;
        this.periodsAtDay = periodsAtDay;
    }

    /**
     * saves the current {@link Settings} to {@link SharedPreferences}, overwrites old settings
     */
    public void saveSettings() {
        SharedPreferences.Editor preferences = context.getSharedPreferences(context.getApplicationContext().toString(), Context.MODE_PRIVATE).edit();

        preferences.putString(DATE_FORMAT, activeDateFormat);
        preferences.putInt(PERIODS_AT_DAY, periodsAtDay);

        preferences.apply();
    }

    //region getter & setter

    /**
     * gets the date format used in the app
     *
     * @return One of {@link Settings#DATE_FORMAT_DDMMYYYY} ,
     * {@link Settings#DATE_FORMAT_MMDDYYYY} , {@link Settings#DATE_FORMAT_YYYYMMDD}
     */
    public String getActiveDateFormat() {
        return activeDateFormat;
    }

    /**
     * sets the date format used in the app
     *
     * @param activeDateFormat One of {@link Settings#DATE_FORMAT_DDMMYYYY} ,
     *                         {@link Settings#DATE_FORMAT_MMDDYYYY} , {@link Settings#DATE_FORMAT_YYYYMMDD}
     */
    public void setActiveDateFormat(String activeDateFormat) {
        this.activeDateFormat = activeDateFormat;
    }

    /**
     * gets the number of periods at each day
     *
     * @return number of periods at each day
     */
    public int getPeriodsAtDay() {
        return periodsAtDay;
    }

    /**
     * sets the number of periods at each day
     *
     * @param periodsAtDay number of periods at each day
     */
    public void setPeriodsAtDay(int periodsAtDay) {
        this.periodsAtDay = periodsAtDay;
    }
    //endregion


}
