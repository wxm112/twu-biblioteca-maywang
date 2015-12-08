package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by mayw on 7/12/2015.
 */
public class Librarian {
    private final JSONObject bookData;
    private Message mess = new Message();

    public Librarian(String filename) {
        this.bookData = new IO().fileReader(filename);
    }


    public JSONObject checkoutBook(String bookName) {
        return getJsonObject(bookName, "No", "Yes", "checkout");
    }

    public JSONObject returnBook(String bookName) {
        return getJsonObject(bookName, "Yes", "No","return");
    }

    private JSONObject getJsonObject(String bookName, String no, String yes, String functionName) {
        JSONObject selectedBook = (JSONObject) bookData.get(bookName);
        if (selectedBook != null) {
            String checkoutStatus = (String) selectedBook.get("Checkout");
            if (checkoutStatus.equals(no)) {
                selectedBook.remove("Checkout");
                selectedBook.put("Checkout", yes);
                mess.printMessasge(functionName + "Successmessage");
                return bookData;
            }
        }
        mess.printMessasge(functionName + "Unsuccessmessage");
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

    public ArrayList<String> menuList() {
        ArrayList<String> menu = new ArrayList<String>();
        menu.add("Q: Quit");
        menu.add("C: Checkout book");
        menu.add("L: List Books");
        menu.add("R: Return Books");
        return menu;
    }

    public void render(ArrayList<String> options){
        for ( String option : options) {
            System.out.println(option);
        }
    }

}
