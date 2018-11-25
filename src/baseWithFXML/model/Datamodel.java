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

    public Datamodel() {
        items = FXCollections.observableArrayList();
        ofm = new OwnFileManager();

        items = ofm.fillObservableList(items);
        idCounter = getHighestIdFromList(items) + 1;
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

    }

    //TODO someway of removing an item.

    /** Saves the list to a file. Should be called when the windows is closed. */
    public void saveList(){

        ArrayList<Item> itemsList = new ArrayList<>();
        for (Item item : items) {
            itemsList.add(item);
        }

        ofm.saveInformationToFile(itemsList);
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

    public int getNextId(){
        return idCounter++;
    }
}
