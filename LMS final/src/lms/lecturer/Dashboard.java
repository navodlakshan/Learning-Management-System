package lms.lecturer;

import lms.DBConnector.MyDbConnector;
import lms.login.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dashboard extends JFrame{
    private JPanel panDashboard;
    private JLabel lblName;
    private JLabel lblPosition;
    private JLabel lblDepartment;
    private JLabel lblEmail;
    private JLabel lblTP;
    private JButton btnUpdateProfile;
    private JButton btnAddCourseMaterials;
    private JButton btnUpdateExamMarks;
    private JButton btnViewNotices;
    private JButton btnViewStudentDetails;
    private JButton LOGOUTButton;
    private JButton calculateCAEligibilityButton;
    private JButton calculateGPAsButton;
    private JButton viewStudentAttendanceButton;
    private JButton viewStudentMarkButton;
    private JButton viewStudentMedicalsButton;
    private Connection DBconnection;

    private void displayUserDetails(String userName){
        MyDbConnector mdbcon = new MyDbConnector();
        DBconnection = mdbcon.getMyConnection();

        String query ="SELECT First_Name,Last_Name,Gender,Photo,E_mail,Department_id,Phone_No,Position "+
                "FROM user,user_phone,lecturer WHERE id=? AND user_phone.User_Id=? AND lecturer.User_Id=?";

        try {
            PreparedStatement preparedStatement=DBconnection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userName);
            preparedStatement.setString(3,userName);

            ResultSet rs=preparedStatement.executeQuery();
            System.out.println("First_Name | Last_Name | Gender | Photo | E_mail | Department_id | Phone_No | Position");

            rs.next();
            System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8));

            lblName.setText(rs.getString(1)+" "+rs.getString(2));
            lblDepartment.setText(rs.getString(6));
            lblEmail.setText(rs.getString(5));
            lblTP.setText(rs.getString(7));
            lblPosition.setText(rs.getString(8));
            //rs.getString(3)

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
public Dashboard(String userName) {

    add(panDashboard);
    setSize(800,600);
    setTitle("LMS-Lecturer Dashboard");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocation(300,80);

    //Displaying user's data
    displayUserDetails(userName);

    setVisible(true);

    btnUpdateProfile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new UpdateProfile(DBconnection,userName);
        }
    });
    btnAddCourseMaterials.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddMaterial(DBconnection,userName);
        }
    });
    btnUpdateExamMarks.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new UpdateMark(DBconnection,userName);
        }
    });

    btnViewNotices.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewNotice(DBconnection,userName);
        }
    });

    LOGOUTButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Login view = new Login();
            view.setVisible(true);
        }


    });

    calculateCAEligibilityButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CAEligibilityCalculate(DBconnection,userName);
        }
    });
    calculateGPAsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GPACalculate(DBconnection,userName);
        }
    });
    btnViewStudentDetails.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            new ViewStudentDetails(DBconnection);
        }
    });
    viewStudentAttendanceButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewStudentAttendence(DBconnection);
        }
    });
    viewStudentMarkButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewStudentMark(DBconnection);
        }
    });
    viewStudentMedicalsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewStudentMedicalRecords(DBconnection);
        }
    });
}


   /* public static void main(String[] args) {
        new Dashboard("Lec_001");
    }*/
}
