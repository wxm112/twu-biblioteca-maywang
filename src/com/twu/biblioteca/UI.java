package com.twu.biblioteca;

/**
 * Created by mayw on 8/12/2015.
 */
public class UI {
    private Librarian librarian;
    private Message message;
    private IO io;
    public boolean flag = true;

    public UI(Librarian lib, Message mes,IO file) {
        this.librarian = lib;
        this.message = mes;
        this.io = file;
    }

    public void printMainMenu(){
        while (flag) {
            message.printMessasge("Options");
            librarian.render(librarian.menuList());
            mainMenuHelper(io.getUserOption());
        }
    }

    public boolean mainMenuHelper(String userInput) {
        if (userInput.equals("Q")) {
            flag = false;
        }else if (userInput.equals("L")) {
            librarian.render(librarian.getAvailabeBooks());
        }else if (userInput.equals("C")) {
            message.printMessasge("EnterBookName");
            librarian.checkoutBook(io.getUserOption());
        }else if (userInput.equals("R")) {
            message.printMessasge("EnterBookName");
            librarian.returnBook(io.getUserOption());
        }else {
            message.printMessasge("InvalidOption");
        }
        return true;
    }


}
