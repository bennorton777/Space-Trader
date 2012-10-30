package com.TableFlip.SpaceTrader.Model;

import java.util.HashMap;

/**
 * Contains an inventory, how to fly, etc.
 */
public class Ship {
    private int _fuelRemaining;
    private int _fuelMax;
    private String _name;
    private Port _currentPort;

    private Port _targetPort;

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

    public Port getTargetPort() {
        return _targetPort;
    }

    public void setTargetPort(Port targetPort) {
        _targetPort = targetPort;
    }

    public String toString() {
        return _name + ": Range " + _fuelMax + " Fuel " + _fuelRemaining + "/" + _fuelMax;
    }
}
