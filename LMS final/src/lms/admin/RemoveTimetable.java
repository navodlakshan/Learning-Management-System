package lms.admin;

import lms.DBConnector.MyDbConnector;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveTimetable extends JFrame{
    private JComboBox sdepartment;
    private JButton BACKButton;
    private JButton REMOVEButton;
    private JPanel jpanal;

    MyDbConnector mdc;

    public  RemoveTimetable(){
        setContentPane(jpanal);
        setTitle("REMOVE TIMETABLE");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectd=(String)sdepartment.getSelectedItem();

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;


                try {

                    String query = "DELETE FROM Time_Table WHERE Department_ID = ?";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1,selectd);
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
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MaintainTimetables view = new MaintainTimetables();
                view.setVisible(true);
            }
        });
    }


}
