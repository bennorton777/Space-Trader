package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.PortNames;

import java.util.*;

/**
 * Hold reference to all planets and their locations.  Instantiating Port triggers planet creation
 */

public class Ocean {
    List<Port> _ports;
    int _oceanHeight;
    int _oceanWidth;

    public Map<Port, Coordinates> getLocations() {
        return _locations;
    }

    //Do not call gnenerateLocations if planets have not yet been generated!
    public Map<Coordinates, Port> generateLocations() {
        HashMap<Coordinates, Port> map = new HashMap<Coordinates, Port>();
        for (Port port : _ports){
            port.setCoordinates(newRandomLocation(map));
            //System.out.println(port.getCoordinates());
        }
        return map;
    }

    /**
     * Makes new random coordinates that does not conflict with coordinates in the parameter map
     * @param map
     * @return
     */
    private Coordinates newRandomLocation(HashMap<Coordinates, Port> map){
        Random random=new Random();
        int x=random.nextInt(getOceanWidth());
        int y=random.nextInt(getOceanHeight());
        Coordinates candidate=new Coordinates(x, y);
        if (map.containsKey(candidate)){
            candidate=newRandomLocation(map);
        }
        return candidate;
    }

    Map<Port, Coordinates> _locations;

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
        setOceanHeight(100);
        setOceanWidth(100);
        _ports =new ArrayList<Port>();
        for (String portName : PortNames.getInstance().getPortNames()){
            _ports.add(new RandomPort(portName));
        }
        generateLocations();


    }
    public List<Port> getPorts() {
        return _ports;
    }

    public void setPlanets(List<Port> ports) {
        _ports = ports;
    }

    public int getOceanHeight() {
        return _oceanHeight;
    }

    public void setOceanHeight(int oceanHeight) {
        _oceanHeight = oceanHeight;
    }

    public int getOceanWidth() {
        return _oceanWidth;
    }

    public void setOceanWidth(int oceanWidth) {
        _oceanWidth = oceanWidth;
    }

    /**
     * Generates a beautiful ascii representation of the galaxy.  Too cool for school
     * @return String ascii awesomeness
     */
    @Override
    public String toString() {
        List<Port> ports = getPorts();
        char[][] out = new char[_oceanWidth][_oceanHeight];
        String outstring = "";

        for(int i = 0; i < _oceanWidth; i++) {
            Arrays.fill(out[i], '-');
        }

        for (Port j : ports)
        {
            out[j.getCoordinates().getxPos()][j.getCoordinates().getyPos()] = 'X';
        }

        for(int i = 0; i < _oceanWidth; i++) {
            for(int j = 0; j < _oceanHeight; j++) {
                outstring += String.valueOf(out[i][j]);
            }
            outstring += String.valueOf('\n');
        }

        return outstring;
    }
}
