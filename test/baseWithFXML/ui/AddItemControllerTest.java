package baseWithFXML.ui;

import baseWithFXML.testUtilities.TestUtilities;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddItemControllerTest {

    @Test //Legit input
    public void isInputLegit01(){

        AddItemController atc = new AddItemController();

        boolean bool = atc.isInputLegit("Hello", "500", "500");

        assertTrue(bool);
    }

    @Test //Empty name
    public void isInputLegit02(){

        AddItemController atc = new AddItemController();

        boolean bool = atc.isInputLegit("", "500", "500");

        assertFalse(bool);
    }

    @Test //Empty weight
    public void isInputLegit03(){

        AddItemController atc = new AddItemController();

        boolean bool = atc.isInputLegit("Hello", "", "500");

        assertTrue(bool);
    }

    @Test //Empty price
    public void isInputLegit04(){

        AddItemController atc = new AddItemController();

        boolean bool = atc.isInputLegit("Hello", "500", "");

        assertTrue(bool);
    }

    @Test //Illegal price
    public void isInputLegit05(){

        AddItemController atc = new AddItemController();

        boolean bool = atc.isInputLegit("Hello", "500", "asda2");

        assertFalse(bool);
    }

    @Test //Illegal weight
    public void isInputLegit06(){

        AddItemController atc = new AddItemController();

        boolean bool = atc.isInputLegit("Hello", "2ada2", "500");

        assertFalse(bool);
    }
}
