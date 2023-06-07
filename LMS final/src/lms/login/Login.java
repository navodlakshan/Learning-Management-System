package lms.login;


import lms.DBConnector.MyDbConnector;
import lms.admin.Adminpanal;
import lms.lecturer.Dashboard;
import lms.technicalofficer.Technical_officer;
import lms.student.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements LoginProcess {
    private JTextField uname;
    private JPasswordField pword;
    private JButton LOGINButton;
    private JButton CANCELButton;
    private JPanel loginpanal;
    private JLabel errormsg;

    MyDbConnector mdc;

    private String Username;
    private String Password;


    public Login() {

        setContentPane(loginpanal);
        setTitle("LOGIN");
        setSize(650,620);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);




    LOGINButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LoginProcess();
        }
    });


    CANCELButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
    }

    @Override
    public void LoginProcess() {

        mdc = new MyDbConnector();
        Connection con = mdc.getMyConnection();
        Statement stmt;

        Username = uname.getText();
        Password = pword.getText();
        String position = null;

        try {
            stmt = con.createStatement();

            String query = "SELECT * FROM user";
            String query1 = "SELECT * FROM  Admin";
            String query2 = "SELECT * FROM  Technical_Officer";
            String query3 = "SELECT * FROM  Lecturer";
            String query4 = "SELECT * FROM  Student";

            ResultSet rs1 = stmt.executeQuery(query1);

            while(rs1.next())
            {
                if(Username.equals(rs1.getString(1)))
                {
                    position="Admin";

                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");

                }
            }

            ResultSet rs2 = stmt.executeQuery(query2);

            while(rs2.next())
            {
                if(Username.equals(rs2.getString(1)))
                {
                    position="Technical_Officer";

                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");
                }
            }

            ResultSet rs3 = stmt.executeQuery(query3);

            while(rs3.next())
            {
                if(Username.equals(rs3.getString(1)))
                {
                    position="Lecturer";

                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");
                }
            }

            ResultSet rs4 = stmt.executeQuery(query4);

            while(rs4.next())
            {
                if(Username.equals(rs4.getString(1)))
                {
                    position="Student";

                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");

                }
            }

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
            {
                if(Username.equals(rs.getString(1)) && Password.equals(rs.getString(7)))
                {
                    if(position=="Admin") {
                        setVisible(false);
                        Adminpanal view = new Adminpanal();
                        view.setVisible(true);



                    }
                    else  if(position=="Technical_Officer") {
                        setVisible(false);
                        Technical_officer view = new Technical_officer(Username);
                        view.setVisible(true);


                    }
                    else  if(position=="Lecturer") {
                        setVisible(false);
                        Dashboard view = new Dashboard(Username);
                        view.setVisible(true);


                    }
                    else  if(position=="Student") {
                        setVisible(false);
                        Student view = new Student(Username);
                        view.setVisible(true);


                    }
                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME or PASSWORD");
                }
            }



        } catch (SQLException ex) {
            System.out.println("Error in displaying all students" + ex.getMessage());
        }

    }


    public static void main(String[] args) {

        Login obj=new Login();

    }

}
