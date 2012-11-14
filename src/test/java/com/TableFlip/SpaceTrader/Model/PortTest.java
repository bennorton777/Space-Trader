package com.TableFlip.SpaceTrader.Model;

import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 11/14/12
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PortTest extends TestCase {
    JSONObject dry;

    @Before
    public void setUp() throws Exception {
        dry = new JSONObject("{\"resources\":\"LOTSOFHERBS\",\"location\":{\"y\":62,\"x\":31},\"name\":\"Og\",\"techLevel\":\"RENAISSANCE\"}");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHydrate() throws Exception {
        Port test = Port.hydrate(dry);
        testLocation(test);
        testName(test);
        testResources(test);
        testTechlevel(test);
    }

    private void testResources(Port test) {
        assertEquals("Resources do not match", "LOTSOFHERBS", test.getResources().toString());
    }

    private void testLocation(Port test) {
        assertEquals("Coordinates do not match", new Coordinates(31, 62), test.getCoordinates());
    }

    private void testName(Port test) {
        assertEquals("Name does not match", "Og", test.getName());
    }

    private void testTechlevel(Port test) {
        assertEquals("Tech level does not match", "RENAISSANCE", test.getTechLevel().toString());
    }
}
