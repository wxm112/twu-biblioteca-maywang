package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by mayw on 8/12/2015.
 */
public class UITest {
    Librarian mockedLibrarian = mock(Librarian.class);
    Message mockedMessage = mock(Message.class);
    Users mockedUsers = mock(Users.class);
    UI app = new UI(mockedLibrarian,mockedMessage, mockedUsers);



    @Test
     public void listAllAvailableBooksTest() throws Exception {
        List<String> bookList = mockedLibrarian.getAvailableItemsOfType("Book","user");
        app.mainMenuHelper("LB");
        verify(mockedLibrarian, times(1)).render(bookList);
    }

    @Test
    public void listAllAvailableMoviesTest() throws Exception {
        List<String> movieList = mockedLibrarian.getAvailableItemsOfType("Movie","user");
        app.mainMenuHelper("LM");
        verify(mockedLibrarian, times(1)).render(movieList);
    }

    @Test
     public void checkoutAbookTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("anyString");
        app.mainMenuHelper("CB");
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedLibrarian, times(1)).checkoutBook("anyString");
    }

    @Test
    public void checkoutAmovieTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("anyString");
        app.mainMenuHelper("CM");
        verify(mockedMessage, times(1)).printMessage("EnterMovieName");
        verify(mockedLibrarian, times(1)).checkoutMovie("anyString");
    }

    @Test
     public void returnAbookTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("anyString");
        app.mainMenuHelper("RB");
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedLibrarian, times(1)).returnBook("anyString");
    }

    @Test
    public void returnAmovieTest() throws Exception {
        when(mockedLibrarian.getUserInput()).thenReturn("anyString");
        app.mainMenuHelper("RM");
        verify(mockedMessage, times(1)).printMessage("EnterMovieName");
        verify(mockedLibrarian, times(1)).returnMovie("anyString");
    }

    @Test
    public void invalidUnserOptionTest() throws Exception {
        app.mainMenuHelper("x");
        verify(mockedMessage, times(1)).printMessage("InvalidOption");
    }

    @Test
    public void teturnOptionTest() throws Exception {
        app.mainMenuHelper("Q");
        assertEquals(app.getFlag(), false);
    }

}