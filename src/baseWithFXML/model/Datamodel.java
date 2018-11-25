package baseWithFXML.model;

import baseWithFXML.utils.OwnFileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;

public class Datamodel {

    private ObservableList<Item> items;
    private OwnFileManager ofm;

    public Datamodel() {
        items = FXCollections.observableArrayList();
        ofm = new OwnFileManager();

        items = ofm.fillObservableList(items);
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

    public void addItem(Item ... item){
        items.addAll(Arrays.asList(item));
    }

    public ObservableList<Item> getDataList() {
        return items;
    }
}
