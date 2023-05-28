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

public class AddTimeTable extends JFrame {
    private JPanel addTimetable;
    private JTextField id;
    private JComboBox department;
    private JButton browseButton;
    private JButton MAINMENUButton;
    private JButton SUBMITButton;
    private JButton newbutton;
    private JLabel lblProfilePicture;

    String imagePath;
    MyDbConnector mdc;

    public AddTimeTable(){

        setContentPane(addTimetable);
        setTitle("ADD TIMETABLE");
        setSize(600,600);
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
                String sDepartment = (String) department.getSelectedItem();


                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                PreparedStatement stmt;


                try {
                    String query = " insert into Time_Table(Time_Table,Department_ID,Admin_ID) VALUES (?, ?, ?)";

                    stmt = con.prepareStatement(query);

                    stmt.setString(1, imagePath);
                    stmt.setString(2, sDepartment);
                    stmt.setString(3, sId);


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

        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                id.setText("");
                String NUll = null;
                imagePath=NUll;

            }
        });
        MAINMENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                MaintainTimetables view = new MaintainTimetables();
                view.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {

        AddTimeTable obj=new AddTimeTable();
    }
    }

