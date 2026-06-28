package model;

public class Donor {
    private int donorID;
    private String donorName;
    private String contact;
    private String donorAddr;

    public Donor(int donorID, String donorName, String contact, String donorAddr) {
        this.donorID = donorID;
        this.donorName = donorName;
        this.contact = contact;
        this.donorAddr = donorAddr;
    }

    public int getDonorID() { return donorID; }
    public void setDonorID(int donorID) { this.donorID = donorID; }
    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getDonorAddr() { return donorAddr; }
    public void setDonorAddr(String donorAddr) { this.donorAddr = donorAddr; }
}
