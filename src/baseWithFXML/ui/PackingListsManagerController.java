package baseWithFXML.ui;

import baseWithFXML.model.ItemChecked;
import baseWithFXML.model.PackingList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PackingListsManagerController {

    @FXML private TableColumn<ItemChecked, String> tableColumnNameOfItem;
    @FXML private TableColumn<ItemChecked, String> tableColumnBrand;
    @FXML private TableColumn<ItemChecked, String> tableColumnModel;
    @FXML private TableColumn<ItemChecked, String> tableColumnNote;
    @FXML private TableColumn<ItemChecked, String> tableColumnPrice;
    @FXML private TableColumn<ItemChecked, String> tableColumnPurchaseLocation;
    @FXML private TableColumn<ItemChecked, String> tableColumnWeight;
    @FXML private TableColumn<ItemChecked, String> tableColumnCount;
    @FXML private TableColumn<ItemChecked, String> tableColumnChecked;

    @FXML private ListView<PackingList> packingListList;
    @FXML private TableView<ItemChecked> packingListTable;

    @FXML private Label labelTotalWeight;
    @FXML private Label labelTotalPrice;

    private PrimaryController pc;

    public void initialize(PrimaryController pc){
        this.pc = pc;
        setUpTableColumns();
        refreshListView();
        setUpListeners();
    }

    /** Done as initialization for the table. */
    private void setUpTableColumns(){
        //Telling witch value from Item goes into witch Column
        tableColumnNameOfItem.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("nameOfItem"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("weightInGrams"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("model"));
        tableColumnPurchaseLocation.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("purchaseLocation"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("priceInDKK"));
        tableColumnNote.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("note"));
        tableColumnCount.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("count"));
        tableColumnChecked.setCellValueFactory(new PropertyValueFactory<ItemChecked, String>("isChecked")); //TODO USE THE CUSTOM ONE!
        tableColumnChecked.getColumns().addListener(new ListChangeListener<TableColumn<ItemChecked, ?>>() {
            @Override
            public void onChanged(Change<? extends TableColumn<ItemChecked, ?>> c) {
                System.out.println("Hello");
            }
        });
    }

    private void setUpListeners(){
        packingListList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> doSomethingWhenClicked()));
    }

    /** Calls the model and refreshes the stats shown. */
    private void refreshStats(){
        labelTotalPrice.setText(String.valueOf(getSelectedPackingList().getTotalPrice(pc.getDatamodel())) + " DKK");
        labelTotalWeight.setText(String.valueOf(getSelectedPackingList().getTotalWeight(pc.getDatamodel())) + " grams");
    }

    /** Gets called when the listener for the packinglists list, gets triggered.*/
    private void doSomethingWhenClicked(){

        if(getSelectedPackingList() != null){
            PackingList packingList = getSelectedPackingList();
            ObservableList<ItemChecked> itemsList = packingList.getFullList(FXCollections.observableList(pc.getDatamodel().getDataList()));
            packingListTable.setItems(itemsList);
            refreshStats();
        }
        System.out.println("TRIGGERED");
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
        packingListList.getSelectionModel().selectLast();
    }

    @FXML
    void deleteListBtnAction(ActionEvent event) {
        pc.getDatamodel().removeFromPackingList(getSelectedPackingList());
        refreshListView();
        packingListList.getSelectionModel().selectLast();
    }

    private PackingList getSelectedPackingList(){
        return packingListList.getSelectionModel().getSelectedItem();
    }
}
