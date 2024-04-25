package Entity.Menu;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private String description;
    private String category;

    private int availability;

    public MenuItem(String name, double price, String description, String category, int availability) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.availability = availability;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Name: " + this.name +" Price: $" + this.price + " Category: " + this.category + " Description: " + this.description;
    }
}
