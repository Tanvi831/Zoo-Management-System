package model;

public class MedicalCheckup {
    private int checkupID;
    private int animalID;
    private int staffID;
    private String date;
    private String diagnosis;
    private String treatment;
    private String nextCheckupDate;

    public MedicalCheckup(int checkupID, int animalID, int staffID, String date, String diagnosis,
                          String treatment, String nextCheckupDate) {
        this.checkupID = checkupID;
        this.animalID = animalID;
        this.staffID = staffID;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.nextCheckupDate = nextCheckupDate;
    }

    public int getCheckupID() { return checkupID; }
    public void setCheckupID(int checkupID) { this.checkupID = checkupID; }
    public int getAnimalID() { return animalID; }
    public void setAnimalID(int animalID) { this.animalID = animalID; }
    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public String getNextCheckupDate() { return nextCheckupDate; }
    public void setNextCheckupDate(String nextCheckupDate) { this.nextCheckupDate = nextCheckupDate; }
}
