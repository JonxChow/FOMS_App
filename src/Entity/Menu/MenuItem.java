package Entity.Menu;

import java.io.Serializable;


/**
 * Represents a menu item that can be serialized.
 */
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private String description;
    private String category;
    private int availability;

    /**
     * Constructs a new MenuItem object with the specified details.
     *
     * @param name         the name of the menu item
     * @param price        the price of the menu item
     * @param description  the description of the menu item
     * @param category     the category of the menu item
     * @param availability the availability count of the menu item
     */
    public MenuItem(String name, double price, String description, String category, int availability) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.availability = availability;
    }

    /**
     * Returns the name of the menu item.
     *
     * @return the name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param name the new name of the menu item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the menu item.
     *
     * @return the price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price the new price of the menu item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the description of the menu item.
     *
     * @return the description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     *
     * @param description the new description of the menu item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the category of the menu item.
     *
     * @return the category of the menu item
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the menu item.
     *
     * @param category the new category of the menu item
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the availability count of the menu item.
     *
     * @return the availability count of the menu item
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Sets the availability count of the menu item.
     *
     * @param availability the new availability count of the menu item
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    /**
     * Returns a string representation of the menu item details.
     *
     * @return a string representation of the menu item details
     */
    @Override
    public String toString() {
        return "Name: " + this.name +" Price: $" + this.price + " Category: " + this.category + " Description: " + this.description;
    }
}
