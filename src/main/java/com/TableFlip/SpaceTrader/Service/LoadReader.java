package com.TableFlip.SpaceTrader.Service;

import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Model.Ship;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Noirbot
 * Date: 11/6/12
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoadReader {
    private static LoadReader _instance;

    private LoadReader() {

    }

    public static LoadReader getInstance() {
        if (_instance == null)
        {
            _instance = new LoadReader();
        }

        return _instance;
    }

    public void loadGame() {
        try {
            File f = new File("save.json");
            if(f.exists()) {
                BufferedReader read = new BufferedReader(new FileReader(f));
                String line = read.readLine();
                try {
                    JSONObject ocean = new JSONObject(line);
                    JSONArray islands = ocean.getJSONArray("islands");
                    ArrayList<Island> toMake = new ArrayList<Island>();

                    for (int i = 0; i < islands.length(); i++)
                    {
                        toMake.add(Island.hydrate(islands.getJSONObject(i)));
                    }

                    Ocean.hydrateOcean(toMake, Port.hydrate(ocean.getJSONObject("highlighted")));

                    line = read.readLine();

                    JSONObject player = new JSONObject(line);

                    HashMap<Enums.Skill, Integer> stats = new HashMap<Enums.Skill, Integer>();

                    for (Enums.Skill e : Enums.Skill.values())
                    {
                        stats.put(e, player.getJSONObject("skills").getInt(e.toString()));
                    }

                    Player.hydratePlayer(player.getString("name"), player.getInt("credits"), stats, Ship.hydrate(player.getJSONObject("ship")));
                } catch (JSONException e) {
                    System.out.println("JSON creation error " + e.toString());
                }

            } else {
                System.out.println("No save file found.");
            }

        } catch (IOException e) {
            System.err.println("Load IO File Access Fail: " + e.toString());
        }
    }
}
