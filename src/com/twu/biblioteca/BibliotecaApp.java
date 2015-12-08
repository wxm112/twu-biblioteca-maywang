package com.twu.biblioteca;

public class BibliotecaApp {
    private UI ui;
    private Message message = new Message();
    private Librarian librarian = new Librarian("./data.json", message);

    public BibliotecaApp() {
        this.ui = new UI(librarian,message);
    }


    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.message.printMessasge("Welcome");
        app.ui.printMainMenu();
    }


}
