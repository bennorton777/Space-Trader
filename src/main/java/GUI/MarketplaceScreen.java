package GUI;

import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Dannielle
 * Date: 10/23/12
 * Time: 6:26 PM
 *
 * MarketplaceScreen
 * The GUI that handles the Marketplace. Currently organizes a lot of the goods found in the target port
 * (via the GoodsRegistry) into a format readable for the GUI.
 * Displays the Player's goods, its respective quantities and selling prices, and the target port's
 * own market quantities and buying prices. GUI for the player to buy and sell.
 * Also displays the current port.
 */
public class MarketplaceScreen {
    private JLabel MarketplaceLabel;
    private JLabel playerInfoLabel;
    private JList goodsList;
    private JLabel goods;
    private JLabel inventoryLabel;
    private JLabel marketLabel;
    private JButton sellButton;
    private JButton buyButton;
    private JButton returnToMainButton;
    private JList invQuantityList;
    private JList sellPriceList;
    private JList marketQuantityList;
    private JList buyPriceList;
    private JPanel _panel;
    private JPanel buySpinnerPanel;
    private JPanel sellSpinnerPanel;
    private JLabel currentPortLabel;
    private JLabel targetPortLabel;
    private static JFrame frame;

    private static Player _player;
    private static GoodsRegistry _goodsRegistry;
    private static Port _port;
    private static Port _targetPort;

    private static ArrayList<Good> goodsArrayList;
    private ArrayList<JSpinner> sellSpinners;
    private ArrayList<JSpinner> buySpinners;

