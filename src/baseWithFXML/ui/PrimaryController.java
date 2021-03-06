package baseWithFXML.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import baseWithFXML.model.Datamodel;
import baseWithFXML.model.Item;
import baseWithFXML.utils.OwnFileManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{

    @FXML private Button buttonNewItem;
    @FXML private Button buttonRefreshList;
    @FXML private Label labelTotalWeight;
    @FXML private Label labelTotalPrice;

    @FXML private TableColumn<Item, String> tableColumnNameOfItem;
    @FXML private TableColumn<Item, String> tableColumnBrand;
    @FXML private TableColumn<Item, String> tableColumnCount;
    @FXML private TableColumn<Item, String> tableColumnModel;
    @FXML private TableColumn<Item, String> tableColumnNote;
    @FXML private TableColumn<Item, String> tableColumnPrice;
    @FXML private TableColumn<Item, String> tableColumnPurchaseLocation;
    @FXML private TableColumn<Item, String> tableColumnWeight;
    @FXML private TableView<Item> tableData;

    private Datamodel datamodel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datamodel = OwnFileManager.loadDatamodel();
        setUpTableColumns();
        refreshList();
        refreshStats();
    }

    /** Calls the model and refreshes the stats shown. */
    private void refreshStats(){
        labelTotalPrice.setText(String.valueOf(datamodel.getTotalPrice()) + " DKK");
        labelTotalWeight.setText(String.valueOf(datamodel.getTotalWeight()) + " grams");
    }

    /** Gets called when the buttonAddItem is pressed. Opens the addItem window.*/
    public void openWindowAddItem(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/baseWithFXML/ui/AddItemScene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            AddItemController atc = fxmlLoader.getController();
            atc.setPsc(this);

            openWindowHelper(root, "Add New Item");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Gets called when the button openListManager is pressed. Opens the packinglist manager. */
    public void openPacketListManagerWindow(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/baseWithFXML/ui/PackingListsManager.fxml"));
            Parent root = fxmlLoader.load();
            PackingListsManagerController pmc = fxmlLoader.getController();
            pmc.initialize(this);

            openWindowHelper(root, "Packing List Manager");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /** Takes an loaded fxml file and creates the window. */
    private void openWindowHelper(Parent root, String windowTitle){

        Stage addItemWindowScene = new Stage();
        //Specifies the modality for new window (connection with parent window)
        addItemWindowScene.initModality(Modality.WINDOW_MODAL);
        addItemWindowScene.setTitle(windowTitle);
        addItemWindowScene.setScene(new Scene(root));
        addItemWindowScene.setResizable(false);
        addItemWindowScene.show();
    }

    @FXML
    public void refreshList() {
        tableData.setItems(FXCollections.observableList(datamodel.getDataList())); //Filling table
    }

    /** Initialization for the table. */
    private void setUpTableColumns(){
        //Telling witch value from Item goes into witch Column
        tableColumnNameOfItem.setCellValueFactory(new PropertyValueFactory<Item, String>("nameOfItem"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory<Item, String>("weightInGrams"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
        tableColumnPurchaseLocation.setCellValueFactory(new PropertyValueFactory<Item, String>("purchaseLocation"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Item, String>("priceInDKK"));
        tableColumnNote.setCellValueFactory(new PropertyValueFactory<Item, String>("note"));
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<Item, String>("count"));
    }

    @FXML
    public void btnActionDeleteItem(){
        datamodel.removeItem(tableData.getSelectionModel().getSelectedItem());
        refreshList();
    }

    public Datamodel getDatamodel() {
        return datamodel;
    }
}