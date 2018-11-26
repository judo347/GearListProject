package baseWithFXML.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class ItemChecked extends Item {

    private CheckBox isChecked;
    private PackingList owner;

    public ItemChecked(Item item, PackingList owner) {
        this(item, false, owner);
    }

    public ItemChecked(Item item, boolean isChecked, PackingList owner) {
        super(item.getNameOfItem(), item.getCount(), item.getWeightInGrams(), item.getBrand(), item.getModel(), item.getPurchaseLocation(), item.getPriceInDKK(), item.getNote(), item.getId());
        this.isChecked = new CheckBox();
        this.isChecked.setSelected(isChecked);
        this.owner = owner;

        this.isChecked.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                owner.itemHasBeenTriggered(getThis());
            }
        });
    }

    public boolean isChecked() {
        return isChecked.isSelected();
    }

    public void setChecked(boolean checked) {
        isChecked.setSelected(checked);
    }

    public CheckBox getIsChecked() {
        return isChecked;
    }

    public ItemChecked getThis(){
        return this;
    }
}
