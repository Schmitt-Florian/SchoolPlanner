package schmitt_florian.schoolplanner.logic.objects;

/**
 * The Subject Class represents an Object in the Subject SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Subject {
    /**
     * the default color for a subject in {@link schmitt_florian.schoolplanner.gui.ScheduleFragment}.
     * Similar to the default color for android {@link android.widget.Button}s
     */
    public static final String DEFAULT_COLOR = "#e0e0e0";

    /**
     * numeric id of the subject (unique)
     */
    private final int id;

    /**
     * the {@link Teacher} that teaches this subject
     */
    private final Teacher teacher;

    /**
     * name of the subject as string e.g "Math"
     */
    private final String name;

    /**
     * number/code of the room as String e.g "B201"
     */
    private final String room;

    /**
     * color of subject as Hex with '#' e.g "#ffffff"
     */
    private final String color;

    /**
     * standard c'tor for Subject class
     *
     * @param id      unique numeric id of the subject
     * @param teacher the {@link Teacher} that teaches this subject
     * @param name    name of the subject as string e.g "Math"
     * @param room    number/code of the room as String e.g "B201"
     * @param color   color of subject as Hex with '#' e.g "#ffffff"
     */
    public Subject(int id, Teacher teacher, String name, String room, String color) {
        this.id = id;
        this.teacher = teacher;
        this.name = name;
        this.room = room;
        this.color = color;
    }

    /**
     * gets id of the subject
     *
     * @return unique numeric id of the subject
     */
    public int getId() {
        return id;
    }

    /**
     * gets  the {@link Teacher} that teaches this subject
     *
     * @return the teacher that teaches this subject
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * gets name of the subject
     *
     * @return name of the subject as string e.g "Math"
     */
    public String getName() {
        return name;
    }

    /**
     * gets number/code of the room
     *
     * @return number/code of the room as String e.g "B201"
     */
    public String getRoom() {
        return room;
    }

    /**
     * get color of subject as Hex with '#' e.g "#ffffff"
     *
     * @return Hex String
     */
    public String getColor() {
        return color;
    }

    /**
     * method to indicate if one Subject matches another one by the values of their fields
     *
     * @param otherSubject the other Subject
     * @return true if all fields are the same in both Subjects, else false
     */
    public boolean match(Subject otherSubject) {
        return this.id == otherSubject.id && this.teacher.match(otherSubject.teacher) &&
                this.name.equals(otherSubject.name)
                && this.room.equals(otherSubject.room)
                && this.color.equals(otherSubject.color);
    }

    /**
     * builds a string from Subject's values
     *
     * @return Subject as String
     */
    @Override
    public String toString() {
        return "---Subject--- \n" +
                "Id: \t" + id + "\n" +
                teacher.toString() + "\n" +
                "Name: \t" + name + "\n" +
                "Room: \t" + room + "\n" +
                "Color: \t#" + color + "\n" +
                "---####---";
    }
}
