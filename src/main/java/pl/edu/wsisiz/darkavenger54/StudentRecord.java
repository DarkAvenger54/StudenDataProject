package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StudentRecord
{
    private List<Student> students;
    public StudentRecord()
    {
        students = new ArrayList<Student>();
    }
    public List<Student> getStudents()
    {
        return students;
    }
    public void setStudents(List<Student> students)
    {
        this.students = students;
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(Student student)
    {
        students.remove(student);
    }
    public List<Student> findStudentsByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Student findStudentByAlbum(String album)
    {
        return students.stream().filter(s -> s.getAlbumNumber().equalsIgnoreCase(album)).findFirst().orElse(null);
    }
    public void forEachStudent(Consumer<Student> action) {
        students.forEach(action);
    }


}
