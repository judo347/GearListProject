package baseWithFXML.testUtilities;

import baseWithFXML.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TestUtilities {

    /** @return the given number of items. */
    public static ArrayList<Item> getNumberOfItems(int count){
        ArrayList<Item> items = new ArrayList<>();

        for(int i = 0; i < count; i++){
            items.add(getItem(i));
        }

        return items;
    }

    public static ObservableList<Item> getNumberOfItemsObs(int count){
        return FXCollections.observableList(getNumberOfItems(count));
    }

    public static Item getItem(int i){
        return new Item("TestName " + 1, String.valueOf(i), String.valueOf(i*100), "Test brand " + i,
                "Model " + i, "Test Purcahse Location " + i, String.valueOf(i*50), "Test note " + i, i);
    }
}
