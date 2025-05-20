/*
 * Created by JFormDesigner on Sat May 17 20:40:18 CEST 2025
 */

package pl.edu.wsisiz.darkavenger54;

import pl.edu.wsisiz.darkavenger54.dialogs.group.AddGroupDialog;
import pl.edu.wsisiz.darkavenger54.dialogs.group.EditGroupDialog;
import pl.edu.wsisiz.darkavenger54.dialogs.student.AddStudentDialog;
import pl.edu.wsisiz.darkavenger54.dialogs.student.EditStudentDialog;
import pl.edu.wsisiz.darkavenger54.dialogs.subject.AddSubjectDialog;
import pl.edu.wsisiz.darkavenger54.dialogs.subject.EditSubjectDialog;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
    private StudentRecord studentRecord;
    private GroupRecord groupRecord;
    private SubjectRecord subjectRecord;

    private DefaultTableModel groupsTableModel;
    private DefaultTableModel subjectsTableModel;
    private DefaultTableModel studentsTableModel;

    public MainFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("System Look and Feel Not Supported");
        }
        initComponents();
        setTitle("Student Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //records
        groupRecord = new GroupRecord();
        subjectRecord = new SubjectRecord();
        studentRecord = new StudentRecord();
        //groupsTable
        groupsTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        groupsTableModel.addColumn("Group");
        groupsTable.setModel(groupsTableModel);
        //subjectsTable
        subjectsTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        subjectsTableModel.addColumn("Subject");
        subjectsTable.setModel(subjectsTableModel);
        //studentsTable
        studentsTableModel = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        studentsTableModel.addColumn("Name");
        studentsTableModel.addColumn("Surname");
        studentsTableModel.addColumn("Album");
        studentsTable.setModel(studentsTableModel);
        studentsTable.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < studentsTable.getColumnCount(); ++i)
        {
            studentsTable.getColumnModel().getColumn(i).setResizable(false);
        }

        for (int i = 0; i < 100; ++i)
        {
            studentRecord.addStudent(new Student("Tyler", "Durden", Integer.toString(i)));
        }
        update();
    }
    private void update()
    {
        studentsTableModel.setRowCount(0);
        subjectsTableModel.setRowCount(0);
        groupsTableModel.setRowCount(0);
        List<Student> students = studentRecord.getStudents();
        List<Subject> subjects = subjectRecord.getSubjects();
        List<Group> groups = groupRecord.getGroups();
        for(Student student : students)
        {
            studentsTableModel.addRow(new Object[]{student.getName(), student.getSurname(), student.getAlbumNumber()});
        }
        for(Subject subject : subjects)
        {
            subjectsTableModel.addRow(new Object[]{subject.getSubjectName()});
        }
        for(Group group : groups)
        {
            groupsTableModel.addRow(new Object[]{group.getGroupID()});
        }
    }

    private void about(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "StudentDataProject\nMade by:\nYevhenii Manuilov 21679\nOleh Buriakivski 21569\nYurii Batiukh 21583\nBohdan Pazunka 21699");
    }

    private void open(ActionEvent e) {
        // TODO add your code here
    }

    private void save(ActionEvent e) {
        // TODO add your code here
    }

    private void groupAdd(ActionEvent e) {
        AddGroupDialog addGroupDialog = new AddGroupDialog(this);
        if (addGroupDialog.isConfirmed())
        {
            String groupID = addGroupDialog.getGroupID();
            String specialization = addGroupDialog.getSpecialization();
            String description = addGroupDialog.getDescription();
            if(groupRecord.findGroupByGroupId(groupID) == null)
            {
                groupRecord.addGroup(new Group(groupID, specialization, description));
                update();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Group Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
            addGroupDialog.dispose();
        }
    }

    private void groupDelete(ActionEvent e) {
        int selectedRow = groupsTable.getSelectedRow();
        if(selectedRow != -1)
        {
            String groupID = groupsTableModel.getValueAt(selectedRow, 0).toString();
            Group group = groupRecord.findGroupByGroupId(groupID);
            groupRecord.removeGroup(group);
            update();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void groupSearch(ActionEvent e) {

    }

    private void groupEdit(ActionEvent e) {
        int selectedRow = groupsTable.getSelectedRow();
        if(selectedRow != -1)
        {
            String groupID = groupsTableModel.getValueAt(selectedRow, 0).toString();
            Group group = groupRecord.findGroupByGroupId(groupID);
            System.out.println(group);
            EditGroupDialog editGroupDialog = new EditGroupDialog(this, group, studentRecord);
            update();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void subjectAdd(ActionEvent e) {
        AddSubjectDialog addSubjectDialog = new AddSubjectDialog(this);
        if(addSubjectDialog.isConfirmed())
        {
            String subjectName = addSubjectDialog.getSubjectName();
            if(subjectRecord.findSubjectBySubjectName(subjectName) == null)
            {
                subjectRecord.addSubject(new Subject(subjectName));
                update();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Subject Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void subjectDelete(ActionEvent e) {
        int selectedRow = subjectsTable.getSelectedRow();
        if(selectedRow != -1)
        {
            String subjectID = subjectsTableModel.getValueAt(selectedRow, 0).toString();
            Subject subject = subjectRecord.findSubjectBySubjectName(subjectID);
            subjectRecord.removeSubject(subject);
            update();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Choose, a row");
        }
    }

    private void subjectSearch(ActionEvent e) {
        // TODO add your code here
    }

    private void subjectEdit(ActionEvent e) {
        int selectedRow = subjectsTable.getSelectedRow();
        if(selectedRow != -1)
        {
            String subjectName = subjectsTableModel.getValueAt(selectedRow, 0).toString();
            Subject subject = subjectRecord.findSubjectBySubjectName(subjectName);
            System.out.println(subject);
            EditSubjectDialog editSubjectDialog = new EditSubjectDialog(this, subject, studentRecord);
            update();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Choose, a row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void studentAdd(ActionEvent e) {
        AddStudentDialog addStudentDialog = new AddStudentDialog(this);
        if(addStudentDialog.isConfirmed())
        {
            String name = addStudentDialog.getStudentName();
            String surname = addStudentDialog.getSurname();
            String album = addStudentDialog.getAlbum();
            if (studentRecord.findStudentByAlbum(album) != null) {
                JOptionPane.showMessageDialog(this, "Student Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                studentRecord.addStudent(new Student(name, surname, album));
                update();
            }
            addStudentDialog.dispose();
        }
    }

    private void studentDelete(ActionEvent e) {
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow != -1)
        {
            String albumId = studentsTableModel.getValueAt(selectedRow, 2).toString();
            Student student = studentRecord.findStudentByAlbum(albumId);
            studentRecord.removeStudent(student);
            update();
        }
        else {
            JOptionPane.showMessageDialog(this, "Choose, a row");
        }
    }

    private void studentSearch(ActionEvent e) {

    }

    private void studentEdit(ActionEvent e) {
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow != -1)
        {
            String albumId = studentsTableModel.getValueAt(selectedRow, 2).toString();
            Student student = studentRecord.findStudentByAlbum(albumId);
            System.out.println(student);
            EditStudentDialog editStudentDialog = new EditStudentDialog(this, subjectRecord, groupRecord, student);
            update();
        }
        else {
            JOptionPane.showMessageDialog(this, "Choose, a row");
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        openMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        menu2 = new JMenu();
        aboutMenuItem = new JMenuItem();
        tabbedPane1 = new JTabbedPane();
        groupsPanel = new JPanel();
        panel3 = new JPanel();
        groupAddButton = new JButton();
        groupDeleteButton = new JButton();
        groupEditButton = new JButton();
        groupSearchButton = new JButton();
        scrollPane4 = new JScrollPane();
        groupsTable = new JTable();
        subjectsPanel = new JPanel();
        panel2 = new JPanel();
        subjectAddButton = new JButton();
        subjectDeleteButton = new JButton();
        subjectEditButton = new JButton();
        subjectSearchButton = new JButton();
        scrollPane2 = new JScrollPane();
        subjectsTable = new JTable();
        studentsPanel = new JPanel();
        panel1 = new JPanel();
        studentAddButton = new JButton();
        studentDeleteButton = new JButton();
        studentSearchButton = new JButton();
        studentEditButton = new JButton();
        scrollPane3 = new JScrollPane();
        studentsTable = new JTable();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- openMenuItem ----
                openMenuItem.setText("Open");
                openMenuItem.addActionListener(e -> open(e));
                menu1.add(openMenuItem);

                //---- saveMenuItem ----
                saveMenuItem.setText("Save");
                saveMenuItem.addActionListener(e -> save(e));
                menu1.add(saveMenuItem);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("Info");

                //---- aboutMenuItem ----
                aboutMenuItem.setText("About");
                aboutMenuItem.addActionListener(e -> about(e));
                menu2.add(aboutMenuItem);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== tabbedPane1 ========
        {

            //======== groupsPanel ========
            {

                //======== panel3 ========
                {

                    //---- groupAddButton ----
                    groupAddButton.setText("Add");
                    groupAddButton.addActionListener(e -> groupAdd(e));

                    //---- groupDeleteButton ----
                    groupDeleteButton.setText("Delete");
                    groupDeleteButton.addActionListener(e -> groupDelete(e));

                    //---- groupEditButton ----
                    groupEditButton.setText("Edit");
                    groupEditButton.addActionListener(e -> groupEdit(e));

                    //---- groupSearchButton ----
                    groupSearchButton.setText("Search");
                    groupSearchButton.addActionListener(e -> groupSearch(e));

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(groupAddButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupDeleteButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                                .addComponent(groupSearchButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupEditButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(groupAddButton)
                                    .addComponent(groupDeleteButton)
                                    .addComponent(groupEditButton)
                                    .addComponent(groupSearchButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(16, Short.MAX_VALUE))
                    );
                }

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(groupsTable);
                }

                GroupLayout groupsPanelLayout = new GroupLayout(groupsPanel);
                groupsPanel.setLayout(groupsPanelLayout);
                groupsPanelLayout.setHorizontalGroup(
                    groupsPanelLayout.createParallelGroup()
                        .addGroup(groupsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(groupsPanelLayout.createParallelGroup()
                                .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                );
                groupsPanelLayout.setVerticalGroup(
                    groupsPanelLayout.createParallelGroup()
                        .addGroup(groupsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Groups", groupsPanel);

            //======== subjectsPanel ========
            {

                //======== panel2 ========
                {

                    //---- subjectAddButton ----
                    subjectAddButton.setText("Add");
                    subjectAddButton.addActionListener(e -> subjectAdd(e));

                    //---- subjectDeleteButton ----
                    subjectDeleteButton.setText("Delete");
                    subjectDeleteButton.addActionListener(e -> subjectDelete(e));

                    //---- subjectEditButton ----
                    subjectEditButton.setText("Edit");
                    subjectEditButton.addActionListener(e -> subjectEdit(e));

                    //---- subjectSearchButton ----
                    subjectSearchButton.setText("Search");
                    subjectSearchButton.addActionListener(e -> subjectSearch(e));

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(subjectAddButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectDeleteButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                                .addComponent(subjectSearchButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectEditButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(subjectDeleteButton)
                                        .addComponent(subjectEditButton)
                                        .addComponent(subjectSearchButton))
                                    .addComponent(subjectAddButton))
                                .addContainerGap(16, Short.MAX_VALUE))
                    );
                }

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(subjectsTable);
                }

                GroupLayout subjectsPanelLayout = new GroupLayout(subjectsPanel);
                subjectsPanel.setLayout(subjectsPanelLayout);
                subjectsPanelLayout.setHorizontalGroup(
                    subjectsPanelLayout.createParallelGroup()
                        .addGroup(subjectsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(subjectsPanelLayout.createParallelGroup()
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                                .addGroup(subjectsPanelLayout.createSequentialGroup()
                                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())))
                );
                subjectsPanelLayout.setVerticalGroup(
                    subjectsPanelLayout.createParallelGroup()
                        .addGroup(subjectsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Subjects", subjectsPanel);

            //======== studentsPanel ========
            {

                //======== panel1 ========
                {

                    //---- studentAddButton ----
                    studentAddButton.setText("Add");
                    studentAddButton.addActionListener(e -> studentAdd(e));

                    //---- studentDeleteButton ----
                    studentDeleteButton.setText("Delete");
                    studentDeleteButton.addActionListener(e -> studentDelete(e));

                    //---- studentSearchButton ----
                    studentSearchButton.setText("Search");
                    studentSearchButton.addActionListener(e -> studentSearch(e));

                    //---- studentEditButton ----
                    studentEditButton.setText("Edit");
                    studentEditButton.addActionListener(e -> studentEdit(e));

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(studentAddButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentDeleteButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                                .addComponent(studentSearchButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentEditButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(studentAddButton)
                                    .addComponent(studentDeleteButton)
                                    .addComponent(studentEditButton)
                                    .addComponent(studentSearchButton))
                                .addContainerGap(31, Short.MAX_VALUE))
                    );
                }

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(studentsTable);
                }

                GroupLayout studentsPanelLayout = new GroupLayout(studentsPanel);
                studentsPanel.setLayout(studentsPanelLayout);
                studentsPanelLayout.setHorizontalGroup(
                    studentsPanelLayout.createParallelGroup()
                        .addGroup(studentsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(studentsPanelLayout.createParallelGroup()
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
                            .addContainerGap())
                );
                studentsPanelLayout.setVerticalGroup(
                    studentsPanelLayout.createParallelGroup()
                        .addGroup(studentsPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Students", studentsPanel);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(3, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenu menu2;
    private JMenuItem aboutMenuItem;
    private JTabbedPane tabbedPane1;
    private JPanel groupsPanel;
    private JPanel panel3;
    private JButton groupAddButton;
    private JButton groupDeleteButton;
    private JButton groupEditButton;
    private JButton groupSearchButton;
    private JScrollPane scrollPane4;
    private JTable groupsTable;
    private JPanel subjectsPanel;
    private JPanel panel2;
    private JButton subjectAddButton;
    private JButton subjectDeleteButton;
    private JButton subjectEditButton;
    private JButton subjectSearchButton;
    private JScrollPane scrollPane2;
    private JTable subjectsTable;
    private JPanel studentsPanel;
    private JPanel panel1;
    private JButton studentAddButton;
    private JButton studentDeleteButton;
    private JButton studentSearchButton;
    private JButton studentEditButton;
    private JScrollPane scrollPane3;
    private JTable studentsTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
