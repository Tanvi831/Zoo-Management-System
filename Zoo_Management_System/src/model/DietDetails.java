package model;

public class DietDetails {
    private int dietID;
    private String foodName;
    private String quantity;

    public DietDetails(int dietID, String foodName, String quantity) {
        this.dietID = dietID;
        this.foodName = foodName;
        this.quantity = quantity;
    }

    public int getDietID() { return dietID; }
    public void setDietID(int dietID) { this.dietID = dietID; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
}
