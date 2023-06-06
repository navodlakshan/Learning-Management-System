package lms.student;

import net.proteanit.sql.DbUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudentMedical extends JFrame{
    private JButton btnBack;
    private JTable tblMedical;
    private JTextField txtStudentID;
    private JButton btnShow;
    private JPanel panMedicalDetails;
    private JLabel studentID;
    private JLabel medicalDetailsLabel;
    private JLabel lblDisplay;
    private JTextField txtMedicaId;
    private JButton SHOWButton;
    private Connection con;

    public ViewStudentMedical(Connection con) {
        setContentPane(panMedicalDetails);
        setTitle("Medical Details");
        setSize(1600, 825);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.con = con;

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = txtStudentID.getText();

                try {
                    String query = "SELECT Medical_id,Absent_Date,Committe_Desicion,Medical_document FROM medical WHERE Student_id='" + studentID + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    tblMedical.setModel(DbUtils.resultSetToTableModel(rs));


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Student view = new Student(null);
                view.setVisible(true);
            }
        });
        SHOWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String medicalId = txtMedicaId.getText();
                try {
                    String query = "SELECT Medical_document FROM medical WHERE Medical_id='" + medicalId + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        BufferedImage bi = ImageIO.read(rs.getBinaryStream(1));
                        lblDisplay.setIcon(new ImageIcon(bi));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
