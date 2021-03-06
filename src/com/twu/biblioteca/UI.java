package com.twu.biblioteca;

/**
 * Created by mayw on 8/12/2015.
 */
public class UI {
    private Librarian librarian;
    private Message message;
    private Users users;
    private boolean flag = true;

    public boolean getFlag(){return flag;}

    public UI(Librarian lib, Message mes, Users us) {
        this.librarian = lib;
        this.message = mes;
        this.users = us;
    }

    public void printUsersMainMenu(){
        users.userCredential();
        if (users.getLoged() == true) {
            while (flag) {
                message.printMessage("Options");
                librarian.render(librarian.menuList());
                mainMenuHelper(librarian.getUserInput());
            }
        }
    }

    public boolean mainMenuHelper(String userInput){
        userInput = userInput.toUpperCase();
        if (userInput.equals("Q")) {
            flag = false;
        }else if (userInput.equals("I")) {
            librarian.render(users.getUserInforMation(librarian.getCurrentUser()));
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
        }else if (userInput.equals("CD")) {
            librarian.render(librarian.getCheckoutItemDetails());
        }else {
            message.printMessage("InvalidOption");
        }
        return true;
    }

}
