package dao;

import model.Donor;
import java.sql.*;
import java.util.*;


public class DonorDAO {

    public boolean addDonor(Donor d) {
        String sql = "INSERT INTO Donor (DonorID, Donor_Name, Contact, Donor_Addr) VALUES (?, ?, ?,?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, d.getDonorID());
            ps.setString(2, d.getDonorName());
            ps.setString(3, d.getContact());
            ps.setString(4, d.getDonorAddr());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDonor(Donor d) {
        String sql = "UPDATE Donor SET Donor_Name=?, Contact=?, Donor_Addr=? WHERE DonorID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, d.getDonorName());
            ps.setString(2, d.getContact());
            ps.setString(3, d.getDonorAddr());
            ps.setInt(4, d.getDonorID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDonor(int donorID) {
        String sql = "DELETE FROM Donor WHERE DonorID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, donorID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Donor getDonorById(int donorID) {
        String sql = "SELECT * FROM Donor WHERE DonorID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, donorID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Donor(
                    rs.getInt("DonorID"),
                    rs.getString("Donor_Name"),
                    rs.getString("Contact"),
                    rs.getString("Donor_Addr")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Donor> getAllDonors() {
        List<Donor> list = new ArrayList<>();
        String sql = "SELECT * FROM Donor";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Donor d = new Donor(
                        rs.getInt("DonorID"),
                        rs.getString("Donor_Name"),
                        rs.getString("Contact"),
                        rs.getString("Donor_Addr")
                );
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
