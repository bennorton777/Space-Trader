package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;

import java.util.ArrayList;

/**
 * Registry containing all of the goods
 */
public class GoodsRegistry {
    private ArrayList<Good> _goods;
    private static GoodsRegistry _instance;

    /**
     * Here's where setting biases of goods happens!
     */
    private GoodsRegistry(){
        _goods=new ArrayList<Good>();
        _goods.add(new Good("Rum", 30, 50)
                .addResourcesPriceModifiers(Enums.Resources.WHYISTHERUMGONE, 3)
                .addResourcesQuantityModifiers(Enums.Resources.WHYISTHERUMGONE, -3)
                .addResourcesPriceModifiers(Enums.Resources.LOTSOFRUM, -3)
                .addResourcesQuantityModifiers(Enums.Resources.LOTSOFRUM, 3)
                .plentifulWhenHighTech()
                .plentifulWhenLowTech()
        );
        _goods.add(new Good("Furs", 230, 30)
                .addResourcesPriceModifiers(Enums.Resources.WARLIKE, 1)
                .addResourcesPriceModifiers(Enums.Resources.RICHFAUNA, -3)
                .addResourcesQuantityModifiers(Enums.Resources.RICHFAUNA, 3)
                .expensiveWhenLowTech()
                .plentifulWhenLowTech()
        );
        _goods.add(new Good("Food", 100, 40)
                .addResourcesPriceModifiers(Enums.Resources.POORSOIL, 3)
                .addResourcesPriceModifiers(Enums.Resources.RICHSOIL, -3)
                .addResourcesPriceModifiers(Enums.Resources.RICHFAUNA, -3)
                .addResourcesQuantityModifiers(Enums.Resources.POORSOIL, -3)
                .addResourcesQuantityModifiers(Enums.Resources.RICHSOIL, 3)
                .addResourcesQuantityModifiers(Enums.Resources.RICHFAUNA, 3)
                .expensiveWhenLowTech()
                .plentifulWhenLowTech()
        );
        _goods.add(new Good("Ore", 350, 25)
                .addResourcesPriceModifiers(Enums.Resources.MINERALRICH, -3)
                .addResourcesPriceModifiers(Enums.Resources.MINERALPOOR, 3)
                .addResourcesQuantityModifiers(Enums.Resources.MINERALRICH, 3)
                .addResourcesQuantityModifiers(Enums.Resources.MINERALPOOR, -3)
                .plentifulWhenLowTech()
        );
        _goods.add(new Good("Games", 250, 20)
                .addResourcesPriceModifiers(Enums.Resources.ARTISTIC, 2)
                .addResourcesPriceModifiers(Enums.Resources.WARLIKE, -3)
                .addResourcesQuantityModifiers(Enums.Resources.ARTISTIC, 2)
                .addResourcesQuantityModifiers(Enums.Resources.WARLIKE, -3)
                .expensiveWhenHighTech()
                .plentifulWhenHighTech()
        );
        _goods.add(new Good("Firearms", 1000, 35)
                .addResourcesPriceModifiers(Enums.Resources.ARTISTIC, -3)
                .addResourcesPriceModifiers(Enums.Resources.WARLIKE, 3)
                .addResourcesQuantityModifiers(Enums.Resources.ARTISTIC, -3)
                .addResourcesQuantityModifiers(Enums.Resources.WARLIKE, 3)
                .setTechCutOff(Enums.TechLevel.MEDIEVAL)
                .plentifulWhenHighTech()
        );
        _goods.add(new Good("Medicine", 500, 20)
                .addResourcesPriceModifiers(Enums.Resources.WARLIKE, 3)
                .addResourcesPriceModifiers(Enums.Resources.WEIRDMUSHROOMS, -2)
                .addResourcesPriceModifiers(Enums.Resources.LOTSOFHERBS, -3)
                .addResourcesQuantityModifiers(Enums.Resources.WARLIKE, -3)
                .addResourcesQuantityModifiers(Enums.Resources.WEIRDMUSHROOMS, 2)
                .addResourcesQuantityModifiers(Enums.Resources.LOTSOFHERBS, 3)
                .setTechCutOff(Enums.TechLevel.INDUSTRIAL)
                .expensiveWhenLowTech()
                .plentifulWhenHighTech()
        );
        _goods.add(new Good("Machines", 700, 15)
                .setTechCutOff(Enums.TechLevel.INDUSTRIAL)
                .expensiveWhenLowTech()
                .plentifulWhenHighTech()
        );
        _goods.add(new Good("Narcotics", 2500, 5)
                .addResourcesPriceModifiers(Enums.Resources.LOTSOFHERBS, -1)
                .addResourcesPriceModifiers(Enums.Resources.WEIRDMUSHROOMS, -2)
                .addResourcesPriceModifiers(Enums.Resources.ARTISTIC, 1)
                .addResourcesPriceModifiers(Enums.Resources.WARLIKE, -1)
                .addResourcesQuantityModifiers(Enums.Resources.LOTSOFHERBS, 1)
                .addResourcesQuantityModifiers(Enums.Resources.WEIRDMUSHROOMS, 2)
                .addResourcesQuantityModifiers(Enums.Resources.ARTISTIC, 3)
                .setTechCutOff(Enums.TechLevel.POSTINDUSTRIAL)
                .expensiveWhenHighTech()
                .plentifulWhenHighTech()
        );
        _goods.add(new Good("Bionic Parrots", 3500, 10)
                .addResourcesPriceModifiers(Enums.Resources.MINERALRICH, -1)
                .addResourcesPriceModifiers(Enums.Resources.MINERALPOOR, 1)
                .addResourcesPriceModifiers(Enums.Resources.WARLIKE, 1)
                .setTechCutOff(Enums.TechLevel.HITECH)
                .expensiveWhenLowTech()
                .plentifulWhenHighTech()
        );
    }

    public static GoodsRegistry getInstance(){
        if (_instance!=null){
            return _instance;
        }
        else{
            _instance=new GoodsRegistry();
            return _instance;
        }
    }

    public ArrayList<Good> getGoods() {
        return _goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        _goods = goods;
    }
}
