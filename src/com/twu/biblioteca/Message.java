package com.twu.biblioteca;

import org.json.simple.JSONObject;

/**
 * Created by mayw on 7/12/2015.
 */
public class Message {

    public void printMessasge(String messageName){
        JSONObject messages = new JSONObject();
        messages.put("Welcome", "Hello, welcome to Biblioteca!");
        messages.put("Options", "Please let us know your options: ");
        messages.put("InvalidOption", "Select a invalid option!");
        messages.put("EnterBookName", "Please enter the book's name: ");
        messages.put("checkoutSuccessmessage", "Thank you! Enjoy the book");
        messages.put("checkoutUnsuccessmessage", "That book is not available.");
        messages.put("returnSuccessmessage", "Thank you for returning the book.");
        messages.put("returnUnsuccessmessage", "That is not a valid book to return.");

        System.out.println(messages.get(messageName) + "\n");
    }
}
