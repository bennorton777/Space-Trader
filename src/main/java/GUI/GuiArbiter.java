package GUI;

import com.TableFlip.SpaceTrader.GameEntity.Gnat;
import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.GameEntity.RandomPort;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;

import java.util.HashMap;
import java.util.Random;
import java.util.*;

/**
 * Arbiter class that interprets between GUI and game logic.
 * Allows changes in game logic and GUI to be made independently.
 * Any changes in a GUI class should bubble up no further than this class.
 * Any changes in a Game Logic class should bubble down no further than this class.
 */
public class GuiArbiter {
    /**
     * Interpreter method to make new Character GUI things happen.
     * Game logic does not need to know how this is done.
     */
    public static void charGen(){
        CharGen.main(new String[3]);
    }
    private static Player _player;

    /**
     * new character game logic abstraction.  The GUI classes do not need to know how to make the Game Logic create a new character.
     * @param name
     * @param pilotSkill
     * @param fighterSkill
     * @param traderSkill
     * @param engineerSkill
     */
    public static void newCharacter(String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill){
        HashMap<Enums.Skill, Integer> stats=new HashMap<Enums.Skill, Integer>();
        stats.put(Enums.Skill.PILOT, pilotSkill);
        stats.put(Enums.Skill.FIGHTER, fighterSkill);
        stats.put(Enums.Skill.TRADER, traderSkill);
        stats.put(Enums.Skill.ENGINEER, engineerSkill);
        _player=Player.getInstance()
                .setName(name)
                .setCredits(1000)
                .setShip(new Gnat())
                .setStats(stats);
        System.out.println("Created character with name: "+_player.getName()+" and stats as follows:  fighter->"+_player.getStats().get(Enums.Skill.FIGHTER)+" pilot->"+_player.getStats().get(Enums.Skill.PILOT)+" trader->"+_player.getStats().get(Enums.Skill.TRADER)+" engineer->"+_player.getStats().get(Enums.Skill.ENGINEER)+".  This player has "+_player.getCredits()+" credits and ship: " + _player.getShip().toString());
        com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper.generateOcean();


        //Code to pick a random port on a random island to assign it to the player
        Ocean ocean = Ocean.getInstance();
        List<Island> islands = ocean.getIslands();
        Random random = new Random();
        int pickIsland = random.nextInt(islands.size());
        Island island = islands.get(pickIsland);
        int pickPort = random.nextInt(island.getPorts().size());
        Port port = island.getPorts().get(pickPort);

        _player.setCurrentPort(port);
    }
    public static void GameScreen(String name, int coins){
        String[] args=new String[2];
        args[0]=name;
        args[1]=Integer.toString(coins);
        GameScreen.main(args);
    }

    public static void MarketplaceScreen(){
        String[] args=new String[0];
        MarketplaceScreen.main(args);
    }

    public static void SelectPortScreen(){
        SelectPortScreen.main(new String[1]);
        GameScreen.close();
    }
}
