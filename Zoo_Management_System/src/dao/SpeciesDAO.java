package dao;

import model.Species;
import java.sql.*;
import java.util.*;


public class SpeciesDAO {

    public boolean addSpecies(Species s) {
        String sql = "INSERT INTO Species (SpeciesID, ScientificName, CommonName, Category, Avg_Lifespan) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }

        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, s.getSpeciesID());
            ps.setString(2, s.getScientificName());
            ps.setString(3, s.getCommonName());
            ps.setString(4, s.getCategory());
            ps.setInt(5, s.getAvgLifespan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    

    public boolean updateSpecies(Species s) {
        String sql = "UPDATE Species SET ScientificName=?, CommonName=?, Category=?, Avg_Lifespan=? WHERE SpeciesID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }

        	PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getScientificName());
            ps.setString(2, s.getCommonName());
            ps.setString(3, s.getCategory());
            ps.setInt(4, s.getAvgLifespan());
            ps.setInt(5, s.getSpeciesID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSpecies(int id) {
        String sql = "DELETE FROM Species WHERE SpeciesID=?";
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
    
    
    public static Species getSpeciesById(int speciesID) {
        String sql = "SELECT * FROM Species WHERE SpeciesID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, speciesID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Species(
                    rs.getInt("SpeciesID"),
                    rs.getString("ScientificName"),
                    rs.getString("CommonName"),
                    rs.getString("Category"),
                    rs.getInt("Avg_Lifespan")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public List<Species> getAllSpecies() {
        List<Species> list = new ArrayList<>();
        String sql = "SELECT * FROM Species";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Species s = new Species(
                        rs.getInt("SpeciesID"),
                        rs.getString("ScientificName"),
                        rs.getString("CommonName"),
                        rs.getString("Category"),
                        rs.getInt("Avg_Lifespan")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
