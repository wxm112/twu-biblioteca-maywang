package com.twu.biblioteca;

import org.json.simple.JSONObject;

/**
 * Created by mayw on 7/12/2015.
 */
public class Message {

    public void printMessage(String messageName){
        JSONObject messages = new JSONObject();
        messages.put("Welcome", "Hello, welcome to Biblioteca!");
        messages.put("Options", "Please let us know your options: ");
        messages.put("InvalidOption", "Select a invalid option!");
        messages.put("InvalidUserInfor", "Invalid library number or password: ");
        messages.put("EnterBookName", "Please enter the book's name: ");
        messages.put("EnterMovieName", "Please enter the movie's name: ");
        messages.put("EnterLibraryNumber", "Please enter your user name: ");
        messages.put("EnterUserPassword", "Please enter your password: ");
        messages.put("checkoutBookSuccessmessage", "Thank you! Enjoy the book");
        messages.put("checkoutBookUnsuccessmessage", "That book is not available.");
        messages.put("checkoutMovieSuccessmessage", "Thank you! Enjoy the movie");
        messages.put("checkoutMovieUnsuccessmessage", "That movie is not available.");
        messages.put("returnBookSuccessmessage", "Thank you for returning the book.");
        messages.put("returnBookUnsuccessmessage", "That is not a valid book to return.");
        messages.put("returnMovieSuccessmessage", "Thank you for returning the movie.");
        messages.put("returnMovieUnsuccessmessage", "That is not a valid movie to return.");

        System.out.println(messages.get(messageName) + "\n");
    }
}
