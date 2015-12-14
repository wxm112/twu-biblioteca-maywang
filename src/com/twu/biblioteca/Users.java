package com.twu.biblioteca;

import org.json.simple.JSONObject;

/**
 * Created by mayw on 11/12/2015.
 */
public class Users {
    private Message message;
    private JSONObject usersData;
    private Librarian librarian;
    private boolean loged = false;

    public boolean getLoged(){return loged;}

    public Users(String file, Message mes, Librarian lib) {
        this.usersData = new IO().fileReader(file);
        this.message = mes;
        this.librarian = lib;
    }

    public void userCredential(){
        while (loged == false) {
            message.printMessage("EnterLibraryNumber");
            String libraryNo = librarian.getUserInput();
            message.printMessage("EnterUserPassword");
            String inputedpassword = librarian.getUserInput();
            JSONObject userDetails = (JSONObject) usersData.get(libraryNo);
            if (userDetails != null) {
                String savedPassword = (String) userDetails.get("Password");
                if (inputedpassword.equals(savedPassword)) {
                    librarian.setCurrentUser(libraryNo);
                    if(userDetails.get("UserName").equals("Admin")) librarian.setAdmin(true);
                    loged = true;
                } else message.printMessage("InvalidUserInfor");
            } else message.printMessage("InvalidUserInfor");
        }
    }
}
