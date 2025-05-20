package pl.edu.wsisiz.darkavenger54;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class StudentRecordTest {
    private StudentRecord repo;

    @BeforeEach
    void setUp() {
        repo = new StudentRecord();
    }

    @Test
    void addAndFindByAlbum() {
        Student s = new Student("Ivan", "Petrov", "A123");
        repo.addStudent(s);
        Student found = repo.findStudentByAlbum("A123");
        assertNotNull(found);
        assertEquals("Ivan", found.getName());
    }

    @Test
    void findByAlbumNotFound() {
        assertNull(repo.findStudentByAlbum("X999"));
    }

    @Test
    void findStudentsByName() {
        repo.addStudent(new Student("Anna", "Smith", "001"));
        repo.addStudent(new Student("Anna", "Johnson", "002"));
        repo.addStudent(new Student("Bob", "Brown", "003"));

        List<Student> list = repo.findStudentsByName("Anna");
        assertEquals(2, list.size());
    }

    @Test
    void removeStudent() {
        Student s = new Student("John", "Doe", "004");
        repo.addStudent(s);
        repo.removeStudent(s);
        assertNull(repo.findStudentByAlbum("004"));
    }

    @Test
    void forEachStudent() {
        repo.addStudent(new Student("A", "B", "1"));
        repo.addStudent(new Student("C", "D", "2"));
        int[] count = {0};
        repo.forEachStudent(st -> count[0]++);
        assertEquals(2, count[0]);
    }
}
