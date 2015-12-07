package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mayw on 7/12/2015.
 */
public class ListTest {
    private IO file = new IO();

    @Test
    public void getAvailabeBooksTest() throws Exception {
        ArrayList<String> availableBooks = new ArrayList<String>();
        availableBooks.add("Book Title: SECRET GARDEN");
        availableBooks.add("Author: Johanna Basford");
        availableBooks.add("Publication Year: 2015"+ "\n");

        assertEquals(availableBooks, new List().getAvailabeBooks("./testData.json"));
    }
}