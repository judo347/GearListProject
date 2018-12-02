package baseWithFXML.model;

import baseWithFXML.utils.OwnFileManager;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datamodel {

    private ArrayList<Item> items;
    private int currentHighestId; //Used to determine the next id to give an item.
    private ArrayList<PackingList> packingLists;

    public Datamodel(ArrayList<Item> itemsList, ArrayList<PackingList> packingLists){

        this.items = itemsList;
        this.packingLists = packingLists;
        this.currentHighestId = getHighestIdFromList(items);
    }

    /** @return the highest id in the given list of items. */
    private int getHighestIdFromList(List<Item> list){

        int highestCounter = 0;
        for (Item item : list) {
            if(item.getId() > highestCounter)
                highestCounter = item.getId();
        }

        return highestCounter;
    }

    /** Saves the list to a file. Should be called when the main window is closed. */
    public void saveList(){
        OwnFileManager.saveDatamodel(this);
    }

    /** STATS: total weight. */
    public int getTotalWeight(){
        return getTotalWeight(items);
    }

    /** STATS: total weight. */
    public int getTotalWeight(List<Item> items){
        int totalWeight = 0;

        for (Item item : items) {
            if(!item.getWeightInGrams().equals(""))
                totalWeight += Integer.parseInt(item.getWeightInGrams());
        }

        return totalWeight;
    }

    /** STATS: total price. */
    public int getTotalPrice(){
        return getTotalPrice(items);
    }

    /** STATS: total price. */
    public int getTotalPrice(List<Item> items){
        int totalPrice = 0;

        for (Item item : items) {
            if(!item.getPriceInDKK().equals(""))
                totalPrice += Integer.parseInt(item.getPriceInDKK());
        }

        return totalPrice;
    }

    /** @return the id for the next item added to this list. */
    public int getNextId(){
        return ++currentHighestId;
    }


    //-----------ADD/REMOVE section----------
    /** Removes the given items from the list. */
    public void removeItem(Item ... item){
        items.removeAll(Arrays.asList(item));
    }

    /** Adds the given items to the list. */
    public void addItem(Item ... item){
        items.addAll(Arrays.asList(item));
    }

    public void addToPackingList(PackingList ... pl){
        packingLists.addAll(Arrays.asList(pl));
    }

    public void removeFromPackingList(PackingList pl){
        packingLists.removeAll(Arrays.asList(pl));
    }


    //----------GETTERS/SETTERS---------

    public ArrayList<PackingList> getPackingLists() {
        return packingLists;
    }

    public ArrayList<Item> getDataListArrayList(){
        return new ArrayList<>(items);
    }

    public ArrayList<Item> getDataList() {
        return items;
    }

    public int getCurrentHighestId() {
        return currentHighestId;
    }
}
