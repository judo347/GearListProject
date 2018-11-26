package baseWithFXML.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PackingList {

    private ArrayList<Integer> itemIds;
    private String name;

    public PackingList(String name) {
        this.name = name;
        itemIds = new ArrayList<>();
    }

    /***/
    public ObservableList<ItemChecked> getList(ObservableList<Item> fullItemsList){

        ArrayList<Item> inputList = new ArrayList<>(fullItemsList);
        ArrayList<ItemChecked> returnList = new ArrayList<>();

        //Add items that has id matching
        for (Item item : new ArrayList<>(inputList)) {
            if(isItemOnList(item)){
                returnList.add(new ItemChecked(item, true, this));
                inputList.remove(item);
            }
        }

        //Add the rest us ItemsChecked false
        for (Item item : inputList) {
            returnList.add(new ItemChecked(item, false, this));
        }

        return FXCollections.observableArrayList(returnList);
    }

    /** @return true if the given items id is on this' id list. */
    private boolean isItemOnList(Item item){
        for (Integer itemId : itemIds) {
            if(item.getId() == itemId)
                return true;
        }

        return false;
    }

    /** Gets called when an item on this list changed checked state. */
    public void itemHasBeenTriggered(ItemChecked item){
        if(isItemOnList(item)){ //TODO BUGGED
            for (Integer id : itemIds) {
                if(id == item.getId())
                    itemIds.remove(id);
            }
        }else
            itemIds.add(item.getId());
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
