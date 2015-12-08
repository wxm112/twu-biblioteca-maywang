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
    IO mockedIo = mock(IO.class);
    UI app = new UI(mockedLibrarian,mockedMessage,mockedIo);



    @Test
    public void shouldCallRenderFunctionTest() throws Exception {
        ArrayList<String> bookList = mockedLibrarian.getAvailabeBooks();
        app.mainMenuHelper("L");
        verify(mockedLibrarian, times(1)).render(bookList);
    }

    @Test
    public void shouldCallCheckoutBookFunctionTest() throws Exception {
        when(mockedIo.getUserOption()).thenReturn("anyString");
        app.mainMenuHelper("C");
        verify(mockedMessage, times(1)).printMessasge("EnterBookName");
        verify(mockedLibrarian, times(1)).checkoutBook("anyString");
    }

    @Test
     public void shouldCallReturnBookBookFunctionTest() throws Exception {
        when(mockedIo.getUserOption()).thenReturn("anyString");
        app.mainMenuHelper("R");
        verify(mockedMessage, times(1)).printMessasge("EnterBookName");
        verify(mockedLibrarian, times(1)).returnBook("anyString");
    }

    @Test
    public void invalidUnserOptionTest() throws Exception {
        app.mainMenuHelper("x");
        verify(mockedMessage, times(1)).printMessasge("InvalidOption");
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
//        verify(mockedMessage, times(1)).printMessasge("Options");
//        verify(mockedLibrarian, times(1)).render(menuList);
//        verify(mockedIo, times(1)).getUserOption();
//    }
}