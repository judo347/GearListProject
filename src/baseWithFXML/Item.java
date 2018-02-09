package baseWithFXML;

public class Item {

    private String nameOfItem;
    private String count;
    private String weightInGrams;
    private String brand;
    private String model;
    private String purchaseLocation;
    private String priceInDKK;
    private String note;

    //Is this one really needed?
    public Item(String nameOfItem, String count, String weightInGrams, String brand, String model, String purchaseLocation, String priceInDKK, String note) {
        this.nameOfItem = nameOfItem;
        this.count = count;
        this.weightInGrams = weightInGrams;
        this.brand = brand;
        this.model = model;
        this.purchaseLocation = purchaseLocation;
        this.priceInDKK = priceInDKK;
        this.note = note;
    }

    public Item(){
        this.nameOfItem = "";
        this.count = "";
        this.weightInGrams = "";
        this.brand = "";
        this.model = "";
        this.purchaseLocation = "";
        this.priceInDKK = "";
        this.note = "";
    }

    //Help Method
    public void printItemObj(){
        System.out.println("Name of Item: " + this.getNameOfItem());
        System.out.println("Count: " + this.getCount());
        System.out.println("Weight: " + this.getWeightInGrams());
        System.out.println("Brand: " + this.getBrand());
        System.out.println("Model: " + this.getModel());
        System.out.println("Purchase Location: " + this.getPurchaseLocation());
        System.out.println("Price: " + this.getPriceInDKK());
        System.out.println("Note: " + this.getNote());
    }

    /* TEMP BACKUP
    public void printItemObj(Item item){
        System.out.println("Name of Item: " + item.getNameOfItem());
        System.out.println("Count: " + item.getCount());
        System.out.println("Weight: " + item.getWeightInGrams());
        System.out.println("Brand: " + item.getBrand());
        System.out.println("Model: " + item.getModel());
        System.out.println("Purchase Location: " + item.getPurchaseLocation());
        System.out.println("Price: " + item.getPriceInDKK());
        System.out.println("Note: " + item.getNote());
    }

     */

    //Getters

    public String getNameOfItem() {
        return nameOfItem;
    }

    public String getCount() {
        return count;
    }

    public String getWeightInGrams() {
        return weightInGrams;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPurchaseLocation() {
        return purchaseLocation;
    }

    public String getPriceInDKK() {
        return priceInDKK;
    }

    public String getNote() {
        return note;
    }

    //Setters

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setWeightInGrams(String weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPurchaseLocation(String purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }

    public void setPriceInDKK(String priceInDKK) {
        this.priceInDKK = priceInDKK;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
