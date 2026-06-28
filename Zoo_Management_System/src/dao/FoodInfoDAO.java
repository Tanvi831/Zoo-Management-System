package dao;

import model.FoodInfo;
import java.sql.*;
import java.util.*;


public class FoodInfoDAO {

    public boolean addFood(FoodInfo f) {
        String sql = "INSERT INTO Food_Info (Food_Name, Description, Cost) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, f.getFoodName());
            ps.setString(2, f.getDescription());
            ps.setDouble(3, f.getCost());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFood(FoodInfo f) {
        String sql = "UPDATE Food_Info SET Description=?, Cost=? WHERE Food_Name=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, f.getDescription());
            ps.setDouble(2, f.getCost());
            ps.setString(3, f.getFoodName());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFood(String foodName) {
        String sql = "DELETE FROM Food_Info WHERE Food_Name=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, foodName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static FoodInfo getFoodByName(String foodName) {
        String sql = "SELECT * FROM Food_Info WHERE Food_Name = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, foodName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new FoodInfo(
                    rs.getString("Food_Name"),
                    rs.getString("Description"),
                    rs.getDouble("Cost")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<FoodInfo> getAllFood() {
        List<FoodInfo> list = new ArrayList<>();
        String sql = "SELECT * FROM Food_Info";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FoodInfo f = new FoodInfo(
                        rs.getString("Food_Name"),
                        rs.getString("Description"),
                        rs.getDouble("Cost")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
