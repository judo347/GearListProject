package baseWithFXML.model;

public class ItemChecked {

    private Item item;
    private boolean isChecked;

    public ItemChecked(Item item) {
        this(item, false);
    }

    public ItemChecked(Item item, boolean isChecked) {
        this.item = item;
        this.isChecked = isChecked;
    }

    public Item getItem() {
        return item;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
