package com.twu.biblioteca;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by mayw on 7/12/2015.
 */
public class LibrarianTest extends ProjectGenerator {
    Message mockedMessage = mock(Message.class);
    IO io = new IO();
    Librarian app = new Librarian("./testData.json", mockedMessage, io);

    @Test
    public void checkoutBookShouldUpdateDataFileTest() throws Exception {
        app.setCurrentUser("123-1234");
        app.checkoutBook("SECRET GARDEN");
        verify(mockedMessage, times(1)).printMessage("checkoutBookSuccessmessage");
        assertEquals(initializeBooks(true, true, "123-1234", "123-1234"),new IO().fileReader("./testData.json"));
    }

    @Test
     public void checkoutBookMissWithWrongBookNameTest() throws Exception {
        app.checkoutBook("THE DAY THE CRAYONS QUIT");
        verify(mockedMessage, times(1)).printMessage("checkoutBookUnsuccessmessage");
    }

    @Test
    public void checkoutBookMissWithGibberishTest() throws Exception {
        assertEquals(app.checkoutBook("tyjed"), null);
        verify(mockedMessage, times(1)).printMessage("checkoutBookUnsuccessmessage");
    }

    @Test
    public void returnBookShouldUpdateDataFileTest() throws Exception {
        app.returnBook("THE DAY THE CRAYONS QUIT");
        verify(mockedMessage, times(1)).printMessage("returnBookSuccessmessage");
        assertEquals(initializeBooks(false, false, null,null),new IO().fileReader("./testData.json"));
    }

    @Test
    public void returnBookMissWithWrongBookTest() throws Exception {
        assertEquals(app.returnBook("SECRET GARDEN"), null);
        verify(mockedMessage, times(1)).printMessage("returnBookUnsuccessmessage");
    }

    @Test
    public void returnBookMissWithGibberishTest() throws Exception {
        assertEquals(app.returnBook("tyjed"), null);
        verify(mockedMessage, times(1)).printMessage("returnBookUnsuccessmessage");
    }

    @Test
    public void checkoutMovieShouldUpdateDataFileTest() throws Exception {
        app.setCurrentUser("123-1234");
        app.checkoutMovie("Movie1");
        verify(mockedMessage, times(1)).printMessage("checkoutMovieSuccessmessage");
        assertEquals(new IO().fileReader("./testData.json"), initializeMovies(true, true,"123-1234","123-1234"));
    }

    @Test
    public void checkoutMovieMissWithWrongMovieNameTest() throws Exception {
        assertEquals(app.checkoutMovie("Movie0"), null);
        verify(mockedMessage, times(1)).printMessage("checkoutMovieUnsuccessmessage");
    }

    @Test
    public void checkoutMovieMissWithGibberishTest() throws Exception {
        assertEquals(app.checkoutMovie("Movie0"), null);
        verify(mockedMessage, times(1)).printMessage("checkoutMovieUnsuccessmessage");
    }

    @Test
    public void returntMovieShouldUpdateDataFileTest() throws Exception {
        app.returnMovie("Movie2");
        verify(mockedMessage, times(1)).printMessage("returnMovieSuccessmessage");
        assertEquals(new IO().fileReader("./testData.json"), initializeMovies(false, false, null, null));

    }

    @Test
    public void returnMovieMissWithWrongMovieTest() throws Exception {
        assertEquals(app.returnMovie("Movie0"), null);
        verify(mockedMessage, times(1)).printMessage("returnMovieUnsuccessmessage");
    }

    @Test
    public void returnMovieMissWithGibberishTest() throws Exception {
        assertEquals(app.returnMovie("kwkw"), null);
        verify(mockedMessage, times(1)).printMessage("returnMovieUnsuccessmessage");
    }

    @Test
    public void getCheckoutItemDetailsTest() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("Item Title: THE DAY THE CRAYONS QUIT");
        list.add("Borrower's library NO.: 123-1234" + "\n");
        list.add("Item Title: Movie2");
        list.add("Borrower's library NO.: 123-1234" + "\n");
        assertEquals(app.getCheckoutItemDetails(), list);
    }

    @Test
    public void testThatTheExpectedBooksAreThere() throws Exception {
        List<String> availabeBookList = new ArrayList<String>();
        availabeBookList.add("Book Title: SECRET GARDEN");
        availabeBookList.add("Author: Johanna Basford");
        availabeBookList.add("Publication Year: 2015" + "\n");
        availabeBookList.add("Book Title: HARRY POTTER COLOURING BOOK");
        availabeBookList.add("Author: Warner Brothers");
        availabeBookList.add("Publication Year: 2015" + "\n");

        assertEquals(app.getAvailableBooks(), availabeBookList);
    }

    @Test
    public void testThatTheExpectedMoviesAreThere() throws Exception {
        List<String> availabeMoviesList = new ArrayList<String>();
        availabeMoviesList.add("Movie Title: Movie1");
        availabeMoviesList.add("Year: 2011");
        availabeMoviesList.add("Rate: 1");
        availabeMoviesList.add("Director: Director1" + "\n");

        assertEquals(app.getAvailableMovies(), availabeMoviesList);
    }

    @After
    public void recoverData() {
        io.updateJasonFile(initialize(false,true, false,true, null, "123-1234", null, "123-1234"), "./testData.json");
    }
}