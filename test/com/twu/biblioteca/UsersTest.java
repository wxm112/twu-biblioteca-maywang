package com.twu.biblioteca;

import org.junit.Test;

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
    public void askForLibraryNoAndPasswordAgainWhenUserEnteredWrongPasswordTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","2334","123-1234","1234");
        app.userCredential();
        verify(mockedMessage,times(2)).printMessage("EnterLibraryNumber");
        verify(mockedMessage,times(2)).printMessage("EnterUserPassword");
        verify(mockedMessage,times(1)).printMessage("InvalidUserInfor");
        assertEquals(app.getLoged(), true);
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
}