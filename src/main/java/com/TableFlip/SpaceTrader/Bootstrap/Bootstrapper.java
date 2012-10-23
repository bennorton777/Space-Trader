package com.TableFlip.SpaceTrader.Bootstrap;

import GUI.GuiArbiter;
import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;

/**
 * Bootstrapper deals with initial setup phases.  Each method should correspond to a major phase in the "boot process"
 * of the game.  The main method of this class is the first thing to run upon launching this game.
 */
public class Bootstrapper {
    /**
     * First method to run upon game launch.
     * Launches Character Generation.
     * @param args
     */
    public static void main(String[] args){
        GoodsRegistry goods= GoodsRegistry.getInstance();
        GuiArbiter.charGen();
    }

    /**
     * Second major phase of game boot (Following Character generation.)
     * This phase launches galaxy generation.
     */
    public static void generateGalaxy(){
        System.out.println("Generating ocean.");
        Ocean ocean = Ocean.getInstance();
        System.out.println(ocean);
        displayGameScreen();
    }
    /**
     * Third major phase of game boot.  (Following galaxy generation)
     * Display the game screen!
     */
    public static void displayGameScreen(){
        System.out.println("Displaying Game Screen");
        //TODO Make this come from Player
        GuiArbiter.GameScreen("Pirate Butts", 1000);
    }
}
