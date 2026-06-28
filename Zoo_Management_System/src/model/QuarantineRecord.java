package model;

public class QuarantineRecord {
    private int quarantineID;
    private int animalID;
    private int staffID;
    private String startDate;
    private String endDate;
    private String reason;
    private String location;

    public QuarantineRecord(int quarantineID, int animalID, int staffID, String startDate,
                            String endDate, String reason, String location) {
        this.quarantineID = quarantineID;
        this.animalID = animalID;
        this.staffID = staffID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.location = location;
    }

    public int getQuarantineID() { return quarantineID; }
    public void setQuarantineID(int quarantineID) { this.quarantineID = quarantineID; }
    public int getAnimalID() { return animalID; }
    public void setAnimalID(int animalID) { this.animalID = animalID; }
    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
