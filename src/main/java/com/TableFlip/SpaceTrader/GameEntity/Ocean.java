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
    int _oceanHeight;
    int _oceanWidth;
    private static Ocean _instance;
    private static final int _MAXNUMPORTS = 7;

    /**
     * Sets default values for galaxy
     */
    private Ocean(){
        setOceanHeight(100);
        setOceanWidth(100);
        _islands =new ArrayList<Island>();
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

        while (names.size() != 0)
        {
            int size;
            if (_MAXNUMPORTS > names.size())
                size = gen.nextInt(names.size()) + 1;
            else
                size = gen.nextInt(_MAXNUMPORTS) + 1;

            Coordinates pos = newIslandLocation(size);

            String[] tempNames = new String[size];

            for (int i = 0; i < size; i ++)
            {
                tempNames[i] = names.remove(gen.nextInt(names.size()));
            }

            Island temp = new Island(tempNames,  pos);

            _islands.add(temp);
        }
    }

    private Coordinates newIslandLocation(int radius) {
        boolean failed;

        do{
            failed = false;

            // Generate new test point.
            Coordinates trial = new RandomCoordinates(_oceanWidth - radius*2, _oceanHeight - radius*2);
            trial.setxPos(trial.getxPos() + radius);
            trial.setyPos(trial.getyPos() + radius);

            for (Island isl : _islands)
            {
                int span = radius + (isl.getSize()/2);

                if (Math.abs(isl.getLocation().getxPos() - trial.getxPos()) < span && Math.abs(isl.getLocation().getyPos() - trial.getyPos()) < span)
                {
                    failed = true;
                    break;
                }
            }
        } while (failed);
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

    /**
     * Generates a beautiful ascii representation of the galaxy.  Too cool for school
     * @return String ascii awesomeness
     */
    @Override
    public String toString() {
        List<Port> ports = getIslands();
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
