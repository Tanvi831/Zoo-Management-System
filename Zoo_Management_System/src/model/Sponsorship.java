package model;

public class Sponsorship {
    private int sponsorshipID;
    private int donorID;
    private int animalID;
    private String startDate;
    private String endDate;
    private double amount;

    public Sponsorship(int sponsorshipID, int donorID, int animalID, String startDate, String endDate, double amount) {
        this.sponsorshipID = sponsorshipID;
        this.donorID = donorID;
        this.animalID = animalID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public int getSponsorshipID() { return sponsorshipID; }
    public void setSponsorshipID(int sponsorshipID) { this.sponsorshipID = sponsorshipID; }
    public int getDonorID() { return donorID; }
    public void setDonorID(int donorID) { this.donorID = donorID; }
    public int getAnimalID() { return animalID; }
    public void setAnimalID(int animalID) { this.animalID = animalID; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
