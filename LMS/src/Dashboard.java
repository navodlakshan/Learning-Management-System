import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dashboard  extends JFrame{
    private JPanel panDashboard;
    private JLabel lblDashLecName;
    private JButton logOutButton;
    private JLabel lblProfileImage;
    private JLabel lblLecName;
    private JLabel lblLecPosition;
    private JLabel lblLecDep;
    private JLabel lblLecEmail;
    private JLabel lblLecTP;
    private JButton updateProfileButton;
    private JButton addCourseMaterialsButton;
    private JButton updateMarksToExamsButton;
    private JButton viewNoticesButton;
    private JButton viewStudentDetailsButton;
    String firstName;
    String lastName;
    String gender;
    Blob photo;
    String eMail;
    String department;
    String telPhoneNo;
    String position;

    public Dashboard(String userId){

        add(panDashboard);
        setSize(800,600);
        setTitle("LMS-Lecturer Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,80);
        setVisible(true);

    //getting connection
        Connection connection = DatabaseConnection.getDBConnection();
        try {
         //create statement
            String sql = "SELECT " +
                    "First_Name,Last_Name,Gender,Photo,E_mail,Department_id,Phone_No,Position " +
                    "FROM user,user_phone,lecturer " +
                    "WHERE id='" + userId + "' AND user_phone.User_Id='" + userId + "' AND lecturer.User_Id='" + userId + "'; ";
         //System.out.println(sql);
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            result.next();
            firstName = result.getString(1);
            lastName = result.getString(2);
            gender = result.getString(3);
            photo = result.getBlob(4);
            eMail = result.getString(5);
            department = result.getString(6);
            telPhoneNo = result.getString(7);
            position = result.getString(8);


            if (0 == gender.compareTo("M")) {
                lblDashLecName.setText("Hi,Mr." + result.getString(1));
            } else {
                lblDashLecName.setText("Hi,Ms." + result.getString(1));
            }

        //Displaying image
            byte[] img = result.getBytes(4);
            ImageIcon image=new ImageIcon(img);
            Image rescaledImage = image.getImage().getScaledInstance(100,130,Image.SCALE_SMOOTH);
            BufferedImage bi = new BufferedImage(rescaledImage.getWidth(null),rescaledImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = bi.createGraphics();
            graphics2D.drawImage(bi,0,0,null);
            graphics2D.dispose();
            ImageIcon newImage = new ImageIcon(bi);
            lblProfileImage.setIcon(newImage);
        //

        }catch(Exception e){
            System.out.println("Error: "+e);
        }

        lblLecName.setText(firstName+" "+lastName);
        lblLecPosition.setText("Lecturer("+position+")");
        lblLecDep.setText("Department of "+ department);
        lblLecEmail.setText(eMail);
        lblLecTP.setText("TP: "+telPhoneNo);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Login view = new Login();
                view.setVisible(true);
            }
        });
        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UpdateProfile(userId);
            }
        });
        addCourseMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddMaterial(userId);
            }
        });
        updateMarksToExamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new InsertMark(userId);

            }
        });
        viewStudentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StudentDetails();
            }
        });
        viewNoticesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ViewNotice(userId);

            }
        });
    }

    public static void main(String[] args) {
        new Dashboard("Lec_005");
    }

}
