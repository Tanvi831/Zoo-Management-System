package dao;
import java.sql.*;
public class Test {
    public static void main(String[] args) {
        try (Connection conn = dao.DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to DB");
                System.out.println("Connected to: " + conn.getMetaData().getURL());

                var ps = conn.prepareStatement("INSERT INTO Staff(Name, Role) VALUES(?, ?)");
                ps.setString(1, "Test User");
                ps.setString(2, "Caretaker");
                ps.executeUpdate();
                System.out.println("Insert done!");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

