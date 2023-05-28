package lms.lecturer;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudentDetails extends JFrame {
    private JTable table1;
    private JPanel studentdetails;
    private JButton backButton;

    private Connection con;

    public ViewStudentDetails(Connection con){

        setContentPane(studentdetails);
        setTitle("View Student Details");
        setSize(650,620);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.con=con;

        try {
            String query="SELECT Id,First_Name, Last_Name, Gender, E_mail, Department_id,Acedamic_year FROM user,student WHERE student.User_Id=user.Id;";
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            table1.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
