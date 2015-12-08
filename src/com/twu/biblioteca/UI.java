package com.twu.biblioteca;

/**
 * Created by mayw on 8/12/2015.
 */
public class UI {
    private Librarian librarian;
    private Message message;
    public boolean flag = true;

    public UI(Librarian lib, Message mes) {
        this.librarian = lib;
        this.message = mes;
    }

    public void printMainMenu(){
        while (flag) {
            message.printMessasge("Options");
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
            message.printMessasge("EnterBookName");
            librarian.checkoutBook(librarian.getUserOption());
        }else if (userInput.equals("R")) {
            message.printMessasge("EnterBookName");
            librarian.returnBook(librarian.getUserOption());
        }else {
            message.printMessasge("InvalidOption");
        }
        return true;
    }



}
