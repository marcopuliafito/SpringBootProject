package com.mycompany.clientprog2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class FileJson {

    public static void writeFileJson(String fileName, String[] key, String[] value) throws IOException {
        JSONObject json = new JSONObject();
        for (int i = 0; i < key.length; i++) {
            if (value[i] == null)
                json.put(key[i], "");
            else
                json.put(key[i], value[i]);
        }
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String importJSON(String path) {
        FileReader f = null;
        try {
            f = new FileReader(path);
        } catch (FileNotFoundException ex) {
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
            }
        }
        return fileContent;
    }

    public FileJson() {
    }
}
