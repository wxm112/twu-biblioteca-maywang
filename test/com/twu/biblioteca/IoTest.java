package com.twu.biblioteca;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mayw on 7/12/2015.
 */
public class IoTest {

    private IO file;

    @Before
    public void initialize(){
        file = new IO();
        JSONObject obj = new JSONObject();
        JSONObject bookDetails = new JSONObject();
        bookDetails.put("Checkout","No");
        bookDetails.put("Author","Johanna Basford");
        bookDetails.put("PublicationYear", "2015");
        obj.put("SECRET GARDEN", bookDetails);
        file.updateJasonFile(obj,"./testData.json");
    }


    @Test
    public void fileReaderTest() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("Checkout","No");
        obj.put("Author","Johanna Basford");
        obj.put("PublicationYear", "2015");
        assertEquals(obj, file.fileReader("./testData.json").get("SECRET GARDEN"));
    }

    @Test
    public void updateJasonFileTest() throws Exception {
        JSONObject obj = new JSONObject();
        JSONObject bookDetails = new JSONObject();
        bookDetails.put("Checkout","Yes");
        bookDetails.put("Author","Xuemei");
        bookDetails.put("PublicationYear", "2000");
        obj.put("SECRET GARDEN", bookDetails);
        file.updateJasonFile(obj,"./testData.json");

        JSONObject checkoutStatus = (JSONObject) file.fileReader("./testData.json").get("SECRET GARDEN");
        assertEquals("Yes", checkoutStatus.get("Checkout"));
        assertEquals("Xuemei", checkoutStatus.get("Author"));
        assertEquals("2000", checkoutStatus.get("PublicationYear"));
    }


}
