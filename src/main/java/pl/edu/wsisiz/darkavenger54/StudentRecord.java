package pl.edu.wsisiz.darkavenger54;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * Represents a record of students.
 * Provides methods for managing and searching students in the system.
 */
public class StudentRecord
{

    /** The list of students in the record. */
    @Expose
    private List<Student> students;

    /**
     * Constructs an empty StudentRecord.
     */
    public StudentRecord()
    {
        students = new ArrayList<Student>();
    }

    /**
     * Returns the list of all students in the record.
     *
     * @return the list of students
     */
    public List<Student> getStudents()
    {
        return students;
    }

    /**
     * Sets the list of students in the record.
     *
     * @param students the new list of students
     */
    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    /**
     * Adds a student to the record.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes a student from the record.
     *
     * @param student the student to remove
     */
    public void removeStudent(Student student)
    {
        students.remove(student);
    }

    /**
     * Finds a student by their album number (case-insensitive).
     *
     * @param album the album number to search for
     * @return the matching student, or {@code null} if not found
     */

    public Student findStudentByAlbum(String album)
    {
        return students.parallelStream()
                .filter(s -> s.getAlbumNumber().equalsIgnoreCase(album.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Performs the given action for each student in the record.
     *
     * @param action the action to perform on each student
     */
    public void forEachStudent(Consumer<Student> action) {
        students.forEach(action);
    }
}