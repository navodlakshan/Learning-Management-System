package lms.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintainTimetables extends JFrame{
    private JButton ADDTIMETABLEButton;
    private JButton REMOVETIMETABLEButton;
    private JButton backButton;
    private JPanel maintaintimetable;

    public MaintainTimetables() {


    setContentPane(maintaintimetable);
    setTitle("MAINTAIN TIMETABLE");
    setSize(600,600);
    setLocation(500,100);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            Adminpanal view = new Adminpanal();
            view.setVisible(true);

        }
    });
    ADDTIMETABLEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            AddTimeTable view = new AddTimeTable();
            view.setVisible(true);

        }
    });
    REMOVETIMETABLEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            RemoveTimetable view = new RemoveTimetable();
            view.setVisible(true);

        }
    });
}


}
