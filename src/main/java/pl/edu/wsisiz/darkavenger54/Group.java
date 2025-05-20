package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student group with a specific ID, specialization, and description.
 * It contains a list of students that belong to the group.
 */
public class Group
{
    /** The unique identifier for the group. */
    private String GroupID;

    /** The specialization of the group. */
    private String specialization;

    /** A textual description of the group. */
    private String description;

    /** A list of students in the group. */
    private List<Student> students;

    /**
     * Constructs a new Group with the given ID, specialization, and description.
     *
     * @param GroupID       the unique ID of the group
     * @param specialization the specialization of the group
     * @param description   a brief description of the group
     */
    public Group(String GroupID, String specialization, String description)
    {
        this.GroupID = GroupID;
        this.specialization = specialization;
        this.description = description;
        students = new ArrayList<Student>();
    }

    /**
     * Adds a student to the group.
     *
     * @param student the student to add
     */
    public void addStudent(Student student)
    {
        students.add(student);
    }

    /**
     * Removes a student from the group.
     *
     * @param student the student to remove
     */
    public void removeStudent(Student student)
    {
        students.remove(student);
    }

    /**
     * Returns the group ID.
     *
     * @return the group ID
     */
    public String getGroupID()
    {
        return GroupID;
    }

    /**
     * Sets the group ID.
     *
     * @param groupID the new group ID
     */
    public void setGroupID(String groupID)
    {
        GroupID = groupID;
    }

    /**
     * Returns the description of the group.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description of the group.
     *
     * @param description the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Returns the specialization of the group.
     *
     * @return the specialization
     */
    public String getSpecialization()
    {
        return specialization;
    }

    /**
     * Sets the specialization of the group.
     *
     * @param specialization the new specialization
     */
    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
    }

    /**
     * Returns the list of students in the group.
     *
     * @return the list of students
     */
    public List<Student> getStudents()
    {
        return students;
    }
}
