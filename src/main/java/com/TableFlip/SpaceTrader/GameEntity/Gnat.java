package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Ship;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 9/6/12
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */

public class Gnat extends Ship {
    private final static int MAXFUEL = 15;
    private final static String NAME = "Gnat";

    public Gnat(){
        super(MAXFUEL, MAXFUEL, NAME);
        //TODO: equipment slots and stuff
    }
}
