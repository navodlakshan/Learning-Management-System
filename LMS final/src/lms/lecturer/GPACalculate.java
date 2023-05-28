package lms.lecturer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class GPACalculate extends JFrame {
    private Connection con;
    private String lecturerId;
    private String courseId;
    private JPanel panGPACal;
    private JTextField txtCourseID;
    private JButton calculateButton;
    private JButton backButton;

    public GPACalculate(Connection con, String userName) {
        add(panGPACal);
        setSize(800,600);
        setTitle("LMS-GPA calculate");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,80);
        setVisible(true);

        this.con=con;
        lecturerId=userName;

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseId=txtCourseID.getText();
                String query;
                String courseType=getLectureType();

                if(courseType.equals("Lecture/Practical")) {
                    query="SELECT Mark_ID,Eligibility,Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,Mid_Practical,End_Theory,End_Practical FROM mark WHERE Lecturer_ID='"+lecturerId+"' AND Course_ID='"+courseId+"';";
                }else if(courseType.equals("Lecture")){
                    query="SELECT Mark_ID,Eligibility,Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,End_Theory FROM mark WHERE Lecturer_ID='"+lecturerId+"' AND Course_ID='"+courseId+"';";
                }else{
                    query="SELECT Mark_ID,Eligibility,Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Practical,End_Practical FROM mark WHERE Lecturer_ID='"+lecturerId+"' AND Course_ID='"+courseId+"';";
                }

                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while(rs.next()){
                        double[] quizMarkArray=new double[4];
                        double finalQuizMarks = 0.0;
                        double finalAssessmentMarks=0.0;
                        double finalMidMarks=0.0;
                        double finalEndMarks=0.0;
                        String grade;

                        int markId=rs.getInt(1);
                        String eligibility=rs.getString(2);

                        if(eligibility.equals("No")){
                            continue;
                        }

                        if(courseType.equals("Lecture/Practical")) {
                            quizMarkArray[0] = rs.getDouble(3);
                            quizMarkArray[1] = rs.getDouble(4);
                            quizMarkArray[2] = rs.getDouble(5);
                            quizMarkArray[3] = rs.getDouble(6);
                            finalQuizMarks = getFinalQuizMarks(quizMarkArray);

                            finalAssessmentMarks = getFinalAssessmentMarks(rs.getDouble(7), rs.getDouble(8));

                            finalMidMarks = getfinalMidMarks(rs.getDouble(9), rs.getDouble(10));

                            finalEndMarks = getFinalEndMarks(rs.getDouble(11), rs.getDouble(12));

                        }/*else if(courseType.equals("Lecture")){
                            quizMarkArray[0]=rs.getDouble(3);
                            quizMarkArray[1]=rs.getDouble(4);
                            quizMarkArray[2]=rs.getDouble(5);
                            quizMarkArray[3]=rs.getDouble(6);
                            finalQuizMarks=getFinalQuizMarks(quizMarkArray);
                            finalAssessmentMarks=getFinalAssessmentMarks(rs.getDouble(7),rs.getDouble(8));
                            finalMidMarks=getfinalMidMarks(rs.getDouble(9));
                            finalEndMarks=getFinalEndMarks(rs.getDouble(10));

                        }*/else{
                            quizMarkArray[0]=rs.getDouble(3);
                            quizMarkArray[1]=rs.getDouble(4);
                            quizMarkArray[2]=rs.getDouble(5);
                            quizMarkArray[3]=rs.getDouble(6);
                            finalQuizMarks=getFinalQuizMarks(quizMarkArray);
                            finalAssessmentMarks=getFinalAssessmentMarks(rs.getDouble(7),rs.getDouble(8));
                            finalMidMarks=getfinalMidMarks(rs.getDouble(9));
                            finalEndMarks=getFinalEndMarks(rs.getDouble(10));
                        }

                        grade=calculateGrade(finalQuizMarks,finalAssessmentMarks,finalMidMarks,finalEndMarks);
                        updateGrade(markId,grade);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Successful");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    private String calculateGrade(double finalQuizMarks,double finalAssessmentMarks,double finalMidMarks,double finalEndMarks){
        double totalFinalMark= finalQuizMarks+finalAssessmentMarks+finalMidMarks+finalEndMarks;

        String grade;
        if(totalFinalMark>85) {
            grade = "A+";
        }
        else if(totalFinalMark>70) {
            grade = "A";
        }
        else if(totalFinalMark>65) {
            grade = "A-";
        }
        else if(totalFinalMark>60) {
            grade = "B+";
        }
        else if(totalFinalMark>55) {
            grade = "B";
        }
        else if(totalFinalMark>50) {
            grade = "B-";
        }
        else if(totalFinalMark>45) {
            grade = "C+";
        }
        else if(totalFinalMark>40) {
            grade = "C";
        }
        else if(totalFinalMark>35) {
            grade = "C-";
        }
        else if(totalFinalMark>30) {
            grade = "D+";
        }
        else if(totalFinalMark>25){
            grade = "D";
        }
        else {
            grade = "E";
        }

        return grade;
    }
    private void updateGrade(int markId,String grade){

        try{
            String query = "UPDATE mark SET Grade='" + grade + "' WHERE Mark_ID='" + markId + "';";
            Statement stmt = con.createStatement();
            if(1==stmt.executeUpdate(query)){
                System.out.println("grade updated");
            }else{
                System.out.println("Grade not updated");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private String getLectureType(){
        String courseType;
        try {
            String query1="SELECT Course_Type FROM course WHERE Course_ID='"+courseId+"'";
            Statement stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery(query1);
            rs2.next();
            courseType=rs2.getString(1);
            /*if(rs2.isClosed()){
                System.out.println("Course type fetching statement was closed");
            }else{
                stmt.close();
                System.out.println("Course type fetching statement is closed now");
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseType;
    }
    private double getFinalQuizMarks(double[] quizMarkArray){
        double quizSum = 0.0;
        Arrays.sort(quizMarkArray);

        for(int i=3;i>0;i--){
            quizSum+=quizMarkArray[i];
        }
        quizSum/=3;
        return quizSum* (10.0 / 100.0);
    }
    private double getFinalAssessmentMarks(double assMark1,double assMark2){
        double finalAssesmentMark=0.0;

        finalAssesmentMark=(assMark1+assMark2)/2;

        return finalAssesmentMark* (10.0 / 100.0);
    }
    private double getfinalMidMarks(double midMarks){
        return midMarks* (20.0 / 100.0);
    }
    private double getfinalMidMarks(double midTheory,double midPractical){
        double finalMidMarks=(midTheory+midPractical)/2;

        return finalMidMarks* (20.0 / 100.0);
    }
    private double getFinalEndMarks(double finalMarks){
        finalMarks=finalMarks*(60.0/100.0);

        return finalMarks;
    }
    private double getFinalEndMarks(double finalTheoryMarks,double finalPracticalMarks){
        finalTheoryMarks=finalTheoryMarks*(40.0/100.0);
        finalPracticalMarks=finalPracticalMarks*(30.0/100.0);

        return (finalTheoryMarks+finalPracticalMarks);
    }
}
