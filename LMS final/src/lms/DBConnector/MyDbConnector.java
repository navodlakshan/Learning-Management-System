package lms.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDbConnector {

    private Connection myConnection = null;

    private String url = "jdbc:mysql://localhost:3306/ooplms";

    private String user = "root";

    private String pw = "";

    private void registerMyConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully Registered to the Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in registering the diver" + ex.getMessage());
        }
    }


    public Connection getMyConnection(){

        registerMyConnection();

        try {
            myConnection = DriverManager.getConnection(url, user, pw);
            System.out.println("Successfully Connected to the Database");
        } catch (SQLException ex) {
            System.out.println("Error in getting lms.connection");
        }
        return myConnection;
    }
}
