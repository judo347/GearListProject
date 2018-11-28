package baseWithFXML.model;

import baseWithFXML.utils.OwnFileManager;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datamodel {

    private ObservableList<Item> items;
    private int idCounter; //Used to determine the next id to give an item.
    private ArrayList<PackingList> packingLists;

    public Datamodel(ObservableList<Item> itemsList, ArrayList<PackingList> packingLists){

        this.items = itemsList;
        this.packingLists = packingLists;
        //tempTestTodoFillPackingList(); //TODO TEMP

        idCounter = getHighestIdFromList(items);
    }

    public Datamodel(){
        this(OwnFileManager.loadItemsListFromFile(), OwnFileManager.loadPackingListsFromFile());
    }

    /** TODO: Temp function. */
    private void tempTestTodoFillPackingList(){
        packingLists.add(new PackingList("Heloo 1"));
        packingLists.add(new PackingList("Heloo 2"));
        packingLists.add(new PackingList("Heloo 3"));
        packingLists.add(new PackingList("Heloo 4"));
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

    private void loadFile(){
        items = OwnFileManager.loadItemsListFromFile();
        packingLists = OwnFileManager.loadPackingListsFromFile();
    }

    /** Saves the list to a file. Should be called when the windows is closed. */
    public void saveList(){

        ArrayList<Item> itemsList = new ArrayList<>();
        for (Item item : items) {
            itemsList.add(item);
        }

        OwnFileManager.saveInformationToFile(itemsList);

        OwnFileManager.savePackingLitsToFile(getPackingLists());
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
        return getTotalWeight(items);
    }

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

    public int getTotalPrice(List<Item> items){
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

    public int getIdCounter() {
        return idCounter;
    }
}
