package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.MarketGenerator;

import java.util.Random;

/**
 * Port subclass that sets its values randomly
 */
public class RandomPort extends Port {
    public RandomPort(String name){
        Random random=new Random();
        setName(name);
        setTechLevel(Enums.TechLevel.values()[random.nextInt(Enums.TechLevel.values().length)]);
        setResources(Enums.Resources.values()[random.nextInt(Enums.Resources.values().length)]);
        //System.out.println("Generating planet named: " + name);
        setLocalMarket(MarketGenerator.getInstance().generateMarket(this).getLocalMarket());     //.getLocalMarket
    }
}
