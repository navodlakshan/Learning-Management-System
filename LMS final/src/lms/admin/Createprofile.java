package lms.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Createprofile extends JFrame{
    private JButton ADMINPROFILEButton;
    private JButton tOFFICERPROFILEButton;
    private JButton LECTUREPROFILEButton;
    private JButton STUDENTPROFILEButton;
    private JPanel createprofile;
    private JButton BACKButton;

    public Createprofile() {

    setContentPane(createprofile);
    setTitle("CREATE PROFILE");
    setSize(600,600);
    setLocation(500,100);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);

    ADMINPROFILEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            adminprofile view = new adminprofile();
            view.setVisible(true);

        }
    });
    tOFFICERPROFILEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Technicalofficar view = new Technicalofficar();
            view.setVisible(true);

        }
    });
    LECTUREPROFILEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            LectureProfile view = new LectureProfile();
            view.setVisible(true);
        }
    });
    STUDENTPROFILEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Studentprofile view = new Studentprofile();
            view.setVisible(true);
        }
    });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Maintainprofile view = new Maintainprofile();
                view.setVisible(true);
            }
        });
    }

}
