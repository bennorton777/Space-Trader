package com.TableFlip.SpaceTrader.Model;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 10/22/12
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class RandomCoordinates extends Coordinates {
    public RandomCoordinates(int rangeX,  int rangeY)
    {
        super(0, 0);
        Random gen = new Random();
        setxPos(gen.nextInt(rangeX));
        setyPos(gen.nextInt(rangeY));
    }
}
