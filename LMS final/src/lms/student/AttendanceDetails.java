package lms.student;

import net.proteanit.sql.DbUtils;

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
    private Connection con;

    public AttendanceDetails(Connection con) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Attendance Details");
        setSize(1600, 825);
        setContentPane(attendanceDetails);
        setVisible(true);

        this.con = con;

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = stIndex.getText();

                try {
                    String query = "SELECT * FROM Attendance WHERE Student_id ='" + studentID + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    AttendanceTable.setModel(DbUtils.resultSetToTableModel(rs));


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
}
