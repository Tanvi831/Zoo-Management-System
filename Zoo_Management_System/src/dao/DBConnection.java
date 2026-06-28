
package dao;
import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/zoo_management"; 
    private static final String USER = "root";  // your DB username
    private static final String PASSWORD = "root"; // your DB password

    private static Connection conn;

    // Method to get connection
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // load MySQL driver
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                conn.setAutoCommit(true);
                System.out.println("Database connected successfully!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Database connection failed!");
            }
        }
       
        return conn;
    }
}
