package baseWithFXML.model;

public class ItemChecked extends Item {

    private boolean isChecked;

    public ItemChecked(Item item) {
        this(item, false);
    }

    public ItemChecked(Item item, boolean isChecked) {
        super(item.getNameOfItem(), item.getCount(), item.getWeightInGrams(), item.getBrand(), item.getModel(), item.getPurchaseLocation(), item.getPriceInDKK(), item.getNote(), item.getId());
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
