package dao;

import java.sql.*;
import java.util.concurrent.*;

public class AlertEngine {

    private static ScheduledExecutorService scheduler;

    // Call this once when the app starts (from ZooManagementUI constructor)
    public static void start() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        // Run immediately, then every 60 minutes
        scheduler.scheduleAtFixedRate(AlertEngine::runChecks, 0, 60, TimeUnit.MINUTES);
        System.out.println("Alert engine started.");
    }

    public static void stop() {
        if (scheduler != null) scheduler.shutdown();
    }

    private static void runChecks() {
        checkVetDue();
        checkLowStock();
        checkFeedingDue();
    }

    // Animals whose next checkup is within 3 days
    private static void checkVetDue() {
        String sql = "SELECT a.Name, mc.NextCheckupDate " +
                     "FROM MedicalCheckup mc " +
                     "JOIN Animal a ON mc.AnimalID = a.AnimalID " +
                     "WHERE mc.NextCheckupDate BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 3 DAY)";
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String msg = "Vet checkup due for " + rs.getString("Name") +
                             " on " + rs.getString("NextCheckupDate");
                NotificationDAO.insertNotification("VET_DUE", msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Food items below minimum threshold
    private static void checkLowStock() {
        String sql = "SELECT FoodName, CurrentStock, MinThreshold, Unit " +
                     "FROM Inventory WHERE CurrentStock < MinThreshold";
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String msg = "Low stock: " + rs.getString("FoodName") +
                             " — only " + rs.getDouble("CurrentStock") +
                             rs.getString("Unit") + " left (min: " +
                             rs.getDouble("MinThreshold") + rs.getString("Unit") + ")";
                NotificationDAO.insertNotification("LOW_STOCK", msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Feeding schedules that have no log entry for today
    private static void checkFeedingDue() {
        // Checks if today's scheduled feeding time has passed but no record logged
        String sql = "SELECT a.Name, fs.Time FROM Feeding_Schedule fs " +
                     "JOIN Animal a ON fs.AnimalID = a.AnimalID " +
                     "WHERE TIME(fs.Time) < CURTIME() " +
                     "AND fs.AnimalID NOT IN (" +
                     "  SELECT AnimalID FROM Feeding_Log WHERE DATE(FedAt) = CURDATE())";
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String msg = "Feeding overdue for " + rs.getString("Name") +
                             " (scheduled " + rs.getString("Time") + ")";
                NotificationDAO.insertNotification("FEEDING_DUE", msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}