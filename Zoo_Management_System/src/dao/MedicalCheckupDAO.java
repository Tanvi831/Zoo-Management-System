package dao;

import model.MedicalCheckup;
import java.sql.*;
import java.util.*;


public class MedicalCheckupDAO {

    public boolean addCheckup(MedicalCheckup m) {
        String sql = "INSERT INTO MedicalCheckup (CheckupID, AnimalID, StaffID, Date, Diagnosis, Treatment, Next_Checkup_Date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, m.getCheckupID());
            ps.setInt(2, m.getAnimalID());
            ps.setInt(3, m.getStaffID());
            ps.setString(4, m.getDate());
            ps.setString(5, m.getDiagnosis());
            ps.setString(6, m.getTreatment());
            ps.setString(7, m.getNextCheckupDate());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCheckup(MedicalCheckup m) {
        String sql = "UPDATE MedicalCheckup SET AnimalID=?, StaffID=?, Date=?, Diagnosis=?, Treatment=?, Next_Checkup_Date=? WHERE CheckupID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getAnimalID());
            ps.setInt(2, m.getStaffID());
            ps.setString(3, m.getDate());
            ps.setString(4, m.getDiagnosis());
            ps.setString(5, m.getTreatment());
            ps.setString(6, m.getNextCheckupDate());
            ps.setInt(7, m.getCheckupID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCheckup(int id) {
        String sql = "DELETE FROM MedicalCheckup WHERE CheckupID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static MedicalCheckup getCheckupById(int checkupID) {
        String sql = "SELECT * FROM MedicalCheckup WHERE CheckupID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, checkupID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new MedicalCheckup(
                    rs.getInt("CheckupID"),
                    rs.getInt("AnimalID"),
                    rs.getInt("StaffID"),
                    rs.getString("Date"),
                    rs.getString("Diagnosis"),
                    rs.getString("Treatment"),
                    rs.getString("Next_Checkup_Date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MedicalCheckup> getAllCheckups() {
        List<MedicalCheckup> list = new ArrayList<>();
        String sql = "SELECT * FROM MedicalCheckup";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                MedicalCheckup m = new MedicalCheckup(
                        rs.getInt("CheckupID"),
                        rs.getInt("AnimalID"),
                        rs.getInt("StaffID"),
                        rs.getString("Date"),
                        rs.getString("Diagnosis"),
                        rs.getString("Treatment"),
                        rs.getString("Next_Checkup_Date")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
