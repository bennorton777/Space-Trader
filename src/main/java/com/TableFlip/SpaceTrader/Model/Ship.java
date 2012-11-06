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

    private int mantattanDistance(Coordinates a, Coordinates b) {
        return Math.abs(a.getxPos() - b.getxPos()) + Math.abs(a.getyPos() - b.getyPos());
    }

    /**
     * Most important method in ship!  Currently does nothing.  Stay tuned!
     */
    public void fly(){

        Port _target = this.getTargetPort();
        Port _current = this.getCurrentPort();

            if(_target == _current)
                System.out.println("You are currently on the right port!");
            else{

                //get remaining supply
                int _remaining = this.getSuppliesRemaining();

                //find distance between 2 planets
                int _distBetween = this.mantattanDistance(_target.getCoordinates(), _current.getCoordinates());

                //check to see if you have enough fuel to travel
                if(_remaining < _distBetween)
                    System.out.println("You don't have enough fuel to travel!");
                else
                {
                        this.setCurrentPort(_target); //you then travel to planet
                        System.out.println("You have now reached your target port");
                        int _decrementSupplies = (this.getSuppliesRemaining() - _distBetween);
                        //this sets the new supply amount
                        this.setSuppliesRemaining(_decrementSupplies);
                }


            }
        Random randomGenerator = new Random();
        System.out.println("Generating a random event!");
        int randomInt = randomGenerator.nextInt(4);

        if(randomInt == 0)
            System.out.print("You are safe to travel!");
        if(randomInt == 1)
            //pirate steals weapons
            System.out.print("You have come up against a pirate who has stolen a weapon!");
                this.setWeaponSlots(this.getWeaponSlots() - 1);
        if(randomInt == 2) {
            //police officer checks to see if you have narcotics
            System.out.println("You have met a police office who is checking for narcotics!");
            Good _narcotics = new Good("Narcotics", 2500, 5);
            if(this._goodsRegistry.getGoods().contains(_narcotics))
                   System.out.println("You have Narcotics, you are going to jail!");
            else
                System.out.println("You don't have any narcotics, good job!");
        }
        else
            System.out.println("You have come up against a pirate!");


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
