package lms.admin;

import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveNotices extends JFrame{
    private JTextField id;
    private JButton cancelButton;
    private JButton removeButton;
    private JPanel noticep;

    MyDbConnector mdc;

    public RemoveNotices(){
        setContentPane(noticep);
        setTitle("REMOVE NOTICES");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nid= id.getText();
                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;

                try {

                    String query = "DELETE FROM Notice WHERE Notice_ID = ?";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1,nid);
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
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Maintainnotices view = new Maintainnotices();
                view.setVisible(true);

            }
        });
    }


}
