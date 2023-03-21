import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddMaterial extends JFrame {

    private JTextField tfDescription;
    private JTextField tfCourseID;
    private JButton browseButton;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel panAddMaterials;
    private JLabel lblSelectedFile;
    String filePath;
    public AddMaterial(String userId){
        add(panAddMaterials);
        setSize(800,600);
        setTitle("LMS- Add Course Materials");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(310,90);
        setVisible(true);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                    lblSelectedFile.setText(selectedFile.getName());
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseIDText=tfCourseID.getText();
                String descriptionText=tfDescription.getText();

                if(0!=courseIDText.length() && null!=filePath){


                    Connection con = DatabaseConnection.getDBConnection();
                    try{
                        String sql= "INSERT INTO lecture_mateial(Description,Material,Course_ID,Lecturer_ID) VALUES(?,?,?,?);";
                        System.out.println("filepath : "+filePath);
                        System.out.println("description:" +descriptionText);
                        System.out.println("CourseID:" +courseIDText+",LetterLength:"+courseIDText.length());

                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,descriptionText);
                        ps.setString(2,"load_file('"+filePath.replace("\\","\\\\")+"')");
                        ps.setString(3,courseIDText);
                        ps.setString(4,userId);

                        System.out.println(sql);

                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Insert is Successfully done !");

                    }catch (Exception ex){
                        System.out.println("Error: "+ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Course ID & Document are necessary fields!");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        //new AddMaterial("Lec_005");
    }
}
