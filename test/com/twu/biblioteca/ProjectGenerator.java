package com.twu.biblioteca;

import org.json.simple.JSONObject;

/**
 * Created by mayw on 14/12/2015.
 */
public class ProjectGenerator {
    public JSONObject initializeBooks(boolean firstCheckoutStatus, boolean secondCheckoutStatus, String firstBorrower, String secondBorrower){
        return initialize(firstCheckoutStatus,secondCheckoutStatus,false,true, firstBorrower, secondBorrower, null, "123-1234");
    }

    public JSONObject initializeMovies(boolean firstCheckoutStatus, boolean secondCheckoutStatus,String firstBorrower, String secondBorrower){
        return initialize(false,true,firstCheckoutStatus,secondCheckoutStatus, null, "123-1234",firstBorrower, secondBorrower );
    }

    public JSONObject initialize(boolean checkoutStatus1, boolean checkoutStatus2, boolean checkoutStatus3, boolean checkoutStatus4, String borrower1,String borrower2,String borrower3,String borrower4) {
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

}
