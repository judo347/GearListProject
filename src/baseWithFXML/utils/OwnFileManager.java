package baseWithFXML.utils;

import baseWithFXML.model.Item;
import baseWithFXML.model.PackingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

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
    static String formatPackingListSaveString(PackingList packingList){
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
    static String formatSaveString(Item item){
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
                itemArray.add(cutFormattedLineToItem(line));

        }catch (IOException e) {
            System.out.println("Failed to open and read file:");
            e.printStackTrace();
        }

        //Convert string to ObservableList
        return FXCollections.observableList(itemArray);
    }

    /** @return a packlingList created from the given string. */
    static PackingList cutFormattedLineToPacklingList(String line){

        ArrayList<String> elements = getElements(line);
        if(elements.size() > 2)
            throw new IllegalStateException();

        if(elements.size() == 1)
            return new PackingList(elements.get(0));

        String name = elements.get(0);
        String idsString = elements.get(1);

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



    /** Take <xx><yyy><zz> -> xx yy zz.
     * @return an arrayList of strings from the given line.*/
    static ArrayList<String> getElements(String line){
        ArrayList<String> elements = new ArrayList<String>(Arrays.asList(line.split("<")));

        //Remove first empty element
        elements.remove(0);

        /*for (String element : new ArrayList<>(elements)) {
            if(element.equals(""))
                elements.remove(element);
        }*/


        //Remove last char in each string: >
        for (String element : new ArrayList<>(elements)) {
            String tempString = element.replaceAll(">", "");

            elements.remove(element);
            elements.add(tempString);
        }

        //TODO USE THIS TO REWORK CUT METHODS

        return elements;
    }

    //TODO: TEST (WORKING?)
    //TODO: MISSING COUNT IN ADD NEW ITEM WINDOW
    /** @return an item created from the given string. */
    static Item cutFormattedLineToItem(String line){

        ArrayList<String> elements = getElements(line);

        Item tempItem = new Item();
        tempItem.setNameOfItem(elements.get(0));
        tempItem.setCount(elements.get(1));
        tempItem.setWeightInGrams(elements.get(2));
        tempItem.setBrand(elements.get(3));
        tempItem.setModel(elements.get(4));
        tempItem.setPurchaseLocation(elements.get(5));
        tempItem.setPriceInDKK(elements.get(6));
        tempItem.setNote(elements.get(7));
        tempItem.setId(Integer.valueOf(elements.get(8)));


        /*
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

        tempItem.setId(Integer.valueOf(tempId));*/

        return tempItem;
    }

    //--------METHOD GETTERS FOR TESTING------------
}
