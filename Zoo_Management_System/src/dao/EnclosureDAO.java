package dao;

import model.Enclosure;
import java.sql.*;
import java.util.*;


public class EnclosureDAO {

    public boolean addEnclosure(Enclosure e) {
        String sql = "INSERT INTO Enclosure (EnclosureID, Name, Size, Type, EnclosureCapacity, StaffID) VALUES (?, ?, ?, ?, ?,?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, e.getEnclosureID());
            ps.setString(2, e.getName());
            ps.setString(3, e.getSize());
            ps.setString(4, e.getType());
            ps.setInt(5, e.getCapacity());
            ps.setInt(6, e.getStaffID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateEnclosure(Enclosure e) {
        String sql = "UPDATE Enclosure SET Name=?, Size=?, Type=?, EnclosureCapacity=?, StaffID=? WHERE EnclosureID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, e.getName());
            ps.setString(2, e.getSize());
            ps.setString(3, e.getType());
            ps.setInt(4, e.getCapacity());
            ps.setInt(5, e.getStaffID());
            ps.setInt(6, e.getEnclosureID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteEnclosure(int id) {
        String sql = "DELETE FROM Enclosure WHERE EnclosureID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static Enclosure getEnclosureById(int enclosureID) {
        String sql = "SELECT * FROM Enclosure WHERE EnclosureID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enclosureID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Enclosure(
                    rs.getInt("EnclosureID"),
                    rs.getString("Name"),
                    rs.getString("Size"),
                    rs.getString("Type"),
                    rs.getInt("EnclosureCapacity"),
                    rs.getInt("StaffID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Enclosure> getAllEnclosures() {
        List<Enclosure> list = new ArrayList<>();
        String sql = "SELECT * FROM Enclosure";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Enclosure e = new Enclosure(
                        rs.getInt("EnclosureID"),
                        rs.getString("Name"),
                        rs.getString("Size"),
                        rs.getString("Type"),
                        rs.getInt("EnclosureCapacity"),
                        rs.getInt("StaffID")
                );
                list.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}

