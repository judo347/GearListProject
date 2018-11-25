package baseWithFXML.ui;

import baseWithFXML.model.Item;
import baseWithFXML.model.PackingList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PackingListsManagerController {

    @FXML private TableColumn<Item, String> tableColumnNameOfItem;
    @FXML private TableColumn<Item, String> tableColumnBrand;
    @FXML private TableColumn<Item, String> tableColumnCount;
    @FXML private TableColumn<Item, String> tableColumnModel;
    @FXML private TableColumn<Item, String> tableColumnNote;
    @FXML private TableColumn<Item, String> tableColumnPrice;
    @FXML private TableColumn<Item, String> tableColumnPurchaseLocation;
    @FXML private TableColumn<Item, String> tableColumnWeight;

    @FXML private ListView<PackingList> packingListList;
    @FXML private TableView<Item> packingListTable;

    private PrimaryController pc;

    public void initialize(PrimaryController pc){
        this.pc = pc;
        setUpTableColumns();
        //Opdate listView
        refreshListView();
        setUpListeners();


        //Set actionButton listeners to update listView on action.
    }

    /** Done as initialization for the table. */
    private void setUpTableColumns(){
        //Telling witch value from Item goes into witch Column
        tableColumnNameOfItem.setCellValueFactory(new PropertyValueFactory<Item, String>("nameOfItem"));
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<Item, String>("count"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory<Item, String>("weightInGrams"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
        tableColumnPurchaseLocation.setCellValueFactory(new PropertyValueFactory<Item, String>("purchaseLocation"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Item, String>("priceInDKK"));
        tableColumnNote.setCellValueFactory(new PropertyValueFactory<Item, String>("note"));
    }

    private void setUpListeners(){
        packingListList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> doSomethingWhenClicked()));
    }

    private void doSomethingWhenClicked(){
        packingListTable.getItems().clear();
        //packingListTable.setItems(packingListList.getSelectionModel().getSelectedItem().getList(pc.getDatamodel().getDataListArrayList()));

        System.out.println("TRIGGERED");
        //TODO Change what is viewed in tableView
    }

    private void refreshListView(){
        packingListList.getItems().clear();
        for (PackingList packingList : pc.getDatamodel().getPackingLists()) {
            packingListList.getItems().add(packingList);
        }
    }

    @FXML
    void newListBtnAction(ActionEvent event) {
        pc.getDatamodel().addToPackingList(new PackingList("Temp name, rename me!"));
        refreshListView();
    }

    @FXML
    void deleteListBtnAction(ActionEvent event) {
        pc.getDatamodel().removeFromPackingList(packingListList.getSelectionModel().getSelectedItem());
        refreshListView();
    }
}
