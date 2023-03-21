import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

        private static void registerToDriver(){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Registered to the Driver Class");
            }
            catch(Exception e){
                System.out.println("Error: couldn't register to the Diver class: "+e);
            }
        }

        private static Connection establishConnection(){
            Connection conn=null;
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ooplms", "root", "");
                System.out.println("Connection established");

            }
            catch(Exception e){
                System.out.println("Error: couldn't create the connection: "+e);
            }
            return conn;
        }

        public static Connection getDBConnection(){
            registerToDriver();
            return establishConnection();
        }

}
