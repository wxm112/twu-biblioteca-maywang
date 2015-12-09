package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mayw on 7/12/2015.
 */
public class Librarian {
    private final JSONObject bookData;
    private Message message;

    public Librarian(String filename, Message message) {
        this.bookData = new IO().fileReader(filename);
        this.message = message;
    }


    public JSONObject checkoutBook(String bookName) {
        return getJsonObject(bookName, true, "checkout");
    }

    public JSONObject returnBook(String bookName) {
        return getJsonObject(bookName, false,"return");
    }

    private JSONObject getJsonObject(String bookName, boolean newCheckoutStatus, String functionName) {
        JSONObject selectedBook = (JSONObject) bookData.get(bookName);
        message.printMessage("EnterBookName");
        if (selectedBook != null) {
            Object checkoutStatus = selectedBook.get("Checkout");
            if (!checkoutStatus.equals(newCheckoutStatus)) {
                selectedBook.remove("Checkout");
                selectedBook.put("Checkout", newCheckoutStatus);
                message.printMessage(functionName + "Successmessage");
                return bookData;
            }
        }
        message.printMessage(functionName + "Unsuccessmessage");
        return null;
    }

    public ArrayList<String> getAvailabeBooks() {
        ArrayList<String> availableBooks = new ArrayList<String>();
        for (Object o : this.bookData.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) this.bookData.get(key);
            Object checkoutStatus = details.get("Checkout");
            if (checkoutStatus.equals(false)) {
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

    public String getUserOption(){
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim().toUpperCase();
    }

}
