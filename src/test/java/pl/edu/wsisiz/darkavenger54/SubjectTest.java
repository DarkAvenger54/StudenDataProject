package pl.edu.wsisiz.darkavenger54;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

class SubjectTest {
    private Subject subj;

    @BeforeEach
    void init() {
        subj = new Subject("Physics");
    }

    @Test
    void addTopicWithinLimit() {
        subj.addTopic("Exam", 50);
        subj.addTopic("Lab", 50);
        assertEquals(2, subj.getSubjectTopics().size());
        assertEquals(100, subj.getCurrentPoints());
    }

    @Test
    void addTopicExceedsLimit() {
        subj.addTopic("Exam", 60);
        subj.addTopic("Lab", 50); // цей не вліз
        Map<String, Integer> topics = subj.getSubjectTopics();
        assertTrue(topics.containsKey("Exam"));
        assertFalse(topics.containsKey("Lab"));
        assertEquals(60, subj.getCurrentPoints());
    }

    @Test
    void removeTopic() {
        subj.addTopic("Quiz", 20);
        subj.removeTopic("Quiz");
        assertFalse(subj.getSubjectTopics().containsKey("Quiz"));
        assertEquals(0, subj.getCurrentPoints());
    }

    @Test
    void addAndRemoveStudent() {
        Student s = new Student("A", "B", "S2");
        subj.addStudent(s);
        assertTrue(subj.getStudents().contains(s));
        subj.removeStudent(s);
        assertFalse(subj.getStudents().contains(s));
    }
}
