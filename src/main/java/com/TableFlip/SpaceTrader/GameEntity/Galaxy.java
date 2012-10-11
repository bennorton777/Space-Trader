package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Model.Planet;
import com.TableFlip.SpaceTrader.Service.PlanetNames;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 9/7/12
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
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
            System.out.println(planet.getCoordinates());
        }
        return map;
    }

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
    private Galaxy(){
        setUniverseHeight(150);
        setUniverseWidth(150);
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

    @Override
    public String toString() {
        List<Planet> planets = getPlanets();
        char[][] out = new char[_universeWidth ][_universeHeight];
        String outstring = "";

        for(int i = 0; i < 150; i++) {
            //System.out.println(i);
            Arrays.fill(out[i], '-');
        }

        //System.out.println("Filled.");

        for (Planet j : planets)
        {
            out[j.getCoordinates().getxPos()][j.getCoordinates().getyPos()] = 'X';
        }

        System.out.println("Xed.");

        for(int i = 0; i < 150; i++) {
            for(int j = 0; j < 150; j++) {
                //System.out.print(out[i][j]);
                outstring += String.valueOf(out[i][j]);
            }
            //System.out.println();
            outstring += String.valueOf('\n');
        }

        System.out.println("Done.");

        return outstring;
    }
}
