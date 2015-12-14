package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Librarian {
    private JSONObject data;
    private Message message;
    private String currentUser = null;
    private boolean admin = false;
    private IO io;
    private String file;


    public String getCurrentUser(){return currentUser;}
    public void setCurrentUser(String user){ this.currentUser = user;}

    public boolean getAdmin(){return admin;}
    public void setAdmin(boolean adminStatus){ this.admin = adminStatus;}


    public Librarian(String filename, Message message, IO io) {
        this.file = filename;
        this.io = io;
        this.data = io.fileReader(file);
        this.message = message;
    }


    public JSONObject checkoutBook(String bookName) {
        return changeCheckoutStatus(bookName, "Book", true, "checkout",currentUser);
    }

    public JSONObject returnBook(String bookName) {
        return changeCheckoutStatus(bookName, "Book", false, "return", null);
    }

    public JSONObject checkoutMovie(String moiveName) {
        return changeCheckoutStatus(moiveName, "Movie", true, "checkout", currentUser);
    }

    public JSONObject returnMovie(String moiveName) {
        return changeCheckoutStatus(moiveName, "Movie", false, "return", null);
    }

    private JSONObject changeCheckoutStatus(String itemName, String listName, boolean newCheckoutStatus, String functionName, String borrower) {
        JSONObject list = (JSONObject) data.get(listName);
        JSONObject selectedItem = (JSONObject) list.get(itemName);
        if (selectedItem != null) {
            Object checkoutStatus = selectedItem.get("Checkout");
            if (!checkoutStatus.equals(newCheckoutStatus)) {
                selectedItem.remove("Checkout");
                selectedItem.put("Checkout", newCheckoutStatus);
                selectedItem.remove("Borrower");
                selectedItem.put("Borrower", borrower);
                message.printMessage(functionName + listName + "Successmessage");
                io.updateJasonFile(data, file);
                return data;
            }
        }
        message.printMessage(functionName + listName + "Unsuccessmessage");
        return null;
    }


    public List<String> getAvailableBooks() {
        return getAvailableItemsOfType("Book","user");
    }

    public List<String> getAvailableMovies() {
        return getAvailableItemsOfType("Movie", "user");
    }

    public List<String> getCheckoutItemDetails() {
        List<String> checkoutItems = getAvailableItemsOfType("Book", "Admin");
        for (String item : getAvailableItemsOfType("Movie", "Admin")) {
            checkoutItems.add(item);
        }
        return checkoutItems;
    }




    public List<String> menuList() {
        List<String> menu = new ArrayList<String>();
        menu.add("Q: Quit");
        if (admin){
            menu.add("CD: Checkout details");
        }else {
            menu.add("I: User information");
            menu.add("LB: List Books");
            menu.add("CB: Checkout Books");
            menu.add("RB: Return Books");
            menu.add("LM: List Movies");
            menu.add("CM: Checkout Movies");
            menu.add("RM: Return Movies");
        }
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

    public List<String> getAvailableItemsOfType(String itemType, String userType) {
        List<String> availableItems = new ArrayList<String>();
        JSONObject items = (JSONObject) this.data.get(itemType);
        for (Object o : items.keySet()) {
            String key = (String) o;
            JSONObject details = (JSONObject) items.get(key);
            Object checkoutStatus = details.get("Checkout");
            if(userType.equals("Admin")) {
                if (checkoutStatus.equals(true)) {
                    addItemToList(availableItems, itemType,userType, key, details);
                }
            }else if (checkoutStatus.equals(false)){
                addItemToList(availableItems, itemType, userType,key, details);
            }
        }
        return availableItems;
    }

    private void addItemToList(List<String> items, String itemType,String userType, String key, JSONObject details) {
        if (userType.equals("Admin")) {
            items.add("Item Title: " + key);
            items.add("Borrower's library NO.: " + details.get("Borrower") + "\n");
        } else {
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
}
