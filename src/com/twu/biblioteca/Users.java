package com.twu.biblioteca;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                    if(userDetails.get("User Name").equals("Admin")) librarian.setAdmin(true);
                    loged = true;
                } else message.printMessage("InvalidUserInfor");
            } else message.printMessage("InvalidUserInfor");
        }
    }

    public List<String> getUserInforMation(String libraryNo){
        List<String> userInfor= new ArrayList<String>();
        for(Object obj : usersData.keySet()) {
            if(obj.equals(libraryNo)) {
                HashMap userDetail = (HashMap)usersData.get(obj);
                for(Object user : userDetail.keySet()) {
                    userInfor.add(user + ": " + userDetail.get(user));
                }
                userInfor.add("\n");
            }
        }
        return userInfor;
    }
}
