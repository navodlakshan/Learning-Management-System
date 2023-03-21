import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMark extends JFrame {
    private JButton cancelButton;
    private JButton insertButton;
    private JTextField txtQuiz1;
    private JTextField txtQuiz2;
    private JTextField txtQuiz3;
    private JTextField txtQuiz4;
    private JTextField txtAssessment1;
    private JTextField txtAssessment2;
    private JTextField txtMidTheory;
    private JTextField txtMidPractical;
    private JTextField txtEndTheory;
    private JTextField txtEndPractical;
    private JTextField txtStuID;
    private JTextField txtCourseID;
    private JPanel panInsertMark;

    public InsertMark(String userID) {
        add(panInsertMark);
        setSize(800,600);
        setTitle("LMS- Add Course Materials");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(310,90);
        setVisible(true);


        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quiz1=txtQuiz1.getText();
                String quiz2=txtQuiz2.getText();
                String quiz3=txtQuiz3.getText();
                String quiz4=txtQuiz4.getText();
                String Assignment1=txtAssessment1.getText();
                String Assignment2=txtAssessment2.getText();
                String midTheory =txtMidTheory.getText();
                String midPractical=txtMidPractical.getText();
                String endTheory =txtEndTheory.getText();
                String endPractical=txtMidPractical.getText();

                String studentID= txtStuID.getText();
                String courseID= txtCourseID.getText();

                if(0!=studentID.length() && 0!=courseID.length()){
                    String sql="INSERT INTO mark(Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,Mid_Practical,End_Theory,End_Practical,Course_ID,Student_ID,Lecturer_ID) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    Connection connection=DatabaseConnection.getDBConnection();
                    try {
                        PreparedStatement ps=connection.prepareStatement(sql);

                        ps.setInt(1,Integer.parseInt(quiz1));


                        ps.setInt(2,parseInt(quiz2));
                        ps.setInt(3,parseInt(quiz3));
                        ps.setInt(4,parseInt(quiz4));
                        ps.setInt(5,parseInt(Assignment1));
                        ps.setInt(6,parseInt(Assignment2));
                        ps.setInt(7,parseInt(midTheory));
                        ps.setInt(8,parseInt(midPractical));
                        ps.setInt(9,parseInt(endTheory));
                        ps.setInt(10,parseInt(endPractical));
                        ps.setString(11,courseID);
                        ps.setString(12,studentID);
                        ps.setString(13,userID);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Insert is Successfully done !");

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Course ID & StudentID are necessary fields!");
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
    private static int parseInt(String s){
        if(!s.isEmpty()){
            return Integer.parseInt(s);
        }else{
            return 0;
        }
    }
    public static void main(String[] args) {
    //new InsertMark("Lec_005");
    }
    }
