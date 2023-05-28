package lms.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    private String url = "jdbc:mysql://localhost:3306/ooplms";
    private String user ="root";
    private String pw ="6438";

    private static Connection myCon = null;
    private static void registerToDriver(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully registered...");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error: couldn't register to the Diver class: "+e.getMessage());
        }
    }

    public static Connection establishConnection(){
        //Connection conn=null;
        try{
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/ooplms", "root", "6438");
            System.out.println("Connection established");
        }
        catch(SQLException e){
            System.out.println("Error: couldn't create the connection: "+e.getMessage());
        }
        return myCon;
    }

    public static Connection getDBConnection(){
        registerToDriver();
        return establishConnection();
    }


}
