package pl.edu.wsisiz.darkavenger54;

import com.google.gson.annotations.Expose;

public class CombinedRecord
{
    @Expose
    private GroupRecord groupRecord;
    @Expose
    private SubjectRecord subjectRecord;
    @Expose
    private StudentRecord studentRecord;

    public CombinedRecord(GroupRecord groupRecord, SubjectRecord subjectRecord, StudentRecord studentRecord)
    {
        this.groupRecord = groupRecord;
        this.subjectRecord = subjectRecord;
        this.studentRecord = studentRecord;
    }

    public GroupRecord getGroupRecord()
    {
        return groupRecord;
    }

    public SubjectRecord getSubjectRecord()
    {
        return subjectRecord;
    }

    public StudentRecord getStudentRecord()
    {
        return studentRecord;
    }

    public void setGroupRecord(GroupRecord groupRecord)
    {
        this.groupRecord = groupRecord;
    }

    public void setSubjectRecord(SubjectRecord subjectRecord)
    {
        this.subjectRecord = subjectRecord;
    }

    public void setStudentRecord(StudentRecord studentRecord)
    {
        this.studentRecord = studentRecord;
    }
}
