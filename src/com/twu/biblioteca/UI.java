package com.twu.biblioteca;

/**
 * Created by mayw on 8/12/2015.
 */
public class UI {
    private Leyebrarian leyebrarian;
    private Message message;
    private boolean flag = true;

    public boolean getFlag(){return flag;}

    public UI(Leyebrarian lib, Message mes) {
        this.leyebrarian = lib;
        this.message = mes;
    }

    public void printMainMenu(){
        while (flag) {
            message.printMessage("Options");
            leyebrarian.render(leyebrarian.menuList());
            mainMenuHelper(leyebrarian.getUserOption());
        }
    }

    public boolean mainMenuHelper(String userInput) {
        if (userInput.equals("Q")) {
            flag = false;
        }else if (userInput.equals("L")) {
            leyebrarian.render(leyebrarian.getAvailableBooks());
        }else if (userInput.equals("C")) {
            leyebrarian.checkoutBook(leyebrarian.getUserOption());
        }else if (userInput.equals("R")) {
            leyebrarian.returnBook(leyebrarian.getUserOption());
        }else {
            message.printMessage("InvalidOption");
        }
        return true;
    }



}
