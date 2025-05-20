/*
 * Created by JFormDesigner on Mon May 19 02:08:55 CEST 2025
 */

package pl.edu.wsisiz.darkavenger54.dialogs.subject;

import pl.edu.wsisiz.darkavenger54.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Zhenia
 */
public class EditSubjectDialog extends JDialog {
    private Subject subject;
    private StudentRecord studentRecord;
    private DefaultTableModel topicTableModel;
    private DefaultTableModel studentTableModel;
    public EditSubjectDialog(MainFrame mainFrame, Subject subject, StudentRecord studentRecord) {
        super(mainFrame, "Edit Subject", true);
        this.subject = subject;
        this.studentRecord = studentRecord;
        initComponents();
        topicTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        topicTableModel.addColumn("Topic");
        topicTableModel.addColumn("Max Points");
        studentTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        studentTableModel.addColumn("Name");
        studentTableModel.addColumn("Surname");
        studentTableModel.addColumn("Album");
        topicTable.setModel(topicTableModel);
        studentTable.setModel(studentTableModel);
        updateDialog();
        setVisible(true);
    }
    private void updateDialog()
    {
        this.subjectNameTextField.setText(subject.getSubjectName());
        studentTableModel.setRowCount(0);
        topicTableModel.setRowCount(0);
        Map<String, Integer> subjectTopics = subject.getSubjectTopics();
        List<Student> students = subject.getStudents();
        for (Student student : students)
        {
            studentTableModel.addRow(new Object[]{student.getName(), student.getSurname(), student.getAlbumNumber()});
        }
        for (Map.Entry<String, Integer> entry : subjectTopics.entrySet())
        {
            topicTableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }

    private void updateName(ActionEvent e) {
        if(!subjectNameTextField.getText().isEmpty())
        {
            subject.setSubjectName(subjectNameTextField.getText());
            updateDialog();
        }
    }

    private void addTopic(ActionEvent e) {
        if(!topicTextField.getText().isEmpty() && Utility.tryParseIntAndCheckIntegerClosed(maxGradeTextField.getText(), 0,100))
        {
            int currentpoints = subject.getCurrentPoints();
            int points = Integer.parseInt(maxGradeTextField.getText());
            if(currentpoints + points <= 100)
            {
                subject.addTopic(topicTextField.getText(), Integer.parseInt(maxGradeTextField.getText()));
                updateDialog();
                System.out.println("All topics:");
                for (Map.Entry<String, Integer> entry : subject.getSubjectTopics().entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Maximum points exceeded", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid info", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent(ActionEvent e) {
        Student student = studentRecord.findStudentByAlbum(albumTextField.getText());
        if(student != null)
        {
            subject.addStudent(student);
            student.addSubject(subject);
            updateDialog();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid info", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ok(ActionEvent e) {
        this.dispose();
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void deleteStudent(ActionEvent e) {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String album = studentTableModel.getValueAt(selectedRow, 2).toString();
            Student student = studentRecord.findStudentByAlbum(album);
            if (student != null) {
                subject.removeStudent(student);
                student.getSubjects().remove(subject); // если `student` содержит список предметов
                updateDialog();
            } else {
                JOptionPane.showMessageDialog(this, "Selected student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTopic(ActionEvent e) {
        int selectedRow = topicTable.getSelectedRow();
        if (selectedRow != -1) {
            String topicName = topicTableModel.getValueAt(selectedRow, 0).toString();
            subject.removeTopic(topicName);
            updateDialog();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a topic to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        subjectNameTextField = new JTextField();
        updateNameButton = new JButton();
        panel2 = new JPanel();
        label2 = new JLabel();
        topicTextField = new JTextField();
        label3 = new JLabel();
        maxGradeTextField = new JTextField();
        addTopicButton = new JButton();
        label4 = new JLabel();
        albumTextField = new JTextField();
        addStudentButton = new JButton();
        deleteTopicButton = new JButton();
        deleteStudentButton = new JButton();
        scrollPane1 = new JScrollPane();
        topicTable = new JTable();
        scrollPane2 = new JScrollPane();
        studentTable = new JTable();
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
                    label1.setText("Subject Name");

                    //---- updateNameButton ----
                    updateNameButton.setText("Update Name");
                    updateNameButton.addActionListener(e -> updateName(e));

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectNameTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateNameButton)
                                .addContainerGap(10, Short.MAX_VALUE))
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(subjectNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateNameButton))
                                .addContainerGap(11, Short.MAX_VALUE))
                    );
                }

                //======== panel2 ========
                {

                    //---- label2 ----
                    label2.setText("Topic");

                    //---- label3 ----
                    label3.setText("Max Grade");

                    //---- addTopicButton ----
                    addTopicButton.setText("Add Topic");
                    addTopicButton.addActionListener(e -> addTopic(e));

                    //---- label4 ----
                    label4.setText("Album");

                    //---- addStudentButton ----
                    addStudentButton.setText("Add Student");
                    addStudentButton.addActionListener(e -> addStudent(e));

                    //---- deleteTopicButton ----
                    deleteTopicButton.setText("Delete Topic");
                    deleteTopicButton.addActionListener(e -> deleteTopic(e));

                    //---- deleteStudentButton ----
                    deleteStudentButton.setText("Delete Student");
                    deleteStudentButton.addActionListener(e -> deleteStudent(e));

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(label2)
                                    .addComponent(label4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(topicTextField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(albumTextField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maxGradeTextField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addTopicButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteTopicButton))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(addStudentButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteStudentButton)))
                                .addContainerGap(10, Short.MAX_VALUE))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(topicTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3)
                                    .addComponent(maxGradeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addTopicButton)
                                    .addComponent(deleteTopicButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(albumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addStudentButton)
                                    .addComponent(deleteStudentButton))
                                .addContainerGap(51, Short.MAX_VALUE))
                    );
                }

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(topicTable);
                }

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(studentTable);
                }

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
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
    private JTextField subjectNameTextField;
    private JButton updateNameButton;
    private JPanel panel2;
    private JLabel label2;
    private JTextField topicTextField;
    private JLabel label3;
    private JTextField maxGradeTextField;
    private JButton addTopicButton;
    private JLabel label4;
    private JTextField albumTextField;
    private JButton addStudentButton;
    private JButton deleteTopicButton;
    private JButton deleteStudentButton;
    private JScrollPane scrollPane1;
    private JTable topicTable;
    private JScrollPane scrollPane2;
    private JTable studentTable;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
