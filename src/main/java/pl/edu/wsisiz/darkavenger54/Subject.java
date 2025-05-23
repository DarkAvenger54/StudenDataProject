package pl.edu.wsisiz.darkavenger54;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a subject with a name, topics, grades, and enrolled students.
 */
public class Subject
{
    /** The name of the subject. */
    @Expose
    private String subjectName;

    /** A map of topic names to their maximum possible grades. */
    @Expose
    private Map<String, Integer> subjectTopics;

    /** A nested map of student album numbers to their grades for each topic. */
    private Map<String, Map<String, Integer>> subjectGrades;

    /** A list of students enrolled in this subject. */
    private List<Student> students;

    /** The sum of maximum points across all topics (cannot exceed 100). */
    private int currentPoints;

    public Subject()
    {
        this.students = new ArrayList<>();
    }
    /**
     * Constructs a new subject with the specified name.
     *
     * @param subjectName the name of the subject
     */
    public Subject(String subjectName)
    {
        this.subjectName = subjectName;
        students = new ArrayList<Student>();
        subjectTopics = new HashMap<String, Integer>();
        subjectGrades = new HashMap<>();
    }

    /**
     * Adds a topic to the subject with a maximum grade.
     * The total of all topic points must not exceed 100.
     *
     * @param topic    the name of the topic
     * @param maxGrade the maximum grade for the topic
     */
    public void addTopic(String topic, int maxGrade)
    {
        if (topic != null && !topic.isEmpty() && maxGrade >= 0 && maxGrade + currentPoints <= 100)
        {
            subjectTopics.put(topic, maxGrade);
            currentPoints += maxGrade;
        }
    }

    /**
     * Removes a topic from the subject.
     * Reduces the total possible points accordingly.
     *
     * @param topic the topic to remove
     */
    public void removeTopic(String topic)
    {
        Integer removed = subjectTopics.remove(topic);
        if (removed != null)
        {
            currentPoints -= removed;
        }
    }

    /**
     * Returns the name of the subject.
     *
     * @return the subject name
     */
    public String getSubjectName()
    {
        return subjectName;
    }

    /**
     * Sets the name of the subject.
     *
     * @param subjectName the new subject name
     */
    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    /**
     * Returns the list of students enrolled in the subject.
     *
     * @return the list of students
     */
    public List<Student> getStudents()
    {
        return students;
    }

    /**
     * Adds a student to the subject.
     *
     * @param student the student to add
     */
    public void addStudent(Student student)
    {
        students.add(student);
    }

    /**
     * Removes a student from the subject.
     *
     * @param student the student to remove
     */
    public void removeStudent(Student student)
    {
        students.remove(student);
    }

    /**
     * Returns the map of topics and their maximum grades.
     *
     * @return a map of topic names to maximum grades
     */
    public Map<String, Integer> getSubjectTopics()
    {
        return subjectTopics;
    }

    /**
     * Returns the total sum of maximum points across all topics.
     *
     * @return the current total points
     */
    public int getCurrentPoints()
    {
        return currentPoints;
    }

    /**
     * Sets a grade for a student on a specific topic.
     *
     * @param student the student to grade
     * @param topic   the topic for which the grade is assigned
     * @param grade   the grade value
     */
    public void setGrade(Student student, String topic, int grade)
    {
        if (student == null || !subjectTopics.containsKey(topic)) return;

        subjectGrades.putIfAbsent(student.getAlbumNumber(), new HashMap<>());
        subjectGrades.get(student.getAlbumNumber()).put(topic, grade);
    }
}