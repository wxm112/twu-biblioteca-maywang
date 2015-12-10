package com.twu.biblioteca;

public class BibliotecaApp {
    private UI ui;
    private Message message = new Message();
    private IO io = new IO();
    private Leyebrarian leyebrarian = new Leyebrarian("./data.json", message,io);

    public BibliotecaApp() {
        this.ui = new UI(leyebrarian,message);
    }


    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.message.printMessage("Welcome");
        app.ui.printMainMenu();
    }


}
