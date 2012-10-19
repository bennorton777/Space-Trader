package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.GameEntity.RandomPlanet;
import com.TableFlip.SpaceTrader.Model.Good;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class generates prices for planets
 */
public class PriceGenerator {
    private static PriceGenerator _instance;
    private GoodsRegistry _goodsRegistry;
    private PriceGenerator(){
        _goodsRegistry=GoodsRegistry.getInstance();
    }

    public static PriceGenerator getInstance() {
        if (_instance==null){
            _instance=new PriceGenerator();
            return _instance;
        }
        else{
            return _instance;
        }
    }

    /**
     * The bulk of this class's logic is contained here.  Generate me some prices!
     * @param planet
     * @return
     */
    public RandomPlanet generatePrices(RandomPlanet planet){
        Map<Good, Integer> values=new HashMap<Good, Integer>();
        for (Good good : _goodsRegistry.getGoods()){
            int bias = good.getBias(planet.getResources(), planet.getTechLevel());
            Random random=new Random();
            int modifiedBias=random.nextInt(10)*bias;
            int biasAdjust=modifiedBias*good.getBaseCost()/100;
            values.put(good, good.getBaseCost()+biasAdjust);
        }
        values=filterGoods(values); //Filter Goods
        planet.setLocalPrices(values);
        return planet;
    }

    /**
     * Just a placeholder for now, but can eventually be used to filter out Goods
     * @param candidateValues
     * @return
     */
    private Map<Good, Integer> filterGoods(Map<Good, Integer> candidateValues){
        for (Good good : candidateValues.keySet()){
            //System.out.println(good.getName());
        }
        return candidateValues;
    }
}
