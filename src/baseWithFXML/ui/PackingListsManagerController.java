package baseWithFXML.ui;

import baseWithFXML.model.Item;
import baseWithFXML.model.ItemChecked;
import baseWithFXML.model.PackingList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PackingListsManagerController {

    @FXML private TableColumn<ItemChecked, String> tableColumnNameOfItem;
    @FXML private TableColumn<ItemChecked, String> tableColumnBrand;
    @FXML private TableColumn<ItemChecked, String> tableColumnCount;
    @FXML private TableColumn<ItemChecked, String> tableColumnModel;
    @FXML private TableColumn<ItemChecked, String> tableColumnNote;
    @FXML private TableColumn<ItemChecked, String> tableColumnPrice;
    @FXML private TableColumn<ItemChecked, String> tableColumnPurchaseLocation;
    @FXML private TableColumn<ItemChecked, String> tableColumnWeight;

    @FXML private ListView<PackingList> packingListList;
    @FXML private TableView<ItemChecked> packingListTable;

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
        tableColumnNameOfItem.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("nameOfItem"));
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("count"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("weightInGrams"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("model"));
        tableColumnPurchaseLocation.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("purchaseLocation"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("priceInDKK"));
        tableColumnNote.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("note"));
    }

    private void setUpListeners(){
        packingListList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> doSomethingWhenClicked()));
    }

    private void doSomethingWhenClicked(){
        //packingListTable.getItems().clear();
        packingListTable.setItems(packingListList.getSelectionModel().getSelectedItem().getList(pc.getDatamodel().getDataListArrayList()));

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
