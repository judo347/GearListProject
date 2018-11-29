package baseWithFXML.utils;

import baseWithFXML.model.Item;
import baseWithFXML.model.PackingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class OwnFileManager {

    private static String filename = "datafile.txt";

    /** Saves the given items list to a file. */
    public static void saveInformationToFile(ArrayList<Item> items){

        try{
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, false); //Appending
            BufferedWriter bw = new BufferedWriter(fw); //Better performance

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

    /** Saves the given packingList to a file. */
    public static void savePackingLitsToFile(ArrayList<PackingList> packingLists){

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

    /** @return a formatted string created from the given packingList.
     * The format is: <NameOfList><id1,id2,id3,id4,id5,id6>*/
    private static String formatPackingListSaveString(PackingList packingList){
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(packingList.getName()).append("><");

        for (Integer id : packingList.getItemIds())
            sb.append(id).append(",");

        sb.deleteCharAt(sb.length() - 1);
        sb.append(">");

        return sb.toString();
    }

    /** @return a formatted string created from the given item.
     * The format is: <Name><WeightInGrams><Brand><Model><PurchaseLocation><PriceInDKK><Note><id>*/
    private static String formatSaveString(Item item){
        String composed = "<" + item.getNameOfItem() + ">" + "<" + item.getCount() + ">" +
                          "<" + item.getWeightInGrams() + ">" + "<" + item.getBrand() + ">" +
                          "<" + item.getModel() + ">" + "<" + item.getPurchaseLocation() + ">" +
                          "<" + item.getPriceInDKK() + ">" + "<" + item.getNote() + ">" +
                          "<" + item.getId() + ">";
        return composed;
    }

    /** @return an ArrayList of packingLists loaded from the file. */
    public static ArrayList<PackingList> loadPackingListsFromFile(){

        ArrayList<PackingList> packingLists = new ArrayList<>();

        File file = new File(filename);

        //Fill array with packing lists from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            //traverse the file to find the start of the packinglists
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

    /**@return an ArrayList of items loaded from the file. */
    public static ObservableList<Item> loadItemsListFromFile(){

        ArrayList<Item> itemArray = new ArrayList<Item>();

        File file = new File(filename);

        //Fill array with items from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            String line;
            while((line = br.readLine()) != null && !line.equals("#"))
                itemArray.add(cutFormattedLineToItem(line, new Item()));

        }catch (IOException e) {
            System.out.println("Failed to open and read file:");
            e.printStackTrace();
        }

        //Convert string to ObservableList
        return FXCollections.observableList(itemArray);
    }

    /** @return a packlingList created from the given string. */
    private static PackingList cutFormattedLineToPacklingList(String line){

        //Cut into elements
        String[] elemenets = line.split("<"); //TODO This contains /n strings?!??!
        String name = elemenets[1].substring(0, elemenets[1].length() -1);
        String idsString = elemenets[2].substring(0, elemenets[2].length() -1);

        //Get individual ids
        ArrayList<Integer> ids = new ArrayList<>();
        String valueHolder = "";
        int counter = 0;
        while (counter < idsString.length()){

            if(idsString.charAt(counter) == ','){
                ids.add(Integer.parseInt(valueHolder));
                valueHolder = "";
            }else{
                valueHolder = valueHolder + idsString.charAt(counter);
            }

            counter++;
        }
        ids.add(Integer.parseInt(valueHolder));

        PackingList packingList = new PackingList(name);
        packingList.addIdsToList(ids);
        return packingList;
    }

    //TODO: TEST (WORKING?)
    //TODO: MISSING COUNT IN ADD NEW ITEM WINDOW
    /** @return an item created from the given string. */
    private static Item cutFormattedLineToItem(String line, Item tempItem){

        int currentElement = 0;
        boolean toggle = false;
        String tempId = "";

        for(int i = 0; i < line.length(); i++){
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

        return tempItem;
    }
}
