package lms.technicalofficer;

import lms.DBConnector.MyDbConnector;
import lms.login.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Technical_officer extends JFrame{

    private JPanel panTechnicalOfficer;

    private JLabel Technical_officer;

    private JButton btnProfile;
    private JButton btnAttendance;
    private JButton btnMedical;
    private JButton btnNotice;
    private JButton btnTimetable;
    private JButton logout;

    String tid=null;

    public Technical_officer(String username) {
        add(panTechnicalOfficer);
        setVisible(true);
        setSize(700, 600);
        setLocation(500,100);
        setTitle("Technical Officer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tid= String.valueOf(username);


        btnAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Attendance view = new Attendance();
                view.setVisible(true);
            }
        });
        btnTimetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                ViewTimeTable view = new ViewTimeTable();
                view.setVisible(true);


            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Login view = new Login();
                view.setVisible(true);


            }
        });
        btnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                UpdateProfile view = new UpdateProfile(username);
                view.setVisible(true);

            }
        });
        btnNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                MyDbConnector connector=new MyDbConnector();
                new ViewNotice(connector.getMyConnection(),username);



            }
        });
        btnMedical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AddMedical view = new AddMedical();
                view.setVisible(true);

            }
        });
    }


    public  static void main(String[] args) {
        Technical_officer TechnicalOfficer = new Technical_officer(null);
    }
}
