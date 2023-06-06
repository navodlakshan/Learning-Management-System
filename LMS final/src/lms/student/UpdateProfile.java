package lms.student;

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

public class UpdateProfile extends JFrame{
    private JLabel lblProfilePicture;
    private JLabel lblEmail;
    private JLabel lblTP;
    private JTextField txtEmailField;
    private JTextField txtTPField;
    private JButton browseButton;
    private JButton saveChangesButton;
    private JButton BackButton;
    private JPanel panUpdate;

    String eMail;
    String telePhone;
    String imagePath;
    MyDbConnector mdc;
    UpdateProfile (String userId){
        add(panUpdate);
        setSize(1600, 825);
        setTitle("UPDATE STUDENT PROFILE");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
                        ImageIO.write(bi,"png", new File("d:\\out1.png"));//saving image to pc
                        ImageIcon thumbnail= new ImageIcon(bi);
                        lblProfilePicture.setIcon(thumbnail);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eMail=txtEmailField.getText();
                telePhone=txtTPField.getText();

                mdc = new MyDbConnector();
                Connection con = mdc.getMyConnection();
                try{
                    String sql= "UPDATE user,user_phone SET  E_mail='"+eMail+"',photo='load_file(\""+imagePath.replace("\\","\\\\") +"\")',Phone_No='"+telePhone +"' WHERE Id='" + userId + "' AND User_Id='" + userId + "';";
                    System.out.println(sql);
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.execute();
                    JOptionPane.showMessageDialog(null,"Update is Successfully done !");


                }catch (Exception ex){
                    System.out.println("Error: "+ex.getMessage());
                }

            }
        });

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Student view = new Student(userId);
                view.setVisible(true);
            }
        });

    }


}
