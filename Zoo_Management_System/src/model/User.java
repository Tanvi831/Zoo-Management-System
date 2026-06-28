package model;

public class User {
    private int userID;
    private String username;
    private String passwordHash;
    private String role;   // "ADMIN", "STAFF", "VET"
    private int staffID;

    public User() {}

    public User(int userID, String username, String passwordHash, String role, int staffID) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.staffID = staffID;
    }

    public int getUserID()            { return userID; }
    public String getUsername()       { return username; }
    public String getPasswordHash()   { return passwordHash; }
    public String getRole()           { return role; }
    public int getStaffID()           { return staffID; }

    public void setUserID(int id)          { this.userID = id; }
    public void setUsername(String u)      { this.username = u; }
    public void setPasswordHash(String p)  { this.passwordHash = p; }
    public void setRole(String r)          { this.role = r; }
    public void setStaffID(int id)         { this.staffID = id; }
}