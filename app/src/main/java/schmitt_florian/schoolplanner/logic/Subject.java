package schmitt_florian.schoolplanner.logic;

/**
 * The Subject Class represents an Object in the Subject SQL table and is usually returned by methods from the DatabaseHelper Interface
 */
public class Subject {
    /**
     * numeric id of the subject (unique)
     */
    private int id;

    /**
     * the {@link Teacher} that teaches this subject
     */
    private Teacher teacher;

    /**
     * name of the subject as string e.g "Math"
     */
    private String name;

    /**
     * number/code of the room as String e.g "B201"
     */
    private String room;

    /**
     * standard c'tor for Subject class
     *
     * @param id      unique numeric id of the subject
     * @param teacher the {@link Teacher} that teaches this subject
     * @param name    name of the subject as string e.g "Math"
     * @param room    number/code of the room as String e.g "B201"
     */
    public Subject(int id, Teacher teacher, String name, String room) {
        this.id = id;
        this.teacher = teacher;
        this.name = name;
        this.room = room;
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
}
