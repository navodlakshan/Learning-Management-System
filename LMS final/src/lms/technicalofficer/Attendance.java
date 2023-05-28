package lms.technicalofficer;

import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Attendance extends JFrame {
    private JTextField CourseIdTxt;
    private JTextField StudentIdTxt;
    private JTextField DateTxt;
    private JComboBox  LectureTypeTxt;
    private JTextField TimeTxt;
    private JLabel CourseId;
    private JLabel StudentId;
    private JLabel Date;
    private JLabel LectureType;
    private JLabel Time;
    private JPanel panAttendance;
    private JButton btnSubmit;
    private JButton btnClear;
    private JLabel prtResult;
    private JLabel Attendance;
    private JButton backButton;

    MyDbConnector mdc;

    public Attendance() {
        add(panAttendance);
        setTitle("Attendance");
        setSize(700, 600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CourseId = CourseIdTxt.getText();
                String StudentId = StudentIdTxt.getText();
                String Date    = DateTxt.getText();
                String LectureType =(String) LectureTypeTxt.getSelectedItem();
                String Time  = TimeTxt.getText();

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;

                try {
                    String sql = " insert into Attendance(Date,Lecture_type,Time,Course_ID,Student_id) VALUES (?, ?, ?, ?, ?)";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(4,CourseId);
                    stmt.setString(5,StudentId);
                    stmt.setString(1,Date);
                    stmt.setString(2,LectureType);
                    stmt.setString(3,Time);

                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "ADDED");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Technical_officer view = new Technical_officer(null);
                view.setVisible(true);

            }
        });


        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseIdTxt.setText("");
                StudentIdTxt.setText("");
                DateTxt.setText("");
                TimeTxt.setText("");
            }
        });
    }


    public static void main(String[] args) {
        Attendance maintain  = new Attendance();
    }
}
