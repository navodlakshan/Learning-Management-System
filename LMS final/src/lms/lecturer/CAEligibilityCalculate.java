package lms.lecturer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CAEligibilityCalculate extends JFrame{
    private JPanel panCAElig;
    private JTextField txtCourseID;
    private JButton calculateButton;
    private JButton backButton;

    public CAEligibilityCalculate(Connection con,String userName){
        add(panCAElig);
        setSize(800,600);
        setTitle("LMS-Calculate CA Eligibility");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,80);
        setVisible(true);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseId=txtCourseID.getText();
                System.out.println("from txtfield:"+txtCourseID.getText());
                System.out.println(courseId);

                if(!courseId.isBlank()){
                    new CheckCAEligibilty(con).calculateCAEligibility(courseId, userName);
                    JOptionPane.showMessageDialog(null,"Successful");
                }else{
                    JOptionPane.showMessageDialog(null,"Please fill CourseID");
                }
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
