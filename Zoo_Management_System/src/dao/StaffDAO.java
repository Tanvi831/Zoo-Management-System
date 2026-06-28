package dao;

import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    // INSERT staff
    public static boolean addStaff(Staff staff) {
        String sql = "INSERT INTO Staff (StaffID, Name, Role, Contact, Salary, DOJ, Address ,Shift, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";
        
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staff.getStaffID());
            ps.setString(2, staff.getName());
            ps.setString(3, staff.getRole());
            ps.setLong(4, staff.getContact());
            ps.setDouble(5, staff.getSalary());
            ps.setString(6, staff.getDateOfJoining());
            ps.setString(7, staff.getAddress());
            ps.setString(8, staff.getShift());
            ps.setString(9, staff.getDob());

            int result = ps.executeUpdate();
            System.out.println("Rows inserted: " + result);
            return result > 0;
            
            


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // SELECT all staff
    public static List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
             }
       	    Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Staff s = new Staff();
                s.setStaffID(rs.getInt("StaffID"));
                s.setName(rs.getString("Name"));
                s.setRole(rs.getString("Role"));
                s.setDob(rs.getString("DOB"));
                s.setContact(rs.getLong("Contact"));
                s.setSalary(rs.getDouble("Salary"));
                s.setShift(rs.getString("Shift"));
                s.setDateOfJoining(rs.getString("DOJ"));
                s.setAddress(rs.getString("Address"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static Staff getStaffById(int staffID) {
        String sql = "SELECT * FROM Staff WHERE StaffID = ?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
       

        	 PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staffID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Staff s = new Staff();
                s.setStaffID(rs.getInt("StaffID"));
                s.setName(rs.getString("Name"));
                s.setRole(rs.getString("Role"));
                s.setContact(rs.getLong("Contact"));
                s.setSalary(rs.getDouble("Salary"));
                s.setDateOfJoining(rs.getString("DOJ"));
                s.setAddress(rs.getString("Address"));
                s.setShift(rs.getString("Shift"));
                s.setDob(rs.getString("DOB"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // UPDATE staff
    public static boolean updateStaff(Staff staff) {
        String sql = "UPDATE Staff SET Name=?, Role=?, DOB=?, Contact=?, Salary=?, DOJ=?, Address=?,Shift = ? WHERE StaffID=?";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	
        	 PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, staff.getName());
            ps.setString(2, staff.getRole());
            ps.setString(3, staff.getDob());
            ps.setLong(4, staff.getContact());
            ps.setDouble(5, staff.getSalary());
            ps.setString(6, staff.getDateOfJoining());
            ps.setString(7, staff.getAddress());
            ps.setString(8, staff.getShift());
            ps.setInt(9, staff.getStaffID());

            int result = ps.executeUpdate();
            System.out.println("Rows updated: " + result);
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE staff
    public static boolean deleteStaff(int staffID) {
        String sql = "DELETE FROM Staff WHERE StaffID=?";
      
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                conn = DBConnection.getConnection();
            }
        	 PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, staffID);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}





