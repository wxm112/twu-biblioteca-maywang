package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

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
            } catch (Exception e){
                e.printStackTrace();
            }
            return jsonObject;
        }
    
}
