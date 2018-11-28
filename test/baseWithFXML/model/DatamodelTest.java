package baseWithFXML.model;

import baseWithFXML.testUtilities.TestUtilities;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatamodelTest {

    @Test
    public void getHighestIdFromList01(){

        int numberOfItems = 5;

        Datamodel datamodel = new Datamodel(TestUtilities.getNumberOfItemsObs(numberOfItems), new ArrayList<>());

        assertEquals(numberOfItems - 1, datamodel.getCurrentHighestId());
    }

    @Test
    public void getHighestIdFromList02(){

        int numberOfItems = 0;

        Datamodel datamodel = new Datamodel(TestUtilities.getNumberOfItemsObs(numberOfItems), new ArrayList<>());

        assertEquals(numberOfItems, datamodel.getCurrentHighestId());
    }

    @Test
    public void getNextId01(){
        int numberOfItems = 5;

        Datamodel datamodel = new Datamodel(TestUtilities.getNumberOfItemsObs(numberOfItems), new ArrayList<>());

        assertEquals(numberOfItems, datamodel.getNextId());
        assertEquals(numberOfItems + 1, datamodel.getNextId());
    }

    @Test
    public void getNextId02(){
        int numberOfItems = 0;

        Datamodel datamodel = new Datamodel(TestUtilities.getNumberOfItemsObs(numberOfItems), new ArrayList<>());

        assertEquals(numberOfItems + 1, datamodel.getNextId());
        assertEquals(numberOfItems + 2, datamodel.getNextId());
    }

    @Test
    public void removeItem01(){
        int numberOfItems = 5;

        Datamodel datamodel = new Datamodel(TestUtilities.getNumberOfItemsObs(numberOfItems), new ArrayList<>());

        Item item = new Item();

        datamodel.addItem(item);

        assertEquals(numberOfItems + 1, datamodel.getDataList().size());
        datamodel.removeItem(item);
        assertEquals(numberOfItems, datamodel.getDataList().size());
    }

    @Test
    public void addItem01(){

    }
}
