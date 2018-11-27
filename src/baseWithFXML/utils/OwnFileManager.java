package baseWithFXML.utils;

import baseWithFXML.model.Item;
import baseWithFXML.model.PackingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class OwnFileManager {

    private String filename = "datafile.txt";

    public void saveInformationToFile(ArrayList<Item> items){

        try{
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, false); //Appending
            BufferedWriter bw = new BufferedWriter(fw); //Better performance
            //bw = null; //Clears the bw?

            //Print section
            for (Item item : items){
                bw.write(formatSaveString(item));
                bw.newLine();
            }
            bw.flush(); //clears bw
            bw.close(); //Close file

        } catch (IOException e) {
            System.out.println("Failed to open and write to file:");
            e.printStackTrace();
        }
    }

    public void savePackingLitsToFile(ArrayList<PackingList> packingLists){

        try{
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, true); //Appending
            BufferedWriter bw = new BufferedWriter(fw); //Better performance

            //Print section
            bw.write("#");
            bw.newLine();
            for (PackingList list : packingLists) {
                bw.write(formatPackingListSaveString(list));
                bw.newLine();
            }
            bw.flush(); //clears bw
            bw.close(); //Close file
        } catch (IOException e) {
            System.out.println("Failed to open and write to file:");
            e.printStackTrace();
        }
    }

    //<NameOfList><x,y,e,d,g,h>
    private String formatPackingListSaveString(PackingList packingList){
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(packingList.getName()).append("><");

        for (Integer id : packingList.getItemIds()) {
            sb.append(id).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(">");

        return sb.toString();
    }

    //<Name><WeightInGrams><Brand><Model><PurchaseLocation><PriceInDKK><Note><id><isChecked> //7 items
    public String formatSaveString(Item item){
        String composed = "<" + item.getNameOfItem() + ">" + "<" + item.getCount() + ">" +
                          "<" + item.getWeightInGrams() + ">" + "<" + item.getBrand() + ">" +
                          "<" + item.getModel() + ">" + "<" + item.getPurchaseLocation() + ">" +
                          "<" + item.getPriceInDKK() + ">" + "<" + item.getNote() + ">" +
                          "<" + item.getId() + ">";
        return composed;

        //return item.getNameOfItem() + item.getWeightInGrams() + item.getBrand() + item.getModel() + item.getPurchaseLocation() + item.getNote(); //TEMP needs <> if used
    }

    public ArrayList<PackingList> loadPackingListsFromFile(){

        ArrayList<PackingList> packingLists = new ArrayList<>();

        File file = new File(filename);

        //Fill array with packing lists from file
        //Fill array with items from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while((line = br.readLine()) != null && !line.equals("#"));

            while((line = br.readLine()) != null){
                System.out.println(line);
                packingLists.add(cutFormattedLineToPacklingList(line));
            }
        }catch (IOException e) {
            System.out.println("Failed to open and read file:");
            e.printStackTrace();
        }

        return packingLists;
    }

    public ObservableList<Item> fillObservableList(ObservableList<Item> data){

        ArrayList<Item> itemArray = new ArrayList<Item>(); //Creating array of Item for lines from file

        File file = new File(filename);

        //Fill array with items from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null && !line.equals("#")){
                //System.out.println(line); //TEMP
                Item tempItem = new Item();
                tempItem = cutFormattedLineToItem(line, tempItem);
                //tempItem.printItemObj(); //TEMP
                itemArray.add(tempItem);
            }
        }catch (IOException e) {
            System.out.println("Failed to open and read file:");
            e.printStackTrace();
        }

        //Convert string to ObservableList
        data = FXCollections.observableList(itemArray); //WORKING?

        return data;
    }

    private PackingList cutFormattedLineToPacklingList(String line){

        ArrayList<Integer> ids = new ArrayList<>();
        String listName = "";

        //Cut into elements
        String[] elemenets = line.split("<"); //TODO This contains /n strings?!??!
        //String name = elemenets[1];
        //name.replace(">", "");
        String name = elemenets[1].substring(0, elemenets[1].length() -1);
        String idsString = elemenets[2].substring(0, elemenets[2].length() -1);

        System.out.println(idsString);

        String value = "";
        int counter = 0;
        while (counter < idsString.length()){

            if(idsString.charAt(counter) == ','){
                ids.add(Integer.parseInt(value));
                value = "";
            }else{
                value = value + idsString.charAt(counter);
            }

            counter++;
        }
        ids.add(Integer.parseInt(value));

        PackingList packingList = new PackingList(name);
        packingList.addIdsToList(ids);
        return packingList;
    }

    //TODO: TEST (WORKING?)
    //TODO: MISSING COUNT IN ADD NEW ITEM WINDOW
    public Item cutFormattedLineToItem(String line, Item tempItem){

        //System.out.println("Start of cutFormattedLineToItem"); //TEMP
        //System.out.println("The line: " + line); //TEMP
        int currentElement = 0;
        boolean toggle = false;
        String tempId = "";

        for(int i = 0; i < line.length(); i++){
            //System.out.println(i + " : " + line.charAt(i)); //TEMP
            if(line.charAt(i) == '<') {
                currentElement++;
                i++;
                if(line.charAt(i) != '>') toggle = true; //Handling Empty element
            } else if(line.charAt(i) == '>')
                toggle = false;

            if(toggle){
                switch (currentElement){
                    case 1 : tempItem.setNameOfItem(tempItem.getNameOfItem() + line.charAt(i));
                        break;
                    case 2 : tempItem.setCount(tempItem.getCount() + line.charAt(i));
                        break;
                    case 3 : tempItem.setWeightInGrams(tempItem.getWeightInGrams() + line.charAt(i));
                        break;
                    case 4 : tempItem.setBrand(tempItem.getBrand() + line.charAt(i));
                        break;
                    case 5: tempItem.setModel(tempItem.getModel() + line.charAt(i));
                        break;
                    case 6: tempItem.setPurchaseLocation(tempItem.getPurchaseLocation() + line.charAt(i));
                        break;
                    case 7: tempItem.setPriceInDKK(tempItem.getPriceInDKK() + line.charAt(i));
                        break;
                    case 8: tempItem.setNote(tempItem.getNote() + line.charAt(i));
                        break;
                    case 9: tempId = tempId + line.charAt(i);
                        break;
                }
            }
        }

        tempItem.setId(Integer.valueOf(tempId));


        tempItem.printItemObj(); //TEMP
        System.out.println("End of cutFormattedLineToItem"); //TEMP

        return tempItem;
    }
}
