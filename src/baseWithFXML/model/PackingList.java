package baseWithFXML.model;

import java.util.ArrayList;

public class PackingList {

    private ArrayList<Integer> itemIds;
    private String name;

    public PackingList(String name) {
        this.name = name;
        itemIds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
