package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    private String GroupID;
    private String specialization;
    private String description;
    private List<Student> students;

    public Group(String GroupID, String specialization, String description)
    {
        this.GroupID = GroupID;
        this.specialization = specialization;
        this.description = description;
        students = new ArrayList<Student>();
    }
    public void addStudent(Student student)
    {
        students.add(student);
    }

    public String getGroupID()
    {
        return GroupID;
    }

    public String getDescription()
    {
        return description;
    }

    public String getSpecialization()
    {
        return specialization;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setGroupID(String groupID)
    {
        GroupID = groupID;
    }
    public void removeStudent(Student student)
    {
        students.remove(student);
    }
}
