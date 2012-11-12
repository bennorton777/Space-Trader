package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.GameEntity.RandomPort;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 10/15/12
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Island
{
    private static Coordinates[][] _offsets = {
            {new Coordinates(0, 0)},
            {new Coordinates(-1, -1), new Coordinates(0, -1), new Coordinates(1, -1), new Coordinates(-1, 0), new Coordinates(1, 0), new Coordinates(-1, 1), new Coordinates(0, 1), new Coordinates(1, 1)},
            {new Coordinates (2, 0) , new Coordinates (0, 2) , new Coordinates (-2, 0) , new Coordinates (0, -2), new Coordinates (2, 1) , new Coordinates (-1, 2) , new Coordinates (-2, -1) , new Coordinates (1, -2), new Coordinates (1, 2) , new Coordinates (-2, 1) , new Coordinates (-1, -2) , new Coordinates (2, -1)},
            {new Coordinates (3, 0) , new Coordinates (0, 3) , new Coordinates (-3, 0) , new Coordinates (0, -3), new Coordinates (3, 1) , new Coordinates (-1, 3) , new Coordinates (-3, -1) , new Coordinates (1, -3), new Coordinates (2, 2) , new Coordinates (-2, 2) , new Coordinates (-2, -2) , new Coordinates (2, -2), new Coordinates (1, 3) , new Coordinates (-3, 1) , new Coordinates (-1, -3) , new Coordinates (3, -1)},
    };
    private ArrayList<Coordinates> _candidatePorts = new ArrayList<Coordinates>();
    private Coordinates _location;
    private int _size;
    private ArrayList<Port> _ports;

    public Island(String[] names, Coordinates location) {
        _size = names.length <= 7 ? names.length : 7;
        _location = location;
        _ports = new ArrayList<Port>();

        for (String name : names)
        {
            _ports.add(new RandomPort(name));
        }

        placePorts();
    }

    public Island() {

    }

    public Coordinates getLocation() {
        return _location;
    }

    public Island setLocation(Coordinates location) {
        _location = location;
        return this;
    }

    public int getSize() {
        return _size;
    }

    public Island setSize(int size) {
        _size = size;
        return this;
    }

    public ArrayList<Port> getPorts() {
        return _ports;
    }

    public Island setPorts(ArrayList<Port> ports) {
        _ports = ports;
        return this;
    }

    private void placePorts() {
        makeCandidates();
        Random gen = new Random();

        if (_size == 0 || _ports == null)
        {
            return;
        }
        else
        {
            for(Port p : _ports)
            {
                int index = gen.nextInt(_candidatePorts.size());

                p.setCoordinates(_candidatePorts.remove(index));
            }
        }
    }

    private void makeCandidates()
    {
        Coordinates[] shift = _offsets[_size/2];

        _candidatePorts.clear();

        for (Coordinates c : shift)
        {
            _candidatePorts.add(new Coordinates(c.getxPos() + _location.getxPos(), c.getyPos() + _location.getyPos()));
        }
    }

    @Override
    public String toString() {
        String done = "Island at location " + _location + " with ports:";

        for (Port p : _ports)
        {
            done += "\n\t" + p.toString();
        }

        return done;
    }

    public String toSave() {
        String save = "island|" + _location.getyPos() + '|' + _location.getxPos() + '\n';

        for (Port p : _ports)
        {
            save += "\t" + p.toSave() + '\n';
        }

        save += "endisland";

        return save;
    }

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        JSONArray arr = new JSONArray();

        try {
            for (Port i : _ports)
            {
                arr.put(i.toJSON());
            }

            ret.put("ports", arr);
            ret.put("location", _location.toJSON());
        } catch (JSONException e) {
            System.out.println("JSON creation error " + e.toString());
        }
        return ret;
    }

    public static Island hydrate(JSONObject dry) {
        try {
            JSONArray dryPorts = dry.getJSONArray("ports");
            ArrayList<Port> wetPorts = new ArrayList<Port>();

            for (int i = 0; i < dryPorts.length(); i++) {
                wetPorts.add(Port.hydrate(dryPorts.getJSONObject(i)));
            }

            Island wet = new Island().setLocation(Coordinates.hydrate(dry.getJSONObject("location"))).setPorts(wetPorts).setSize(wetPorts.size());

            return wet;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
