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
            System.out.println("New Save. JSON");
            BufferedWriter out = new BufferedWriter(new FileWriter("save.json"));
            //System.out.println(Player.getInstance().toJSON().toString());
            out.write(Ocean.getInstance().toJSON().toString(), 0, Ocean.getInstance().toJSON().toString().length());
            out.newLine();
            out.write(Player.getInstance().toJSON().toString(), 0, Player.getInstance().toJSON().toString().length());
            out.flush();
        } catch (IOException e) {
            System.err.println("Save IO File Access Fail: " + e.toString());
        }
    }
}
