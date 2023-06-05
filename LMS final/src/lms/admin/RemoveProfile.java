package lms.admin;

import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveProfile extends JFrame {
    private JComboBox sprofile;
    private JTextField uname;
    private JButton removeButton;
    private JButton backButton;
    private JPanel rprofile;

    MyDbConnector mdc;


    public RemoveProfile(){

        setContentPane(rprofile);
        setTitle("REMOVE PROFILE");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;
                PreparedStatement stmt1;
                PreparedStatement stmt2;


                String username=uname.getText();
                String selctprofile=(String) sprofile.getSelectedItem();

                try {

                    if(selctprofile=="ADMIN"){

                        String query = "DELETE FROM user WHERE Id= ?";
                        String query1 = "DELETE FROM User_Phone WHERE User_Id= ?";
                        String query2 = "DELETE FROM Admin WHERE User_Id= ?";

                        //remove from user table
                        stmt = con.prepareStatement(query);
                        stmt.setString(1,username);
                        stmt.execute();

                        //remove from User_Phone table
                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,username);
                        stmt1.execute();

                        //remove from admin table
                        stmt2 = con.prepareStatement(query2);
                        stmt2.setString(1,username);
                        stmt2.execute();

                        JOptionPane.showMessageDialog(null, "sussfully remove");


                    } else if (selctprofile=="TECHNICAL OFFICER") {


                        String query = "DELETE FROM user WHERE Id= ?";
                        String query1 = "DELETE FROM User_Phone WHERE User_Id= ?";
                        String query2 = "DELETE FROM Technical_Officer WHERE User_Id= ?";

                        //remove from user table
                        stmt = con.prepareStatement(query);
                        stmt.setString(1,username);
                        stmt.execute();

                        //remove from User_Phone table
                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,username);
                        stmt1.execute();

                        //remove from Technical_Officer table
                        stmt2 = con.prepareStatement(query2);
                        stmt2.setString(1,username);
                        stmt2.execute();

                        JOptionPane.showMessageDialog(null, "sussfully remove");


                    } else if (selctprofile=="LECTURE") {

                        String query = "DELETE FROM user WHERE Id= ?";
                        String query1 = "DELETE FROM User_Phone WHERE User_Id= ?";
                        String query2 = "DELETE FROM Lecturer WHERE User_Id= ?";

                        //remove from user table
                        stmt = con.prepareStatement(query);
                        stmt.setString(1,username);
                        stmt.execute();

                        //remove from User_Phone table
                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,username);
                        stmt1.execute();

                        //remove from Lecturer table
                        stmt2 = con.prepareStatement(query2);
                        stmt2.setString(1,username);
                        stmt2.execute();

                        JOptionPane.showMessageDialog(null, "sussfully remove");


                    } else if (selctprofile=="STUDENT") {

                        String query = "DELETE FROM user WHERE Id= ?";
                        String query1 = "DELETE FROM User_Phone WHERE User_Id= ?";
                        String query2 = "DELETE FROM Student WHERE User_Id= ?";

                        //remove from user table
                        stmt = con.prepareStatement(query);
                        stmt.setString(1,username);
                        stmt.execute();

                        //remove from User_Phone table
                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,username);
                        stmt1.execute();

                        //remove from Student table
                        stmt2 = con.prepareStatement(query2);
                        stmt2.setString(1,username);
                        stmt2.execute();

                        JOptionPane.showMessageDialog(null, "sussfully remove");



                    }


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
                Maintainprofile view = new Maintainprofile();
                view.setVisible(true);
            }
        });
    }


}
