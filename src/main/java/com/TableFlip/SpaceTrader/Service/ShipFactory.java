package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.Model.Ship;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 10/29/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShipFactory {
    public static Ship makeShip(String name) {
        ShipRegistry reg = ShipRegistry.getInstance();

        if (reg.getRegistry().containsKey(name)) {
            ShipPrototype maker = reg.getRegistry().get(name);
            return new Ship(name, maker.getSuppliesMax(), maker.getCargoSpace(), maker.getWeaponSlots(), maker.getArmorSlots(), maker.getCrewSlots(), maker.getToolSlots());
        } else {
            return null;
        }

    }
}
