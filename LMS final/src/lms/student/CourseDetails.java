package lms.student;

import net.proteanit.sql.DbUtils;

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
    private Connection con;

    public CourseDetails(Connection con){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Course Details");
        setSize(1600, 825);
        setContentPane(JPanal);
        setVisible(true);

        this.con = con;

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID = coIndex.getText();

                try {
                    String query = "SELECT * FROM course WHERE Course_ID ='" + Course_ID + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    CourseTable.setModel(DbUtils.resultSetToTableModel(rs));


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
