package lms.lecturer;

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
    private JPanel panUpdateProfile;
    private JButton btnSaveChanges;
    private JButton clearButton;
    private JTextField txtEmail;
    private JTextField txtTelephone;
    private JButton backButton;
    private JButton browseButton;
    private JLabel lblProfilePicture;
    private JButton uploadPhotoButton;

    private final Connection con;
    private String imagePath;

    private void updateLecturerProfile(String userName,String newEmail){
        String query = "UPDATE user SET E_mail=? WHERE Id=?";

        try {
            PreparedStatement pstmt =con.prepareStatement(query);

            pstmt.setString(1,newEmail);
            pstmt.setString(2,userName);

            System.out.println(pstmt.executeUpdate());
            System.out.println("Email is updating...");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void updateLecturerProfile(String userName,Object newTelephone){
        String query = "UPDATE user_phone SET Phone_No=? WHERE User_Id=?";

        try {
            PreparedStatement pstmt =con.prepareStatement(query);

            pstmt.setString(1,newTelephone.toString());
            pstmt.setString(2,userName);

            System.out.println(pstmt.executeUpdate());
            System.out.println("Telephone is updating...");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void updateLecturerProfile(String userName,String newEmail,String newTelephone){

        String query = "UPDATE user,user_phone SET  E_mail=? ,Phone_No=? WHERE Id=? AND User_Id=?";

        try {
            PreparedStatement pstmt =con.prepareStatement(query);

            pstmt.setString(1,newEmail);
            pstmt.setString(2,newTelephone);
            pstmt.setString(3,userName);
            pstmt.setString(4,userName);

            System.out.println(pstmt.executeUpdate());
            System.out.println("Email & TP are updating...");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public UpdateProfile(Connection con,String userName){
        add(panUpdateProfile);
        setSize(800,600);
        setTitle("LMS-Lecturer Profile Update");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,80);
        setVisible(true);

        this.con=con;

        btnSaveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String newEmail = txtEmail.getText();
                String newTelephone = txtTelephone.getText();

                if(!newEmail.isBlank() && !newTelephone.isBlank()){
                    //execute when only both fields are filled
                    updateLecturerProfile(userName,newEmail,newTelephone);
                    System.out.println("both filds were updated");
                    JOptionPane.showMessageDialog(null, "email and phone-no fields were updated");
                }
                else{
                    if(!newEmail.isBlank()){
                        //execute when only Email filed is filled
                        updateLecturerProfile(userName,newEmail);
                        System.out.println("Email was updated");
                        JOptionPane.showMessageDialog(null, "Email was updated");

                    }
                    else if(!newTelephone.isBlank()){
                        //execute when only TP field is filled
                        updateLecturerProfile(userName,(Object)newTelephone);
                        System.out.println("TP was updated");
                        JOptionPane.showMessageDialog(null, "TP was updated");
                    }
                    else{
                        //execute both fields are no filled
                        System.out.println("No changes to save");
                        JOptionPane.showMessageDialog(null, "No changes to saved");
                    }
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 txtEmail.setText("");
                 txtTelephone.setText("");

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
                        ImageIO.write(bi,"png", new File("d:\\out1.png"));//saving image to pc
                        ImageIcon thumbnail= new ImageIcon(bi);
                        lblProfilePicture.setIcon(thumbnail);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });
        uploadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    try{
                        if(!imagePath.isBlank()) {
                            String sql = "UPDATE user SET photo='load_file(\"" + imagePath.replace("\\", "\\\\") + "\")' WHERE Id='" + userName + "';";
                            System.out.println(sql);
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.execute();
                            JOptionPane.showMessageDialog(null, "Photo Uploaded Successfully");
                        }else{
                            JOptionPane.showMessageDialog(null, "Photo not selected");
                        }

                    }catch (Exception ex){
                        System.out.println("Error: "+ex);
                    }

                }
            }
        });
    }

}
