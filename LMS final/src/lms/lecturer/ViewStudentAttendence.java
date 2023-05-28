package lms.lecturer;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudentAttendence extends JFrame{
    private Connection con;
    private JTextField txtStudentId;
    private JButton showButton;
    private JButton backButton;
    private JTable table1;
    private JPanel panViewAttendance;

    public ViewStudentAttendence(Connection con) {

        setContentPane(panViewAttendance);
        setTitle("View Attendance");
        setSize(650,620);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.con=con;
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId=txtStudentId.getText();
                try {
                    String query="SELECT * FROM attendance WHERE Student_id='"+studentId+"';";
                    Statement stmt=con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    table1.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
