package lms.student;

import lms.DBConnector.MyDbConnector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ViewNotice extends JFrame{
    private JTable table1;
    private JPanel panViewNotice;
    private JButton viewButton;
    private JTextField txtGetnNoticeId;
    private JLabel lbldisplayNotice;
    private JButton backButton;
    private Connection con;

    public ViewNotice(Connection con,String userName){
        add(panViewNotice);
        setSize(1600,825);
        setTitle("Notices");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        this.con=con;

        int columnCount=0;
        String[] columnNames;
        Object[][] data;

        try {
            String query="SELECT Notice_ID,Submit_Date,Title,Department_ID FROM notice";
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd=rs.getMetaData();
            columnCount=rsmd.getColumnCount();
            columnNames = new String[columnCount];

            for (int i = 0; i < columnCount; i++) {
                columnNames[i]=rsmd.getColumnName(i+1);
            }
            data=new Object[10][columnCount];
            while(rs.next()){
                for (int j = 0; j < columnCount; j++) {
                    data[rs.getRow()-1][j]=rs.getString(j+1);
                }
            }



            table1.setModel(new DefaultTableModel(data,columnNames));

        } catch (Exception ex) {
            System.out.println(ex);
        }


        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txtGetNoticeId=txtGetnNoticeId.getText();
                int noticeId;
                try{
                    if(!txtGetNoticeId.isBlank()){
                        noticeId=Integer.parseInt(txtGetNoticeId);
                        System.out.println("Selected notice id: "+noticeId);
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM notice WHERE Notice_ID="+noticeId+";");

                        while (resultSet.next()){

                            BufferedImage bi = ImageIO.read(resultSet.getBinaryStream(4));
                            lbldisplayNotice.setIcon(new ImageIcon(bi));
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Enter valid Number");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Student view = new Student(null);
                view.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        MyDbConnector connector=new MyDbConnector();
        new ViewNotice(connector.getMyConnection(),"Lec_001");
    }
}