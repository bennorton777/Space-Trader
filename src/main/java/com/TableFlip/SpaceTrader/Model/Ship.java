package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.Service.ShipPrototype;

import java.util.HashMap;

/**
 * Contains an inventory, how to fly, etc.
 */
public class Ship {
    private int _suppliesRemaining;
    private int _suppliesMax;
    private String _name;
    private Port _currentPort;
    private HashMap<Good, Integer> _cargo;
    private int _cargoSpace;
    private int _weaponSlots;
    private int _armorSlots;
    private int _crewSlots;
    private int _toolSlots;

    public Ship(String name, int suppliesMax, int cargoSpace, int weaponSlots, int armorSlots, int crewSlots, int toolSlots) {
        _suppliesRemaining = suppliesMax;
        _suppliesMax = suppliesMax;
        _name = name;
        _cargo=new HashMap<Good, Integer>();
        _cargoSpace = cargoSpace;
        _weaponSlots = weaponSlots;
        _armorSlots = armorSlots;
        _crewSlots = crewSlots;
        _toolSlots = toolSlots;
    }

    /**
     * Most important method in ship!  Currently does nothing.  Stay tuned!
     */
    public void fly(){
        //TODO
    }

    public HashMap<Good, Integer> getCargo() {
        return _cargo;
    }

    public Ship setCargo(HashMap<Good, Integer> cargo) {
        _cargo = cargo;
        return this;
    }

    public int getCargoSpace() {
        return _cargoSpace;
    }

    public Ship setCargoSpace(int _cargoSpace) {
        this._cargoSpace = _cargoSpace;
        return this;
    }

    public int getSuppliesRemaining() {
        return _suppliesRemaining;
    }

    public Ship setSuppliesRemaining(int SuppliesRemaining) {
        _suppliesRemaining = SuppliesRemaining;
        return this;
    }

    public int getSuppliesMax() {
        return _suppliesMax;
    }

    public Ship setSuppliesMax(int SuppliesMax) {
        _suppliesMax = SuppliesMax;
        return this;
    }

    public String getName() {
        return _name;
    }

    public Ship setName(String name) {
        _name = name;
        return this;
    }

    public Port getCurrentPort() {
        return _currentPort;
    }

    public Ship setCurrentPort(Port currentPort) {
        _currentPort = currentPort;
        return this;
    }

    public int getWeaponSlots() {
        return _weaponSlots;
    }

    public Ship setWeaponSlots(int weaponSlots) {
        _weaponSlots = weaponSlots;
        return this;
    }

    public int getArmorSlots() {
        return _armorSlots;
    }

    public Ship setArmorSlots(int armorSlots) {
        _armorSlots = armorSlots;
        return this;
    }

    public int getCrewSlots() {
        return _crewSlots;
    }

    public Ship setCrewSlots(int crewSlots) {
        _crewSlots = crewSlots;
        return this;
    }

    public int getToolSlots() {
        return _toolSlots;
    }

    public Ship setToolSlots(int toolSlots) {
        _toolSlots = toolSlots;
        return this;
    }

    public String toString() {
        return _name + ": Range " + _suppliesMax + " Fuel " + _suppliesRemaining + "/" + _suppliesMax;
    }
}
