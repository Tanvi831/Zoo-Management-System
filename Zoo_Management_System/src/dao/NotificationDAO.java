package dao;

import model.Notification;
import java.sql.*;
import java.util.*;

public class NotificationDAO {

    public static void insertNotification(String type, String message) {
        // Avoid duplicate alerts for same message today
        String checkSql = "SELECT COUNT(*) FROM Notifications WHERE Message = ? AND DATE(CreatedAt) = CURDATE()";
        String insertSql = "INSERT INTO Notifications (Type, Message) VALUES (?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setString(1, message);
            ResultSet rs = check.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                PreparedStatement ps = conn.prepareStatement(insertSql);
                ps.setString(1, type);
                ps.setString(2, message);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Notification> getUnread() {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE IsRead = FALSE ORDER BY CreatedAt DESC";
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Notification(
                    rs.getInt("NotifID"),
                    rs.getString("Type"),
                    rs.getString("Message"),
                    rs.getBoolean("IsRead"),
                    rs.getString("CreatedAt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void markAllRead() {
        try {
            Connection conn = DBConnection.getConnection();
            conn.createStatement().executeUpdate("UPDATE Notifications SET IsRead = TRUE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}