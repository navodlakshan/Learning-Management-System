package lms.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maintainprofile extends JFrame{
    private JButton ADDPROFILESButton;
    private JButton REMOVEPROFILEButton;
    private JButton backButton;
    private JPanel maintainprofile;
    private JButton UPDATEPROFILEButton;
    private JButton CURRENTPROFILEButton;


    public Maintainprofile(){

        setContentPane(maintainprofile);
        setTitle("MAINTAIN PROFILE");
        setSize(900,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        ADDPROFILESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Createprofile view = new Createprofile();
                view.setVisible(true);

            }
        });
        REMOVEPROFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RemoveProfile view = new RemoveProfile();
                view.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Adminpanal view = new Adminpanal();
                view.setVisible(true);
            }
        });
        UPDATEPROFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Updateprofile view = new Updateprofile();
                view.setVisible(true);

            }
        });
        CURRENTPROFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Displayprofile view = new Displayprofile();
                view.setVisible(true);

            }
        });
    }


}
