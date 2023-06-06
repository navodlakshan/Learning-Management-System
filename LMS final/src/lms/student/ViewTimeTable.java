package lms.student;

import lms.DBConnector.MyDbConnector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewTimeTable extends JFrame{
    private JPanel jpanal;
    private JLabel jLabel9;
    private JComboBox selectdep;
    private JButton displayButton;
    private JButton back;

    MyDbConnector mdc;




    public ViewTimeTable(){

        setContentPane(jpanal);
        setTitle("View TimeTable");
        setSize(1600,825);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showPricipal();

            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Student view = new Student(null);
                view.setVisible(true);


            }
        });
    }
    public void showPricipal()
    {

        String dep = (String) selectdep.getSelectedItem();


            try
            {
                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                Statement stmt;

                stmt = con.createStatement();
                String query ="select * from Time_Table where Department_ID ="+"'"+dep+"'";

                ResultSet rs = stmt.executeQuery(query);


                while(rs.next())
                {

                    BufferedImage im = ImageIO.read(rs.getBinaryStream(2));
                    jLabel9.setIcon(new ImageIcon(im));

                }


            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }




    public static void main(String[] args) {
        ViewTimeTable o=new ViewTimeTable();
    }
}

