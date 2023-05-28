package lms.lecturer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddMaterial extends JFrame{
    private JPanel panAddMaterial;
    private JTextField txtCourseId;
    private JTextField txtDescription;
    private JButton btnBrowse;
    private JLabel lblSelectedFile;
    private JButton btnUpload;
    private JButton btnReset;
    private JButton backButton;
    private String filePath;
    private final Connection conn;
    private File selectedFile;
    public void addMaterial(String userName,String filePath,String courseId){
        try{
            String sql= "INSERT INTO lecture_mateial(Material,Course_ID,Lecturer_ID) VALUES(?,?,?)";
            System.out.println("filepath : "+filePath);
            System.out.println("CourseID:" +courseId+",LetterLength:"+courseId.length());

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"load_file('"+filePath.replace("\\","\\\\")+"')");
            ps.setString(2,courseId);
            ps.setString(3,userName);

            System.out.println(sql);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Insert is Successfully done !");

        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
    public void addMaterial(String userName,String filePath,String courseId,String description){
        try{
            String sql= "INSERT INTO lecture_mateial(Description,Material,Course_ID,Lecturer_ID) VALUES(?,?,?,?)";
            System.out.println("filepath : "+filePath);
            System.out.println("description:" +description);
            System.out.println("CourseID:" +courseId+",LetterLength:"+courseId.length());

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,description);
            ps.setString(2,"load_file('"+filePath.replace("\\","\\\\")+"')");
            ps.setString(3,courseId);
            ps.setString(4,userName);

            System.out.println(sql);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Insert is Successfully done !");

        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    public AddMaterial(Connection conn,String userName){
        add(panAddMaterial);
        setSize(800,600);
        setTitle("LMS-Lecturer Profile Update");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,80);
        setVisible(true);

        this.conn=conn;

        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION) {
                    selectedFile= fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                    lblSelectedFile.setText(selectedFile.getName());
                }
            }
        });

        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseID=txtCourseId.getText();
                String description=txtDescription.getText();

                if(!courseID.isBlank() && null!=filePath && !description.isBlank()){
                    addMaterial(userName,filePath,courseID,description);
                    System.out.println("File, course and description was uploaded");
                }
                else if(!courseID.isBlank() && null!=filePath && description.isBlank()){
                    addMaterial(userName,filePath,courseID);
                    System.out.println("File, course was uploaded");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Course ID & Document are necessary fields!");
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedFile.delete()) {
                    lblSelectedFile.setText(null);
                    filePath=null;
                }
                txtCourseId.setText(null);
                txtDescription.setText(null);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
