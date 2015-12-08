package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by mayw on 7/12/2015.
 */
public class IO {

    public JSONObject fileReader(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public void updateJasonFile(JSONObject updatedobj, String fileName) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(updatedobj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getUserOption(){
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim().toUpperCase();
    }

}
