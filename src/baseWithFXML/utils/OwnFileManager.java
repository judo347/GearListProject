package baseWithFXML.utils;

import baseWithFXML.model.Datamodel;
import com.google.gson.Gson;

import java.io.*;

public class OwnFileManager {

    private static String filename = "jsonfile.txt";

    /** Saves the given datamodel to file as json with the help of GSON*/
    public static void saveDatamodel(Datamodel datamodel){
        Gson gson = new Gson();
        String datamodelGson = gson.toJson(datamodel);

        try{
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, false); //Appending
            BufferedWriter bw = new BufferedWriter(fw); //Better performance

            //Print section
            bw.write(datamodelGson);
            bw.flush(); //clears bw
            bw.close(); //Close file

        } catch (IOException e) {
            System.out.println("Failed to open and write to file:");
            e.printStackTrace();
        }
    }

    /** @return a datamodel obj loaded from file using GSON.*/
    public static Datamodel loadDatamodel(){

        Datamodel datamodel = null;
        Gson gson = new Gson();
        String line = "";

        File file = new File(filename);

        //Load json line
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            line = br.readLine();
        }catch (IOException e) {
            System.out.println("Failed to open and read file:");
            e.printStackTrace();
        }

        datamodel =  gson.fromJson(line, Datamodel.class);
        datamodel.updateFields();
        return datamodel;
    }
}
