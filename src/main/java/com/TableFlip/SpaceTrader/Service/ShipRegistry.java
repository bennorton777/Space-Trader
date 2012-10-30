package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.Model.Ship;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 10/29/12
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShipRegistry {
    private HashMap<String, ShipPrototype> _registry;
    private static ShipRegistry _instance;

    private ShipRegistry() {
        _registry = new HashMap<String, ShipPrototype>();

        _registry.put("Barque", new ShipPrototype(10, 18, 2, 1, 2, 1));
        _registry.put("Brig", new ShipPrototype(12, 18, 3, 2, 3, 2));
        _registry.put("Brig of War", new ShipPrototype(12, 20, 4, 3, 3, 2));
        _registry.put("Flag Galleon", new ShipPrototype(15, 24, 5, 4, 4, 3));
        _registry.put("Frigate", new ShipPrototype(15, 20, 4, 2, 3, 2));
        _registry.put("East Indiaman", new ShipPrototype(15, 30, 1, 2, 2, 2));
        _registry.put("Sloop", new ShipPrototype(12, 12, 1, 1, 1, 0));
        _registry.put("Royal Sloop", new ShipPrototype(12, 14, 2, 2, 1, 1));
        _registry.put("Trade Galleon", new ShipPrototype(15, 30, 2, 3, 3, 2));
        _registry.put("Brigantine", new ShipPrototype(12, 16, 2, 2, 2, 2));
        _registry.put("Row Boat", new ShipPrototype(0, 0, 0, 0, 0, 0));
    }

    public static ShipRegistry getInstance(){
        if (_instance!=null){
            return _instance;
        }
        else{
            _instance=new ShipRegistry();
            return _instance;
        }
    }

    public HashMap<String, ShipPrototype> getRegistry() {
        return _registry;
    }
}
