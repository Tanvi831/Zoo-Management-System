package dao;

import model.QuarantineRecord;
import java.sql.*;
import java.util.*;


public class QuarantineRecordDAO {

    public boolean addQuarantine(QuarantineRecord q) {
        String sql = "INSERT INTO QuarantineRecord (QuarantineID,AnimalID, StaffID, StartDate, EndDate, Reason, Location) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, q.getQuarantineID());
            ps.setInt(2, q.getAnimalID());
            ps.setInt(3, q.getStaffID());
            ps.setString(4, q.getStartDate());
            ps.setString(5, q.getEndDate());
            ps.setString(6, q.getReason());
            ps.setString(7, q.getLocation());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQuarantine(QuarantineRecord q) {
        String sql = "UPDATE QuarantineRecord SET AnimalID=?, StaffID=?, StartDate=?, EndDate=?, Reason=?, Location=? WHERE QuarantineID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, q.getAnimalID());
            ps.setInt(2, q.getStaffID());
            ps.setString(3, q.getStartDate());
            ps.setString(4, q.getEndDate());
            ps.setString(5, q.getReason());
            ps.setString(6, q.getLocation());
            ps.setInt(7, q.getQuarantineID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteQuarantine(int id) {
        String sql = "DELETE FROM QuarantineRecord WHERE QuarantineID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static QuarantineRecord getQuarantineById(int quarantineID) {
        String sql = "SELECT * FROM QuarantineRecord WHERE QuarantineID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quarantineID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new QuarantineRecord(
                    rs.getInt("QuarantineID"),
                    rs.getInt("AnimalID"),
                    rs.getInt("StaffID"),
                    rs.getString("StartDate"),
                    rs.getString("EndDate"),
                    rs.getString("Reason"),
                    rs.getString("Location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<QuarantineRecord> getAllQuarantines() {
        List<QuarantineRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM QuarantineRecord";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                QuarantineRecord q = new QuarantineRecord(
                        rs.getInt("QuarantineID"),
                        rs.getInt("AnimalID"),
                        rs.getInt("StaffID"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate"),
                        rs.getString("Reason"),
                        rs.getString("Location")
                );
                list.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
