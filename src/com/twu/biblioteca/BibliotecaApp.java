package com.twu.biblioteca;

public class BibliotecaApp {
    private UI ui;
    private Message message = new Message();
    private IO io = new IO();
    private Librarian librarian = new Librarian("./data.json");

    public BibliotecaApp() {
        this.ui = new UI(librarian,message,io);
    }


    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.message.printMessasge("Welcome");
        app.ui.printMainMenu();
    }


}
