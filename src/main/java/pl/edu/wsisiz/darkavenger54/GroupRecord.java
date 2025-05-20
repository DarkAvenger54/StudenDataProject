package pl.edu.wsisiz.darkavenger54;

import java.util.ArrayList;
import java.util.List;

public class GroupRecord
{
    private List<Group> groups;
    public GroupRecord()
    {
        groups = new ArrayList<Group>();
    }

    public List<Group> getGroups()
    {
        return groups;
    }
    public void addGroup(Group group)
    {
        groups.add(group);
    }
    public Group findGroupByGroupId(String groupId)
    {
        return groups.stream().filter(group -> group.getGroupID().equals(groupId)).findFirst().orElse(null);
    }
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
