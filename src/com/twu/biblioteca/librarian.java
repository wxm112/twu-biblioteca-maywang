package com.twu.biblioteca;

import org.json.simple.JSONObject;

/**
 * Created by mayw on 7/12/2015.
 */
public class Librarian {
    private final JSONObject bookData;

    public Librarian(String filename) {
        this.bookData = new IO().fileReader(filename);
    }


    public JSONObject checkoutBook(String bookName) {
        return getJsonObject(bookName, "No", "Yes");
    }

    public JSONObject returnBook(String bookName) {
        return getJsonObject(bookName, "Yes", "No");
    }

    private JSONObject getJsonObject(String bookName, String no, String yes) {
        JSONObject selectedBook = (JSONObject) bookData.get(bookName);
        if (selectedBook != null) {
            String checkoutStatus = (String) selectedBook.get("Checkout");
            if (checkoutStatus.equals(no)) {
                selectedBook.remove("Checkout");
                selectedBook.put("Checkout", yes);
                return bookData;
            }
        }
        return null;
    }
}
