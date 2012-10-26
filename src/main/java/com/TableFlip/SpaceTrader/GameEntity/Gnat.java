package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Ship;

/**
 * Subclass of Ship, does very little other than override name and maxfuel
 */

public class Gnat extends Ship {
    private final static int MAXFUEL = 15;
    private final static String NAME = "Gnat";
    private final static int BASECARGOSPACE = 10;

    public Gnat(){
        super(MAXFUEL, MAXFUEL, NAME);
        this.setCargoSpace(BASECARGOSPACE);
        //TODO: equipment slots and stuff
    }
}
