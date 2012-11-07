package GUI;

import com.TableFlip.SpaceTrader.DataStructure.SparseArray.Node;
import com.TableFlip.SpaceTrader.DataStructure.SparseArray.SparseArray;
import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.*;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.TableFlip.SpaceTrader.Service.SaveMaker;
import com.TableFlip.SpaceTrader.Service.ShipFactory;

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

        ocean.setHighlightedPort(_player.getTargetPort()); //sets chosen target port as highlighted port

        GameScreen();
    }

    public static void GameScreen(){
        String[] args=new String[0];
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

    public static Port getHighlightedPort(){
        Ocean ocean = Ocean.getInstance();
        return ocean.getHighlightedPort();
    }

    public static int setTargetPort(){
        Ocean ocean = Ocean.getInstance();
        Port highlightedPort = ocean.getHighlightedPort();
        Player player = Player.getInstance();
        if (calculateDistance(highlightedPort.getCoordinates(), player.getCurrentPort().getCoordinates()) > getCurrentSupplies()){
            return 1;
        }
        if (player.getCurrentPort().equals(highlightedPort)){
            return 2;
        }

        player.setTargetPort(highlightedPort);
        return 0;
    }

    public static int calculateDistance(Coordinates port1, Coordinates port2){
        double distance = Math.sqrt( Math.pow((port1.getxPos()-port2.getxPos()),2) +
                Math.pow((port1.getyPos()-port2.getyPos()),2) );
        int d = (int)distance;
        return d;
    }

    public static void up(){
        Ocean ocean = Ocean.getInstance();
        Node<Port> highlightedNode = ocean.getPortSparseArray().findNodeAt(ocean.getHighlightedPort().getCoordinates().getyPos(), ocean.getHighlightedPort().getCoordinates().getxPos());
        ocean.setHighlightedPort(ocean.getPortSparseArray().moveUp(highlightedNode));
    }

    public static void down(){
        Ocean ocean = Ocean.getInstance();
        Node<Port> highlightedNode = ocean.getPortSparseArray().findNodeAt(ocean.getHighlightedPort().getCoordinates().getyPos(), ocean.getHighlightedPort().getCoordinates().getxPos());
        ocean.setHighlightedPort(ocean.getPortSparseArray().moveDown(highlightedNode));
    }

    public static void right(){
        Ocean ocean = Ocean.getInstance();
        Node<Port> highlightedNode = ocean.getPortSparseArray().findNodeAt(ocean.getHighlightedPort().getCoordinates().getyPos(), ocean.getHighlightedPort().getCoordinates().getxPos());
        ocean.setHighlightedPort(ocean.getPortSparseArray().moveRight(highlightedNode));
    }

    public static void left(){
        Ocean ocean = Ocean.getInstance();
        Node<Port> highlightedNode = ocean.getPortSparseArray().findNodeAt(ocean.getHighlightedPort().getCoordinates().getyPos(), ocean.getHighlightedPort().getCoordinates().getxPos());
        ocean.setHighlightedPort(ocean.getPortSparseArray().moveLeft(highlightedNode));
    }

    public static Map<Good, HashMap<Enums.MarketValues, Integer>> getLocalMarket(){
        Port port = getCurrentPort();
        return port.getLocalMarket();
    }

    public static int getGoodQuantity(Good good){
        Player player = Player.getInstance();
        return player.getShip().getCargo().get(good);
    }

    public static boolean playerBuy(Good good, int amount){
        Player player = Player.getInstance();
        return player.buy(good, amount);
    }

    public static boolean playerSell(Good good, int amount){
        Player player = Player.getInstance();
        return player.sell(good, amount);
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
     * Displays and formats the target port
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

    /**
     * Displays and formats basic player information for the main screen
     */
    public static void updatePlayerInfo(JLabel playerInfo){
        String name = Player.getInstance().getName();
        String coins = Integer.toString(Player.getInstance().getCredits());
        String cargoSpace = Integer.toString(Player.getInstance().getShip().getCargoSpace());
        playerInfo.setText("Name: " + name + " You have: " + coins + " coins and " + cargoSpace + " cargo spaces left.");
    }

    /**
     * Displays and formats the status of the current ship for the main screen
     */
    public static void updateShipStatus( JLabel shipStatusLabel){
        Player player = Player.getInstance();
        Ship ship = player.getShip();
        shipStatusLabel.setText("<html>Ship Status:<br />" +
            "<br />Name: " + ship.getName() +
            "<br />Max Supplies Number: " + ship.getSuppliesMax() +
            "<br />Weapons Slots: " + ship.getWeaponSlots() +
            "<br />Armor Slots: " + ship.getArmorSlots() +
            "<br />Crew Slots: " + ship.getCrewSlots() +
            "<br />Tool Slots: " + ship.getToolSlots() +
            "</html>");
    }

    /**
     * gets an ArrayList of ONLY goods that have a non-null quantity at the current port
     *
     * @return a String[] of the names of goods of non-null quantity
     */
    public static String getGoodsList(ArrayList goodsArrayList) {
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = getLocalMarket();
        GoodsRegistry _goodsRegistry = GoodsRegistry.getInstance();
        String goodsNames = "<html>";
        for (int i = 0; i < goodsRegSize(); i++) {
            Good good = getGood(i);
            if (_localMarket.get(good) != null) {
                goodsNames = goodsNames + good.getName() + "<br /><br />";
                goodsArrayList.add(good);
            }
        }
        goodsNames = goodsNames + "</html>";
        return goodsNames;
    }

    /**
     * getCargoQuantities
     * gets the quantities of ONLY the goods in the ship's cargo that are ALSO found in the target port
     * and stores it into an ArrayList _cargoQuantityArray
     *
     * @return the String[] version of _cargoQuantityArray so that its respective JList can read it
     */
    public static String getCargoQuantities() {
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = getLocalMarket();
        String _cargoQuantityString = "<html>";
        for (int i = 0; i < goodsRegSize(); i++) {
            Good good = getGood(i);
            // if a good in the goodsRegistry is also found in the _localMarket and is not null,
            // then add the name of the good to our ArrayList _cargoQuantityArray
            if (_localMarket.get(good) != null) {
                _cargoQuantityString = _cargoQuantityString + getGoodQuantity(good) + "<br /><br />";
            }
        }
        // returns a String for JLabel to read
        _cargoQuantityString = _cargoQuantityString + "</html>";
        return _cargoQuantityString;
    }

    /**
     * gets a String[] of the market prices found in the localMarket
     *
     * @return a String[] of prices for buying/selling a good at this particular port in the order of _goodsRegistry
     */
    public static String getMarketPrices() {
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = GuiArbiter.getLocalMarket();
        String marketPriceString = "<html>";
        for (int i = 0; i < goodsRegSize(); i++) {
            Good good = getGood(i);
            if (_localMarket.get(good) != null) {
                Integer _price = _localMarket.get(good).get(Enums.MarketValues.PRICE);
                marketPriceString = marketPriceString + _price.toString() + "<br /><br />";
            }
        }
        marketPriceString = marketPriceString + "</html>";
        return marketPriceString;
    }

    /**
     * gets a String[] of the market quantities found in the localMarket
     *
     * @return a String[] of quantities of goods at this particular port in the order of the _goodsRegistry
     */
    public static String getMarketQuantities() {
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = getLocalMarket();
        String marketQuantityString = "<html>";
        for (int i = 0; i < goodsRegSize(); i++) {
            Good good = getGood(i);
            if (_localMarket.get(good) != null) {
                Integer _quantity = _localMarket.get(good).get(Enums.MarketValues.QUANTITY);
                marketQuantityString = marketQuantityString + _quantity.toString() + "<br /><br />";
            }
        }
        marketQuantityString = marketQuantityString + "</html>";
        return marketQuantityString;
    }

    public static int goodsRegSize(){
        GoodsRegistry goodsRegistry = GoodsRegistry.getInstance();
        return goodsRegistry.getGoods().size();
    }

    public static Good getGood(int i){
        return GoodsRegistry.getInstance().getGoods().get(i);
    }

    public static void fly(){
        Player.getInstance().getShip().fly();
    }

    public static void save(){
        SaveMaker.getInstance().save();
    }

}
