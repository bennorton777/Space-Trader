package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Ship;

/**
 * Subclass of Ship, does very little other than override name and maxfuel
 */

public class Gnat extends Ship {
    private final static int MAXFUEL = 15;
    private final static String NAME = "Gnat";

    public Gnat(){
        super(MAXFUEL, MAXFUEL, NAME);
        //TODO: equipment slots and stuff
    }
}
