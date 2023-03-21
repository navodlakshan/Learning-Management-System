import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class ViewNotice extends JFrame{
    private JPanel panNotice;
    private JButton cancelButton;
    private JTable noticeTable;
    String idList;
    String dateList;
    String descriptionList;
    String departmentList;
    ViewNotice(String userID){
        add(panNotice);
        setSize(800,600);
        setTitle("LMS-View Notice");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(310,90);
        setVisible(true);

        Connection connection=DatabaseConnection.getDBConnection();
        try{
            String sql="SELECT Notice_ID,Submit_Date,Title,Department_ID FROM notice";
            Statement stmt=connection.createStatement();
            ResultSet result=stmt.executeQuery(sql);

            int rowCount=0;
            while(result.next()){rowCount++;}

            Object[][] data=new Object[rowCount][4];

            result=stmt.executeQuery(sql);

            while(result.next()){

                idList=Integer.toString(result.getInt(1));
                dateList=result.getString(2);
                descriptionList=result.getString(3);
                departmentList=result.getString(4);

                data[result.getRow()-1][0]=idList;
                data[result.getRow()-1][1]=dateList;
                data[result.getRow()-1][2]=descriptionList;
                data[result.getRow()-1][3]=departmentList;

                System.out.println(result.getRow()+":-----");
                System.out.println(idList);
                System.out.println(dateList);
                System.out.println(descriptionList);
                System.out.println(departmentList);
            }
            System.out.println(Arrays.deepToString(data));
            noticeTable.setModel(new DefaultTableModel(data,new String[] {"ID","Date","Description","Department"}));

        }catch(Exception e){
            System.out.println("Error: "+e);
        }

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        /*downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement statement= connection.createStatement();
                    ResultSet resultSet= statement.executeQuery("SELECT Notice FROM notice WHERE Notice_ID=1");
                    resultSet.next();

                    Blob blob = resultSet.getBlob("Notice");
                    InputStream inputStream = blob.getBinaryStream();
                    OutputStream outStream = new FileOutputStream("c:\\yyy.png");
                    outStream.write(inputStream.readAllBytes());
                    outStream.close();
                    JOptionPane.showMessageDialog(null,"file saved to c:\\ location");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });*/
    }

    public static void main(String[] args) {
       // new ViewNotice("Lec_003");
    }
}
