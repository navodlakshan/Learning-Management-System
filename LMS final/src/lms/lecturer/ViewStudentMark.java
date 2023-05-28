package lms.lecturer;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudentMark extends JFrame{
    private JTextField txtStudentId;
    private JTable table1;
    private JButton showButton;
    private JButton backButton;
    private JPanel panViewEligibility;
    private Connection con;
    public ViewStudentMark(Connection con) {

        setContentPane(panViewEligibility);
        setTitle("View Student Exam Eligibility");
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
                    String query="SELECT Mark_ID,Eligibility,SGPA,CGPA,Grade,Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,Mid_Practical,End_Theory,End_Practical,Final_Marks,Course_ID FROM mark WHERE Student_ID='"+studentId+"';";
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
