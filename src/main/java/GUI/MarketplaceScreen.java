package GUI;

import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JTextPane currentPortInfoPane;
    private JButton returnToMainButton;
    private JList invQuantityList;
    private JList sellPriceList;
    private JList sellSpinnerList;
    private JList marketQuantityList;
    private JList buyPriceList;
    private JList buySpinnerList;

    String[] _goodsArray;
    int[] _invQuantityArray;
    int[] _sellPriceArray;
    int[] _marketQuantityArray;
    int[] _buyPriceArray;

    private JPanel _panel;
    private static JFrame frame;


    private static Player _player;

    public MarketplaceScreen() {
        _goodsRegistry = GoodsRegistry.getInstance();
        _player = Player.getInstance();

        returnToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper.displayGameScreen();
            }
        });

        updateGoodsLists();
        //updateMarketPrices();
        //updateMarketQuantities();
    }


    public MarketplaceScreen(String[] goodsArray, int[] invQuantityArray, int[] sellPriceArray, int[] marketQuantityArray, int[] buyPriceArray) {
        _goodsArray = goodsArray;
        _invQuantityArray = invQuantityArray;
        _sellPriceArray = sellPriceArray;
        _marketQuantityArray = marketQuantityArray;
        _buyPriceArray = buyPriceArray;



        updateGoodsLists();
        updateMarketPrices();
        updateMarketQuantities();
    }


    public void updateGoodsLists(){
        goodsList.setListData(GuiArbiter.getGoodsList());
    }

    public void updateMarketPrices(){
        sellPriceList.setListData(getMarketPrices());
    }

    public void updateMarketQuantities(){
        marketQuantityList.setListData(getMarketQuantities());
    }

    private static GoodsRegistry _goodsRegistry;
    private static String[] goodsArray;
    private static String[] marketPriceArray;
    private static String[] marketQuantityArray;

    public String[] getMarketPrices(){
        Port _port = _player.getCurrentPort();
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        for (int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            Integer _price = _localMarket.get(good).get(Enums.MarketValues.PRICE);
            if (_price == null){

            }
            else{
                marketPriceArray[i] = _price.toString();
            }
        }
        return marketPriceArray;
    }

    public String[] getMarketQuantities(){
        Port _port = _player.getCurrentPort();
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = _port.getLocalMarket();
        for (int i = 0; i < _goodsRegistry.getGoods().size(); i++){
            Good good = _goodsRegistry.getGoods().get(i);
            Integer _quantity = _localMarket.get(good).get(Enums.MarketValues.QUANTITY);
            if (_quantity == null){

            }
            else{
                marketQuantityArray[i] = _quantity.toString();
            }
        }
        return marketQuantityArray;
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
        currentPortInfoPane = new JTextPane();
        currentPortInfoPane.setText("");
        _panel.add(currentPortInfoPane, cc.xy(1, 11, CellConstraints.FILL, CellConstraints.FILL));
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
        sellSpinnerList = new JList();
        _panel.add(sellSpinnerList, cc.xy(7, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        buyPriceList = new JList();
        _panel.add(buyPriceList, cc.xy(11, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        buySpinnerList = new JList();
        _panel.add(buySpinnerList, cc.xy(13, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        invQuantityList = new JList();
        _panel.add(invQuantityList, cc.xy(3, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return _panel;
    }
}
