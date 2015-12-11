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
            mainMenuHelper(librarian.getUserInput());
        }
    }

    public boolean mainMenuHelper(String userInput){
        userInput = userInput.toUpperCase();
        if (userInput.equals("Q")) {
            flag = false;
        }else if (userInput.equals("LB")) {
            librarian.render(librarian.getAvailableBooks());
        }else if (userInput.equals("CB")) {
            message.printMessage("EnterBookName");
            librarian.checkoutBook(librarian.getUserInput());
        }else if (userInput.equals("RB")) {
            message.printMessage("EnterBookName");
            librarian.returnBook(librarian.getUserInput());
        }else if (userInput.equals("LM")) {
            librarian.render(librarian.getAvailableMovies());
        }else if (userInput.equals("CM")) {
            message.printMessage("EnterMovieName");
            librarian.checkoutMovie(librarian.getUserInput());
        }else if (userInput.equals("RM")) {
            message.printMessage("EnterMovieName");
            librarian.returnMovie(librarian.getUserInput());
        }else {
            message.printMessage("InvalidOption");
        }
        return true;
    }

}
