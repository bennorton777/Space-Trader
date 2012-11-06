package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 11/5/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class SaveMaker {
    private static SaveMaker _instance;

    private SaveMaker() {

    }

    public static SaveMaker getInstance() {
        if (_instance==null){
            _instance=new SaveMaker();
        }
        return _instance;
    }

    public void save() {
        try {
            System.out.println("New Save.");
            BufferedWriter out = new BufferedWriter(new FileWriter("save.txt"));
            out.write(Ocean.getInstance().toSave(), 0, Ocean.getInstance().toSave().length());
            out.newLine();
            out.write(Player.getInstance().toSave(), 0, Player.getInstance().toSave().length());
            out.flush();
        } catch (IOException e) {
            System.err.println("Save IO File Access Fail: " + e.toString());
        }
    }
}
