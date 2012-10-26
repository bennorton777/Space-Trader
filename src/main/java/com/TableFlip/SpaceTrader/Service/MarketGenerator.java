package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.GameEntity.RandomPort;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;

import java.util.*;

/**
 * This class generates prices and quantities for ports= sets up the market.
 */
public class MarketGenerator {
    private static MarketGenerator _instance;
    private GoodsRegistry _goodsRegistry;
    private MarketGenerator(){
        _goodsRegistry=GoodsRegistry.getInstance();
    }

    public static MarketGenerator getInstance() {
        if (_instance==null){
            _instance=new MarketGenerator();
            return _instance;
        }
        else{
            return _instance;
        }
    }



    /**
     * The bulk of this class's logic is contained here.  Generates prices and quantities.
     * @param port
     * @return
     */
    public RandomPort generateMarket(RandomPort port){
        Map<Good, HashMap<Enums.MarketValues, Integer>> goodValues =
                new HashMap<Good, HashMap<Enums.MarketValues, Integer>>();
        for (Good good : _goodsRegistry.getGoods()){
            goodValues.put(good, new HashMap<Enums.MarketValues, Integer>());
            int priceBias = good.getPriceBias(port.getResources(), port.getTechLevel());
            Random random=new Random();
            int modifiedPriceBias=random.nextInt(5)*priceBias;
            int biasAdjustPrice=modifiedPriceBias*2*(good.getBaseCost()/100);
            int finalPrice = good.getBaseCost()+biasAdjustPrice;
            if (finalPrice < 0){
                finalPrice = 0; //add check so that we don't end up with negative prices
            }

            int quantityBias = good.getQuantityBias(port.getResources(), port.getTechLevel()) ;
            int modifiedQuantityBias=random.nextInt(5)*quantityBias;
            int biasAdjustQuantity=modifiedQuantityBias*(good.getBaseQuantity()/10);
                    //multiply by .02 to scale it
            int finalQuantity = good.getBaseQuantity()+biasAdjustQuantity;
            if (finalQuantity < 0){
                finalQuantity = 0; //add check so that we don't end up with negative quantities
            }

            goodValues.get(good).put(Enums.MarketValues.PRICE, finalPrice);
            goodValues.get(good).put(Enums.MarketValues.QUANTITY, finalQuantity);
        }
        goodValues=filterGoods(goodValues, port); //Filter Goods
        port.setLocalMarket(goodValues);
        return port;
    }

    /**
     * Filters goods with use of helper methods for each tech level.
     * @param candidateValues
     * @return
     */
    private Map<Good, HashMap<Enums.MarketValues, Integer>> filterGoods(Map<Good, HashMap<Enums.MarketValues, Integer>> candidateValues, RandomPort port){ //}, RandomPort port){
        Set<Good> keys = new HashSet<Good>();
        for (Good good : candidateValues.keySet()){
            if (filterIfPreAgriculture(port, good) ||
                    filterIfAgriculture(port, good) ||
                    filterIfMedieval(port, good) ||
                    filterIfRenaissance(port, good) ||
                    filterIfEarly(port, good) ||
                    filterIfIndustrial(port, good)){
                keys.add(good);
            }
        }
        for (Good key : keys)  {
            candidateValues.remove(key);
        }
        return candidateValues;
    }

    private boolean filterIfPreAgriculture(RandomPort port, Good good){
        if (port.getTechLevel()== Enums.TechLevel.PREAGRICULTURE){
            if ((good.getName().equals("Games")) || (good.getName().equals("Firearms")) || (good.getName().equals("Machines"))
                    || (good.getName().equals("Narcotics")) || (good.getName().equals("Bionic Parrots"))){
                return true;
            }
        }
          return false;
    }

    private boolean filterIfAgriculture(RandomPort port, Good good){
        if (port.getTechLevel()== Enums.TechLevel.AGRICULTURE){
            if ((good.getName().equals("Games")) || (good.getName().equals("Firearms")) || (good.getName().equals("Machines"))
                    || (good.getName().equals("Narcotics")) || (good.getName().equals("Bionic Parrots"))){
                return true;
            }
        }
        return false;
    }

    private boolean filterIfMedieval(RandomPort port, Good good){
        if (port.getTechLevel()== Enums.TechLevel.MEDIEVAL){
            if ((good.getName().equals("Games")) || (good.getName().equals("Firearms")) || (good.getName().equals("Machines"))
                    || (good.getName().equals("Narcotics")) || (good.getName().equals("Bionic Parrots"))){
                return true;
            }
        }
        return false;
    }

    private boolean filterIfRenaissance(RandomPort port, Good good){
        if (port.getTechLevel()== Enums.TechLevel.RENAISSANCE){
            if ((good.getName().equals("Machines"))
                    || (good.getName().equals("Narcotics")) || (good.getName().equals("Bionic Parrots"))){
                return true;
            }
        }
        return false;
    }

    private boolean filterIfEarly(RandomPort port, Good good){
        if (port.getTechLevel()== Enums.TechLevel.EARLY){
            if ((good.getName().equals("Machines"))
                    || (good.getName().equals("Narcotics")) || (good.getName().equals("Bionic Parrots"))){
                return true;
            }
        }
        return false;
    }

    private boolean filterIfIndustrial(RandomPort port, Good good){
        if (port.getTechLevel()== Enums.TechLevel.INDUSTRIAL){
            if ((good.getName().equals("Bionic Parrots"))){
                return true;
            }
        }
        return false;
    }

}
