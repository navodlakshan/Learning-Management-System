package lms.admin;

import lms.DBConnector.MyDbConnector;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Viewnotices extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton backButton;

    MyDbConnector mdc;

    public Viewnotices(){

        setContentPane(panel1);
        setTitle("VIEW CURRENT NOTICES");
        setSize(600,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        mdc = new MyDbConnector();
        Connection con = mdc.getMyConnection();
        Statement stmt;


        try {
            String query="select * from notice";
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
                Maintainnotices view = new Maintainnotices();
                view.setVisible(true);

            }
        });
    }


}
