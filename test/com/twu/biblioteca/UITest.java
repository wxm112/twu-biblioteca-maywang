package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by mayw on 8/12/2015.
 */
public class UITest {
    Librarian mockedLibrarian = mock(Librarian.class);
    Message mockedMessage = mock(Message.class);
    UI app = new UI(mockedLibrarian,mockedMessage);



    @Test
    public void shouldCallRenderFunctionTest() throws Exception {
        ArrayList<String> bookList = mockedLibrarian.getAvailabeBooks();
        app.mainMenuHelper("L");
        verify(mockedLibrarian, times(1)).render(bookList);
    }

    @Test
    public void shouldCallCheckoutBookFunctionTest() throws Exception {
        when(mockedLibrarian.getUserOption()).thenReturn("anyString");
        app.mainMenuHelper("C");
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedLibrarian, times(1)).checkoutBook("anyString");
    }

    @Test
     public void shouldCallReturnBookBookFunctionTest() throws Exception {
        when(mockedLibrarian.getUserOption()).thenReturn("anyString");
        app.mainMenuHelper("R");
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedLibrarian, times(1)).returnBook("anyString");
    }

    @Test
    public void invalidUnserOptionTest() throws Exception {
        app.mainMenuHelper("x");
        verify(mockedMessage, times(1)).printMessage("InvalidOption");
    }

    @Test
    public void teturnOptionTest() throws Exception {
        app.mainMenuHelper("Q");
        assertEquals(app.flag, false);
    }

//    @Test
//     public void optionMessageGetCalledbyPrintMainMenuTest() throws Exception {
//        ArrayList<String> menuList = new ArrayList<String>();
//        when(mockedLibrarian.menuList()).thenReturn(menuList);
//        app.printMainMenu();
//        verify(mockedMessage, times(1)).printMessage("Options");
//        verify(mockedLibrarian, times(1)).render(menuList);
//        verify(mockedIo, times(1)).getUserOption();
//    }
}