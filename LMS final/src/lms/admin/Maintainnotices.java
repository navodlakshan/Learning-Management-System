package lms.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maintainnotices extends JFrame{
    private JButton ADDNOTICESButton;
    private JButton REMOVENOTICESButton;
    private JButton backButton;
    private JPanel maintainnotices;
    private JButton VIEWCURRENTNOTICESButton;

    public Maintainnotices() {

    setContentPane(maintainnotices);
    setTitle("ADMIN PANAL");
    setSize(800,600);
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
    ADDNOTICESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            AddNotices view = new AddNotices();
            view.setVisible(true);

        }
    });
    REMOVENOTICESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            RemoveNotices view = new RemoveNotices();
            view.setVisible(true);

        }
    });
        VIEWCURRENTNOTICESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Viewnotices view = new Viewnotices();
                view.setVisible(true);
            }
        });
    }

}
