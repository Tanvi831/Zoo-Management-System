package dao;

import model.Sponsorship;
import java.sql.*;
import java.util.*;


public class SponsorshipDAO {

    public boolean addSponsorship(Sponsorship s) {
        String sql = "INSERT INTO Sponsorship (SponsorshipID, DonorID, AnimalID, StartDate, EndDate, Amount) VALUES (?, ?, ?, ?, ?,?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, s.getSponsorshipID());
            ps.setInt(2, s.getDonorID());
            ps.setInt(3, s.getAnimalID());
            ps.setString(4, s.getStartDate());
            ps.setString(5, s.getEndDate());
            ps.setDouble(6, s.getAmount());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSponsorship(Sponsorship s) {
        String sql = "UPDATE Sponsorship SET DonorID=?, AnimalID=?, StartDate=?, EndDate=?, Amount=? WHERE SponsorshipID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, s.getDonorID());
            ps.setInt(2, s.getAnimalID());
            ps.setString(3, s.getStartDate());
            ps.setString(4, s.getEndDate());
            ps.setDouble(5, s.getAmount());
            ps.setInt(6, s.getSponsorshipID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSponsorship(int id) {
        String sql = "DELETE FROM Sponsorship WHERE SponsorshipID=?";
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
    
    public static Sponsorship getSponsorshipById(int spnID) {
        String sql = "SELECT * FROM Sponsorship WHERE SponsorshipID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, spnID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Sponsorship(
                    rs.getInt("SponsorshipID"),
                    rs.getInt("DonorID"),
                    rs.getInt("AnimalID"),
                    rs.getString("StartDate"),
                    rs.getString("EndDate"),
                    rs.getDouble("Amount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sponsorship> getAllSponsorships() {
        List<Sponsorship> list = new ArrayList<>();
        String sql = "SELECT * FROM Sponsorship";
       
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Sponsorship s = new Sponsorship(
                        rs.getInt("SponsorshipID"),
                        rs.getInt("DonorID"),
                        rs.getInt("AnimalID"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate"),
                        rs.getDouble("Amount")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
