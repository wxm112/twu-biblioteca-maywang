package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by mayw on 7/12/2015.
 */
public class List<S> {
    private IO file = new IO();

    public ArrayList<String> getAvailabeBooks(String fileName) {
        JSONObject data = file.fileReader(fileName);
        ArrayList<String> availableBooks = new ArrayList<String>();
        for (Object o : data.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) data.get(key);
            String checkout = (String) details.get("Checkout");
            if ("No".equals(checkout)) {
                availableBooks.add("Book Title: " + key);
                availableBooks.add("Author: " + details.get("Author"));
                availableBooks.add("Publication Year: " + details.get("PublicationYear") + "\n");
            }
        }
        return availableBooks;
    }
}

