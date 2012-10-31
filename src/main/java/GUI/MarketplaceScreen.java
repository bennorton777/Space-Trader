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
 * User: DelRosario
 * Date: 10/23/12
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
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

    private ArrayList<JSpinner> sellSpinners;
    private ArrayList<JSpinner> buySpinners;

    private JPanel _panel;
    private JPanel buySpinnerPanel;
    private JPanel sellSpinnerPanel;
    private JLabel currentPortLabel;
    private JLabel targetPortLabel;
    private static JFrame frame;


    private static Player _player;

    private static Port _port;
    private static Port _targetPort;

    private static ArrayList<Good> goodsArrayList;

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

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < buySpinners.size(); i++){
                    JSpinner spinner = buySpinners.get(i);
//                    System.out.println("The spinner in the buySpinners at index" + i + " is value: " + spinner.getValue().toString());
                    if ((Integer) spinner.getValue() > 0){
                        updateMarketQuantities();
                        updateCargoQuantities();
                        updatePlayerInfoLabel();
                        System.out.println("After: ");
                        _player.buy(goodsArrayList.get(i), (Integer) spinner.getValue());
                        System.out.println("The good associated with the spinner is: " + goodsArrayList.get(i).getName());
                        System.out.println("The quantity associated with the spinner is: " + spinner.getValue().toString());
                        System.out.println("We are in the for loop!");
                        updateMarketQuantities();
                        updateCargoQuantities();
                        updatePlayerInfoLabel();
                    }
                }
            }
        });
    }

    public void updatePlayerInfoLabel(){
        playerInfoLabel.setText("You have: " + _player.getCredits() + " coins");
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

    public String[] getCargoQuantities(){
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        ArrayList<String> _cargoQuantityArray = new ArrayList<String>();
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                _cargoQuantityArray.add(_player.getShip().getCargo().get(good).toString());
            }
        }
        String[] string = _cargoQuantityArray.toArray(new String[0]);
        System.out.println(Arrays.toString(string));
        return string;
    }

    public void updateBuySpinners(){
        buySpinners = new ArrayList<JSpinner>();
        buySpinnerPanel.setLayout(new BoxLayout(buySpinnerPanel, BoxLayout.Y_AXIS));
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                SpinnerModel model = new SpinnerNumberModel(0, 0, (int) _localMarket.get(good).get(Enums.MarketValues.QUANTITY), 1);
                JSpinner jsp = new JSpinner(model);
                jsp.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JSpinner spinner = (JSpinner)e.getSource();
                        System.out.println("The buying spinner value is " + spinner.getValue());
                        spinner.setValue((Integer) spinner.getValue());
                    }
                });
                jsp.setValue(0);
                //jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
                buySpinners.add(jsp);
                buySpinnerPanel.add(jsp);
            }
        }
    }

    public void updateSellSpinners(){
        sellSpinners = new ArrayList<JSpinner>();
        sellSpinnerPanel.setLayout(new BoxLayout(sellSpinnerPanel,BoxLayout.Y_AXIS));
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        for(int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            if (_localMarket.get(good) != null){
                SpinnerModel model = new SpinnerNumberModel(0, 0, (int) _player.getShip().getCargo().get(good), 1);
                JSpinner jsp = new JSpinner(model);
                jsp.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JSpinner spinner = (JSpinner)e.getSource();
                        System.out.println("Makario says: " + spinner.getValue());
                        spinner.setValue((Integer) spinner.getValue());
                    }
                });
                jsp.setValue(0);
                sellSpinners.add(jsp);
                sellSpinnerPanel.add(jsp);

            }
        }
    }

    public void updateCurrentPortInfo() {
        currentPortLabel.setText("<html>Current Port Details<br /><br />" +
                "Name: " + _port.getName() + "<br />" +
                "Tech Level: " + _port.getTechLevel() + "<br />" +
                "Resource: " + _port.getResources() + "<br />" +
                "Coordinates: " + _port.getCoordinates() + "<br /></html>");
    }

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


    private static GoodsRegistry _goodsRegistry;


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
        frame = new JFrame("Main Screen");
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
