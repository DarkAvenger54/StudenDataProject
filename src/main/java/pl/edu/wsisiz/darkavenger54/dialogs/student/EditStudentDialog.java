/*
 * Created by JFormDesigner on Sun May 18 23:52:24 CEST 2025
 */

package pl.edu.wsisiz.darkavenger54.dialogs.student;

import java.awt.event.*;

import pl.edu.wsisiz.darkavenger54.*;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Zhenia
 */
public class EditStudentDialog extends JDialog {
    private DefaultTableModel subjectsTableModel;
    private SubjectRecord subjectRecord;
    private GroupRecord groupRecord;
    private Student student;
    public EditStudentDialog(MainFrame mainFrame, SubjectRecord subjectRecord, GroupRecord groupRecord, Student student) {
        super(mainFrame, "Edit Student", true);
        this.subjectRecord = subjectRecord;
        this.groupRecord = groupRecord;
        this.student = student;
        initComponents();
        subjectsTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        subjectsTableModel.addColumn("Subject");
        subjectsTable.setModel(subjectsTableModel);
        updateDialog();
        this.setVisible(true);
    }
    private void updateDialog()
    {
        nameTextField.setText(student.getName());
        surnameTextField.setText(student.getSurname());
        albumTextField.setText(student.getAlbumNumber());
        if(student.getGroup() == null)
        {
            groupTextField.setText("");
        }
        else
        {
            groupTextField.setText(student.getGroup().getGroupID());
        }
        subjectsTableModel.setRowCount(0);
        List<Subject> studentSubjects = student.getSubjects();
        for(Subject subject : studentSubjects)
        {
            subjectsTableModel.addRow(new Object[]{subject.getSubjectName()});
        }
    }

    private void addGrade(ActionEvent e) {
        int selectedRow = subjectsTable.getSelectedRow();
        String topicName = topicTextField.getText().trim();
        String gradeStr = gradeTextField.getText().trim();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a subject from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (topicName.isEmpty() || gradeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Topic and grade must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String subjectName = subjectsTableModel.getValueAt(selectedRow, 0).toString();
        Subject subject = subjectRecord.findSubjectBySubjectName(subjectName);

        if (subject == null) {
            JOptionPane.showMessageDialog(this, "Subject not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!subject.getStudents().contains(student)) {
            JOptionPane.showMessageDialog(this, "Student is not enrolled in this subject.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!subject.getSubjectTopics().containsKey(topicName)) {
            JOptionPane.showMessageDialog(this, "Topic not found in selected subject.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int maxPoints = subject.getSubjectTopics().get(topicName);
        int grade;

        try {
            grade = Integer.parseInt(gradeStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Grade must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (grade < 0 || grade > maxPoints) {
            JOptionPane.showMessageDialog(this, "Grade must be between 0 and " + maxPoints, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        subject.setGrade(student, topicName, grade);
        JOptionPane.showMessageDialog(this, "Grade successfully added!");
    }

    private void addSubject(ActionEvent e) {
        Subject subject = subjectRecord.findSubjectBySubjectName(subjectTextField.getText());
        if (subject != null)
        {
            subject.addStudent(student);
            student.addSubject(subject);
            subjectsTableModel.addRow(new Object[]{subject.getSubjectName()});
            updateDialog();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No such subject", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ok(ActionEvent e) {
        this.dispose();
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void update(ActionEvent e) {
        Group group = groupRecord.findGroupByGroupId(groupTextField.getText());
        if(group != null)
        {
            group.addStudent(student);
            student.setGroup(group);
            student.setName(nameTextField.getText());
            student.setSurname(surnameTextField.getText());
            student.setAlbumNumber(albumTextField.getText());
            updateDialog();
        }
        else if(groupTextField.getText().isEmpty())
        {
            student.setGroup(null);
            student.setName(nameTextField.getText());
            student.setSurname(surnameTextField.getText());
            student.setAlbumNumber(albumTextField.getText());
            updateDialog();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No such group", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label12 = new JLabel();
        surnameTextField = new JTextField();
        nameTextField = new JTextField();
        albumTextField = new JTextField();
        groupTextField = new JTextField();
        updateButton = new JButton();
        scrollPane1 = new JScrollPane();
        subjectsTable = new JTable();
        panel2 = new JPanel();
        label9 = new JLabel();
        subjectTextField = new JTextField();
        addSubjectButton = new JButton();
        label10 = new JLabel();
        topicTextField = new JTextField();
        label11 = new JLabel();
        gradeTextField = new JTextField();
        addGradebutton = new JButton();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //======== panel1 ========
                {

                    //---- label1 ----
                    label1.setText("Info");

                    //---- label2 ----
                    label2.setText("Name");

                    //---- label4 ----
                    label4.setText("Surname");

                    //---- label5 ----
                    label5.setText("Album");

                    //---- label12 ----
                    label12.setText("Group");

                    //---- updateButton ----
                    updateButton.setText("Update");
                    updateButton.addActionListener(e -> update(e));

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label12)
                                        .addGap(18, 18, 18)
                                        .addComponent(groupTextField))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label5)
                                        .addGap(18, 18, 18)
                                        .addComponent(albumTextField, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label4)
                                            .addComponent(label2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(surnameTextField, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                                .addComponent(updateButton)
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(surnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5)
                                    .addComponent(albumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label12)
                                    .addComponent(groupTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateButton))
                                .addContainerGap(11, Short.MAX_VALUE))
                    );
                }

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(subjectsTable);
                }

                //======== panel2 ========
                {

                    //---- label9 ----
                    label9.setText("Subject");

                    //---- addSubjectButton ----
                    addSubjectButton.setText("Add Subject");
                    addSubjectButton.addActionListener(e -> addSubject(e));

                    //---- label10 ----
                    label10.setText("Topic");

                    //---- label11 ----
                    label11.setText("Grade");

                    //---- addGradebutton ----
                    addGradebutton.setText("Add Grade");
                    addGradebutton.addActionListener(e -> addGrade(e));

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label9)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectTextField, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addSubjectButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label10)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(topicTextField, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label11)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradeTextField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addGradebutton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label9)
                                    .addComponent(subjectTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addSubjectButton)
                                    .addComponent(label10)
                                    .addComponent(topicTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label11)
                                    .addComponent(gradeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addGradebutton))
                                .addContainerGap(26, Short.MAX_VALUE))
                    );
                }

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label12;
    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JTextField albumTextField;
    private JTextField groupTextField;
    private JButton updateButton;
    private JScrollPane scrollPane1;
    private JTable subjectsTable;
    private JPanel panel2;
    private JLabel label9;
    private JTextField subjectTextField;
    private JButton addSubjectButton;
    private JLabel label10;
    private JTextField topicTextField;
    private JLabel label11;
    private JTextField gradeTextField;
    private JButton addGradebutton;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
