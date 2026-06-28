package dao;

import model.Animal;
import java.sql.*;
import java.util.*;


public class AnimalDAO {

    public boolean addAnimal(Animal a) {
        String sql = "INSERT INTO Animal (AnimalID, Name, DOB, Gender, Arrival_Date, SpeciesID, DietID, EnclosureID) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, a.getAnimalID());
            ps.setString(2, a.getName());
            ps.setString(3, a.getDob());
            ps.setString(4, a.getGender());
            ps.setString(5, a.getArrivalDate());
            ps.setInt(6, a.getSpeciesID());
            ps.setInt(7, a.getDietID());
            ps.setInt(8, a.getEnclosureID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAnimal(Animal a) {
        String sql = "UPDATE Animal SET Name=?, DOB=?, Gender=?, Arrival_Date=?, SpeciesID=?, DietID=?, EnclosureID=? WHERE AnimalID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getName());
            ps.setString(2, a.getDob());
            ps.setString(3, a.getGender());
            ps.setString(4, a.getArrivalDate());
            ps.setInt(5, a.getSpeciesID());
            ps.setInt(6, a.getDietID());
            ps.setInt(7, a.getEnclosureID());
            ps.setInt(8, a.getAnimalID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAnimal(int animalID) {
        String sql = "DELETE FROM Animal WHERE AnimalID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, animalID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Animal getAnimalById(int animalID) {
        String sql = "SELECT * FROM Animal WHERE AnimalID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, animalID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Animal(
                    rs.getInt("AnimalID"),
                    rs.getString("Name"),
                    rs.getString("DOB"),
                    rs.getString("Gender"),
                    rs.getString("Arrival_Date"),
                    rs.getInt("SpeciesID"),
                    rs.getInt("DietID"),
                    rs.getInt("EnclosureID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Animal> getAllAnimals() {
        List<Animal> list = new ArrayList<>();
        String sql = "SELECT * FROM Animal";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Animal a = new Animal(
                        rs.getInt("AnimalID"),
                        rs.getString("Name"),
                        rs.getString("DOB"),
                        rs.getString("Gender"),
                        rs.getString("Arrival_Date"),
                        rs.getInt("SpeciesID"),
                        rs.getInt("DietID"),
                        rs.getInt("EnclosureID")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
