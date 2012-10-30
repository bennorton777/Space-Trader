package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Model.Ship;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 10/30/12
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShipPrototype {
    private int _suppliesMax;
    private int _cargoSpace;
    private int _weaponSlots;
    private int _armorSlots;
    private int _crewSlots;
    private int _toolSlots;

    public ShipPrototype(int suppliesMax, int cargoSpace, int weaponSlots, int armorSlots, int crewSlots, int toolSlots) {
        _suppliesMax = suppliesMax;
        _cargoSpace = cargoSpace;
        _weaponSlots = weaponSlots;
        _armorSlots = armorSlots;
        _crewSlots = crewSlots;
        _toolSlots = toolSlots;
    }

    public Ship makeShip(Ship build) {
        build.setArmorSlots(_armorSlots);
        build.setCargoSpace(_cargoSpace);
        build.setCrewSlots(_crewSlots);
        build.setSuppliesMax(_suppliesMax);
        build.setToolSlots(_toolSlots);
        build.setWeaponSlots(_weaponSlots);
        return build;
    }

    public int getSuppliesMax() {
        return _suppliesMax;
    }

    public int getCargoSpace() {
        return _cargoSpace;
    }

    public int getWeaponSlots() {
        return _weaponSlots;
    }

    public int getArmorSlots() {
        return _armorSlots;
    }

    public int getCrewSlots() {
        return _crewSlots;
    }

    public int getToolSlots() {
        return _toolSlots;
    }
}
