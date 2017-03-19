package schmitt_florian.schoolplanner.logic;


import android.support.annotation.Nullable;

/**
 * The Teacher Class represents the an Object Teacher SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Teacher {
    /**
     * numeric id of the teacher (unique)
     */
    private int id;
    /**
     * surname as String
     */
    private String name;
    /**
     * unique abbreviation as String, can be null if not available
     */
    private String abbreviation;
    /**
     * gender as Char,'f' for female and 'm' for male
     */
    private char gender;

    /**
     * standard c'tor for Teacher Class
     *
     * @param id           unique numeric id of the teacher
     * @param name         surname as String
     * @param abbreviation unique abbreviation as String, can be null if not available
     * @param gender       gender as Char, use 'f' for female and 'm' for male
     */
    public Teacher(int id, String name, @Nullable String abbreviation, char gender) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.gender = gender;
    }

    /**
     * gets the teachers id
     *
     * @return returns numeric id of the teacher
     */
    public int getId() {
        return id;
    }

    /**
     * gets teachers surname
     *
     * @return returns surname as String
     */
    public String getName() {
        return name;
    }

    /**
     * gets teachers unique abbreviation as String
     *
     * @return returns abbreviation as String if available, else return Null
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * gets teachers gender as Char
     *
     * @return returns gender as Char, 'f' used for female and 'm' used for male
     */
    public char getGender() {
        return gender;
    }
}
