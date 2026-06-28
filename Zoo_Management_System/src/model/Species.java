package model;

public class Species {
    private int speciesID;
    private String scientificName;
    private String commonName;
    private String category;
    private int avgLifespan;

    public Species(int speciesID, String scientificName, String commonName, String category, int avgLifespan) {
        this.speciesID = speciesID;
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.category = category;
        this.avgLifespan = avgLifespan;
    }

    // Getters and Setters
    public int getSpeciesID() { return speciesID; }
    public void setSpeciesID(int speciesID) { this.speciesID = speciesID; }
    public String getScientificName() { return scientificName; }
    public void setScientificName(String scientificName) { this.scientificName = scientificName; }
    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getAvgLifespan() { return avgLifespan; }
    public void setAvgLifespan(int avgLifespan) { this.avgLifespan = avgLifespan; }
}

