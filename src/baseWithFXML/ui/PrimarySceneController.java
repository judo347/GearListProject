package baseWithFXML.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import baseWithFXML.model.Datamodel;
import baseWithFXML.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimarySceneController implements Initializable{

    @FXML private Button buttonNewItem;
    @FXML private Button buttonRefreshList;
    //Table columns
    @FXML private TableColumn<Item, String> tableColumnNameOfItem;
    //private TableColumn<?, ?> tableColumnNameOfItem;
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

        datamodel = new Datamodel();
        refreshList();
        // TODO (don't really need to do anything here).

    }

    // When user click on buttonAddItem
    // this method will be called.
    public void openWindowAddItem(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/baseWithFXML/ui/AddItemScene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            AddItemController atc =fxmlLoader.getController();
            atc.setPsc(this);

            openWindowAddItemHelper(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Takes an loaded fxml file and creates the window. */
    private void openWindowAddItemHelper(Parent root){

        Stage addItemWindowScene = new Stage();
        //Specifies the modality for new window (connection with parent window)
        addItemWindowScene.initModality(Modality.WINDOW_MODAL);

        //Specifies the owner Window(parent) for new window
        //addItemWindowScene.initOwner(primaryStage); //TODO: HOW TO GET primaryStage OBJECT?

        addItemWindowScene.setTitle("Add New Item");
        addItemWindowScene.setScene(new Scene(root));
        addItemWindowScene.setResizable(false);
        addItemWindowScene.show();
    }

    @FXML
    public void refreshList() {

        //Telling witch value from Item goes into witch Column
        tableColumnNameOfItem.setCellValueFactory(new PropertyValueFactory<Item, String>("nameOfItem"));
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<Item, String>("count"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory<Item, String>("weightInGrams"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
        tableColumnPurchaseLocation.setCellValueFactory(new PropertyValueFactory<Item, String>("purchaseLocation"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Item, String>("priceInDKK"));
        tableColumnNote.setCellValueFactory(new PropertyValueFactory<Item, String>("note"));

        //Filling table
        tableData.setItems(datamodel.getDataList());
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