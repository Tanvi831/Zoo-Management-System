package dao;

import model.DietDetails;
import java.sql.*;
import java.util.*;


public class DietDetailsDAO {

    public boolean addDiet(DietDetails d) {
        String sql = "INSERT INTO Diet_Details (DietID, Food_Name, Quantity) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, d.getDietID());
            ps.setString(2, d.getFoodName());
            ps.setString(3, d.getQuantity());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDiet(DietDetails d) {
        String sql = "UPDATE Diet_Details SET Food_Name=?, Quantity=? WHERE DietID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, d.getFoodName());
            ps.setString(2, d.getQuantity());
            ps.setInt(3, d.getDietID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDiet(int dietID) {
        String sql = "DELETE FROM Diet_Details WHERE DietID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, dietID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static DietDetails getDietById(int dietID) {
        String sql = "SELECT * FROM Diet_Details WHERE DietID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dietID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new DietDetails(
                    rs.getInt("DietID"),
                    rs.getString("Food_Name"),
                    rs.getString("Quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DietDetails> getAllDiet() {
        List<DietDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM Diet_Details";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DietDetails d = new DietDetails(
                        rs.getInt("DietID"),
                        rs.getString("Food_Name"),
                        rs.getString("Quantity")
                );
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
