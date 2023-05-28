package lms.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AttendanceDetails extends JFrame {
    private JTextField stIndex;
    private JButton backButton;
    private JButton displayButton;
    private JTable AttendanceTable;
    private JLabel studentID;
    private JLabel attendance;
    private JPanel attendanceDetails;


    private String url = "jdbc:mysql://localhost:3306/ooplms";
    private String username = "root";
    private String password = "";

    public AttendanceDetails() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AttendanceDetails Details");
        setSize(1600, 825);
        setLocationRelativeTo(null);
        setContentPane(attendanceDetails);

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = stIndex.getText();
                displayAttendanceDetails(studentID);
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

    private void displayAttendanceDetails(String studentID) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM Attendance WHERE Student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentID);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            AttendanceTable.setModel(tableModel);

            tableModel.addColumn("Attendance_ID");
            tableModel.addColumn("Date");
            tableModel.addColumn("Lecture_type");
            tableModel.addColumn("Time");
            tableModel.addColumn("Course_ID");
            tableModel.addColumn("Student_id");

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("Attendance_ID"),
                        resultSet.getDate("Date"),
                        resultSet.getString("Lecture_type"),
                        resultSet.getTime("Time"),
                        resultSet.getString("Course_ID"),
                        resultSet.getString("Student_id")
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
                AttendanceDetails attendance = new AttendanceDetails();
                attendance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                attendance.pack();
                attendance.setVisible(true);
            }
        });
    }
}


