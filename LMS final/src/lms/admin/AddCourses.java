package lms.admin;
import lms.DBConnector.MyDbConnector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCourses extends JFrame{
    private JPanel addcourse;
    private JTextField id;
    private JTextField name;
    private JComboBox type;
    private JTextField credit;

    private JTextField lechours;
    private JComboBox gpa;
    private JButton MAINMENUButton;
    private JButton SUBMITButton;
    private JButton newbutton;
    private JLabel lblProfilePicture;
    private JComboBox department;


    MyDbConnector mdc;

    public AddCourses(){


        setContentPane(addcourse);
        setTitle("ADD COURSES");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String cId = id.getText();
                String cName = name.getText();
                String cType = (String) type.getSelectedItem();
                String nocredit=credit.getText();
                String lhourse=lechours.getText();
                String sDepartment = (String) department.getSelectedItem();
                String sGpa = (String) gpa.getSelectedItem();


                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;
                PreparedStatement stmt1;
                PreparedStatement stmt2;

                try {
                    String query = "  insert into Course (Course_ID,Course_Name,Course_Type,No_Of_Credit,Total_Lecture_Hours,GPA_Status,Department_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";


                    stmt = con.prepareStatement(query);
                    stmt.setString(1, cId);
                    stmt.setString(2, cName);
                    stmt.setString(3, cType);
                    stmt.setString(4, nocredit);
                    stmt.setString(5, lhourse);
                    stmt.setString(6, sGpa);
                    stmt.setString(7, sDepartment);


                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "ADDED");


                } catch (SQLException ex) {
                    System.out.println("Error in inserting a Student record" + ex.getMessage());
                }finally {
                    try {
                        con.close();
                    } catch (SQLException x) {
                        System.out.println("Error in closing the lms.connection" + x.getMessage());
                    }
                }

            }

        });

        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                name.setText("");
                credit.setText("");
                lechours.setText("");




            }
        });
        MAINMENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Maintaincourses view = new Maintaincourses();
                view.setVisible(true);

            }
        });
    }

}



