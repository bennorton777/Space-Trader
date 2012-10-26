package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Model.Ship;

import java.util.Map;
/**
 * Largely an information holder class for player stats, equipment, inventory, and ship.
 * Not a pure information holder, as Player will contain some logic for player actions like buying things from a planet.
 */
public class Player {
    /**
     * Empty constructor.  This class uses the builder pattern, and in this case does not need logic in the constructor.
     */
    private Player() {
    }
    private static Player _instance;
    public static Player getInstance(){
        if (_instance==null){
            _instance=new Player();
        }
        return _instance;
    }

    public boolean buy(Good good, int amount){
        int cost=getCurrentPort().price(good)*amount;
        int supply=getCurrentPort().supply(good);
        int currentAmountInCargoHold=_ship.getCargoSpace();
        //check reasonable cost
        if (cost>_credits){
            return false;
        }
        //check reasonable amount from planet
        if (amount>supply){
            return false;
        }
        //check reasonable amount to fit in ship
        if (amount>_ship.getCargoSpace()){
            return false;
        }
        //okay, do the buying thing!
        //import goods to ship
        _ship.getCargo().put(good, amount+currentAmountInCargoHold);
        //charge player
        _credits-=cost;
        //take goods from planet
        getCurrentPort().getLocalMarket().get(good).put(Enums.MarketValues.QUANTITY, supply-amount);
        return true;
    }
    public boolean sell(Good good, int amount){
        int cost=getCurrentPort().price(good)*amount;
        int supply=_ship.getCargo().get(good);
        int currentAmountInPlanet=getCurrentPort().supply(good);
        //check if port buys item
        if (getCurrentPort().getLocalMarket().get(good)==null){
            return false;
        }
        //check reasonable amount from cargo
        if (amount>supply){
            return false;
        }
        //okay, do the selling thing!
        //add good to port
        getCurrentPort().getLocalMarket().get(good).put(Enums.MarketValues.QUANTITY, currentAmountInPlanet+amount);
        //take good from player
        _ship.getCargo().put(good, supply-amount);
        //charge planet
        _credits+=cost;
        return true;
    }

    private Map<Enums.Skill, Integer> _stats;
    private String _name;
    private Ship _ship;
    private int _credits;

    public Port getCurrentPort(){
        if (_ship==null){
            return null;
        }
        else{
            return _ship.getCurrentPort();
        }
    }
    public void setCurrentPort(Port port){
        if (_ship!=null){
            _ship.setCurrentPort(port);
        }
    }
    public Map<Enums.Skill, Integer> getStats() {
        return _stats;
    }

    public Player setStats(Map<Enums.Skill, Integer> stats) {
        _stats = stats;
        return this;
    }

    public String getName() {
        return _name;
    }

    public Player setName(String name) {
        _name = name;
        return this;
    }

    public Ship getShip() {
        return _ship;
    }

    public Player setShip(Ship ship) {
        _ship = ship;
        return this;
    }

    public int getCredits() {
        return _credits;
    }

    public Player setCredits(int credits) {
        _credits = credits;
        return this;
    }
}
