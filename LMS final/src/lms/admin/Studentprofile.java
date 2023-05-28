package lms.admin;

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

public class Studentprofile extends JFrame {
    private JPanel stu;
    private JTextField id;
    private JTextField fname;
    private JTextField lname;
    private JComboBox gender;
    private JTextField email;
    private JPasswordField password;
    private JTextField birthday;
    private JComboBox department;
    private JTextField phono;
    private JButton browseButton;
    private JButton MAINMENUButton;
    private JButton SUBMITButton;
    private JButton newbutton;
    private JLabel lblProfilePicture;
    private JPanel Studentpanal;
    private JTextField ayear;

    String imagePath;
    MyDbConnector mdc;

    public Studentprofile(){

        setContentPane(stu);
        setTitle("STUDENT PROFILE");
        setSize(700,600);
        setLocation(500,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

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
        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String sId = id.getText();
                String sName = fname.getText();
                String sLname= lname.getText();
                String sEmail = email.getText();
                String sdob=birthday.getText();
                String sGender = (String) gender.getSelectedItem();
                String sDepartment = (String) department.getSelectedItem();
                String sPnumber = phono.getText();
                String sPassword = password.getText();
                String syear=ayear.getText();

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;
                PreparedStatement stmt1;
                PreparedStatement stmt2;

                try {
                    String query = "insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";
                    String query1 = "insert into student (User_Id,Acedamic_year) VALUES (?,?)";
                    String query2 = "insert into User_Phone(User_Id,Phone_No) VALUES (?,?)";

                    stmt = con.prepareStatement(query);
                    stmt.setString(1, sId);
                    stmt.setString(2, sName);
                    stmt.setString(3, sLname);
                    stmt.setString(4, sGender);
                    stmt.setString(5, imagePath);
                    stmt.setString(6, sEmail);
                    stmt.setString(7, sPassword);
                    stmt.setString(8, sdob);
                    stmt.setString(9, sDepartment);

                    stmt.executeUpdate();

                    stmt1 = con.prepareStatement(query1);
                    stmt1.setString(1, sId);
                    stmt1.setString(2, syear);

                    stmt1.executeUpdate();

                    stmt2 = con.prepareStatement(query2);
                    stmt2.setString(1, sId);
                    stmt2.setString(2, sPnumber);

                    stmt2.execute();
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

        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 id.setText("");
                 fname.setText("");
                 lname.setText("");
                 email.setText("");
                 birthday.setText("");
                 phono.setText("");
                 password.setText("");
                 ayear.setText("");
                String NUll = null;
                imagePath=NUll;


            }
        });
        MAINMENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Maintainprofile view = new Maintainprofile();
                view.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {

        Studentprofile obj=new Studentprofile();
    }


    }

