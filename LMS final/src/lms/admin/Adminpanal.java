package lms.admin;

import lms.login.Login;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Adminpanal extends JFrame{
    private JButton CREATEPROFILEButton;
    private JButton MAINTAINCOURSESButton;
    private JButton MAINTAINNOTICESButton;
    private JButton MAINTAINTIMETABLEButton;
    private JButton LOGOUTButton;
    private JPanel adminpanal;

    public Adminpanal() {

    setContentPane(adminpanal);
    setTitle("ADMIN PANAL");
    setSize(600,600);
    setLocation(500,100);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);

    CREATEPROFILEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Maintainprofile view = new Maintainprofile();
            view.setVisible(true);

        }
    });
    MAINTAINCOURSESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Maintaincourses view = new Maintaincourses();
            view.setVisible(true);
        }
    });
    MAINTAINNOTICESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Maintainnotices view = new Maintainnotices();
            view.setVisible(true);

        }
    });
    MAINTAINTIMETABLEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            MaintainTimetables view = new MaintainTimetables();
            view.setVisible(true);
        }
    });
    LOGOUTButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Login view = new Login();
            view.setVisible(true);

        }
    });
}


}
