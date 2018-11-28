package baseWithFXML.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PackingList {

    private ArrayList<Integer> itemIds;
    private String name;

    public PackingList(String name) {
        this.name = name;
        itemIds = new ArrayList<>();
    }

    /** @return a list of ItemChecked that contains all items, with the ones on this list checked. */
    public ObservableList<ItemChecked> getFullList(ObservableList<Item> fullItemsList){

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

    /** @return a list of the items on this list. */
    public ArrayList<Item> getCheckedItemsList(List<Item> fullItemsList){

        ArrayList<Item> inputList = new ArrayList<>(fullItemsList);
        ArrayList<Item> returnList = new ArrayList<>();

        //Add items that has id matching
        for (Item item : new ArrayList<>(inputList)) {
            if(isItemOnList(item)){
                returnList.add(item.getCopy());
                inputList.remove(item);
            }
        }

        return returnList;
    }

    public int getTotalWeight(Datamodel datamodel){
        return datamodel.getTotalWeight(getCheckedItemsList(datamodel.getDataList()));
    }

    public int getTotalPrice(Datamodel datamodel){
        return datamodel.getTotalPrice(getCheckedItemsList(datamodel.getDataList()));
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

    public ArrayList<Integer> getItemIds() {
        return itemIds;
    }

    public void addIdsToList(ArrayList<Integer> ids){
        itemIds.addAll(ids);
    }
}
