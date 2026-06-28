package dao;

import model.FeedingSchedule;
import java.sql.*;
import java.util.*;


public class FeedingScheduleDAO {

    public boolean addFeeding(FeedingSchedule f) {
        String sql = "INSERT INTO Feeding_Schedule (StaffID, AnimalID, DietID, Time, ActualFoodQuantity) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, f.getStaffID());
            ps.setInt(2, f.getAnimalID());
            ps.setInt(3, f.getDietID());
            ps.setString(4, f.getTime());
            ps.setString(5, f.getActualFoodQuantity());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFeeding(FeedingSchedule f) {
        String sql = "UPDATE Feeding_Schedule SET Time=?, ActualFoodQuantity=? WHERE StaffID=? AND AnimalID=? AND DietID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, f.getTime());
            ps.setString(2, f.getActualFoodQuantity());
            ps.setInt(3, f.getStaffID());
            ps.setInt(4, f.getAnimalID());
            ps.setInt(5, f.getDietID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFeeding(int staffID, int animalID, int dietID) {
        String sql = "DELETE FROM Feeding_Schedule WHERE StaffID=? AND AnimalID=? AND DietID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, staffID);
            ps.setInt(2, animalID);
            ps.setInt(3, dietID);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static FeedingSchedule getFeedingById(int staffID, int animalID, int dietID) {
        String sql = "SELECT * FROM Feeding_Schedule WHERE StaffID = ? AND AnimalID = ? AND DietID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staffID);
            ps.setInt(2, animalID);
            ps.setInt(3, dietID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new FeedingSchedule(
                    rs.getInt("StaffID"),
                    rs.getInt("AnimalID"),
                    rs.getInt("DietID"),
                    rs.getString("Time"),
                    rs.getString("ActualFoodQuantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FeedingSchedule> getAllFeedings() {
        List<FeedingSchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM Feeding_Schedule";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FeedingSchedule f = new FeedingSchedule(
                        rs.getInt("StaffID"),
                        rs.getInt("AnimalID"),
                        rs.getInt("DietID"),
                        rs.getString("Time"),
                        rs.getString("ActualFoodQuantity")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
