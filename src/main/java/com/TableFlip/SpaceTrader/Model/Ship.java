package com.TableFlip.SpaceTrader.Model;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 9/6/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ship {
    private int _fuelRemaining;
    private int _fuelMax;
    private String _name;
    private Planet _currentPlanet;

    public Ship(int fuelRemaining, int fuelMax, String name) {
        _fuelRemaining = fuelRemaining;
        _fuelMax = fuelMax;
        _name = name;
    }

    public void fly(){
        //TODO
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

    public Planet getCurrentPlanet() {
        return _currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        _currentPlanet = currentPlanet;
    }

    public String toString() {
        return _name + ": Range " + _fuelMax + " Fuel " + _fuelRemaining + "/" + _fuelMax;
    }
}
