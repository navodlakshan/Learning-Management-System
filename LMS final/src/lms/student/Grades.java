package lms.student;

import net.proteanit.sql.DbUtils;

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
    private Connection con;

    public Grades(Connection con) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Grades and Marks Details");
        setSize(1600, 825);
        setContentPane(JPanel);
        setVisible(true);

        this.con = con;

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String student = ExamIndex.getText();

                try {
                    String query = "SELECT * FROM mark WHERE Student_ID ='" + student + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    GradeTable.setModel(DbUtils.resultSetToTableModel(rs));


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
