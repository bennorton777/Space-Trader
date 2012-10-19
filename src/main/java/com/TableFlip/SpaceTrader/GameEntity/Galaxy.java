package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Model.Planet;
import com.TableFlip.SpaceTrader.Service.PlanetNames;

import java.util.*;

/**
 * Hold reference to all planets and their locations.  Instantiating Galaxy triggers planet creation
 */

public class Galaxy {
    List<Planet> _planets;
    int _universeHeight;
    int _universeWidth;

    public Map<Planet, Coordinates> getLocations() {
        return _locations;
    }

    //Do not call gnenerateLocations if planets have not yet been generated!
    public Map<Coordinates, Planet> generateLocations() {
        HashMap<Coordinates, Planet> map = new HashMap<Coordinates, Planet>();
        for (Planet planet : _planets){
            planet.setCoordinates(newRandomLocation(map));
            //System.out.println(planet.getCoordinates());
        }
        return map;
    }

    /**
     * Makes new random coordinates that does not conflict with coordinates in the parameter map
     * @param map
     * @return
     */
    private Coordinates newRandomLocation(HashMap<Coordinates, Planet> map){
        Random random=new Random();
        int x=random.nextInt(getUniverseWidth());
        int y=random.nextInt(getUniverseHeight());
        Coordinates candidate=new Coordinates(x, y);
        if (map.containsKey(candidate)){
            candidate=newRandomLocation(map);
        }
        return candidate;
    }

    Map<Planet, Coordinates> _locations;

    public static Galaxy getInstance() {
        if (_instance==null){
            System.out.println("New Galaxy!");
            _instance=new Galaxy();
            return _instance;
        }
        else{
            return _instance;
        }

    }

    private static Galaxy _instance;

    /**
     * Sets default values for galaxy
     */
    private Galaxy(){
        setUniverseHeight(100);
        setUniverseWidth(100);
        _planets =new ArrayList<Planet>();
        for (String planetName : PlanetNames.getInstance().getPlanetNames()){
            _planets.add(new RandomPlanet(planetName));
        }
        generateLocations();
    }
    public List<Planet> getPlanets() {
        return _planets;
    }

    public void setPlanets(List<Planet> planets) {
        _planets = planets;
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
        List<Planet> planets = getPlanets();
        char[][] out = new char[_universeWidth ][_universeHeight];
        String outstring = "";

        for(int i = 0; i < _universeWidth; i++) {
            Arrays.fill(out[i], '-');
        }

        for (Planet j : planets)
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
