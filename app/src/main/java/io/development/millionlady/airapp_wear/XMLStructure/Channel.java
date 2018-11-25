package io.development.millionlady.airapp_wear.XMLStructure;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String title;
    private ArrayList<Item> items;

    public Channel() {
        title = new String();
        items = new ArrayList<Item>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItemToList(Item item) {
        items.add(item);
    }
}
