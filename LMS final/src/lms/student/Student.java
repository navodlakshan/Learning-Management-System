package lms.student;

import lms.DBConnector.MyDbConnector;
import lms.login.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Student extends JFrame {
    private JButton gradesButton;
    private JButton updateProfileButton;
    private JButton courseDetailsButton;
    private JButton attendanceButton;
    private JButton timeTableButton;
    private JButton medicalButton;
    private JButton noticeButton;
    private JButton logOutButton;
    private JPanel Student;
    private JLabel profileLabel;
    private JButton courseRegistrationButton;
    private Connection con;
    String sid = null;

    String username;

    public Student(String username) {

        add(Student);
        setVisible(true);
        setSize(1600, 825);
        setTitle("Student Panal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sid = username;
        this.username=username;

        MyDbConnector mdbc=new MyDbConnector();
        con=mdbc.getMyConnection();

        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                UpdateProfile view = new UpdateProfile(username);
                view.setVisible(true);
            }
        });

        courseDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CourseDetails view = new CourseDetails(con);
                view.setVisible(true);
            }
        });
        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AttendanceDetails view = new AttendanceDetails(con);
                view.setVisible(true);

            }
        });
        gradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Grades view = new Grades(con);
                view.setVisible(true);
            }
        });
        medicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                ViewStudentMedical view = new ViewStudentMedical(con);
                view.setVisible(true);
            }
        });
       timeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ViewTimeTable viewNotice=new ViewTimeTable();
                viewNotice.setVisible(true);
            }
        });
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ViewNotice view = new ViewNotice(con,username);
                view.setVisible(true);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Login view = new Login();
                view.setVisible(true);

            }
        });
        courseRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                courseregistration view=new courseregistration();
                view.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        new Student("Stu_005");
    }
}

