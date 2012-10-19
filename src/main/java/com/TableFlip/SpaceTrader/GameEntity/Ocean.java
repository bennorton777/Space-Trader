package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Service.PlanetNames;

import java.util.*;

/**
 * Hold reference to all planets and their locations.  Instantiating Ocean triggers planet creation
 */

public class Ocean {
    List<com.TableFlip.SpaceTrader.Model.Ocean> _oceans;
    int _universeHeight;
    int _universeWidth;

    public Map<com.TableFlip.SpaceTrader.Model.Ocean, Coordinates> getLocations() {
        return _locations;
    }

    //Do not call gnenerateLocations if planets have not yet been generated!
    public Map<Coordinates, com.TableFlip.SpaceTrader.Model.Ocean> generateLocations() {
        HashMap<Coordinates, com.TableFlip.SpaceTrader.Model.Ocean> map = new HashMap<Coordinates, com.TableFlip.SpaceTrader.Model.Ocean>();
        for (com.TableFlip.SpaceTrader.Model.Ocean ocean : _oceans){
            ocean.setCoordinates(newRandomLocation(map));
            //System.out.println(ocean.getCoordinates());
        }
        return map;
    }

    /**
     * Makes new random coordinates that does not conflict with coordinates in the parameter map
     * @param map
     * @return
     */
    private Coordinates newRandomLocation(HashMap<Coordinates, com.TableFlip.SpaceTrader.Model.Ocean> map){
        Random random=new Random();
        int x=random.nextInt(getUniverseWidth());
        int y=random.nextInt(getUniverseHeight());
        Coordinates candidate=new Coordinates(x, y);
        if (map.containsKey(candidate)){
            candidate=newRandomLocation(map);
        }
        return candidate;
    }

    Map<com.TableFlip.SpaceTrader.Model.Ocean, Coordinates> _locations;

    public static Ocean getInstance() {
        if (_instance==null){
            System.out.println("New Ocean!");
            _instance=new Ocean();
            return _instance;
        }
        else{
            return _instance;
        }

    }

    private static Ocean _instance;

    /**
     * Sets default values for galaxy
     */
    private Ocean(){
        setUniverseHeight(100);
        setUniverseWidth(100);
        _oceans =new ArrayList<com.TableFlip.SpaceTrader.Model.Ocean>();
        for (String planetName : PlanetNames.getInstance().getPlanetNames()){
            _oceans.add(new RandomOcean(planetName));
        }
        generateLocations();
    }
    public List<com.TableFlip.SpaceTrader.Model.Ocean> getPlanets() {
        return _oceans;
    }

    public void setPlanets(List<com.TableFlip.SpaceTrader.Model.Ocean> oceans) {
        _oceans = oceans;
    }

    public int getUniverseHeight() {
        return _universeHeight;
    }

    public void setUniverseHeight(int universeHeight) {
        _universeHeight = universeHeight;
    }

    public int getUniverseWidth() {
        return _universeWidth;
    }

    public void setUniverseWidth(int universeWidth) {
        _universeWidth = universeWidth;
    }

    /**
     * Generates a beautiful ascii representation of the galaxy.  Too cool for school
     * @return String ascii awesomeness
     */
    @Override
    public String toString() {
        List<com.TableFlip.SpaceTrader.Model.Ocean> oceans = getPlanets();
        char[][] out = new char[_universeWidth ][_universeHeight];
        String outstring = "";

        for(int i = 0; i < _universeWidth; i++) {
            Arrays.fill(out[i], '-');
        }

        for (com.TableFlip.SpaceTrader.Model.Ocean j : oceans)
        {
            out[j.getCoordinates().getxPos()][j.getCoordinates().getyPos()] = 'X';
        }

        for(int i = 0; i < _universeWidth; i++) {
            for(int j = 0; j < _universeHeight; j++) {
                outstring += String.valueOf(out[i][j]);
            }
            outstring += String.valueOf('\n');
        }

        return outstring;
    }
}
