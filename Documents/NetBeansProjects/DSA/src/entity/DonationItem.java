package entity;

import java.io.Serializable;

public class DonationItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double value;
    private String category;
    private boolean isCash;
    
    public DonationItem(String name, double value, String category, boolean isCash) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.isCash = isCash;
    }
    
    // Getter for value
    public double getValue() {
        return value;
    }

    // Getters for other fields
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCash() {
        return isCash;
    }

    // Setters for all fields
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCash(boolean isCash) {
        this.isCash = isCash;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f (%s)", name, value, isCash ? "Cash" : "Kind");
    }
}
