package com.TableFlip.SpaceTrader.Model;

import java.util.HashMap;
import java.util.Random;

/**
 * Contains an inventory, how to fly, etc.
 */
public class Ship {
    private int _fuelRemaining;
    private int _fuelMax;
    private String _name;
    private Port _currentPort;

    public HashMap<Good, Integer> getCargo() {
        return _cargo;
    }

    public void setCargo(HashMap<Good, Integer> cargo) {
        _cargo = cargo;
    }

    private HashMap<Good, Integer> _cargo;

    public int getCargoSpace() {
        return _cargoSpace;
    }

    public void setCargoSpace(int _cargoSpace) {
        this._cargoSpace = _cargoSpace;
    }

    private int _cargoSpace;

    public Ship(int fuelRemaining, int fuelMax, String name) {
        _fuelRemaining = fuelRemaining;
        _fuelMax = fuelMax;
        _name = name;
        _cargo=new HashMap<Good, Integer>();
    }

    /**
     * Most important method in ship!  Currently does nothing.  Stay tuned!
     */
    public void fly(){
        //TODO
        Random randomGenerator = new Random();
        System.out.println("Generating a random event!");
        //do I use enum to generate an event
        //what are the random events?
        //pirates and police
        //depending on number it generates a pirate or police
        //need to get current port and future port to determine distance between the two
        int randomInt = randomGenerator.nextInt(11);

        if(randomInt == 0)
            System.out.print("You are safe!");
        if(randomInt == 1)
            System.out.print("You have come up against a police officer!");
        if(randomInt == 2)
            System.out.print("You have come up against a pirate and you can beat it!");
        if(randomInt == 3)
            System.out.print("You have come up against  2 pirates and you may not escape!");
        if(randomInt == 4)
            System.out.print("You have come up against many pirates and you can escape if you use your skills properly!");
        if(randomInt == 5)
            System.out.print("You have come up against a police officer and a pirate!");
        if(randomInt == 6)
            System.out.print("You have come up against a police officer who is going to arrest you!");
        if(randomInt == 7)
            System.out.print("Hurry, a gang of pirates is coming to steal your goods");
        if(randomInt == 8)
            System.out.print("You have no threats of pirates or police officers!");
        if(randomInt == 9)
            System.out.print("I hope you have enough skills to defeat the threats posed to you at this port!");
        else
            System.out.print("You have come up against a pirate!");


    }

    public int getFuelRemaining() {
        return _fuelRemaining;
    }

    public void setFuelRemaining(int fuelRemaining) {
        _fuelRemaining = fuelRemaining;
    }

    public int getFuelMax() {
        return _fuelMax;
    }

    public void setFuelMax(int fuelMax) {
        _fuelMax = fuelMax;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Port getCurrentPort() {
        return _currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        _currentPort = currentPort;
    }

    public String toString() {
        return _name + ": Range " + _fuelMax + " Fuel " + _fuelRemaining + "/" + _fuelMax;
    }
}
