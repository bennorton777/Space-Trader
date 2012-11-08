package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.GameEntity.RandomPort;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.TableFlip.SpaceTrader.Service.MarketGenerator;
import org.json.JSONException;
import org.json.JSONObject;

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

    public Port setResources(Enums.Resources resources) {
        _resources = resources;
        return this;
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

    public Port setTechLevel(Enums.TechLevel techLevel) {
        _techLevel = techLevel;
        return this;
    }

    public Map<Good, HashMap<Enums.MarketValues, Integer>> getLocalMarket() {
        return _localMarket;
    }

    public Port setLocalMarket(Map<Good, HashMap<Enums.MarketValues, Integer>> localMarket) {
        _localMarket = localMarket;
        return this;
    }

    Enums.TechLevel _techLevel;
    public Port(){
       //Do nothing
    }

    public String getName() {
        return _name;
    }

    public Port setName(String name) {
        _name = name;
        return this;
    }

    public Coordinates getCoordinates() {
        return _coordinates;
    }

    public Port setCoordinates(Coordinates coordinates) {
        _coordinates = coordinates;
        return this;
    }

    @Override
    public String toString() {
        return ("Port " + _name + ". Tech Level: " + _techLevel + " Resource: " + _resources + " at location " + _coordinates);
    }

    public String toSave() {
        return ("port|" + _name + '|' + _techLevel + '|' + _resources + '|' + _coordinates.getyPos() + '|' + _coordinates.getxPos());
    }

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();

        try {
            ret.put("name", _name);
            ret.put("techLevel", _techLevel);
            ret.put("resources", _resources);
            ret.put("location", _coordinates.toJSON());
        } catch (JSONException e) {
            System.out.println("JSON creation error " + e.toString());
        }
        return ret;
    }

    public static Port hydrate(JSONObject dry) {
        try {
            RandomPort wet = new RandomPort(dry.getString("name"));

            wet.setCoordinates(Coordinates.hydrate(dry.getJSONObject("location"))).setTechLevel(Enums.TechLevel.valueOf(dry.getString("techLevel"))).setResources(Enums.Resources.valueOf(dry.getString("resources")));

            wet.setLocalMarket(MarketGenerator.getInstance().generateMarket(wet).getLocalMarket());

            return wet;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
