package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;

public class SubjectRecord
{
    private List<Subject> subjects;
    public SubjectRecord()
    {
        subjects = new ArrayList<>();
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }
    public void addSubject(Subject subject)
    {
        subjects.add(subject);
    }

    public Subject findSubjectBySubjectName(String subjectName)
    {
        return subjects.stream().filter(s -> s.getSubjectName().equals(subjectName)).findFirst().orElse(null);
    }

    public void removeSubject(Subject subject)
    {
        List<Student> students = subject.getStudents();
        for (Student student : students)
        {
            student.removeSubject(subject);
        }
        subjects.remove(subject);
    }
}
