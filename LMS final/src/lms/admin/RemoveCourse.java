package lms.admin;

import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCourse extends JFrame{
    private JTextField cid;
    private JButton REMOVEButton;
    private JButton backButton;
    private JPanel rcourse;

    MyDbConnector mdc;

    public RemoveCourse(){
        setContentPane(rcourse);
        setTitle("REMOVE COURSES");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseId=cid.getText();

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;


                try {

                    String query = "DELETE FROM course WHERE Course_ID = ?";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1,courseId);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "sussfully remove");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                finally {
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

                setVisible(false);
                Maintaincourses view = new Maintaincourses();
                view.setVisible(true);

            }
        });
    }


}
