import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Grades extends JFrame implements ActionListener {
    private JTable table1;
    private JLabel Grades;
    private JButton displayButton;
    private JPanel GradeDetails;

        public Grades() {

            super("Display Stored Data");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            table1 = new JTable();
            JScrollPane scrollPane = new JScrollPane(table1);
            add(scrollPane, BorderLayout.CENTER);
            displayButton = new JButton("Display");
            add(displayButton, BorderLayout.SOUTH);
            displayButton.addActionListener(this);
            pack();
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == displayButton) {


                String url = "jdbc:mysql://localhost:3306/ooplms";
                String username = "root";
                String password = "";
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM mark");
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numCols = rsmd.getColumnCount();
                    String[] columnNames = new String[numCols];
                    for (int i = 0; i < numCols; i++) {
                        columnNames[i] = rsmd.getColumnName(i+1);
                    }
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                    while (rs.next()) {
                        Object[] rowData = new Object[numCols];
                        for (int i = 0; i < numCols; i++) {
                            rowData[i] = rs.getObject(i+1);
                        }
                        model.addRow(rowData);
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                    table1.setModel(model);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error retrieving data from the database: " + ex.getMessage());
                    return;
                }
            }
        }

        public static void main(String[] args) {
            new Grades();
        }
    }


