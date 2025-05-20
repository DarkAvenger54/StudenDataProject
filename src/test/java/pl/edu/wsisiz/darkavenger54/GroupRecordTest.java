package pl.edu.wsisiz.darkavenger54;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GroupRecordTest {
    private GroupRecord repo;

    @BeforeEach
    void init() {
        repo = new GroupRecord();
    }

    @Test
    void addAndFindGroup() {
        Group g = new Group("G1", "CS", "Desc");
        repo.addGroup(g);
        Group found = repo.findGroupByGroupId("G1");
        assertNotNull(found);
        assertEquals("CS", found.getSpecialization());
    }

    @Test
    void findGroupNotFound() {
        assertNull(repo.findGroupByGroupId("G999"));
    }

    @Test
    void removeGroupAndUnlinkStudents() {
        Group g = new Group("G2", "Math", "Desc");
        Student s = new Student("Test", "User", "S1");
        g.addStudent(s);
        s.setGroup(g);

        repo.addGroup(g);
        repo.removeGroup(g);

        assertNull(s.getGroup());
        assertNull(repo.findGroupByGroupId("G2"));
    }
}
