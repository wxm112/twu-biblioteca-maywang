package com.twu.biblioteca;

/**
 * Created by mayw on 8/12/2015.
 */
public class UI {
    private Librarian librarian;
    private Message message;
    private boolean flag = true;

    public boolean getFlag(){return flag;}

    public UI(Librarian lib, Message mes) {
        this.librarian = lib;
        this.message = mes;
    }

    public void printMainMenu(){
        while (flag) {
            message.printMessage("Options");
            librarian.render(librarian.menuList());
            mainMenuHelper(librarian.getUserOption());
        }
    }

    public boolean mainMenuHelper(String userInput) {
        if (userInput.equals("Q")) {
            flag = false;
        }else if (userInput.equals("L")) {
            librarian.render(librarian.getAvailabeBooks());
        }else if (userInput.equals("C")) {
            librarian.checkoutBook(librarian.getUserOption());
        }else if (userInput.equals("R")) {
            librarian.returnBook(librarian.getUserOption());
        }else {
            message.printMessage("InvalidOption");
        }
        return true;
    }



}
