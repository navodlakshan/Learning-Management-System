package lms.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CourseDetails extends JFrame{
    private JTextField coIndex;
    private JButton showButton;
    private JTable CourseTable;
    private JButton backButton;
    private JLabel courseDetailsLabel;
    private JPanel JPanal;
    private JLabel Course_ID;

    private String url = "jdbc:mysql://localhost:3306/ooplms";
    private String username = "root";
    private String password = "";

    public CourseDetails(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Course Details");
        setSize(1600, 825);
        setLocationRelativeTo(null);
        setContentPane(JPanal);

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID = coIndex.getText();
                displayCourseDetails(Course_ID);
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

    private void displayCourseDetails(String Course_ID) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM course WHERE Course_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Course_ID);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            CourseTable.setModel(tableModel);

            tableModel.addColumn("Course_ID");
            tableModel.addColumn("Course_Name");
            tableModel.addColumn("Course_Type");
            tableModel.addColumn("No_Of_Credit");
            tableModel.addColumn("Total_Lecture_Hours");
            tableModel.addColumn("GPA_Status");
            tableModel.addColumn("Department_ID");


            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("Course_ID"),
                        resultSet.getString("Course_Name"),
                        resultSet.getString("Course_Type"),
                        resultSet.getInt("No_Of_Credit"),
                        resultSet.getInt("Total_Lecture_Hours"),
                        resultSet.getString("GPA_Status"),
                        resultSet.getString("Department_ID")
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
                CourseDetails courseDetails = new CourseDetails();
                courseDetails.setVisible(true);
            }
        });
    }
}
