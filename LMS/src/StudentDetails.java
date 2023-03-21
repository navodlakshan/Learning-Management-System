import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDetails extends JFrame {
    private JLabel lblHeading;
    private JLabel lblDashLecName;
    private JButton button1;
    private JPanel panStuDetails;

    public StudentDetails() {
        add(panStuDetails);
        setSize(800, 600);
        setTitle("LMS-Update Lecturer Profile");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        setLocation(310, 90);
        setVisible(true);

    button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
}
}
