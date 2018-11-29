package baseWithFXML.utils;

import baseWithFXML.model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class OwnFileManagerTest {

    @Test
    public void cutFormattedLineToItem01(){

        String name = "Name";
        String weightInGrams = "200";
        String brand = "Brand";
        String model = "Model";
        String purchaseLocation = "Purchase Location";
        String priceInDKK = "1200";
        String note = "Hello this is a note.";
        String id = "99";

        StringBuilder sb = new StringBuilder();
        sb.append("<").append(name).append(">");
        sb.append("<").append(weightInGrams).append(">");
        sb.append("<").append(brand).append(">");
        sb.append("<").append(model).append(">");
        sb.append("<").append(purchaseLocation).append(">");
        sb.append("<").append(priceInDKK).append(">");
        sb.append("<").append(note).append(">");
        sb.append("<").append(id).append(">");

        OwnFileManager.getElements(sb.toString());

        Item item = OwnFileManager.cutFormattedLineToItem(sb.toString());

        assertEquals(name, item.getNameOfItem());
        assertEquals(weightInGrams, item.getWeightInGrams());
        assertEquals(brand, item.getBrand());
        assertEquals(model, item.getBrand());
        assertEquals(purchaseLocation, item.getPurchaseLocation());
        assertEquals(priceInDKK, item.getPriceInDKK());
        assertEquals(note, item.getNote());
        assertEquals(id, item.getId());
    }

}
