package baseWithFXML.utils;

import baseWithFXML.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class OwnFileManager {

    public void saveInformationToFile(ArrayList<Item> items){

        try{
            File file = new File("datafile.txt");

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

    public ObservableList<Item> fillObservableList(ObservableList<Item> data){

        ArrayList<Item> itemArray = new ArrayList<Item>(); //Creating array of Item for lines from file

        File file = new File("datafile.txt");

        //Fill array with items from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
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

    //TODO: TEST (WORKING?)
    //TODO: MISSING COUNT IN ADD NEW ITEM WINDOW
    public Item cutFormattedLineToItem(String line, Item tempItem){

        System.out.println("Start of cutFormattedLineToItem"); //TEMP
        System.out.println("The line: " + line); //TEMP
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
