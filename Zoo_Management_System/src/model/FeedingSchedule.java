package model;

public class FeedingSchedule {
    private int staffID;
    private int animalID;
    private int dietID;
    private String time;
    private String actualFoodQuantity;

    public FeedingSchedule(int staffID, int animalID, int dietID, String time, String actualFoodQuantity) {
        this.staffID = staffID;
        this.animalID = animalID;
        this.dietID = dietID;
        this.time = time;
        this.actualFoodQuantity = actualFoodQuantity;
    }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
    public int getAnimalID() { return animalID; }
    public void setAnimalID(int animalID) { this.animalID = animalID; }
    public int getDietID() { return dietID; }
    public void setDietID(int dietID) { this.dietID = dietID; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getActualFoodQuantity() { return actualFoodQuantity; }
    public void setActualFoodQuantity(String actualFoodQuantity) { this.actualFoodQuantity = actualFoodQuantity; }
}
