package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Enums;
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
    public Player() {
    }

    private Map<Enums.Skill, Integer> _stats;
    private String _name;
    private Ship _ship;
    private int _credits;


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
