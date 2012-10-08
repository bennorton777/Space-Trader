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

    public Ship(int _fuelRemaining, int _fuelMax, int _maxDistance, String _name) {
        this._fuelRemaining = _fuelRemaining;
        this._fuelMax = _fuelMax;
        this._name = _name;
    }

    public void fly(){
        //TODO
    }

    public int get_fuelRemaining() {
        return _fuelRemaining;
    }

    public void set_fuelRemaining(int _fuelRemaining) {
        this._fuelRemaining = _fuelRemaining;
    }

    public int get_fuelMax() {
        return _fuelMax;
    }

    public void set_fuelMax(int _fuelMax) {
        this._fuelMax = _fuelMax;
    }

    public Planet get_currentPlanet() {
        return _currentPlanet;
    }

    public void set_currentPlanet(Planet _currentPlanet) {
        this._currentPlanet = _currentPlanet;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String toString() {
        return _name + ": Range " + _fuelMax + " Fuel " + _fuelRemaining + "/" + _fuelMax;
    }
}
