package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.DataStructure.SparseArray.SparseArray;
import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.PortNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private static final int _OCEANHEIGHT = 100;
    private static final int _OCEANWIDTH = 100;
    private static SparseArray<Port> _portSparseArray;
    private Port _highlightedPort;

    /**
     * Sets default values for galaxy
     */
    private Ocean(boolean makeBlank){
        setOceanHeight(_OCEANHEIGHT);
        setOceanWidth(_OCEANWIDTH);
        if (!makeBlank)
        {
            System.out.println("New full Ocean");
            _portSparseArray = new SparseArray<Port>(_OCEANWIDTH, _OCEANHEIGHT);
            _islands = new ArrayList<Island>();
            generateIslands();

            for (Island island : _islands){
                for(Port port : island.getPorts()){
                    _portSparseArray.putAt(port.getCoordinates().getyPos(), port.getCoordinates().getxPos(), port);
                }
            }
            System.out.println("Ocean Done!");
        } else {
            System.out.println("New empty Ocean.");
            _portSparseArray = new SparseArray<Port>(_OCEANWIDTH, _OCEANHEIGHT);
            _islands = new ArrayList<Island>();
        }
    }

    public static Ocean getInstance() {
        if (_instance==null){

            _instance=new Ocean(false);
            System.out.println("New Ocean!");
            return _instance;
        }
        else{
            return _instance;
        }

    }

    public static Ocean hydrateOcean(List<Island> islands, Port highlighted) {
        _instance = new Ocean(true);

        _instance.setIslands(islands);

        for (Island island : islands){
            for(Port port : island.getPorts()){
                _portSparseArray.putAt(port.getCoordinates().getyPos(), port.getCoordinates().getxPos(), port);
            }
        }

        _instance.setHighlightedPort(highlighted);

        return _instance;
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

            String[] tempNames = new String[size];

            for (int i = 0; i < size; i ++)
            {
                tempNames[i] = names.remove(gen.nextInt(names.size()));
            }
            Island temp = new Island(tempNames,  pos);
            _islands.add(temp);
        }
    }

    private Coordinates newIslandLocation(int numPorts) {
        Random gen = new Random();
        Coordinates base = _candidates.remove(gen.nextInt(_candidates.size()));
        switch (numPorts) {
            case 1:case 2: base.setxPos(base.getxPos() + gen.nextInt(4) - 2); base.setyPos(base.getyPos() + gen.nextInt(4) - 2); break;
            case 3:case 4: base.setxPos(base.getxPos() + gen.nextInt(2) - 1); base.setyPos(base.getyPos() + gen.nextInt(2) - 1); break;
            default: break;
        }
        return base;
    }

    public List<Island> getIslands() {
        return _islands;
    }

    public void setIslands(List<Island> islands) {
        _islands = islands;
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

    public Port getHighlightedPort() {
        return _highlightedPort;
    }

    public void setHighlightedPort(Port port) {
        _highlightedPort = port;
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

    @Override
    public String toString() {
        String stuff = "Ocean with Islands:";

        for (Island i : _islands)
        {
            stuff += "\n" + i.toString();
        }

        return stuff;
    }

    /**
     * Generates a beautiful ascii representation of the galaxy.  Too cool for school
     * @return String ascii awesomeness
     */
    public String ASCIIMap() {
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

    public SparseArray<Port> getPortSparseArray() {
        return _portSparseArray;
    }

    public void setPortSparseArray(SparseArray<Port> portSparseArray) {
        _portSparseArray = portSparseArray;
    }

    public String toSave() {
        String save = "ocean\n";

        for (Island i : _islands)
        {
            save += i.toSave() + '\n';
        }

        save += "endocean";

        return save;
    }

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        JSONArray arr = new JSONArray();

        try {
            for (Island i : _islands)
            {
                arr.put(i.toJSON());
            }

            ret.put("islands", arr);
            ret.put("highlighted", _highlightedPort.toJSON());
        } catch (JSONException e) {
            System.out.println("JSON creation error " + e.toString());
        }
        return ret;
    }
}
