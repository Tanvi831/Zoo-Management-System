package model;

public class Animal {
    private int animalID;
    private String name;
    private String dob;
    private String gender;
    private String arrivalDate;
    private int speciesID;
    private int dietID;
    private int enclosureID;

    public Animal(int animalID, String name, String dob, String gender, String arrivalDate,
                  int speciesID, int dietID, int enclosureID) {
        this.animalID = animalID;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.arrivalDate = arrivalDate;
        this.speciesID = speciesID;
        this.dietID = dietID;
        this.enclosureID = enclosureID;
    }

    // Getters and Setters
    public int getAnimalID() { return animalID; }
    public void setAnimalID(int animalID) { this.animalID = animalID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    public int getSpeciesID() { return speciesID; }
    public void setSpeciesID(int speciesID) { this.speciesID = speciesID; }
    public int getDietID() { return dietID; }
    public void setDietID(int dietID) { this.dietID = dietID; }
    public int getEnclosureID() { return enclosureID; }
    public void setEnclosureID(int enclosureID) { this.enclosureID = enclosureID; }
}
