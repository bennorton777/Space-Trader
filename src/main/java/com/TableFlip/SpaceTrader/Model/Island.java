package com.TableFlip.SpaceTrader.Model;

import com.TableFlip.SpaceTrader.GameEntity.RandomPort;
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

    public Coordinates getLocation() {
        return _location;
    }

    public void setLocation(Coordinates location) {
        _location = location;
    }

    public int getSize() {
        return _size;
    }

    public void setSize(int size) {
        _size = size;
    }

    public ArrayList<Port> getPorts() {
        return _ports;
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
}
