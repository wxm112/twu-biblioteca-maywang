package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mayw on 7/12/2015.
 */
public class IoTest {

    private IO file = new IO();

    @Test
    public void fileReaderTest() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("Checkout", "No");
        obj.put("Author", "Johanna Basford");
        obj.put("PublicationYear", "2015");
        assertEquals(obj, file.fileReader("./testData.json").get("SECRET GARDEN"));
    }

    @Test
    public void updateJasonFileTest() throws Exception {
        JSONObject obj = new JSONObject();
        JSONObject bookDetails = new JSONObject();
        bookDetails.put("Checkout", "Yes");
        bookDetails.put("Author", "Xuemei");
        bookDetails.put("PublicationYear", "2000");
        obj.put("SECRET GARDEN", bookDetails);
        file.updateJasonFile(obj, "./testData.json");

        JSONObject checkoutStatus = (JSONObject) file.fileReader("./testData.json").get("SECRET GARDEN");
        assertEquals("Yes", checkoutStatus.get("Checkout"));
        assertEquals("Xuemei", checkoutStatus.get("Author"));
        assertEquals("2000", checkoutStatus.get("PublicationYear"));
    }

    @After
    public void recoverData() {
        JSONObject obj = new JSONObject();
        JSONObject bookDetail1 = new JSONObject();
        bookDetail1.put("Checkout", "No");
        bookDetail1.put("Author", "Johanna Basford");
        bookDetail1.put("PublicationYear", "2015");
        obj.put("SECRET GARDEN", bookDetail1);
        JSONObject bookDetail2 = new JSONObject();
        bookDetail2.put("Checkout", "No");
        bookDetail2.put("Author", "Warner Brothers");
        bookDetail2.put("PublicationYear", "2015");
        obj.put("HARRY POTTER COLOURING BOOK", bookDetail2);
        JSONObject bookDetail3 = new JSONObject();
        bookDetail3.put("Checkout", "Yes");
        bookDetail3.put("Author", "Drew Daywal");
        bookDetail3.put("PublicationYear", "2015");
        obj.put("THE DAY THE CRAYONS QUIT", bookDetail3);
        file.updateJasonFile(obj, "./testData.json");
    }

}