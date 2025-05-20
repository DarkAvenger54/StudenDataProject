/*
 * Created by JFormDesigner on Mon May 19 02:09:58 CEST 2025
 */

package pl.edu.wsisiz.darkavenger54.dialogs.group;

import pl.edu.wsisiz.darkavenger54.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Zhenia
 */
public class EditGroupDialog extends JDialog {
    private StudentRecord studentRecord;
    private Group group;
    private DefaultTableModel studentsTableModel;
    public EditGroupDialog(MainFrame mainFrame, Group group, StudentRecord studentRecord) {
        super(mainFrame, "Edit Group", true);
        this.studentRecord = studentRecord;
        this.group = group;
        initComponents();
        studentsTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        studentsTableModel.addColumn("Name");
        studentsTableModel.addColumn("Surname");
        studentsTableModel.addColumn("Album");
        studentTable.setModel(studentsTableModel);
        updateDialog();
        setVisible(true);
    }

    public void updateDialog()
    {
        groupIDTextField.setText(group.getGroupID());
        specializationTextField.setText(group.getSpecialization());
        descriptionTextPane.setText(group.getDescription());
        List<Student> students = group.getStudents();
        studentsTableModel.setRowCount(0);
        for(Student student : students)
        {
            studentsTableModel.addRow(new Object[]{student.getName(), student.getSurname(), student.getAlbumNumber()});
        }
    }

    private void ok(ActionEvent e) {
        this.dispose();
    }

    private void cancel(ActionEvent e) {
        this.dispose();
    }

    private void add(ActionEvent e) {
        Student student = studentRecord.findStudentByAlbum(albumTextField.getText());
        if(student != null)
        {
            if(group.getStudents().contains(student))
            {
                JOptionPane.showMessageDialog(this, "Student is already in group");
            }
            else
            {
                if(student.getGroup()!=null)
                {
                    student.getGroup().removeStudent(student);
                }
                group.addStudent(student);
                student.setGroup(group);
                updateDialog();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delete(ActionEvent e) {
        int selectedRow = studentTable.getSelectedRow();
        if(selectedRow != -1)
        {
            String albumId = studentsTableModel.getValueAt(selectedRow, 2).toString();
            Student student = studentRecord.findStudentByAlbum(albumId);
            student.setGroup(null);
            group.removeStudent(student);
            updateDialog();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Select a row", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void update(ActionEvent e) {
        //check
        group.setGroupID(groupIDTextField.getText());
        group.setSpecialization(specializationTextField.getText());
        group.setDescription(descriptionTextPane.getText());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        descriptionTextPane = new JTextPane();
        specializationTextField = new JTextField();
        groupIDTextField = new JTextField();
        updateButton = new JButton();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label4 = new JLabel();
        albumTextField = new JTextField();
        addButton = new JButton();
        deleteButton = new JButton();
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
                    label1.setText("GroupID");

                    //---- label2 ----
                    label2.setText("Specialization");

                    //---- label3 ----
                    label3.setText("Description:");

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(descriptionTextPane);
                    }

                    //---- updateButton ----
                    updateButton.setText("Update");
                    updateButton.addActionListener(e -> update(e));

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                        .addComponent(updateButton))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label3)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addGroup(panel1Layout.createParallelGroup()
                                                    .addComponent(label2)
                                                    .addComponent(label1))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(specializationTextField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                    .addComponent(groupIDTextField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                                        .addGap(0, 362, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(0, 116, Short.MAX_VALUE)
                                        .addComponent(updateButton))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label1)
                                            .addComponent(groupIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2)
                                            .addComponent(specializationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                }

                //======== panel2 ========
                {

                    //======== panel3 ========
                    {

                        //---- label4 ----
                        label4.setText("Student Album");

                        //---- addButton ----
                        addButton.setText("Add Student");
                        addButton.addActionListener(e -> add(e));

                        //---- deleteButton ----
                        deleteButton.setText("Delete Student");
                        deleteButton.addActionListener(e -> delete(e));

                        GroupLayout panel3Layout = new GroupLayout(panel3);
                        panel3.setLayout(panel3Layout);
                        panel3Layout.setHorizontalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label4)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(albumTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteButton)
                                    .addContainerGap(12, Short.MAX_VALUE))
                        );
                        panel3Layout.setVerticalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(albumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addButton)
                                        .addComponent(deleteButton))
                                    .addContainerGap(26, Short.MAX_VALUE))
                        );
                    }

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(studentTable);
                    }

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 128, Short.MAX_VALUE))))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(11, 11, 11))
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
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextPane descriptionTextPane;
    private JTextField specializationTextField;
    private JTextField groupIDTextField;
    private JButton updateButton;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label4;
    private JTextField albumTextField;
    private JButton addButton;
    private JButton deleteButton;
    private JScrollPane scrollPane2;
    private JTable studentTable;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
