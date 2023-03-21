import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame {

    Connection con = null;
    Statement stmt = null;

    public Login() {
        initComponents();
        con = DatabaseConnection.getDBConnection();
        errormsg.setVisible(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        username = new JTextField();
        password = new JPasswordField();
        errormsg = new JLabel();
        login = new JButton();
        cancel = new JButton();
        jLabel4 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("LMS");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("LOGIN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("USERNAME");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("PASSWORD");

        username.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        password.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        errormsg.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        errormsg.setForeground(new java.awt.Color(255, 0, 0));
        errormsg.setText("ERRORMSG");

        login.setBackground(new java.awt.Color(204, 204, 204));
        login.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        login.setText("LOGIN");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        cancel.setBackground(new java.awt.Color(204, 204, 204));
        cancel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cancel.setText("CANCEL");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("Welcome to the Learning Management System");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(185, 185, 185)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(78, 78, 78)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(username)
                                                        .addComponent(password, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(login)
                                                .addGap(53, 53, 53)))
                                .addComponent(cancel)
                                .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 783, GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(394, 394, 394)
                                                .addComponent(errormsg))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel1)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(78, 78, 78)
                                .addComponent(jLabel1)
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(username, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(66, 66, 66)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                                .addComponent(errormsg)
                                                .addGap(42, 42, 42)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(login)
                                                        .addComponent(cancel))
                                                .addGap(42, 42, 42))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        try
        {
            stmt = con.createStatement();

            String query = "SELECT * FROM user";
            String query1 = "SELECT * FROM  Admin";
            String query2 = "SELECT * FROM  Technical_Officer";
            String query3 = "SELECT * FROM  Lecturer";
            String query4 = "SELECT * FROM  Student";


            String Username = username.getText();
            String Password = password.getText();
            String position=null;


            ResultSet rs1 = stmt.executeQuery(query1);

            while(rs1.next())
            {
                if(Username.equals(rs1.getString(1)))
                {
                    position="Admin";
                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");
                }
            }


            ResultSet rs2 = stmt.executeQuery(query2);

            while(rs2.next())
            {
                if(Username.equals(rs2.getString(1)))
                {
                    position="Technical_Officer";
                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");
                }
            }

            ResultSet rs3 = stmt.executeQuery(query3);

            while(rs3.next())
            {
                if(Username.equals(rs3.getString(1)))
                {
                    position="Lecturer";
                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");
                }
            }

            ResultSet rs4 = stmt.executeQuery(query4);

            while(rs4.next())
            {
                if(Username.equals(rs4.getString(1)))
                {
                    position="Student";
                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME");
                }
            }



            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
            {
                if(Username.equals(rs.getString(1)) && Password.equals(rs.getString(7)))
                {
                    if(position=="Admin") {
                        setVisible(false);
                        AdminPanal view = new AdminPanal();
                        view.setVisible(true);

                    }
                    else  if(position=="Technical_Officer") {
                        setVisible(false);
                        Technical_officer view = new Technical_officer(Username);
                        view.setVisible(true);

                    }
                    else  if(position=="Lecturer") {
                        setVisible(false);
                        Dashboard view = new Dashboard(Username);
                        view.setVisible(true);

                    }
                    else  if(position=="Student") {
                        setVisible(false);
                        Student view = new Student(Username);
                        view.setVisible(true);

                    }
                }
                else
                {
                    errormsg.setVisible(true);
                    errormsg.setText("Incorrect USERNAME or PASSWORD");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }



    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JButton cancel;
    private JLabel errormsg;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JButton login;
    private JPasswordField password;
    private JTextField username;
    // End of variables declaration
}
