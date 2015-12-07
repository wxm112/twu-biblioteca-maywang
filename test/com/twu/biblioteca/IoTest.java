package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.json.simple.JSONObject;


import static junit.framework.TestCase.assertEquals;

/**
 * Created by mayw on 7/12/2015.
 */
public class IoTest {

    private IO file;

    @Before public void initialize(){
        file = new IO();
    }

    @Test
    public void fileReaderTest() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("Author","Johanna Basford");
        obj.put("PublicationYear", "2015");
        assertEquals(obj, file.fileReader("./testData.json").get("Secret Garden"));
    }
}
