package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Model.RandomCoordinates;
import com.TableFlip.SpaceTrader.Service.PortNames;

import java.util.*;

/**
 * Hold reference to all planets and their locations.  Instantiating Port triggers planet creation
 */

public class Ocean {
    List<Island> _islands;
    ArrayList<Coordinates> _candidates;
    int _oceanHeight;
    int _oceanWidth;
    private static Ocean _instance;
    private static final int _MAXNUMPORTS = 7;

    /**
     * Sets default values for galaxy
     */
    private Ocean(){
        System.out.println("Starting Constructor.");

        setOceanHeight(100);
        setOceanWidth(100);

        _islands = new ArrayList<Island>();
        System.out.println("Making Islands.");
        generateIslands();
    }

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

    public void generateIslands() {
        ArrayList<String> names = new ArrayList<String>();
        Random gen = new Random();

        for (String n : PortNames.getInstance().getPortNames())
        {
            names.add(n);
        }

        makeCandidates();

        while (names.size() != 0)
        {
            int size;
            if (_MAXNUMPORTS > names.size())
            {
                if (names.size() == 1)
                    break;
                else
                    size = gen.nextInt(names.size()) + 1;
            }
            else
                size = gen.nextInt(_MAXNUMPORTS - 1) + 2;

            Coordinates pos = newIslandLocation(size);

            //System.out.print("New Island with position: " + pos + "and ports: ");

            String[] tempNames = new String[size];

            for (int i = 0; i < size; i ++)
            {
                tempNames[i] = names.remove(gen.nextInt(names.size()));
                //System.out.print(" " + tempNames[i]);
            }
            //System.out.println();
            Island temp = new Island(tempNames,  pos);

            _islands.add(temp);
        }
    }

    private Coordinates newIslandLocation(int radius) {
        Random gen = new Random();
        return _candidates.remove(gen.nextInt(_candidates.size()));
    }

    public List<Island> getIslands() {
        return _islands;
    }

    public void setPlanets(List<Island> ports) {
        _islands = ports;
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

    private void makeCandidates()
    {
        if (_oceanHeight == 0 || _oceanWidth == 0 || _islands.size() != 0)
        {
            return;
        }

        _candidates = new ArrayList<Coordinates>();

        int increment = _MAXNUMPORTS % 2 == 1 ? _MAXNUMPORTS + 1 : _MAXNUMPORTS + 2;

        for (int x = 1; x < _oceanWidth / increment; x++)
        {
            for (int y = 1; y < _oceanHeight / increment; y++)
            {
                _candidates.add(new Coordinates(x*increment, y*increment));
            }
        }
    }

    /**
     * Generates a beautiful ascii representation of the galaxy.  Too cool for school
     * @return String ascii awesomeness
     */
    @Override
    public String toString() {
        List<Island> islands = getIslands();
        ArrayList<Port> ports = new ArrayList<Port>();
        char[][] out = new char[_oceanWidth][_oceanHeight];
        String outstring = "";
        char marker = 'A';

        for(int i = 0; i < _oceanWidth; i++) {
            Arrays.fill(out[i], '-');
        }

        for (Island is : islands)
        {
            for (Port j : is.getPorts())
            {
                out[j.getCoordinates().getxPos()][j.getCoordinates().getyPos()] = marker;
            }

            marker++;
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
