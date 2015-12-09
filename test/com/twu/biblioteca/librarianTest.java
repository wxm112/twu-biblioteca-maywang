package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mayw on 7/12/2015.
 */
public class LibrarianTest {
    Message mockedMessage = mock(Message.class);
    Librarian app = new Librarian("./testData.json", mockedMessage);


    private JSONObject initialize(boolean checkoutStatus1, boolean checkoutStatus2) {
        JSONObject obj = new JSONObject();

        JSONObject bookDetail1 = new JSONObject();
        bookDetail1.put("Checkout", checkoutStatus1);
        bookDetail1.put("Author", "Johanna Basford");
        bookDetail1.put("PublicationYear", "2015");
        obj.put("SECRET GARDEN", bookDetail1);
        JSONObject bookDetail2 = new JSONObject();
        bookDetail2.put("Checkout", false);
        bookDetail2.put("Author", "Warner Brothers");
        bookDetail2.put("PublicationYear", "2015");
        obj.put("HARRY POTTER COLOURING BOOK", bookDetail2);
        JSONObject bookDetail3 = new JSONObject();
        bookDetail3.put("Checkout", checkoutStatus2);
        bookDetail3.put("Author", "Drew Daywal");
        bookDetail3.put("PublicationYear", "2015");
        obj.put("THE DAY THE CRAYONS QUIT", bookDetail3);

        return obj;

    }


    @Test
    public void checkoutBookPassTest() throws Exception {
        assertEquals(app.checkoutBook("SECRET GARDEN"), initialize(true, true));
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedMessage, times(1)).printMessage("checkoutSuccessmessage");
    }


    @Test
    public void checkoutBookMissWithWrongBookNameTest() throws Exception {
        assertEquals(app.checkoutBook("THE DAY THE CRAYONS QUIT"), null);
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedMessage, times(1)).printMessage("checkoutUnsuccessmessage");
    }

    @Test
    public void checkoutBookMissWithGibberishTest() throws Exception {
        assertEquals(app.checkoutBook("tyjed"), null);
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedMessage, times(1)).printMessage("checkoutUnsuccessmessage");
    }

    @Test
    public void returnBookPassTest() throws Exception {
        assertEquals(app.returnBook("THE DAY THE CRAYONS QUIT"), initialize(false, false));
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedMessage, times(1)).printMessage("returnSuccessmessage");
    }

    @Test
    public void returnBookPassWithWrongBookTest() throws Exception {
        assertEquals(app.returnBook("SECRET GARDEN"), null);
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedMessage, times(1)).printMessage("returnUnsuccessmessage");
    }

    @Test
    public void returnBookMissWithGibberishTest() throws Exception {
        assertEquals(app.returnBook("tyjed"), null);
        verify(mockedMessage, times(1)).printMessage("EnterBookName");
        verify(mockedMessage, times(1)).printMessage("returnUnsuccessmessage");
    }

    @Test
    public void getAvailabeBooks() throws Exception {
        ArrayList<String> availabeBookList = new ArrayList<String>();
        availabeBookList.add("Book Title: SECRET GARDEN");
        availabeBookList.add("Author: Johanna Basford");
        availabeBookList.add("Publication Year: 2015" + "\n");
        availabeBookList.add("Book Title: HARRY POTTER COLOURING BOOK");
        availabeBookList.add("Author: Warner Brothers");
        availabeBookList.add("Publication Year: 2015" + "\n");

        assertEquals(app.getAvailabeBooks(), availabeBookList);
    }
}