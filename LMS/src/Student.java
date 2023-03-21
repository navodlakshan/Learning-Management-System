import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student extends JFrame {
    private JButton examResultButton;
    private JButton attendanceButton;
    private JButton updateProfileButton;
    private JButton medicalButton;
    private JButton courseDetailsButton;
    private JButton timeTableButton;
    private JButton noticeButton;
    private JLabel Profile;
    private JPanel Student;
    private JButton LOGOUTButton;

    String Sid=null;
    public Student(String username){

        add(Student);
        setVisible(true);
        setSize(1500, 1000);
        setTitle("Student Panal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Sid= username;



        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                UpdateProfile  view = new UpdateProfile(Sid);
                view.setVisible(true);

            }
        });
        medicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        courseDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CourseDetails  view = new CourseDetails();
                view.setVisible(true);

            }
        });
        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        examResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Grades view = new Grades();
                view.setVisible(true);
            }
        });
        timeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Time_Table1  view = new Time_Table1();
                view.setVisible(true);

            }
        });
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ViewNotice3  view = new ViewNotice3();
                view.setVisible(true);

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
    }
    public static void main(String[] args) {
        new Student(null);
    }
}

