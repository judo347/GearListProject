package baseWithFXML.model;

import baseWithFXML.utils.OwnFileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;

public class Datamodel {

    private ObservableList<Item> items;
    private OwnFileManager ofm;
    private int idCounter;
    private ArrayList<PackingList> packingLists;

    public Datamodel() {
        items = FXCollections.observableArrayList();
        packingLists = new ArrayList<>();
        ofm = new OwnFileManager();
        tempTestTodoFillPackingList();

        //TODO LOAD PACKINGLSITS

        loadFile();
        idCounter = getHighestIdFromList(items) + 1;
    }

    private void tempTestTodoFillPackingList(){
        packingLists.add(new PackingList("Heloo 1"));
        packingLists.add(new PackingList("Heloo 2"));
        packingLists.add(new PackingList("Heloo 3"));
        packingLists.add(new PackingList("Heloo 4"));
    }

    /** @return the highest id in the given list of items. */
    private int getHighestIdFromList(ObservableList<Item> list){

        ArrayList<Item> itemsArray = new ArrayList<>();
        for (Item item : list)
            itemsArray.add(item);

        int highestCounter = 0;
        for (Item item : itemsArray) {
            if(item.getId() > highestCounter)
                highestCounter = item.getId();
        }

        return highestCounter;
    }

    private void loadFile(){
        items = ofm.fillObservableList(items);
        packingLists = ofm.loadPackingListsFromFile();
    }

    //TODO someway of removing an item.

    /** Saves the list to a file. Should be called when the windows is closed. */
    public void saveList(){

        ArrayList<Item> itemsList = new ArrayList<>();
        for (Item item : items) {
            itemsList.add(item);
        }

        ofm.saveInformationToFile(itemsList);

        ofm.savePackingLitsToFile(getPackingLists());
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void addItem(Item ... item){
        items.addAll(Arrays.asList(item));
    }

    public ObservableList<Item> getDataList() {
        return items;
    }

    public ArrayList<Item> getDataListArrayList(){

        ArrayList<Item> returnList = new ArrayList<>();

        for (Item item : items) {
            returnList.add(item);
        }

        return returnList;
    }

    /** STATS: total weight. */
    public int getTotalWeight(){
        int totalWeight = 0;

        for (Item item : items) {
            if(!item.getWeightInGrams().equals(""))
                totalWeight += Integer.parseInt(item.getWeightInGrams());
        }

        return totalWeight;
    }

    /** STATS: total price. */
    public int getTotalPrice(){
        int totalPrice = 0;

        for (Item item : items) {
            if(!item.getPriceInDKK().equals(""))
                totalPrice += Integer.parseInt(item.getPriceInDKK());
        }

        return totalPrice;
    }

    public int getNextId(){
        return idCounter++;
    }

    public ArrayList<PackingList> getPackingLists() {
        return packingLists;
    }

    public void addToPackingList(PackingList pl){
        packingLists.add(pl);
    }

    public void removeFromPackingList(PackingList pl){
        packingLists.remove(pl);
    }
}
