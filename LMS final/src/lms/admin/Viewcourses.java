package lms.admin;

import lms.DBConnector.MyDbConnector;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Viewcourses extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton backButton;

    MyDbConnector mdc;

    public Viewcourses(){

        setContentPane(panel1);
        setTitle("VIEW COURSE DETAILS");
        setSize(650,620);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



        mdc = new MyDbConnector();
        Connection con = mdc.getMyConnection();
        Statement stmt;


        try {
            String query="select * from course;";
            stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            table1.setModel(DbUtils.resultSetToTableModel(rs));


            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
