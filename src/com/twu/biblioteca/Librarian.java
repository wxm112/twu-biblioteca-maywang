package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Librarian {
    private JSONObject data;
    private Message message;
    private IO io;
    private String file;

    public Librarian(String filename, Message message, IO io) {
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

    public JSONObject returnMovie(String moiveName) {
        return changeCheckoutStatus(moiveName, "Movie", false, "return");
    }

    private JSONObject changeCheckoutStatus(String itemName, String listName, boolean newCheckoutStatus, String functionName) {
        JSONObject list = (JSONObject) data.get(listName);
        JSONObject selectedItem = (JSONObject) list.get(itemName);
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
        return getAvailableItemsOfType("Book");
    }

    public List<String> getAvailableMovies() {
        return getAvailableItemsOfType("Movie");
    }

    public List<String> menuList() {
        List<String> menu = new ArrayList<String>();
        menu.add("Q: Quit");
        menu.add("LB: List Books");
        menu.add("CB: Checkout Books");
        menu.add("RB: Return Books");
        menu.add("LM: List Movies");
        menu.add("CM: Checkout Movies");
        menu.add("RM: Return Movies");
        return menu;
    }

    public void render(List<String> options) {
        for (String option : options) {
            System.out.println(option);
        }
    }

    public String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim();
    }

    public List<String> getAvailableItemsOfType(String itemType) {
        List<String> availableItems = new ArrayList<String>();
        JSONObject items = (JSONObject) this.data.get(itemType);
        for (Object o : items.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) items.get(key);
            Object checkoutStatus = details.get("Checkout");
            if (checkoutStatus.equals(false)) {
                addItemToList(availableItems, itemType, key, details);
            }
        }
        return availableItems;
    }

    private void addItemToList(List<String> items, String itemType, String key, JSONObject details) {
        if (itemType.equals("Book")) {
            items.add("Book Title: " + key);
            items.add("Author: " + details.get("Author"));
            items.add("Publication Year: " + details.get("PublicationYear") + "\n");
        } else if (itemType.equals("Movie")) {
            items.add("Movie Title: " + key);
            items.add("Year: " + details.get("Year"));
            items.add("Rate: " + details.get("Rate"));
            items.add("Director: " + details.get("Director") + "\n");
        }
    }

}
