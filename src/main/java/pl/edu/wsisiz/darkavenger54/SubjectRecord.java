package pl.edu.wsisiz.darkavenger54;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a record of subjects.
 * Provides methods to manage and search subjects.
 */
public class SubjectRecord
{
    /** The list of subjects in the record. */
    @Expose
    private List<Subject> subjects;

    /**
     * Constructs an empty SubjectRecord.
     */
    public SubjectRecord()
    {
        subjects = new ArrayList<>();
    }

    /**
     * Returns the list of subjects in the record.
     *
     * @return the list of subjects
     */
    public List<Subject> getSubjects()
    {
        return subjects;
    }

    /**
     * Adds a subject to the record.
     *
     * @param subject the subject to add
     */
    public void addSubject(Subject subject)
    {
        subjects.add(subject);
    }

    /**
     * Finds a subject by its name.
     *
     * @param subjectName the name of the subject to find
     * @return the subject with the given name, or {@code null} if not found
     */
    public Subject findSubjectBySubjectName(String subjectName)
    {
        return subjects.parallelStream()
                .filter(s -> s.getSubjectName().equals(subjectName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes a subject from the record and unregisters it from all students.
     *
     * @param subject the subject to remove
     */
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
