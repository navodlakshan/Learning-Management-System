package lms.student;

import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class courseregistration extends JFrame{
    private JTextField sid;
    private JComboBox level;
    private JComboBox semester;
    private JButton registerButton;
    private JButton backButton;
    private JTextField course1;
    private JTextField course2;
    private JTextField course3;
    private JTextField course4;
    private JPanel courseregistration;

    MyDbConnector mdc;


    public courseregistration() {


            setContentPane(courseregistration);
            setTitle("ADD COURSES");
            setSize(1600,825);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sId=sid.getText();
                String slevel=(String) level.getSelectedItem();
                String sSemester=(String) semester.getSelectedItem();
                String scourse1=course1.getText();
                String scourse2=course2.getText();
                String scourse3=course3.getText();
                String scourse4=course4.getText();

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;

                try {
                    String query = "insert into course_registration(Level,Semester,course_code1,course_code2,course_code3,course_code4,Student_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";


                    stmt = con.prepareStatement(query);
                    stmt.setString(1, slevel);
                    stmt.setString(2, sSemester);
                    stmt.setString(3, scourse1);
                    stmt.setString(4, scourse2);
                    stmt.setString(5, scourse3);
                    stmt.setString(6, scourse4);
                    stmt.setString(7, sId);


                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "sussfully registered");


                } catch (SQLException ex) {
                    System.out.println("Error in inserting a Student record" + ex.getMessage());
                }finally {
                    try {
                        con.close();
                    } catch (SQLException x) {
                        System.out.println("Error in closing the lms.connection" + x.getMessage());
                    }
                }

            }

        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseregistration.this.dispose();
                Student student = new Student((String)null);
                student.setVisible(true);
            }
        });
    }



    public static void main(String[] args) {

        courseregistration o=new courseregistration();
    }
}
