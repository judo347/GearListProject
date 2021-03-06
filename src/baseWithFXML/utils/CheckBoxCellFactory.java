package baseWithFXML.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

//https://stackoverflow.com/questions/7217625/how-to-add-checkboxs-to-a-tableview-in-javafx

public class CheckBoxCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new CheckBoxTableCell<>();
    }
}
