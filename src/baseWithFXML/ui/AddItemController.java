package baseWithFXML.ui;

import java.net.URL;
import java.util.ResourceBundle;

import baseWithFXML.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddItemController implements Initializable {

    @FXML private Button buttonAddItem;
    @FXML private TextField textFieldNameOfItem;
    @FXML private TextField textFieldBrand;
    @FXML private TextField textFieldModel;
    @FXML private TextField textFieldPriceInDKK;
    @FXML private TextField textFieldPurchaseLocation;
    @FXML private TextField textFieldWeightInGrams;
    @FXML private TextField textFieldNote;

    private PrimaryController psc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /** Saves an item from the entered data. */
    public void saveItemBtnAction(ActionEvent event) {

        //if(!isInputLegit()) //TODO DOES NOT WORK
        //    return;

        //Strings from input in window
        Item item = new Item();

        //Filling item with info from textFields
        item.setNameOfItem(textFieldNameOfItem.getText());
        item.setWeightInGrams(textFieldWeightInGrams.getText());
        item.setBrand(textFieldBrand.getText());
        item.setModel(textFieldModel.getText());
        item.setPurchaseLocation(textFieldPurchaseLocation.getText());
        item.setPriceInDKK(textFieldPriceInDKK.getText());
        item.setNote(textFieldNote.getText());
        item.setId(psc.getDatamodel().getNextId());

        //Add item to datamodel
        psc.getDatamodel().addItem(item);

        clearTextFields();
        psc.refreshList();
    }

    /** @return true if the input is valid. */
    private boolean isInputLegit(){

        String name = textFieldNameOfItem.getText();
        String weight = textFieldWeightInGrams.getText();
        String price = textFieldPriceInDKK.getText();

        if (!(weight.equals("") && price.equals(""))) {
            try {
                Integer.parseInt(weight);
                Integer.parseInt(price);
            } catch (NumberFormatException e){
                return false;
            }
        }

        return name.length() >= 1;
    }

    /** Clears all textFields. */
    private void clearTextFields(){
        textFieldNameOfItem.clear();
        textFieldWeightInGrams.clear();
        textFieldBrand.clear();
        textFieldModel.clear();
        textFieldPurchaseLocation.clear();
        textFieldPriceInDKK.clear();
        textFieldNote.clear();
    }

    public void setPsc(PrimaryController psc) {
        this.psc = psc;
    }
}