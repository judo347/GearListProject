package baseWithFXML.utils;

import baseWithFXML.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class OwnFileManager {
    public void examplePrintStatement(){
        System.out.println("TestWOrked!");
    }

    public void saveInformationToFile(Item item){

        try{
            File file = new File("datafile.txt");

            /* Creates new file if none exists TODO: Include?
            if(!file.exists()){
                file.createNewFile();
           }
            */

            FileWriter fw = new FileWriter(file, true); //Appending
            BufferedWriter bw = new BufferedWriter(fw); //Better performance
            //bw = null; //Clears the bw?

            //Print section
            bw.write(formatSaveString(item));
            bw.newLine();
            bw.flush(); //clears bw
            bw.close(); //Close file

        } catch (IOException e) {
            System.out.println("Failed to open and write to file:");
            e.printStackTrace();
        }

    }

    //<Name><WeightInGrams><Brand><Model><PurchaseLocation><PriceInDKK><Note> //7 items
    public String formatSaveString(Item item){
        String composed = "<" + item.getNameOfItem() + ">" + "<" + item.getCount() + ">" +
                          "<" + item.getWeightInGrams() + ">" + "<" + item.getBrand() + ">" +
                          "<" + item.getModel() + ">" + "<" + item.getPurchaseLocation() + ">" +
                          "<" + item.getPriceInDKK() + ">" + "<" + item.getNote() + ">";
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
                }
            }
        }

        tempItem.printItemObj(); //TEMP
        System.out.println("End of cutFormattedLineToItem"); //TEMP

        return tempItem;
    }






    /* WORKING BASIC TEMP
    public ObservableList<Item> fillObservableList(ObservableList<Item> data){

        data = FXCollections.observableArrayList(
                new Item(),
                new Item()
        );

        return data;
    }

     */
}
