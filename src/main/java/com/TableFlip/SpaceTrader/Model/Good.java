package com.TableFlip.SpaceTrader.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Information Holder for Good.
 * Contains preference for modifiers
 * Contains minimal logic to calculate bias.
 */
public class Good {
    private String _name;
    private int _baseCost;
    private Map<Enums.TechLevel, Integer> _techModifiers;
    private Map<Enums.Resources, Integer> _resourcesModifiers;
    private Enums.TechLevel techCutOff;

    /**
     * Basic constructor for a good
     * @param name
     * @param baseCost
     */
    public Good(String name, int baseCost){
        _name=name;
        _baseCost=baseCost;
        _techModifiers=new HashMap<Enums.TechLevel, Integer>();
        _resourcesModifiers=new HashMap<Enums.Resources, Integer>();
    }

    /**
     * Calculates bias for this good based on a given resource level and tech level
     * @param resources
     * @param techLevel
     * @return int bias
     */
    public int getBias(Enums.Resources resources, Enums.TechLevel techLevel){
        int resource=0;
        int tech=0;
        if (_resourcesModifiers.get(resources)!=null){
            resource=_resourcesModifiers.get(resources);
        }
        if (_techModifiers.get(techLevel)!=null){
            tech=_techModifiers.get(techLevel);
        }
        return tech+resource;
    }
    public int getBaseCost() {
        return _baseCost;
    }

    public Good setBaseCost(int baseCost) {
        _baseCost = baseCost;
        return this;
    }

    public String getName() {
        return _name;
    }

    public Good setName(String name) {
        _name = name;
        return this;
    }

    public Map<Enums.TechLevel, Integer> getTechModifiers() {
        return _techModifiers;
    }

    public Good addTechModifier(Enums.TechLevel techLevel, int modifier) {
        _techModifiers.put(techLevel, modifier);
        return this;
    }

    public Map<Enums.Resources, Integer> getResourcesModifiers() {
        return _resourcesModifiers;
    }

    public Good addResourcesModifiers(Enums.Resources resource, int modifier) {
        _resourcesModifiers.put(resource, modifier);
        return this;
    }

    /**
     * Shortcut for setting a lot of biases
     * @return Good
     */
    public Good expensiveWhenLowTech(){
        _techModifiers.put(Enums.TechLevel.PREAGRICULTURE, 3);
        _techModifiers.put(Enums.TechLevel.AGRICULTURE,2);
        _techModifiers.put(Enums.TechLevel.MEDIEVAL,1);
        _techModifiers.put(Enums.TechLevel.RENAISSANCE,0);
        _techModifiers.put(Enums.TechLevel.INDUSTRIAL,-1);
        _techModifiers.put(Enums.TechLevel.POSTINDUSTRIAL,-2);
        _techModifiers.put(Enums.TechLevel.HITECH,-3);
        return this;
    }

    /**
     * Shortcut for setting a lot of biases.
     * @return Good
     */
    public Good expensiveWhenHighTech(){
        _techModifiers.put(Enums.TechLevel.PREAGRICULTURE, -3);
        _techModifiers.put(Enums.TechLevel.AGRICULTURE,-2);
        _techModifiers.put(Enums.TechLevel.MEDIEVAL,-1);
        _techModifiers.put(Enums.TechLevel.RENAISSANCE,0);
        _techModifiers.put(Enums.TechLevel.INDUSTRIAL,1);
        _techModifiers.put(Enums.TechLevel.POSTINDUSTRIAL,2);
        _techModifiers.put(Enums.TechLevel.HITECH,3);
        return this;
    }

    public Enums.TechLevel getTechCutOff() {
        return techCutOff;
    }

    public Good setTechCutOff(Enums.TechLevel techCutOff) {
        this.techCutOff = techCutOff;
        return this;
    }
}
