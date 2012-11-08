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
    private int _baseQuantity;
    private Map<Enums.TechLevel, Integer> _techPriceModifiers;
    private Map<Enums.Resources, Integer> _resourcesPriceModifiers;
    private Map<Enums.TechLevel, Integer> _techQuantityModifiers;
    private Map<Enums.Resources, Integer> _resourcesQuantityModifiers;
    private Enums.TechLevel techCutOff;

    /**
     * Basic constructor for a good
     * @param name
     * @param baseCost
     */
    public Good(String name, int baseCost, int baseQuantity){
        _name=name;
        _baseCost=baseCost;
        _baseQuantity=baseQuantity;
        _techPriceModifiers =new HashMap<Enums.TechLevel, Integer>();
        _resourcesPriceModifiers =new HashMap<Enums.Resources, Integer>();
        _techQuantityModifiers =new HashMap<Enums.TechLevel, Integer>();
        _resourcesQuantityModifiers =new HashMap<Enums.Resources, Integer>();
    }

    /**
     * Calculates price bias for this good based on a given resource level and tech level
     * @param resources
     * @param techLevel
     * @return int bias
     */
    public int getPriceBias(Enums.Resources resources, Enums.TechLevel techLevel){
        int resource=0;
        int tech=0;
        if (_resourcesPriceModifiers.get(resources)!=null){
            resource= _resourcesPriceModifiers.get(resources);
        }
        if (_techPriceModifiers.get(techLevel)!=null){
            tech= _techPriceModifiers.get(techLevel);
        }
        return tech+resource;
    }

    /**
     * Calculates quantity bias for this good based on a given resource level and tech level
     * @param resources
     * @param techLevel
     * @return int bias
     */
    public int getQuantityBias(Enums.Resources resources, Enums.TechLevel techLevel){
        int resource=0;
        int tech=0;
        if (_resourcesQuantityModifiers.get(resources)!=null){
            resource= _resourcesQuantityModifiers.get(resources);
        }
        if (_techQuantityModifiers.get(techLevel)!=null){
            tech= _techQuantityModifiers.get(techLevel);
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

    public int getBaseQuantity() {
        return _baseQuantity;
    }

    public void setBaseQuantity(int _baseQuantity) {
        this._baseQuantity = _baseQuantity;
    }

    public String getName() {
        return _name;
    }

    public Good setName(String name) {
        _name = name;
        return this;
    }

    public Map<Enums.TechLevel, Integer> getTechPriceModifiers() {
        return _techPriceModifiers;
    }

    public Good addTechPriceModifier(Enums.TechLevel techLevel, int modifier) {
        _techPriceModifiers.put(techLevel, modifier);
        return this;
    }

    public Map<Enums.Resources, Integer> getResourcesPriceModifiers() {
        return _resourcesPriceModifiers;
    }

    public Good addResourcesPriceModifiers(Enums.Resources resource, int modifier) {
        _resourcesPriceModifiers.put(resource, modifier);
        return this;
    }

    public Map<Enums.TechLevel, Integer> getTechQuantityModifiers() {
        return _techQuantityModifiers;
    }

    public Good addTechQuantityModifier(Enums.TechLevel techLevel, int modifier) {
        _techQuantityModifiers.put(techLevel, modifier);
        return this;
    }

    public Map<Enums.Resources, Integer> getResourcesQuantityModifiers() {
        return _resourcesQuantityModifiers;
    }

    public Good addResourcesQuantityModifiers(Enums.Resources resource, int modifier) {
        _resourcesQuantityModifiers.put(resource, modifier);
        return this;
    }

    /**
     * Shortcut for setting a lot of price biases
     * @return Good
     */
    public Good expensiveWhenLowTech(){
        _techPriceModifiers.put(Enums.TechLevel.PREAGRICULTURE, 3);
        _techPriceModifiers.put(Enums.TechLevel.AGRICULTURE, 2);
        _techPriceModifiers.put(Enums.TechLevel.MEDIEVAL, 1);
        _techPriceModifiers.put(Enums.TechLevel.RENAISSANCE, 0);
        _techPriceModifiers.put(Enums.TechLevel.INDUSTRIAL, -1);
        _techPriceModifiers.put(Enums.TechLevel.POSTINDUSTRIAL, -2);
        _techPriceModifiers.put(Enums.TechLevel.HITECH, -3);
        return this;
    }

    /**
     * Shortcut for setting a lot of price biases.
     * @return Good
     */
    public Good expensiveWhenHighTech(){
        _techPriceModifiers.put(Enums.TechLevel.PREAGRICULTURE, -3);
        _techPriceModifiers.put(Enums.TechLevel.AGRICULTURE, -2);
        _techPriceModifiers.put(Enums.TechLevel.MEDIEVAL, -1);
        _techPriceModifiers.put(Enums.TechLevel.RENAISSANCE, 0);
        _techPriceModifiers.put(Enums.TechLevel.INDUSTRIAL, 1);
        _techPriceModifiers.put(Enums.TechLevel.POSTINDUSTRIAL, 2);
        _techPriceModifiers.put(Enums.TechLevel.HITECH, 3);
        return this;
    }

    /**
     * Shortcut for setting a lot of quantity biases
     * @return Good
     */
    public Good plentifulWhenLowTech(){
        _techQuantityModifiers.put(Enums.TechLevel.PREAGRICULTURE, 3);
        _techQuantityModifiers.put(Enums.TechLevel.AGRICULTURE, 2);
        _techQuantityModifiers.put(Enums.TechLevel.MEDIEVAL, 1);
        _techQuantityModifiers.put(Enums.TechLevel.RENAISSANCE, 0);
        _techQuantityModifiers.put(Enums.TechLevel.INDUSTRIAL, -1);
        _techQuantityModifiers.put(Enums.TechLevel.POSTINDUSTRIAL, -2);
        _techQuantityModifiers.put(Enums.TechLevel.HITECH, -3);
        return this;
    }

    /**
     * Shortcut for setting a lot of quantity biases.
     * @return Good
     */
    public Good plentifulWhenHighTech(){
        _techQuantityModifiers.put(Enums.TechLevel.PREAGRICULTURE, -3);
        _techQuantityModifiers.put(Enums.TechLevel.AGRICULTURE, -2);
        _techQuantityModifiers.put(Enums.TechLevel.MEDIEVAL, -1);
        _techQuantityModifiers.put(Enums.TechLevel.RENAISSANCE, 0);
        _techQuantityModifiers.put(Enums.TechLevel.INDUSTRIAL, 1);
        _techQuantityModifiers.put(Enums.TechLevel.POSTINDUSTRIAL, 2);
        _techQuantityModifiers.put(Enums.TechLevel.HITECH, 3);
        return this;
    }

    public Enums.TechLevel getTechCutOff() {
        return techCutOff;
    }

    public Good setTechCutOff(Enums.TechLevel techCutOff) {
        this.techCutOff = techCutOff;
        return this;
    }

    public String toString() {
        return _name;
    }
}
