package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject
{
    private String subjectName;
    private Map<String, Integer> subjectTopics;
    private Map<String, Map<String, Integer>> subjectGrades;
    private List<Student> students;
    private int currentPoints;
    public Subject(String subjectName)
    {
        this.subjectName = subjectName;
        students = new ArrayList<Student>();
        subjectTopics = new HashMap<String, Integer>();
        subjectGrades = new HashMap<>();
    }
    public void addTopic(String topic, int maxGrade)
    {
        if(topic != null && !topic.isEmpty() && maxGrade >= 0 && maxGrade + currentPoints <= 100)
        {
            subjectTopics.put(topic, maxGrade);
            currentPoints += maxGrade;
        }
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public List<Student> getStudents()
    {
        return students;
    }
    public void addStudent(Student student)
    {
        students.add(student);
    }

    public Map<String, Integer> getSubjectTopics()
    {
        return subjectTopics;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    public int getCurrentPoints()
    {
        return currentPoints;
    }
    public void removeTopic(String topic) {
        Integer removed = subjectTopics.remove(topic);
        if (removed != null) {
            currentPoints -= removed;
        }
    }
    public void removeStudent(Student student) {
        students.remove(student);
    }
    public void setGrade(Student student, String topic, int grade) {
        if (student == null || !subjectTopics.containsKey(topic)) return;

        subjectGrades.putIfAbsent(student.getAlbumNumber(), new HashMap<>());
        subjectGrades.get(student.getAlbumNumber()).put(topic, grade);
    }
}
