package pl.edu.wsisiz.darkavenger54;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SubjectRecordTest {
    private SubjectRecord repo;

    @BeforeEach
    void setUp() {
        repo = new SubjectRecord();
    }

    @Test
    void addAndFindSubject() {
        Subject subj = new Subject("Math");
        repo.addSubject(subj);
        Subject found = repo.findSubjectBySubjectName("Math");
        assertNotNull(found);
    }

    @Test
    void findSubjectNotFound() {
        assertNull(repo.findSubjectBySubjectName("Physics"));
    }

    @Test
    void removeSubjectAndUnlinkStudents() {
        Subject subj = new Subject("History");
        Student s = new Student("X", "Y", "Z1");
        subj.addStudent(s);
        s.addSubject(subj);

        repo.addSubject(subj);
        repo.removeSubject(subj);

        assertFalse(s.getSubjects().contains(subj));
        assertNull(repo.findSubjectBySubjectName("History"));
    }
}
