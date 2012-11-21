package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Service.PortNames;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: kpalmer
 * Date: 11/12/12
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShipTest extends TestCase {
    @Before
    public void setUp() throws Exception {
             ///not enough supply then fly
            //not enough money
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFly() throws Exception {
        //test if player has no money
        Player.getInstance();
        Player.getInstance().setCredits(0);
        Ship _testShip = new Ship("Test Ship",0,5,5,5,5,5);
        assertEquals(0, Player.getInstance().getCredits());
        _testShip.fly();

        //test if ship has no narcotics
        Good _narcotics = new Good("Narcotics", 2500, 5);
        System.out.println(_testShip.getCargo().containsKey(_narcotics));
        _testShip.fly();
        assertEquals(false,_testShip.getCargo().containsKey(_narcotics) );

        //test to see if ship has narcotics
        Good _nar = new Good("Narcotics", 2500, 2);
        _testShip.getCargo().put(_nar,2);
        System.out.println(_testShip.getCargo());
        _testShip.fly();
        assertEquals(true,_testShip.getCargo().containsKey(_nar));


    }
}
