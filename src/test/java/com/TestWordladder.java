package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class TestWordladder
        extends TestCase
{
    List<String> testDict = new ArrayList<String>();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestWordladder(String testName )
    {
        super( testName );
    }

    public void testCreateLadder() throws IOException
    {
        String s;
        File file = new File("dictionary.txt");
        BufferedReader fr = new BufferedReader(new FileReader(file));
        while((s = fr.readLine()) != null)
        {
            testDict.add(s);
        }
        assertEquals(5, Wordladder.createLadder(testDict, "test", "case"));
        assertEquals(0, Wordladder.createLadder(testDict, "metal", "azure"));
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestWordladder.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
