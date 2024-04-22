package Entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<>();
    }
    public List<MenuItem> getMenu(){
        return items;
    }
}
