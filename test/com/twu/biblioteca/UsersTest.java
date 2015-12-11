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
        assertEquals(app.usercredential(), true);
    }

    @Test
    public void returnFalseWhenUserEnterdWrongPasswordTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("123-1234","2334");
        assertEquals(app.usercredential(), false);
        verify(mockedMessage,times(1)).printMessage("InvalidUserInfor");
    }

    @Test
    public void returnFalseWhenUserEnterdWrongLibraryNoTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("3-1234","1234");
        assertEquals(app.usercredential(), false);
        verify(mockedMessage,times(1)).printMessage("InvalidUserInfor");
    }
}