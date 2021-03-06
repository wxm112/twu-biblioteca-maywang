package com.twu.biblioteca;

import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mayw on 7/12/2015.
 */
public class IoTest extends ProjectGenerator {

    private IO file = new IO();

//    private JSONObject initialize(boolean checkoutStatus1, boolean checkoutStatus2) {
//        JSONObject obj = new JSONObject();
//
//        //Created booksObject with three book objects;
//        JSONObject booksObj = new JSONObject();
//        JSONObject bookDetail1 = new JSONObject();
//        bookDetail1.put("Checkout", checkoutStatus1);
//        bookDetail1.put("Author", "Johanna Basford");
//        bookDetail1.put("PublicationYear", "2015");
//        booksObj.put("SECRET GARDEN", bookDetail1);
//        JSONObject bookDetail2 = new JSONObject();
//        bookDetail2.put("Checkout", false);
//        bookDetail2.put("Author", "Warner Brothers");
//        bookDetail2.put("PublicationYear", "2015");
//        booksObj.put("HARRY POTTER COLOURING BOOK", bookDetail2);
//        JSONObject bookDetail3 = new JSONObject();
//        bookDetail3.put("Checkout", checkoutStatus2);
//        bookDetail3.put("Author", "Drew Daywal");
//        bookDetail3.put("PublicationYear", "2015");
//        booksObj.put("THE DAY THE CRAYONS QUIT", bookDetail3);
//
//        //Created moviesObject with two movie objects;
//        JSONObject movieObj = new JSONObject();
//        JSONObject movieDetail1 = new JSONObject();
//        movieDetail1.put("Checkout",false);
//        movieDetail1.put("Year", "2011");
//        movieDetail1.put("Director", "Director1");
//        movieDetail1.put("Rate", "1");
//        movieObj.put("Movie1", movieDetail1);
//        JSONObject movieDetail2 = new JSONObject();
//        movieDetail2.put("Checkout",true);
//        movieDetail2.put("Year", "2012");
//        movieDetail2.put("Director", "Director2");
//        movieDetail2.put("Rate", "2");
//        movieObj.put("Movie2", movieDetail2);
//
//        //put booksObject and moviesObject to one object
//        obj.put("Book",booksObj);
//        obj.put("Movie",movieObj);
//
//        return obj;
//
//    }


    @Test
    public void fileReaderTest() throws Exception {
        assertEquals(initialize(false,true, false,true, null, "123-1234", null, "123-1234"),file.fileReader("./testData.json"));
    }

    @Test
    public void updateJasonFileTest() throws Exception {
        file.updateJasonFile(initialize(true,true, false,true, "123-1234", "123-1234", null, "123-1234"), "./testData.json");
        assertEquals(initialize(true,true, false,true, "123-1234", "123-1234", null, "123-1234"), file.fileReader("./testData.json"));

    }

    @After
    public void recoverData() {
        file.updateJasonFile(initialize(false,true, false,true, null, "123-1234", null, "123-1234"), "./testData.json");
    }

}
