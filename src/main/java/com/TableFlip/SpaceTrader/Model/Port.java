package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.Service.GoodsRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 9/6/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Port {
    GoodsRegistry _goodsRegistry=GoodsRegistry.getInstance();
    Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket;
    Enums.Resources _resources;
    private String _name;
    private Coordinates _coordinates;

    public Enums.Resources getResources() {
        return _resources;
    }

    public void setResources(Enums.Resources resources) {
        _resources = resources;
    }
    public int price(Good good){
        return getLocalMarket().get(good).get(Enums.MarketValues.PRICE);
    }
    public int supply(Good good){
        return getLocalMarket().get(good).get(Enums.MarketValues.QUANTITY);
    }
    public Enums.TechLevel getTechLevel() {
        return _techLevel;
    }

    public void setTechLevel(Enums.TechLevel techLevel) {
        _techLevel = techLevel;
    }

    public Map<Good, HashMap<Enums.MarketValues, Integer>> getLocalMarket() {
        return _localMarket;
    }

    public void setLocalMarket(Map<Good, HashMap<Enums.MarketValues, Integer>> localMarket) {
        _localMarket = localMarket;
    }

    Enums.TechLevel _techLevel;
    public Port(){
       //Do nothing
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Coordinates getCoordinates() {
        return _coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        _coordinates = coordinates;
    }

    @Override
    public String toString() {
        return ("Port " + _name + ". Tech Level: " + _techLevel + " Resource: " + _resources + " at location " + _coordinates);
    }

    public String toSave() {
        return ("port|" + _name + '|' + _techLevel + '|' + _resources + '|' + _coordinates.getyPos() + '|' + _coordinates.getxPos());
    }
}
