package lms.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Grades extends JFrame{
    private JTable GradeTable;
    private JButton backButton;
    private JTextField ExamIndex;
    private JButton showButton;
    private javax.swing.JPanel JPanel;
    private JLabel student;
    private JLabel examResultLabel;

    private String url = "jdbc:mysql://localhost:3306/ooplms";
    private String username = "root";
    private String password = "";

    public Grades() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Grades and Marks Details");
        setSize(1600, 825);
        setLocationRelativeTo(null);
        setContentPane(JPanel);


        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID = ExamIndex.getText();
                displayExamDetails(Course_ID);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Student student = new Student(null);
                student.setVisible(true);
            }
        });
    }

    private void displayExamDetails(String studentID) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM mark WHERE Student_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,studentID);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            GradeTable.setModel(tableModel);

            tableModel.addColumn("Mark_ID");
            tableModel.addColumn("Eligibility");
            tableModel.addColumn("SGPA");
            tableModel.addColumn("CGPA");
            tableModel.addColumn("Grade");
            tableModel.addColumn("Quiz1");
            tableModel.addColumn("Quiz2");
            tableModel.addColumn("Quiz3");
            tableModel.addColumn("Quiz4");
            tableModel.addColumn("Assessment1");
            tableModel.addColumn("Assessment2");
            tableModel.addColumn("Mid_Theory");
            tableModel.addColumn("Mid_Practical");
            tableModel.addColumn("End_Theory");
            tableModel.addColumn("End_Practical");
            tableModel.addColumn("Final_Marks");
            tableModel.addColumn("Lecturer_ID");
            tableModel.addColumn("Student_ID");
            tableModel.addColumn("Course_ID");

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("Mark_ID"),
                        resultSet.getString("Eligibility"),
                        resultSet.getFloat("SGPA"),
                        resultSet.getFloat("CGPA"),
                        resultSet.getString("Grade"),
                        resultSet.getInt("Quiz1"),
                        resultSet.getInt("Quiz2"),
                        resultSet.getInt("Quiz3"),
                        resultSet.getInt("Quiz4"),
                        resultSet.getInt("Assessment1"),
                        resultSet.getInt("Assessment2"),
                        resultSet.getInt("Mid_Theory"),
                        resultSet.getInt("Mid_Practical"),
                        resultSet.getInt("End_Theory"),
                        resultSet.getInt("End_Practical"),
                        resultSet.getInt("Final_Marks"),
                        resultSet.getString("Lecturer_ID"),
                        resultSet.getString("Student_ID"),
                        resultSet.getString("Course_ID")
                };
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Grades grades = new Grades();
                grades.setVisible(true);
            }
        });
    }
}
