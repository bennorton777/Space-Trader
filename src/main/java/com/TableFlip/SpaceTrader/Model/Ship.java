package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;

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
    private Port _targetPort;
    private Port _highlightedPort;

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

    private int manhattanDistance(Coordinates a, Coordinates b) {
        return Math.abs(a.getxPos() - b.getxPos()) + Math.abs(a.getyPos() - b.getyPos());
    }

    private int distance (Coordinates a, Coordinates b){
        double distance = Math.sqrt( Math.pow((a.getxPos()-b.getxPos()),2) +
                Math.pow((a.getyPos()-b.getyPos()),2) );
        int d = (int)distance;
        return d;
    }

    /**
     * Most important method in ship!  Currently does nothing.  Stay tuned!
     */
    public void fly(){

        Port _target = this.getTargetPort();
        Port _current = this.getCurrentPort();

            if(_target == _current)
                System.out.println("You are currently on the right port!"); //need UI alert
            else{



                //get remaining supply
                int _remaining = this.getSuppliesRemaining();

                //find distance between 2 planets
                //int _distBetween = this.manhattanDistance(_target.getCoordinates(), _current.getCoordinates());
                int _distBetween = this.distance(_target.getCoordinates(), _current.getCoordinates());

                //check to see if you have enough fuel to travel
                if(_remaining < _distBetween)   {
                    System.out.println("You don't have enough fuel to travel!");//need UI alert
                    throw new IndexOutOfBoundsException();
                }
                else
                {
                        this.setCurrentPort(_target); //you then travel to planet
                        System.out.println("You have now reached your target port");//need UI alert
                        int _decrementSupplies = (this.getSuppliesRemaining() - _distBetween);
                        //this sets the new supply amount
                        this.setSuppliesRemaining(_decrementSupplies);
                }


            }
        Random randomGenerator = new Random();
        System.out.println("Generating a random event!");
        int randomInt = randomGenerator.nextInt(4);

        if(randomInt == 0)
            System.out.println("You are safe to travel!");  //UI alert
        if(randomInt == 1) {

            System.out.println("You have come up against a pirate who has stolen some of your money!"); //UI alert
            int _creditAmount = Player.getInstance().getCredits();

            if( _creditAmount <= 0)
                System.out.println("You have no money for the pirate to take"); //UI alert

            else{
                Player.getInstance().setCredits(_creditAmount - 1);

                if(Player.getInstance().getCredits() == 0 || Player.getInstance().getCredits() < 0 )
                    System.out.println("You have no more money ");//UI alert
                else
                    System.out.println("Your amount of money" + Player.getInstance().getCredits());

            }
        }
        if(randomInt == 2) {
            //police officer checks to see if you have narcotics
            //use the cargo
            System.out.println("You have met a police office who is checking for narcotics!");
            Good _narcotics = new Good("Narcotics", 2500, 5);
            if(this._cargo.containsValue(_narcotics))
                   System.out.println("You have Narcotics, you are going to jail!"); //UI alert
            else
                System.out.println("You don't have any narcotics, good job!"); //UI alert
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

    public String toSave() {
        String save = "ship|" + _name + '\n';

        save += _currentPort.toSave() + '\n';
        save += _targetPort.toSave() + '\n';

        for (Good g : _cargo.keySet())
        {
            save += "good|" + g.getName() + '|' + _cargo.get(g) + '\n';
        }

        save += "endship";

        return save;
    }
}
