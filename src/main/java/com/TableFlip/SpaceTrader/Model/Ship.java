package com.TableFlip.SpaceTrader.Model;

import GUI.GuiArbiter;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.TableFlip.SpaceTrader.Service.MarketGenerator;
import com.TableFlip.SpaceTrader.Service.ShipFactory;
import com.TableFlip.SpaceTrader.Service.ShipPrototype;
import org.json.JSONException;
import org.json.JSONObject;


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
            GuiArbiter.popUp("You are currently on the right port!"); //need UI alert
        else{
            //get remaining supply
            int _remaining = this.getSuppliesRemaining();

            //find distance between 2 planets
            //int _distBetween = this.manhattanDistance(_target.getCoordinates(), _current.getCoordinates());
            int _distBetween = this.distance(_target.getCoordinates(), _current.getCoordinates());

            //check to see if you have enough fuel to travel
            if(_remaining < _distBetween)   {
                GuiArbiter.popUp("You don't have enough fuel to travel!");//need UI alert
            }
            else
            {
                    this.setCurrentPort(_target); //you then travel to planet
                    int _decrementSupplies = (this.getSuppliesRemaining() - _distBetween);
                    //this sets the new supply amount
                    this.setSuppliesRemaining(_decrementSupplies);
            }
            Random randomGenerator = new Random();
            System.out.println("Generating a random event!");
            int randomInt = randomGenerator.nextInt(3);
            if(randomInt == 0)
                GuiArbiter.popUp("You arrive without incident.");  //UI alert
            if(randomInt == 1) {
                GuiArbiter.popUp("You have come up against a pirate who attempts to steal some of your money!"); //UI alert
                int _creditAmount = Player.getInstance().getCredits();
                if( _creditAmount <= 0){
                    GuiArbiter.popUp("You have no money for the pirate to take"); //UI alert
                }
                else{
                    Player.getInstance().setCredits(_creditAmount - 1);
                    if(Player.getInstance().getCredits() == 0 || Player.getInstance().getCredits() < 0 ){
                       GuiArbiter.popUp("The pirate stole the last of your money");//UI alert
                    }
                    else{
                        GuiArbiter.popUp("The pirate took a coin, you now have " + Player.getInstance().getCredits() + " coins left");
                    }
                }
            }
            if(randomInt == 2) {
                //police officer checks to see if you have narcotics
                //use the cargo
                GuiArbiter.popUp("You have met a police office who is checking for narcotics!");
                Good _narcotics = new Good("Narcotics", 2500, 5);
                if(this._cargo.containsValue(_narcotics)){
                       GuiArbiter.popUp("The officer finds narcotics, and fines you 10% of your coins!"); //UI alert
                       Player player = Player.getInstance();
                       int currentCoins=player.getCredits();
                        int newCoins=currentCoins-((int) (0.1*currentCoins));
                        player.setCredits(newCoins);
                        GuiArbiter.popUp("You have " + player.getCredits() + " coins left");
                }
                else{
                    GuiArbiter.popUp("You don't have any narcotics, good job!"); //UI alert
                }
            }

        }
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

    public Ship setTargetPort(Port targetPort) {
        _targetPort = targetPort;
        return this;
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

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();

        try {
            ret.put("name", _name);
            ret.put("cargo", _cargo);
            ret.put("targetPort", _targetPort.toJSON());
            ret.put("currentPort", _currentPort.toJSON());
            ret.put("suppliesRemaining", _suppliesRemaining);
        } catch (JSONException e) {
            System.out.println("JSON creation error " + e.toString());
        }
        return ret;
    }

    public static Ship hydrate(JSONObject dry) {
        try {
            JSONObject dryCargo = dry.getJSONObject("cargo");

            Ship wet = ShipFactory.makeShip(dry.getString("name"))
                    .setSuppliesRemaining(dry.getInt("suppliesRemaining"))
                    .setTargetPort(Port.hydrate(dry.getJSONObject("targetPort")))
                    .setCurrentPort(Port.hydrate(dry.getJSONObject("currentPort")));

            for (Good g : wet.getCargo().keySet()) {
                wet.getCargo().put(g, dryCargo.getInt(g.getName()));
            }

            return wet;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
