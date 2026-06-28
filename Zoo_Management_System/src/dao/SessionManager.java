package dao;

import model.User;

public class SessionManager {
    private static User currentUser;

    public static void setCurrentUser(User user) { currentUser = user; }
    public static User getCurrentUser()          { return currentUser; }
    public static String getRole()               { return currentUser != null ? currentUser.getRole() : ""; }

    public static boolean isAdmin() { return "ADMIN".equals(getRole()); }
    public static boolean isVet()   { return "VET".equals(getRole()); }
    public static boolean isStaff() { return "STAFF".equals(getRole()); }

    public static void logout()     { currentUser = null; }
}