package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 11/14/12
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class SparseArrayTest extends TestCase {
    private SparseArray<String> array;
    @Before
    public void setUp() throws Exception {
        array = new SparseArray<String>(10, 10);
    }
    @Test
    public void testPutAt() throws Exception {
        try { //testing out of bounds
            array.putAt(11, 1, "K");
            // should not get here
            assertTrue("Failed to detect row out of bounds", false);
        } catch (ArrayIndexOutOfBoundsException ae) {
            assertTrue(true);
        }
        try {  //testing out of bounds
            array.putAt(1, 11, "K");
            // should not get here
            assertTrue("Failed to detect col out of bounds", false);
        } catch (ArrayIndexOutOfBoundsException ae) {
            assertTrue(true);
        }
        try { //testing out of bounds
            array.putAt(-1, 11, "K");
            // should not get here
            assertTrue("Failed to detect row out of bounds", false);
        } catch (ArrayIndexOutOfBoundsException ae) {
            assertTrue(true);
        }

        array.putAt(5,1, "A");
        array.putAt(1, 5, "B");
        assertEquals("Returned element is wrong", "A", array.getAt(5, 1));
        assertEquals("Returned element is wrong", "B", array.getAt(1, 5));
        array.putAt(9, 5, "C");
        array.putAt(5, 5, "D");
        array.putAt(5, 8, "E");
        assertEquals("Returned element is wrong", "D", array.getAt(5, 5));

        Node<String> n = array.findNodeAt(1, 5);
        assertEquals("Returned element is wrong", "D", array.moveDown(n));

        Node<String> n2 = array.findNodeAt(5, 5);
        assertEquals("Returned element is wrong when moving up", "B", array.moveUp(n2));
        assertEquals("Returned element is wrong when moving down", "C", array.moveDown(n2));
        assertEquals("Returned element is wrong when moving right", "E", array.moveRight(n2));
        assertEquals("Returned element is wrong when moving left", "A", array.moveLeft(n2));

    }

}
