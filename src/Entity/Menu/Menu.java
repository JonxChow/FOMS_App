package Entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu containing a list of menu items.
 */
public class Menu {
    private final List<MenuItem> items;

    /**
     * Constructs a new Menu object with an empty list of menu items.
     */
    public Menu() {
        this.items = new ArrayList<>();
    }

    /**
     * Retrieves the list of menu items in this menu.
     *
     * @return A list of MenuItem objects representing the menu items.
     */
    public List<MenuItem> getMenu(){
        return items;
    }
}
