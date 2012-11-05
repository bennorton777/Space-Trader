package GUI;

import com.TableFlip.SpaceTrader.DataStructure.SparseArray.Node;
import com.TableFlip.SpaceTrader.DataStructure.SparseArray.SparseArray;
import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Service.ShipFactory;
import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;

import javax.swing.*;
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
        Player _player=Player.getInstance()
                .setName(name)
                .setCredits(1000)
                .setShip(ShipFactory.makeShip("Sloop"))
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

        Node<Port> targetNode = ocean.getPortSparseArray().findNodeAt(_player.getCurrentPort().getCoordinates().getyPos(), _player.getCurrentPort().getCoordinates().getxPos());

        //This is all stuff to pick the closest port to choose as the initial target port.
        Port upCandidatePort = ocean.getPortSparseArray().moveUp(targetNode);
        Port downCandidatePort = ocean.getPortSparseArray().moveDown(targetNode);
        Port rightCandidatePort = ocean.getPortSparseArray().moveRight(targetNode);
        Port leftCandidatePort = ocean.getPortSparseArray().moveLeft(targetNode);

        double upDist = Math.sqrt( Math.pow((port.getCoordinates().getxPos()-upCandidatePort.getCoordinates().getxPos()),2) +
                Math.pow((port.getCoordinates().getyPos()-upCandidatePort.getCoordinates().getyPos()),2) );
        double downDist = Math.sqrt( Math.pow((port.getCoordinates().getxPos()-downCandidatePort.getCoordinates().getxPos()),2) +
                Math.pow((port.getCoordinates().getyPos()-downCandidatePort.getCoordinates().getyPos()),2) );
        double rightDist = Math.sqrt( Math.pow((port.getCoordinates().getxPos()-rightCandidatePort.getCoordinates().getxPos()),2) +
                Math.pow((port.getCoordinates().getyPos()-rightCandidatePort.getCoordinates().getyPos()),2) );
        double leftDist = Math.sqrt( Math.pow((port.getCoordinates().getxPos()-leftCandidatePort.getCoordinates().getxPos()),2) +
                Math.pow((port.getCoordinates().getyPos()-leftCandidatePort.getCoordinates().getyPos()),2) );

        if(upDist<downDist && upDist<leftDist && upDist<rightDist){
            _player.setTargetPort(upCandidatePort);
        }
        else if(downDist<upDist && downDist<leftDist && downDist<rightDist){
            _player.setTargetPort(downCandidatePort);
        }
        else if(rightDist<downDist && rightDist<leftDist && rightDist<upDist){
            _player.setTargetPort(rightCandidatePort);
        }
        else{
            _player.setTargetPort(leftCandidatePort);
        }

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

    public static List<Coordinates> getPortCoordinates(){
        List<Coordinates> coords =new ArrayList<Coordinates>();
        for (Island i : Ocean.getInstance().getIslands()){
            for (Port p : i.getPorts()){
                coords.add(p.getCoordinates());
            }
        }
        return coords;
    }

    public static Port getCurrentPort(){
        Player player = Player.getInstance();
        return player.getCurrentPort();
    }

    public static int getCurrentSupplies() {
        Player player = Player.getInstance();
        return player.getShip().getSuppliesRemaining();
    }

    public static Port getTargetPort(){
        Player player = Player.getInstance();
        return player.getTargetPort();
    }

    public static void up(){
        Ocean ocean = Ocean.getInstance();
        Player player = Player.getInstance();
        Node<Port> targetNode = ocean.getPortSparseArray().findNodeAt(player.getTargetPort().getCoordinates().getyPos(), player.getTargetPort().getCoordinates().getxPos());
        player.setTargetPort(ocean.getPortSparseArray().moveUp(targetNode));
    }

    public static void down(){
        Ocean ocean = Ocean.getInstance();
        Player player = Player.getInstance();
        Node<Port> targetNode = ocean.getPortSparseArray().findNodeAt(player.getTargetPort().getCoordinates().getyPos(), player.getTargetPort().getCoordinates().getxPos());
        player.setTargetPort(ocean.getPortSparseArray().moveDown(targetNode));
    }

    public static void right(){
        Ocean ocean = Ocean.getInstance();
        Player player = Player.getInstance();
        Node<Port> targetNode = ocean.getPortSparseArray().findNodeAt(player.getTargetPort().getCoordinates().getyPos(), player.getTargetPort().getCoordinates().getxPos());
        player.setTargetPort(ocean.getPortSparseArray().moveRight(targetNode));
    }

    public static void left(){
        Ocean ocean = Ocean.getInstance();
        Player player = Player.getInstance();
        Node<Port> targetNode = ocean.getPortSparseArray().findNodeAt(player.getTargetPort().getCoordinates().getyPos(), player.getTargetPort().getCoordinates().getxPos());
        player.setTargetPort(ocean.getPortSparseArray().moveLeft(targetNode));
    }

    /**
     * Displays and formats the current port
     */
    public static void updateCurrentPortInfo(JLabel currentPortLabel) {
        Player _player = Player.getInstance();
        Port _port = _player.getCurrentPort();
        currentPortLabel.setText("<html>Current Port Details<br /><br />" +
                "Name: " + _port.getName() + "<br />" +
                "Tech Level: " + _port.getTechLevel() + "<br />" +
                "Resource: " + _port.getResources() + "<br />" +
                "Coordinates: " + _port.getCoordinates() + "<br /></html>");
    }

    /**
     * Displays and formats the target port (to be removed)
     */
    public static void updateTargetPortInfo(JLabel targetPortLabel) {
        Player _player = Player.getInstance();
        Port _targetPort = _player.getTargetPort();
        if (_targetPort != null) {
            targetPortLabel.setText("<html>Target Port Details<br /><br />" +
                    "Name: " + _targetPort.getName() + "<br />" +
                    "Tech Level: " + _targetPort.getTechLevel() + "<br />" +
                    "Resource: " + _targetPort.getResources() + "<br />" +
                    "Coordinates: " + _targetPort.getCoordinates() + "<br /></html>");
        } else {
            targetPortLabel.setText("<html>Target Port Details<br /><br />" +
                    "Name: ---<br />" +
                    "Tech Level: ---<br />" +
                    "Resource: ---<br />" +
                    "Coordinates: ---<br /></html>");
        }
    }
}
