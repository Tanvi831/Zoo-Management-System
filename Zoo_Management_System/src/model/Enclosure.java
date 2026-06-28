package model;

public class Enclosure {
    private int enclosureID;
    private String name;
    private String size;
    private String type;
    private int capacity;
    private int staffID;

    public Enclosure(int enclosureID, String name, String size, String type, int capacity, int staffID) {
        this.enclosureID = enclosureID;
        this.name = name;
        this.size = size;
        this.type = type;
        this.capacity = capacity;
        this.staffID = staffID;
    }

    // Getters and Setters
    public int getEnclosureID() { return enclosureID; }
    public void setEnclosureID(int enclosureID) { this.enclosureID = enclosureID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
}
