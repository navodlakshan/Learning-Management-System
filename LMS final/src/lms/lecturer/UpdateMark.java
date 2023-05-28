package lms.lecturer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class UpdateMark extends JFrame{
    private JTextField txtQuiz1;
    private JTextField txtQuiz2;
    private JTextField txtQuiz3;
    private JTextField txtQuiz4;
    private JTextField txtAssignment1;
    private JTextField txtAssignment2;
    private JTextField txtMidTheory;
    private JTextField txtMidPractical;
    private JTextField txtEndTheory;
    private JTextField txtEndPractical;
    private JTextField txtStudentId;
    private JTextField txtCourseId;
    private JButton btnInsert;
    private JPanel panUpdateMark;
    private JButton btnReset;
    private JButton backButton;
    private Connection conn;
    private final int size=10;
    private String[] marks=new String[size];
    private String studentId,courseId;

    //"fields" array for storing all fields that need to be updated
    private String[] fields={"Quiz1","Quiz2","Quiz3","Quiz4","Assessment1","Assessment2","Mid_Theory","Mid_Practical","End_Theory","End_Practical"};

    //set marks values into the "marks" array;
    public void setMarksArrayValues(){
        try{
            marks[0] = txtQuiz1.getText();
            marks[1] = txtQuiz2.getText();
            marks[2] = txtQuiz3.getText();
            marks[3] = txtQuiz4.getText();
            marks[4] = txtAssignment1.getText();
            marks[5] = txtAssignment2.getText();
            marks[6] = txtMidTheory.getText();
            marks[7] = txtMidPractical.getText();
            marks[8] = txtEndTheory.getText();
            marks[9] = txtEndPractical.getText();

        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    private String setUpdateQuery(String userName){
        String query=null;
        String str1="UPDATE mark SET ";
        int str1InitialCount=str1.length();
        String str2=" WHERE Student_ID='"+studentId+"' AND Course_ID='"+courseId+"'";

        //concatenating the updating fields to str1
        for (int i = 0; i < size; i++) {
            if(!marks[i].isBlank()){
                str1=str1.concat(fields[i]).concat("='"+marks[i]+"',");
                System.out.println("str: "+str1);
            }else{
                System.out.println(fields[i]+": is rejected due to emptiness");
            }

        }

        //checking that there is any occurrences of field in str1
        if(str1InitialCount<str1.length()){
            System.out.println("str1InitialCount<"+str1.length());
            //delete extra comma at the end of the str1
            StringBuilder sb = new StringBuilder( str1 );
            sb.deleteCharAt(str1.length()-1);
            str1 =sb.toString();
            System.out.println("str1: "+str1);

            //concat str1 and str2
            query=str1.concat(str2);
            System.out.println("Final query :"+query);

        }else{
            //query concatenating
            System.out.println("Cannot set update query due to emptiness of inputs");
        }
        return query;
    }

    private String setInsertQuery(String userName){
        String insertQuery=null;
        String str1="INSERT INTO mark(Lecturer_ID,Student_ID,Course_ID,";
        String str2=") VALUES('"+userName+"','"+studentId+"','"+courseId+"',";

        int str1InitialCount=str1.length();
        int str2InitialCount=str2.length();

        //concatenating the Inserting fields to str1 and str2
        for (int i = 0; i < size; i++) {
            if(!marks[i].isBlank()){
                str1=str1.concat(fields[i]).concat(",");
                str2=str2.concat("'"+marks[i]+"',");
                System.out.println("str: "+str1);
                System.out.println("str: "+str2);
            }else{
                System.out.println(fields[i]+": is rejected due to emptiness");
            }

        }

        //checking that there is any occurrences of field in str1
        if(str1InitialCount<str1.length() && str2InitialCount<str2.length()){
            System.out.println("str1InitialCount<"+str1.length());
            System.out.println("str1InitialCount<"+str2.length());

            //delete extra comma at the end of the str1
            StringBuilder sb1 = new StringBuilder( str1 );
            sb1.deleteCharAt(str1.length()-1);
            str1 =sb1.toString();
            System.out.println("str1: "+str1);

            //delete extra comma at the end of the str2
            StringBuilder sb2 = new StringBuilder( str2 );
            sb2.deleteCharAt(str2.length()-1);
            str2 =sb2.toString();
            System.out.println("str2: "+str2);

            //concat str1 and str2
            insertQuery=str1.concat(str2.concat(");"));
            System.out.println("Final query :"+insertQuery);

        }else{
            //query concatenating
            System.out.println("Cannot set insert query due to emptiness of inputs");
        }
        return insertQuery;
    }

public UpdateMark(Connection conn,String userName){
        add(panUpdateMark);
        setSize(800,600);
        setTitle("LMS-Lecturer Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,80);
        setVisible(true);

        this.conn=conn;

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                studentId=  txtStudentId.getText();
                courseId = txtCourseId.getText();
                setMarksArrayValues();
                System.out.println(Arrays.toString(marks));

                if(!studentId.isBlank() && !courseId.isBlank()) {
                    String selectQuery = "SELECT Mark_ID,Quiz1,Lecturer_ID,Student_ID,Course_ID FROM mark WHERE Student_ID=? AND Course_ID=?";
                    String updateQuery,insertQuery;
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(selectQuery);
                        pstmt.setString(1, studentId);
                        pstmt.setString(2, courseId);

                        ResultSet rs = pstmt.executeQuery();

                        rs.next();
                        System.out.println(rs.getRow());
//                        System.out.println(rs.getInt(1)+"|"+rs.getDouble(2)+"|"+
//                                    rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));

                        //checking whether there is a record for this course module
                        if (1==rs.getRow()) {
                            System.out.println("There is such a record in this module");

                            updateQuery=setUpdateQuery(userName);

                            if(updateQuery!=null){
                                Statement stmt =conn.createStatement();
                                if (0 != stmt.executeUpdate(updateQuery)) {
                                    JOptionPane.showMessageDialog(null, "Marks updated");
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR: Marks wasn't updated");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Error: Inputs are Empty");
                            }
                        } else{
                            System.out.println("There is no such a record");
                            insertQuery=setInsertQuery(userName);

                            if(insertQuery!=null){
                                Statement stmt = conn.createStatement();

                                if (0 != stmt.executeUpdate(insertQuery)) {
                                    JOptionPane.showMessageDialog(null, "Marks Inserted");
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR: Marks wasn't updated");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Error: Inputs are Empty");
                             }
                        }

                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Student & CourseID fields are mandetory");
                }
            }
        });
    btnReset.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < size; i++) {
                marks[i]=null;
            }
            txtQuiz1.setText(null);
            txtQuiz2.setText(null);
            txtQuiz3.setText(null);
            txtQuiz4.setText(null);
            txtAssignment1.setText(null);
            txtAssignment2.setText(null);
            txtMidTheory.setText(null);
            txtMidPractical.setText(null);
            txtEndTheory.setText(null);
            txtEndPractical.setText(null);
            txtStudentId.setText(null);
            txtCourseId.setText(null);

            studentId=null;
            courseId=null;
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
