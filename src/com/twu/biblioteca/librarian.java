package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;

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

    public ArrayList<String> getAvailabeBooks() {
        ArrayList<String> availableBooks = new ArrayList<String>();
        for (Object o : this.bookData.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) this.bookData.get(key);
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
