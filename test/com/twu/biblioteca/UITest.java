package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by mayw on 8/12/2015.
 */
public class UITest {
    Leyebrarian mockedLeyebrarian = mock(Leyebrarian.class);
    Message mockedMessage = mock(Message.class);
    UI app = new UI(mockedLeyebrarian,mockedMessage);



    @Test
    public void shouldCallRenderFunctionTest() throws Exception {
        ArrayList<String> bookList = mockedLeyebrarian.getAvailableBooks();
        app.mainMenuHelper("L");
        verify(mockedLeyebrarian, times(1)).render(bookList);
    }

    @Test
    public void shouldCallCheckoutBookFunctionTest() throws Exception {
        when(mockedLeyebrarian.getUserOption()).thenReturn("anyString");
        app.mainMenuHelper("C");
        verify(mockedLeyebrarian, times(1)).checkoutBook("anyString");
    }

    @Test
     public void shouldCallReturnBookBookFunctionTest() throws Exception {
        when(mockedLeyebrarian.getUserOption()).thenReturn("anyString");
        app.mainMenuHelper("R");
        verify(mockedLeyebrarian, times(1)).returnBook("anyString");
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