package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a record of multiple student groups.
 * Provides methods to manage and search for groups.
 */
public class GroupRecord
{
    /** The list of all groups in the record. */
    private List<Group> groups;

    /**
     * Constructs a new, empty GroupRecord.
     */
    public GroupRecord()
    {
        groups = new ArrayList<Group>();
    }

    /**
     * Returns the list of groups in the record.
     *
     * @return the list of groups
     */
    public List<Group> getGroups()
    {
        return groups;
    }

    /**
     * Adds a group to the record.
     *
     * @param group the group to add
     */
    public void addGroup(Group group)
    {
        groups.add(group);
    }

    /**
     * Finds a group by its group ID.
     *
     * @param groupId the ID of the group to find
     * @return the matching group, or {@code null} if not found
     */
    public Group findGroupByGroupId(String groupId)
    {
        return groups.stream()
                .filter(group -> group.getGroupID().equals(groupId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes a group from the record. All students in the group will be unassigned from it.
     *
     * @param group the group to remove
     */
    public void removeGroup(Group group)
    {
        List<Student> students = group.getStudents();
        for (Student student : students)
        {
            student.setGroup(null);
        }
        groups.remove(group);
    }
}