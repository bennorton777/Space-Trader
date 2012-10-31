package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.TableFlip.SpaceTrader.Service.ShipPrototype;

import java.util.HashMap;
import java.util.Random;

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

    private static GoodsRegistry _goodsRegistry;

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

        _goodsRegistry = GoodsRegistry.getInstance();


        for(Good g : _goodsRegistry.getGoods()){
            _cargo.put(g, 0);
        }
    }

    private Port _targetPort;

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

    /**
     * Most important method in ship!  Currently does nothing.  Stay tuned!
     */
    public void fly(){
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

    public Port getTargetPort() {
        return _targetPort;
    }

    public void setTargetPort(Port targetPort) {
        _targetPort = targetPort;
    }

    public String toString() {
        return _name + ": Range " + _suppliesMax + " Fuel " + _suppliesRemaining + "/" + _suppliesMax;
    }
}
