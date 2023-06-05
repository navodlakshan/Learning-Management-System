package lms.admin;

import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Updateprofile extends JFrame{
    private JComboBox sprofile;
    private JTextField fname;
    private JTextField lname;
    private JComboBox gender;
    private JTextField email;
    private JTextField pword;
    private JTextField bday;
    private JComboBox department;
    private JTextField pno;
    private JButton updateButton;
    private JButton backButton;
    private JButton clearButton;
    private JPanel updatepanal;
    private JTextField id;

    MyDbConnector mdc;

    public Updateprofile(){

        setContentPane(updatepanal);
        setTitle("UPDATE PROFILE");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String selctprofile=(String) sprofile.getSelectedItem();
                String uid=id.getText();
                String ufname=fname.getText();
                String ulname=lname.getText();
                String ugender=(String) gender.getSelectedItem();
                String uemail=email.getText();
                String upword=pword.getText();
                String dob=bday.getText();
                String udepartment=(String) department.getSelectedItem();
                String upno=pno.getText();


                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;
                PreparedStatement stmt1;



                try {

                    if(selctprofile=="ADMIN"){

                        String query = "UPDATE user SET First_Name=?,Last_Name=?,Gender=?,E_mail=? ,Password=?,DOB=?,Department_id=? WHERE Id = ? ";
                        String query1 = " UPDATE User_Phone SET Phone_No=?  WHERE User_Id =?";


                        stmt = con.prepareStatement(query);
                        stmt.setString(1,ufname);
                        stmt.setString(2,ulname);
                        stmt.setString(3,ugender);
                        stmt.setString(4,uemail);
                        stmt.setString(5,upword);
                        stmt.setString(6,dob);
                        stmt.setString(7,udepartment);
                        stmt.setString(8,uid);
                        stmt.execute();


                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,upno);
                        stmt1.setString(2,uid);
                        stmt1.execute();

                        JOptionPane.showMessageDialog(null, "sussfully update");


                    } else if (selctprofile=="TECHNICAL OFFICER") {


                        String query = "UPDATE user SET First_Name=?,Last_Name=?,Gender=?,E_mail=? ,Password=?,DOB=?,Department_id=? WHERE Id = ? ";
                        String query1 = " UPDATE User_Phone SET Phone_No=?  WHERE User_Id =?";


                        stmt = con.prepareStatement(query);
                        stmt.setString(1,ufname);
                        stmt.setString(2,ulname);
                        stmt.setString(3,ugender);
                        stmt.setString(4,uemail);
                        stmt.setString(5,upword);
                        stmt.setString(6,dob);
                        stmt.setString(7,udepartment);
                        stmt.setString(8,uid);
                        stmt.execute();


                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,upno);
                        stmt1.setString(2,uid);
                        stmt1.execute();

                        JOptionPane.showMessageDialog(null, "sussfully update");


                    } else if (selctprofile=="LECTURE") {

                        String query = "UPDATE user SET First_Name=?,Last_Name=?,Gender=?,E_mail=? ,Password=?,DOB=?,Department_id=? WHERE Id = ? ";
                        String query1 = " UPDATE User_Phone SET Phone_No=?  WHERE User_Id =?";


                        stmt = con.prepareStatement(query);
                        stmt.setString(1,ufname);
                        stmt.setString(2,ulname);
                        stmt.setString(3,ugender);
                        stmt.setString(4,uemail);
                        stmt.setString(5,upword);
                        stmt.setString(6,dob);
                        stmt.setString(7,udepartment);
                        stmt.setString(8,uid);
                        stmt.execute();


                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,upno);
                        stmt1.setString(2,uid);
                        stmt1.execute();

                        JOptionPane.showMessageDialog(null, "sussfully update");


                    } else if (selctprofile=="STUDENT") {

                        String query = "UPDATE user SET First_Name=?,Last_Name=?,Gender=?,E_mail=? ,Password=?,DOB=?,Department_id=? WHERE Id = ? ";
                        String query1 = " UPDATE User_Phone SET Phone_No=?  WHERE User_Id =?";


                        stmt = con.prepareStatement(query);
                        stmt.setString(1,ufname);
                        stmt.setString(2,ulname);
                        stmt.setString(3,ugender);
                        stmt.setString(4,uemail);
                        stmt.setString(5,upword);
                        stmt.setString(6,dob);
                        stmt.setString(7,udepartment);
                        stmt.setString(8,uid);
                        stmt.execute();


                        stmt1 = con.prepareStatement(query1);
                        stmt1.setString(1,upno);
                        stmt1.setString(2,uid);
                        stmt1.execute();

                        JOptionPane.showMessageDialog(null, "sussfully update");



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
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                 id.setText("");
                 fname.setText("");
                 lname.setText("");
                 email.setText("");
                 pword.setText("");
                 bday.setText("");
                 pno.setText("");

            }
        });
    }


}
