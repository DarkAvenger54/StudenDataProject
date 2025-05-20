package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student
{
    private String name;
    private String surname;
    private String albumNumber;
    private Group group;
    private List<Subject> subjects;

    public Student(String name, String surname, String albumNumber)
    {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
        subjects = new ArrayList<Subject>();
    }

    public void addSubject(Subject subject)
    {
        subjects.add(subject);
    }
    public void removeSubject(Subject subject)
    {
        subjects.remove(subject);
    }

    //getters and setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getAlbumNumber()
    {
        return albumNumber;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public void setAlbumNumber(String albumNumber)
    {
        this.albumNumber = albumNumber;
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }

}
