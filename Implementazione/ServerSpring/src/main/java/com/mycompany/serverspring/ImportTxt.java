/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring;

/**
 *
 * @author march
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class ImportTxt {


    public static String importJSON(String path) {

        FileReader f = null;
        try {
            f = new FileReader(path);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ImportTextFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader b;
        b = new BufferedReader(f);

        String s = null;

        boolean firstLine = true;

        String fileContent = null;

        while (true) {
            String nextLine = "\n";
            try {
                if (firstLine) {
                    s = b.readLine();
                    if (s == null) {
                        break;
                    }
                    fileContent = s;
                    firstLine = false;
                } else {

                    s = b.readLine();
                    if (s == null) {
                        break;
                    }
                    fileContent = fileContent.concat(s);
                }
            } catch (IOException ex) {
                //Logger.getLogger(ImportTextFile.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return fileContent;
    }
    
    //crea un file json passando come argomenti nome file, chiave e valore json
    public static void writeFileJson(String fileName, String[] key, String[] value) throws IOException{
        //creo oggetto json
        JSONObject json = new JSONObject();
        for(int i=0; i<key.length; i++){
            if(value[i] == null)
                json.put(key[i], "");
            else
                json.put(key[i], value[i]);
        }
       //Write JSON file
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

