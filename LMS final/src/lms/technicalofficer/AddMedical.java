package lms.technicalofficer;

import lms.DBConnector.MyDbConnector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddMedical extends JFrame{
    private JLabel Absent;
    private JTextField adate;
    private JButton add;
    private JPanel jpanal;
    private JLabel desition;
    private JTextField cdesition;
    private JTextField mdoc;
    private JLabel stuid;
    private JTextField sid;
    private JLabel doc;
    private JButton browseButton;
    private JLabel lblProfilePicture;
    private JButton back;
    private JButton clear;


    MyDbConnector mdc;
    String imagePath;

    public AddMedical() {

    setContentPane(jpanal);
    setTitle("ADD MEDICAL");
    setSize(700,600);
    setLocation(500,100);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);


    add.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {


            String date=adate.getText();
            String cdicition=cdesition.getText();
            String stuid=sid.getText();


            mdc = new MyDbConnector();
            Connection con = mdc.getMyConnection();
            PreparedStatement stmt;
            try {
                String myStatement = "INSERT INTO medical (Absent_Date,Committe_Desicion,Medical_document,Student_id)   VALUES(?,?,?,?)";

                stmt = con.prepareStatement(myStatement);
                stmt.setString(1, date);
                stmt.setString(2,cdicition);
                stmt.setString(3,imagePath);
                stmt.setString(4,stuid);



                stmt.execute();
                JOptionPane.showMessageDialog(null, "ADDED");

            } catch (SQLException ex) {
                System.out.println("Error in inserting a Student record" + ex.getMessage());
            }finally {
                try {
                    //close the lms.connection
                    con.close();
                } catch (SQLException x) {
                    System.out.println("Error in closing the lms.connection" + x.getMessage());
                }
            }

        }
    });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","jpg","png","jpeg","gif");
                fileChooser.addChoosableFileFilter(filter);
                int result = fileChooser.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath = selectedFile.getAbsolutePath();

                    try {
                        BufferedImage originalImage = ImageIO.read(selectedFile);
                        Image rescaleImage = originalImage.getScaledInstance(80,100, Image.SCALE_SMOOTH);
                        BufferedImage bi = new BufferedImage(rescaleImage.getWidth(null),rescaleImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
                        Graphics2D img2d=bi.createGraphics();
                        img2d.drawImage(rescaleImage,0,0,null);
                        img2d.dispose();
                        //ImageIO.write(bi,"png", new File("d:\\out1.png"));//saving image to pc
                        ImageIcon thumbnail= new ImageIcon(bi);
                        lblProfilePicture.setIcon(thumbnail);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                  cdesition.setText("");
                  adate.setText("");
                  sid.setText("");
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Technical_officer view = new Technical_officer(null);
                view.setVisible(true);

            }
        });

    }

    public static void main(String[] args) {
      AddMedical obj=new AddMedical();
    }
}
