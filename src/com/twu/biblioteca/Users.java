package com.twu.biblioteca;

import org.json.simple.JSONObject;

/**
 * Created by mayw on 11/12/2015.
 */
public class Users {
    private Message message;
    private JSONObject userSData;
    private Librarian librarian;


    public Users(String file, Message mes, Librarian lib) {
        this.userSData = new IO().fileReader(file);
        this.message = mes;
        this.librarian = lib;
    }

    public boolean usercredential(){
        message.printMessage("EnterLibraryNumber");
        String libraryNo = librarian.getUserInput();
        message.printMessage("EnterUserPassword");
        String inputedpassword = librarian.getUserInput();
        JSONObject userDetails = (JSONObject) userSData.get(libraryNo);
        if (userDetails != null) {
            String savedPassword = (String)userDetails.get("Password");
            if(inputedpassword.equals(savedPassword)) return true;
        }
        message.printMessage("InvalidUserInfor");
        return false;
    }
}
