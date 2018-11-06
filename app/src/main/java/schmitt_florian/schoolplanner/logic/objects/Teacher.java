package schmitt_florian.schoolplanner.logic.objects;


import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * The Teacher Class represents an Object in the Teacher SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Teacher {
    /**
     * indicating that the teacher is male
     */
    public static final char MALE = 'm';
    /**
     * indicating that the teacher is female
     */
    public static final char FEMALE = 'f';

    /**
     * numeric id of the teacher (unique)
     */
    private final int id;

    /**
     * surname as String
     */
    private final String name;

    /**
     * unique abbreviation as String, can be null if not available
     */
    private final String abbreviation;

    /**
     * gender as Char,{@link #FEMALE} for female and {@link #MALE} for male
     */
    private final char gender;

    /**
     * standard c'tor for Teacher class
     *
     * @param id           unique numeric id of the teacher
     * @param name         surname as String
     * @param abbreviation unique abbreviation as String, can be null if not available
     * @param gender       gender as Char, use {@link #FEMALE} for female and {@link #MALE} for male
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
     * @return numeric id of the teacher
     */
    public int getId() {
        return id;
    }

    /**
     * gets teachers surname
     *
     * @return surname as String
     */
    public String getName() {
        return name;
    }

    /**
     * gets teachers unique abbreviation as String
     *
     * @return abbreviation as String if available, else return Null
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * gets teachers gender as Char
     *
     * @return gender as Char, {@link #FEMALE} is used for female and {@link #MALE} for male
     */
    public char getGender() {
        return gender;
    }

    /**
     * method to indicate if one teacher matches another one by the values of their fields
     *
     * @param otherTeacher the other teacher
     * @return true if all fields are the same in both teachers, else false
     */
    public boolean match(Teacher otherTeacher) {
        return this.id == otherTeacher.id && this.name.equals(otherTeacher.name) &&
                Objects.requireNonNull(this.abbreviation).equals(otherTeacher.abbreviation)
                && this.gender == otherTeacher.gender;
    }

    /**
     * builds a string from Teachers's values
     *
     * @return Teacher as String
     */
    @Override
    public String toString() {
        return "---Teacher--- \n" +
                "Id: \t" + id + "\n" +
                "Name: \t" + name + "\n" +
                "Abbreviation: \t" + abbreviation + "\n" +
                "Gender: \t" + gender + "\n" +
                "---######---";
    }
}
