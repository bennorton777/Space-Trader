package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Ocean;
import com.TableFlip.SpaceTrader.Service.PriceGenerator;

import java.util.Random;

/**
 * Ocean subclass that sets its values randomly
 */
public class RandomOcean extends Ocean {
    public RandomOcean(String name){
        Random random=new Random();
        setName(name);
        setTechLevel(Enums.TechLevel.values()[random.nextInt(Enums.TechLevel.values().length)]);
        setResources(Enums.Resources.values()[random.nextInt(Enums.Resources.values().length)]);
        //System.out.println("Generating planet named: " + name);
        setLocalPrices(PriceGenerator.getInstance().generatePrices(this).getLocalValues());
    }
}
