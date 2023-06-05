package lms.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maintaincourses extends JFrame{
    private JButton ADDCOURSESButton;
    private JButton REMOVECOURSESButton;
    private JButton backButton;
    private JPanel maintainCourses;
    private JButton VIEWCOURSESButton;

    public Maintaincourses() {

    setContentPane(maintainCourses);
    setTitle("MAINTAIN COURSES");
    setSize(750,600);
    setLocation(500,100);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);

    ADDCOURSESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            AddCourses view = new AddCourses();
            view.setVisible(true);

        }
    });
    REMOVECOURSESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            RemoveCourse view = new RemoveCourse();
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
        VIEWCOURSESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Viewcourses view = new Viewcourses();
                view.setVisible(true);

            }
        });
    }


}
