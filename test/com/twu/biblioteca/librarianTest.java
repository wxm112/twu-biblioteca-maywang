package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mayw on 7/12/2015.
 */
public class LibrarianTest {
    private Librarian app = new Librarian("./testData.json");


    private JSONObject initialize(String checkoutStatus){
        JSONObject obj = new JSONObject();

        JSONObject bookDetail1 = new JSONObject();
        bookDetail1.put("Checkout",checkoutStatus);
        bookDetail1.put("Author","Johanna Basford");
        bookDetail1.put("PublicationYear", "2015");
        obj.put("SECRET GARDEN", bookDetail1);
        JSONObject bookDetail2 = new JSONObject();
        bookDetail2.put("Checkout","No");
        bookDetail2.put("Author","Warner Brothers");
        bookDetail2.put("PublicationYear", "2015");
        obj.put("HARRY POTTER COLOURING BOOK", bookDetail2);
        JSONObject bookDetail3 = new JSONObject();
        bookDetail3.put("Checkout",checkoutStatus);
        bookDetail3.put("Author","Drew Daywal");
        bookDetail3.put("PublicationYear", "2015");
        obj.put("THE DAY THE CRAYONS QUIT", bookDetail3);

        return obj;

    }


    @Test
    public void checkoutBookPassTest() throws Exception {
        assertEquals(app.checkoutBook("SECRET GARDEN"), initialize("Yes"));
    }


    @Test
    public void checkoutBookMissWithWrongBookNameTest() throws Exception {
        assertEquals(app.checkoutBook("THE DAY THE CRAYONS QUIT"), null);
    }

    @Test
    public void checkoutBookMissWithGibberishTest() throws Exception {
        assertEquals(app.checkoutBook("tyjed"), null);
    }

    @Test
    public void returnBookPassTest() throws Exception {
        assertEquals(app.returnBook("THE DAY THE CRAYONS QUIT"), initialize("No"));
    }

    @Test
    public void returnBookPassWithWrongBookTest() throws Exception {
        assertEquals(app.returnBook("SECRET GARDEN"), null);
    }

    @Test
    public void returnBookMissWithGibberishTest() throws Exception {
        assertEquals(app.returnBook("tyjed"), null);
    }

}