package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Leyebrarian {
    private JSONObject data;
    private Message message;
    private IO io;
    private String file;

    public Leyebrarian(String filename, Message message, IO io) {
        this.file = filename;
        this.io = io;
        this.data = io.fileReader(file);
        this.message = message;
    }


    public JSONObject checkoutBook(String bookName) {
        return changeCheckoutStatus(bookName, "Book", true, "checkout");
    }

    public JSONObject returnBook(String bookName) {
        return changeCheckoutStatus(bookName, "Book", false, "return");
    }

    public JSONObject checkoutMovie(String moiveName) {
        return changeCheckoutStatus(moiveName, "Movie", true, "checkout");
    }

    public JSONObject returnMovie(String bookName) {
        return changeCheckoutStatus(bookName, "Movie", false, "return");
    }

    private JSONObject changeCheckoutStatus(String itemName, String listName, boolean newCheckoutStatus, String functionName) {
        JSONObject list = (JSONObject) data.get(listName);
        JSONObject selectedItem = (JSONObject) list.get(itemName);
        message.printMessage("Enter" + listName + "Name");
        if (selectedItem != null) {
            Object checkoutStatus = selectedItem.get("Checkout");
            if (!checkoutStatus.equals(newCheckoutStatus)) {
                selectedItem.remove("Checkout");
                selectedItem.put("Checkout", newCheckoutStatus);
                message.printMessage(functionName + listName + "Successmessage");
                io.updateJasonFile(data, file);
                return data;
            }
        }
        message.printMessage(functionName + listName + "Unsuccessmessage");
        return null;
    }

    public List<String> getAvailableBooks() {
        List<String> availableBooks = new ArrayList<String>();
        JSONObject books = (JSONObject) this.data.get("Book");
        for (Object o : books.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) books.get(key);
            Object checkoutStatus = details.get("Checkout");
            if (checkoutStatus.equals(false)) {
                availableBooks.add("Book Title: " + key);
                availableBooks.add("Author: " + details.get("Author"));
                availableBooks.add("Publication Year: " + details.get("PublicationYear") + "\n");
            }
        }
        return availableBooks;
    }

    public List<String> getAvailableMovies() {
        List<String> availableMovies = new ArrayList<String>();
        JSONObject movies = (JSONObject) this.data.get("Movie");
        for (Object o : movies.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) movies.get(key);
            Object checkoutStatus = details.get("Checkout");
            if (checkoutStatus.equals(false)) {
                availableMovies.add("Movie Title: " + key);
                availableMovies.add("Year: " + details.get("Year"));
                availableMovies.add("Rate: " + details.get("Rate"));
                availableMovies.add("Director: " + details.get("Director") + "\n");
            }
        }
        return availableMovies;
    }


    public List<String> menuList() {
        List<String> menu = new ArrayList<String>();
        menu.add("Q: Quit");
        menu.add("C: Checkout book");
        menu.add("L: List Books");
        menu.add("R: Return Books");
        return menu;
    }

    public void render(List<String> options) {
        for (String option : options) {
            System.out.println(option);
        }
    }

    public String getUserOption() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim().toUpperCase();
    }

}
