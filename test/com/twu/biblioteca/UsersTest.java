package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by mayw on 11/12/2015.
 */
public class UsersTest {
    Message mockedMessage = mock(Message.class);
    Librarian mockedLibrarian = mock(Librarian.class);
    Users app = new Users("./users.json",mockedMessage, mockedLibrarian);

    @Test
    public void returnTrueWhenUserEnterACorrectPasswordTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","1234");
        app.userCredential();
        assertEquals(app.getLoged(), true);
    }

    @Test
    public void setCurrentUserWhenUserEnterACorrectPasswordTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","1234");
        app.userCredential();
        verify(mockedLibrarian,times(1)).setCurrentUser("123-1234");
    }

    @Test
    public void setAdmintoTrueWhenUserIsAdminTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("111-1111","1111");
        app.userCredential();
        verify(mockedLibrarian,times(1)).setAdmin(true);
    }

    @Test
    public void doNotsetAdmintoTrueWhenUserisNotAdminTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","1234");
        app.userCredential();
        verify(mockedLibrarian,times(0)).setAdmin(true);
    }

    @Test
    public void askForLibraryNoAndPasswordAgainWhenUserEnteredWrongPasswordTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","2334","123-1234","1234");
        app.userCredential();
        verify(mockedMessage,times(2)).printMessage("EnterLibraryNumber");
        verify(mockedMessage,times(2)).printMessage("EnterUserPassword");
        verify(mockedMessage,times(1)).printMessage("InvalidUserInfor");
        assertEquals(app.getLoged(), true);
    }

    @Test
    public void doNotsetCurrentUserWhenUserEnterIncorrectPasswordTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","1334","123-1234","1234");
        app.userCredential();
        verify(mockedLibrarian,times(1)).setCurrentUser("123-1234");
    }

    @Test
    public void doNotsetCurrentUserWhenUserEnterIncorrectLibraryNoTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-3","1234","123-1234","1234");
        app.userCredential();
        verify(mockedLibrarian,times(1)).setCurrentUser("123-1234");
    }

    @Test
    public void askForLibraryNoAndPasswordAgainWhenUserEnteredWrongLibraryNoTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("3-1234", "1234", "123-1234", "1234");
        app.userCredential();
        verify(mockedMessage,times(2)).printMessage("EnterLibraryNumber");
        verify(mockedMessage,times(2)).printMessage("EnterUserPassword");
        verify(mockedMessage,times(1)).printMessage("InvalidUserInfor");
        assertEquals(app.getLoged(), true);

    }


    @Test
    public void getUsersDetailInformationTest() throws Exception {
        List<String> userInfor = new ArrayList<>();
        userInfor.add("User Name: Admin");
        userInfor.add("Phone Number: 1234567");
        userInfor.add("Password: 1111");
        userInfor.add("Email Address: admin@gmail.com");
        userInfor.add("\n");

        assertEquals(userInfor, app.getUserInforMation("111-1111"));
    }

}