    /**
     * Empty constructor. The Marketplace can access all that it needs to know through the Player singleton or the
     * GoodsRegistry singleton.
     * Contains button listeners and calls methods to populate the JLists and JPanels.
     */
    public MarketplaceScreen() {
        _goodsRegistry = GoodsRegistry.getInstance();
        _player = Player.getInstance();
        _port = _player.getCurrentPort();
        _targetPort = _player.getTargetPort();
        goodsArrayList = new ArrayList<Good>();

        returnToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper.displayGameScreen();
            }
        });

        //All of the GUI JPanels and Lists to populate!
        updatePlayerInfoLabel();
        updateGoodsLists();
        updateCargoPrices();
        updateCargoQuantities();
        updateMarketPrices();
        updateMarketQuantities();
        updateBuySpinners();
        updateSellSpinners();
        updateTargetPortInfo();
        updateCurrentPortInfo();

        //Button Listeners
        //Goes through the ArrayList that holds the spinners, iterates through them.
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < buySpinners.size(); i++){
                    JSpinner spinner = buySpinners.get(i);
                    //if the spinner is greater than 0, then it is relevant for us to buy!
                    //we use its index to choose the corresponding Good in the goodsArrayList
                    if ((Integer) spinner.getValue() > 0){
                        _player.buy(goodsArrayList.get(i), (Integer) spinner.getValue());
                    }
                }
            //update GUI panels only after all of the purchases have been made
            updateMarketQuantities();
            updateCargoQuantities();
            updatePlayerInfoLabel();
            updateBuySpinners();
            updateSellSpinners();
            }
        });

        //Goes through the ArrayList that holds the spinners, iterates through them.
        sellButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(int i = 0; i < sellSpinners.size(); i++){
                    JSpinner spinner = sellSpinners.get(i);
                    //if the spinner is greater than 0, then it is relevant for us to sell!
                    //we use its index to choose the corresponding Good in the goodsArrayList
                    if ((Integer) spinner.getValue() > 0){
                        _player.sell(goodsArrayList.get(i), (Integer) spinner.getValue());

                    }
                }
                //update GUI panels only after all of the transactions have been made
                updateMarketQuantities();
                updateCargoQuantities();
                updatePlayerInfoLabel();
                updateBuySpinners();
                updateSellSpinners();
            }
        });
    }

    /**
     * updating GUI panels is made flexible if its innerlogic is to be transferred over to GUIArbiter
     */
    public void updatePlayerInfoLabel(){
        playerInfoLabel.setText("You have: " + _player.getCredits() + " coins and " + _player.getShip().getCargoSpace() + " cargo spaces left.");
    }

    public void updateGoodsLists(){
        goodsList.setListData(getGoodsList());
    }

    public void updateMarketPrices(){
        buyPriceList.setListData(getMarketPrices());
    }

    public void updateMarketQuantities(){
        marketQuantityList.setListData(getMarketQuantities());
    }

    public void updateCargoPrices(){
        sellPriceList.setListData(getMarketPrices());
    }

    public void updateCargoQuantities(){
        invQuantityList.setListData(getCargoQuantities());
    }

    /**
     * getCargoQuantities
     * gets the quantities of ONLY the goods in the ship's cargo that are ALSO found in the target port
     * and stores it into an ArrayList _cargoQuantityArray
     *
     * @return the String[] version of _cargoQuantityArray so that its respective JList can read it
     */
    public String[] getCargoQuantities(){
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        ArrayList<String> _cargoQuantityArray = new ArrayList<String>();
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            // if a good in the goodsRegistry is also found in the _localMarket and is not null,
            // then add the name of the good to our ArrayList _cargoQuantityArray
            if (_localMarket.get(good) != null){
                _cargoQuantityArray.add(_player.getShip().getCargo().get(good).toString());
            }
        }
        // returns a String[] for JList to read
        return _cargoQuantityArray.toArray(new String[0]);
    }

    /**
     * Creates the appropriate JSpinners in the appropriate amount.
     * Clears the panel and resets/re-adds them for each transaction.
     * Stores the spinners in an ArrayList.
     */
    public void updateBuySpinners(){
        buySpinners = new ArrayList<JSpinner>();
        // resets the panel from previous transactions
        buySpinnerPanel.removeAll();
        // aligns all spinners vertically on top of each other
        buySpinnerPanel.setLayout(new BoxLayout(buySpinnerPanel, BoxLayout.Y_AXIS));
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                // spinner has initial value 0, minimum 0, maximum is the amount of the good in the _localMarket, goes up by 1
                SpinnerModel model = new SpinnerNumberModel(0, 0, (int) _localMarket.get(good).get(Enums.MarketValues.QUANTITY), 1);
                JSpinner jsp = new JSpinner(model);
                jsp.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JSpinner spinner = (JSpinner)e.getSource();
                        spinner.setValue((Integer) spinner.getValue());
                    }
                });
                jsp.setValue(0);
                buySpinners.add(jsp);
                buySpinnerPanel.add(jsp);
            }
        }
    }

    /**
     * Creates the appropriate JSpinners in the appropriate amount.
     * Clears the panel and resets/re-adds them for each transaction.
     * Stores the spinners in an ArrayList.
     */
    public void updateSellSpinners(){
        sellSpinners = new ArrayList<JSpinner>();
        // resets the panel from previous transactions
        sellSpinnerPanel.removeAll();
        // aligns all spinners vertically on top of each other
        sellSpinnerPanel.setLayout(new BoxLayout(sellSpinnerPanel,BoxLayout.Y_AXIS));
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                // spinner has initial value 0, minimum 0, maximum is the amount of the good in the _localMarket, goes up by 1
                SpinnerModel model = new SpinnerNumberModel(0, 0, (int) _player.getShip().getCargo().get(good), 1);
                JSpinner jsp = new JSpinner(model);
                jsp.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JSpinner spinner = (JSpinner)e.getSource();
                        spinner.setValue((Integer) spinner.getValue());
                    }
                });
                jsp.setValue(0);
                sellSpinners.add(jsp);
                sellSpinnerPanel.add(jsp);

            }
        }
    }

    /**
     * Displays and formats the current port
     */
    public void updateCurrentPortInfo() {
        currentPortLabel.setText("<html>Current Port Details<br /><br />" +
                "Name: " + _port.getName() + "<br />" +
                "Tech Level: " + _port.getTechLevel() + "<br />" +
                "Resource: " + _port.getResources() + "<br />" +
                "Coordinates: " + _port.getCoordinates() + "<br /></html>");
    }

    /**
     * Displays and formats the target port (to be removed)
     */
    public void updateTargetPortInfo() {
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
     * gets an ArrayList of ONLY goods that have a non-null quantity at the current port
     * @return a String[] of the names of goods of non-null quantity
     */
    public static String[] getGoodsList(){
        Port _port = _player.getCurrentPort();
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        String[] goodsArray = new String[_goodsRegistry.getGoods().size()];
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                goodsArray[i] = good.getName();
                goodsArrayList.add(good);
            }
        }
        return goodsArray;
    }

    /**
     * gets a String[] of the market prices found in the localMarket
     * @return a String[] of prices for buying/selling a good at this particular port in the order of _goodsRegistry
     */
    public String[] getMarketPrices(){
        Port _port = _player.getCurrentPort();
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        String[] marketPriceArray = new String[_goodsRegistry.getGoods().size()];
        for (int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                Integer _price = _localMarket.get(good).get(Enums.MarketValues.PRICE);
                marketPriceArray[i] = _price.toString();
            }
        }
        return marketPriceArray;
    }

    /**
     * gets a String[] of the market quantities found in the localMarket
     * @return a String[] of quantities of goods at this particular port in the order of the _goodsRegistry
     */
    public String[] getMarketQuantities(){
        Port _port = _player.getCurrentPort();
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        ArrayList<String> marketQuantityArray = new ArrayList<String>();
        for (int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                Integer _quantity = _localMarket.get(good).get(Enums.MarketValues.QUANTITY);
                marketQuantityArray.add(_quantity.toString());
            }
        }
        return marketQuantityArray.toArray(new String[0]);
    }


    public static void main(String[] args) {
        frame = new JFrame("Marketplace");
        frame.setContentPane(new MarketplaceScreen()._panel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        _panel = new JPanel();
        _panel.setLayout(new FormLayout("fill:10px:grow,left:7dlu:noGrow,fill:10px:grow,left:11dlu:noGrow,fill:187px:noGrow,left:13dlu:noGrow,fill:139px:noGrow,left:27dlu:noGrow,fill:max(d;4px):noGrow,left:14dlu:noGrow,fill:69px:noGrow,left:4dlu:noGrow,fill:96px:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
        _panel.setEnabled(false);
        MarketplaceLabel = new JLabel();
        MarketplaceLabel.setText("MARKETPLACE");
        CellConstraints cc = new CellConstraints();
        _panel.add(MarketplaceLabel, cc.xy(1, 1));
        playerInfoLabel = new JLabel();
        playerInfoLabel.setText("You have: coins");
        _panel.add(playerInfoLabel, cc.xy(1, 3));
        goods = new JLabel();
        goods.setText("Goods");
        _panel.add(goods, cc.xy(1, 5));
        goodsList = new JList();
        _panel.add(goodsList, cc.xy(1, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        inventoryLabel = new JLabel();
        inventoryLabel.setText("Inventory");
        _panel.add(inventoryLabel, cc.xy(3, 5));
        marketLabel = new JLabel();
        marketLabel.setText("Market");
        _panel.add(marketLabel, cc.xy(9, 5));
        sellButton = new JButton();
        sellButton.setText("Sell");
        _panel.add(sellButton, cc.xy(3, 9));
        buyButton = new JButton();
        buyButton.setText("Buy");
        _panel.add(buyButton, cc.xy(9, 9));
        final JTextPane textPane1 = new JTextPane();
        textPane1.setText("");
        _panel.add(textPane1, cc.xy(9, 11, CellConstraints.FILL, CellConstraints.FILL));
        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to Main Screen");
        _panel.add(returnToMainButton, cc.xy(3, 13));
        sellPriceList = new JList();
        _panel.add(sellPriceList, cc.xy(5, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        marketQuantityList = new JList();
        _panel.add(marketQuantityList, cc.xy(9, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        buyPriceList = new JList();
        _panel.add(buyPriceList, cc.xy(11, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        invQuantityList = new JList();
        _panel.add(invQuantityList, cc.xy(3, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        MarketplaceLabel = new JLabel();
        MarketplaceLabel.setText("MARKETPLACE");
        _panel.add(MarketplaceLabel, cc.xy(1, 1));
        playerInfoLabel = new JLabel();
        playerInfoLabel.setText("You have: coins");
        _panel.add(playerInfoLabel, cc.xy(1, 3));
        goods = new JLabel();
        goods.setText("Goods");
        _panel.add(goods, cc.xy(1, 5));
        goodsList = new JList();
        _panel.add(goodsList, cc.xy(1, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        inventoryLabel = new JLabel();
        inventoryLabel.setText("Inventory");
        _panel.add(inventoryLabel, cc.xy(3, 5));
        marketLabel = new JLabel();
        marketLabel.setText("Market");
        _panel.add(marketLabel, cc.xy(5, 5));
        sellButton = new JButton();
        sellButton.setText("Sell");
        _panel.add(sellButton, cc.xy(3, 9));
        buyButton = new JButton();
        buyButton.setText("Buy");
        _panel.add(buyButton, cc.xy(5, 9));
        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to Main Screen");
        _panel.add(returnToMainButton, cc.xy(3, 13));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return _panel;
    }
}
