package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by mayw on 7/12/2015.
 */
public class LibrarianTest {
    Message mockedMessage = mock(Message.class);
    IO io = new IO();
    Librarian app = new Librarian("./testData.json", mockedMessage, io);

    private JSONObject initializeBooks(boolean firstCheckoutStatus, boolean secondCheckoutStatus, String firstBorrower, String secondBorrower){
        return initialize(firstCheckoutStatus,secondCheckoutStatus,false,true, firstBorrower, secondBorrower, null, "123-1234");
    }

    private JSONObject initializeMovies(boolean firstCheckoutStatus, boolean secondCheckoutStatus,String firstBorrower, String secondBorrower){
        return initialize(false,true,firstCheckoutStatus,secondCheckoutStatus, null, "123-1234",firstBorrower, secondBorrower );
    }

    private JSONObject initialize(boolean checkoutStatus1, boolean checkoutStatus2, boolean checkoutStatus3, boolean checkoutStatus4, String borrower1,String borrower2,String borrower3,String borrower4) {
        JSONObject obj = new JSONObject();

        //Created booksObject with three book objects;
        JSONObject booksObj = new JSONObject();
        JSONObject bookDetail1 = new JSONObject();
        bookDetail1.put("Checkout", checkoutStatus1);
        bookDetail1.put("Borrower", borrower1);
        bookDetail1.put("Author", "Johanna Basford");
        bookDetail1.put("PublicationYear", "2015");
        booksObj.put("SECRET GARDEN", bookDetail1);
        JSONObject bookDetail2 = new JSONObject();
        bookDetail2.put("Checkout", false);
        bookDetail2.put("Borrower", null);
        bookDetail2.put("Author", "Warner Brothers");
        bookDetail2.put("PublicationYear", "2015");
        booksObj.put("HARRY POTTER COLOURING BOOK", bookDetail2);
        JSONObject bookDetail3 = new JSONObject();
        bookDetail3.put("Checkout", checkoutStatus2);
        bookDetail3.put("Borrower", borrower2);
        bookDetail3.put("Author", "Drew Daywal");
        bookDetail3.put("PublicationYear", "2015");
        booksObj.put("THE DAY THE CRAYONS QUIT", bookDetail3);

        //Created moviesObject with two movie objects;
        JSONObject movieObj = new JSONObject();
        JSONObject movieDetail1 = new JSONObject();
        movieDetail1.put("Checkout",checkoutStatus3);
        movieDetail1.put("Borrower", borrower3);
        movieDetail1.put("Year", "2011");
        movieDetail1.put("Director", "Director1");
        movieDetail1.put("Rate", "1");
        movieObj.put("Movie1", movieDetail1);
        JSONObject movieDetail2 = new JSONObject();
        movieDetail2.put("Checkout",checkoutStatus4);
        movieDetail2.put("Borrower", borrower4);
        movieDetail2.put("Year", "2012");
        movieDetail2.put("Director", "Director2");
        movieDetail2.put("Rate", "2");
        movieObj.put("Movie2", movieDetail2);

        //put booksObject and moviesObject to one object
        obj.put("Book",booksObj);
        obj.put("Movie",movieObj);

        return obj;

    }


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
    public void testThatTheExpectedBooksAreThere() throws Exception {
        ArrayList<String> availabeBookList = new ArrayList<String>();
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
        ArrayList<String> availabeMoviesList = new ArrayList<String>();
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