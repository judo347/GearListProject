package baseWithFXML;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddItemController implements Initializable {

    //private OwnFileManager ownFileManager; //reference to other class

    @FXML
    private Button buttonAddItem;

    @FXML
    private TextField textFieldNameOfItem;

    @FXML
    private TextField textFieldBrand;

    @FXML
    private TextField textFieldModel;

    @FXML
    private TextField textFieldPriceInDKK;

    @FXML
    private TextField textFieldPurchaseLocation;

    @FXML
    private TextField textFieldWeightInGrams;

    @FXML
    private TextField textFieldNote;

    @FXML
    private Label textLabelConfirmation;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    // When user click on buttonAddItem
    // this method will be called.
    public void saveItemToFile(ActionEvent event) {
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

        //Save strings to file
        OwnFileManager ofm = new OwnFileManager(); //New object from own class. For help with files.

        ofm.saveInformationToFile(item); //Saves the item to file

        //Clear text fields and print msg
        textFieldNameOfItem.clear();
        textFieldWeightInGrams.clear();
        textFieldBrand.clear();
        textFieldModel.clear();
        textFieldPurchaseLocation.clear();
        textFieldPriceInDKK.clear();
        textFieldNote.clear();

        textLabelConfirmation.setText("Item saved!"); //TODO: Display name of the saved item

    }



}