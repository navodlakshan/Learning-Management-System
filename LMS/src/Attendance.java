import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Attendance extends JFrame {
    private JTextField CourseIdTxt;
    private JTextField StudentIdTxt;
    private JTextField DateTxt;
    private JTextField LectureTypeTxt;
    private JTextField TimeTxt;
    private JLabel CourseId;
    private JLabel StudentId;
    private JLabel Date;
    private JLabel LectureType;
    private JLabel Time;
    private JPanel panAttendance;
    private JButton btnSubmit;
    private JButton btnClear;
    private JLabel prtResult;
    private JLabel Attendance;
    private JButton backButton;

    public Attendance() {
        add(panAttendance);
        setTitle("Attendance");
        setSize(1800, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    addAttendance();



            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CourseIdTxt.setText("");
                StudentIdTxt.setText("");
                DateTxt.setText("");
                LectureTypeTxt.setText("");
                TimeTxt.setText("");

                prtResult.setText("");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Technical_officer view = new Technical_officer(null);
                view.setVisible(true);
            }
        });
    }

    private void addAttendance() {

        String CourseId = CourseIdTxt.getText();
        String StudentId = StudentIdTxt.getText();
        String Date    = DateTxt.getText();
        String LectureType = LectureTypeTxt.getText();
        String Time  = TimeTxt.getText();

        if (CourseId.isEmpty() || StudentId.isEmpty() || Date.isEmpty() || LectureType.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        attendance = addTechnical_UserToDatabase(CourseId,StudentId,Date,LectureType,Time);
        if(attendance != null)
        {
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this,
                    "Failed to add attendance.",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Technical_User attendance;
    private Technical_User addTechnical_UserToDatabase(String CourseId,String StudentId,String Date,String LectureType,String Time)
    {
        Technical_User attendance = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/OOPLMS";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                //Connected Database Successfully

            Statement stmt = conn.createStatement();
            String sql = " insert into Attendance(Date,Lecture_type,Time,Course_ID,Student_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);


            preparedStatement.setString(4,CourseId);
            preparedStatement.setString(5,StudentId);
            preparedStatement.setString(1,Date);
            preparedStatement.setString(2,LectureType);
            preparedStatement.setString(3,Time);



            //Insert row into the table
            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0 )
            {
                attendance = new Technical_User();

                attendance.CourseId = CourseId;
                attendance.StudentId = StudentId;
                attendance.Date = Date;
                attendance.LectureType = LectureType;
                attendance.Time = Time;
            }

            Attendance maintain  = new Attendance();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return attendance;
    }

    public static void main(String[] args) {
        Attendance maintain  = new Attendance();
    }
}
