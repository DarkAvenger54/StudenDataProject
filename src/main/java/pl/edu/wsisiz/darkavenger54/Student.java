package pl.edu.wsisiz.darkavenger54;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with personal data, group membership, and a list of subjects.
 */
public class Student
{
    /** The student's first name. */
    @Expose
    private String name;

    /** The student's surname. */
    @Expose
    private String surname;

    /** The student's album (student ID) number. */
    @Expose
    private String albumNumber;

    /** The group to which the student belongs. */
    private Group group;

    /** A list of subjects the student is enrolled in. */
    private List<Subject> subjects;

    public Student()
    {
        this.subjects = new ArrayList<>();
    }
    /**
     * Constructs a new student with the specified name, surname, and album number.
     *
     * @param name         the student's first name
     * @param surname      the student's surname
     * @param albumNumber  the student's album number
     */
    public Student(String name, String surname, String albumNumber)
    {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
        subjects = new ArrayList<Subject>();
    }

    /**
     * Adds a subject to the student's subject list.
     *
     * @param subject the subject to add
     */
    public void addSubject(Subject subject)
    {
        subjects.add(subject);
    }

    /**
     * Removes a subject from the student's subject list.
     *
     * @param subject the subject to remove
     */
    public void removeSubject(Subject subject)
    {
        subjects.remove(subject);
    }

    /**
     * Returns the student's first name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the student's first name.
     *
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the student's surname.
     *
     * @return the surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Sets the student's surname.
     *
     * @param surname the new surname
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Returns the student's album number.
     *
     * @return the album number
     */
    public String getAlbumNumber()
    {
        return albumNumber;
    }

    /**
     * Sets the student's album number.
     *
     * @param albumNumber the new album number
     */
    public void setAlbumNumber(String albumNumber)
    {
        this.albumNumber = albumNumber;
    }

    /**
     * Returns the group the student belongs to.
     *
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }

    /**
     * Sets the group for the student.
     *
     * @param group the group to assign
     */
    public void setGroup(Group group)
    {
        this.group = group;
    }

    /**
     * Returns the list of subjects the student is enrolled in.
     *
     * @return the list of subjects
     */
    public List<Subject> getSubjects()
    {
        return subjects;
    }
}