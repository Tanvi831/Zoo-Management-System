package model;

public class Notification {
    private int notifID;
    private String type;     // "VET_DUE", "FEEDING_DUE", "LOW_STOCK"
    private String message;
    private boolean isRead;
    private String createdAt;

    public Notification(int notifID, String type, String message, boolean isRead, String createdAt) {
        this.notifID = notifID;
        this.type = type;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public int getNotifID()     { return notifID; }
    public String getType()     { return type; }
    public String getMessage()  { return message; }
    public boolean isRead()     { return isRead; }
    public String getCreatedAt(){ return createdAt; }
    public void setRead(boolean r) { this.isRead = r; }
}