package model;

public class FoodInfo {
    private String foodName;
    private String description;
    private double cost;

    public FoodInfo(String foodName, String description, double cost) {
        this.foodName = foodName;
        this.description = description;
        this.cost = cost;
    }

    // Getters and Setters
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}
