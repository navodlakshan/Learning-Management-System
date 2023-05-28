package lms.lecturer;

import lms.DBConnector.MyDbConnector;

import java.sql.*;
import java.util.Arrays;
import java.util.Objects;
public class CheckCAEligibilty {
    private Connection con;
    public CheckCAEligibilty(Connection con) {
        this.con = con;
    }
    private String getLectureType(String courseID){
        String courseType;
        try {
            String query1="SELECT Course_Type FROM course WHERE Course_ID='"+courseID+"'";
            Statement stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery(query1);
            rs2.next();
            courseType=rs2.getString(1);
            if(rs2.isClosed()){
                System.out.println("Course type fetching statement was closed");
            }else{
                stmt.close();
                System.out.println("Course type fetching statement is closed now");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseType;
    }
    protected void calculateCAEligibility(String courseID,String lecturerID){

        String query="SELECT Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,Mid_Practical,Mark_ID,Student_ID FROM mark WHERE Course_ID='"+courseID+"' AND Lecturer_ID='"+lecturerID+"'";
        String courseType=getLectureType(courseID);
        System.out.println("lecture type : "+courseType);

        try{
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            String eligibility;

            while (rs1.next()){
                System.out.print(rs1.getDouble(1)+"|"+rs1.getDouble(2)+"|"+rs1.getDouble(3)+"|"+rs1.getDouble(5)+"|"+rs1.getDouble(6)+"|"+rs1.getDouble(7)+"|"+rs1.getDouble(8));
                int markId;
                String studentId;
                double baseMarks=0.0;
                double[] quizMarkArray =new double[4];
                double quizSum=0.0;
                double finalCAMark=0.0;
                double Assessment1,Assessment2,Mid_Theory,Mid_Practical;

                quizMarkArray[0]=rs1.getDouble(1);
                quizMarkArray[1]=rs1.getDouble(2);
                quizMarkArray[2]=rs1.getDouble(3);
                quizMarkArray[3]=rs1.getDouble(4);

                Arrays.sort(quizMarkArray);
                for(int i=3;i>0;i--){
                    quizSum+=quizMarkArray[i];
                    baseMarks+=100;
                }
                finalCAMark+=quizSum;

                Assessment1=rs1.getDouble(5);
                Assessment2=rs1.getDouble(6);
                baseMarks+=200;
                finalCAMark+=Assessment1+Assessment2;

                if(Objects.equals(courseType, "Lecture/Practical")) {
                    Mid_Theory = rs1.getDouble(7);
                    Mid_Practical=rs1.getDouble(8);
                    finalCAMark+=(Mid_Theory+Mid_Practical)/2;
                    baseMarks+=100;

                }else if(Objects.equals(courseType, "Lecture")){
                    Mid_Theory = rs1.getDouble(7);
                    finalCAMark+=Mid_Theory;
                    baseMarks+=100;

                }else if(Objects.equals(courseType, "Practical")){
                    Mid_Practical=rs1.getDouble(8);
                    finalCAMark+=Mid_Practical;
                    baseMarks+=100;
                }
                else {
                    System.out.println("Cannot get 'Course_Type'");
                }
                markId=rs1.getInt(9);
                studentId=rs1.getString(10);

                if(finalCAMark>(baseMarks/2)){
                    eligibility="Yes";
                    if(calculateAttendancePercentage(studentId,courseID)>12){
                        updateCAEligibity(eligibility,markId);
                    }
                }else{
                    eligibility="No";
                    updateCAEligibity(eligibility,markId);
                }
                System.out.print("->base marks: "+baseMarks+"| final ca marks->"+finalCAMark+" | Eligibility->"+eligibility+"\n");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private int calculateAttendancePercentage(String studentId,String courseId){
        int attendanceCount=0;
        try{
            String query="SELECT COUNT(*) FROM attendance WHERE Course_ID='"+courseId+"' AND Student_id='"+studentId+"'";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                attendanceCount=resultSet.getInt(1);
                System.out.println("attendanceCount: "+attendanceCount);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return attendanceCount;
    }
    private void updateCAEligibity(String eligibility,int markId){
        try{
            String query = "UPDATE mark SET Eligibility=? WHERE Mark_ID=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,eligibility);
            pstmt.setInt(2,markId);

            if(1==pstmt.executeUpdate()){
                System.out.println("Eligibility updated");
            }else{
                System.out.println("Eligibility not updated");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
