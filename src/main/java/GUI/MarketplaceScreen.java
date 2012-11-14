package GUI;

import com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import com.intellij.uiDesigner.core.Spacer;
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
 * <p/>
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
    private JLabel goodsLabel;
    private JLabel invQuantityLabel;
    private JLabel sellPriceLabel;
    private JLabel buyPriceLabel;
    private JLabel marketQuantityLabel;
    private static JFrame frame;

    private static ArrayList<Good> goodsArrayList;
    private ArrayList<JSpinner> sellSpinners;
    private ArrayList<JSpinner> buySpinners;

    /**
     * Empty constructor. The Marketplace can access all that it needs to know through the Player singleton or the
     * GoodsRegistry singleton.
     * Contains button listeners and calls methods to populate the JLists and JPanels.
     */
    public MarketplaceScreen() {
        goodsArrayList = new ArrayList<Good>();

        returnToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GuiArbiter.GameScreen();
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
        //updateHighlightedPortInfo();
        updateCurrentPortInfo();

        //Button Listeners
        //Goes through the ArrayList that holds the spinners, iterates through them.
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < buySpinners.size(); i++) {
                    JSpinner spinner = buySpinners.get(i);
                    //if the spinner is greater than 0, then it is relevant for us to buy!
                    //we use its index to choose the corresponding Good in the goodsArrayList
                    if ((Integer) spinner.getValue() > 0) {
                        Boolean f = GuiArbiter.playerBuy(goodsArrayList.get(i), (Integer) spinner.getValue());
                        if (f == false) {
                            buySpinnerPanel.removeAll();
                        }
                    }
                }
                //update GUI panels only after all of the purchases have been made
                updateMarketQuantities();
                updateCargoQuantities();
                updatePlayerInfoLabel();
                updateBuySpinners();
                updateSellSpinners();
                frame.repaint();
                frame.validate();
            }
        });

        //Goes through the ArrayList that holds the spinners, iterates through them.
        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < sellSpinners.size(); i++) {
                    JSpinner spinner = sellSpinners.get(i);
                    //if the spinner is greater than 0, then it is relevant for us to sell!
                    //we use its index to choose the corresponding Good in the goodsArrayList
                    if ((Integer) spinner.getValue() > 0) {
                        GuiArbiter.playerSell(goodsArrayList.get(i), (Integer) spinner.getValue());
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
     * updating GUI panels is made flexible if its inner logic is to be transferred over to GuiArbiter
     */
    public void updatePlayerInfoLabel() {
        GuiArbiter.updatePlayerInfo(playerInfoLabel);
    }

    public void updateGoodsLists() {
        goodsLabel.setText(GuiArbiter.getGoodsList(goodsArrayList));
    }

    public void updateMarketPrices() {
        buyPriceLabel.setText(GuiArbiter.getMarketPrices());
    }

    public void updateMarketQuantities() {
        marketQuantityLabel.setText(GuiArbiter.getMarketQuantities());
    }

    public void updateCargoPrices() {
        sellPriceLabel.setText(GuiArbiter.getMarketPrices());
    }

    public void updateCargoQuantities() {
        invQuantityLabel.setText(GuiArbiter.getCargoQuantities());
    }

    /**
     * Creates the appropriate JSpinners in the appropriate amount.
     * Clears the panel and resets/re-adds them for each transaction.
     * Stores the spinners in an ArrayList.
     */
    public void updateBuySpinners() {
        // resets the panel from previous transactions
        buySpinnerPanel.removeAll();
        // aligns all spinners vertically on top of each other
        buySpinners = new ArrayList<JSpinner>();
        buySpinnerPanel.setLayout(new BoxLayout(buySpinnerPanel, BoxLayout.Y_AXIS));
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = GuiArbiter.getLocalMarket();
        for (int i = 0; i < GuiArbiter.goodsRegSize(); i++) {
            Good good = GuiArbiter.getGood(i);
            if (_localMarket.get(good) != null) {
                // spinner has initial value 0, minimum 0, maximum is the amount of the good in the _localMarket, goes up by 1
                SpinnerModel model = new SpinnerNumberModel(0, 0, (int) _localMarket.get(good).get(Enums.MarketValues.QUANTITY), 1);
                JSpinner jsp = new JSpinner(model);
                jsp.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JSpinner spinner = (JSpinner) e.getSource();
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
    public void updateSellSpinners() {
        sellSpinners = new ArrayList<JSpinner>();
        // resets the panel from previous transactions
        sellSpinnerPanel.removeAll();
        // aligns all spinners vertically on top of each other
        sellSpinnerPanel.setLayout(new BoxLayout(sellSpinnerPanel, BoxLayout.Y_AXIS));
        Map<Good, HashMap<Enums.MarketValues, Integer>> _localMarket = GuiArbiter.getLocalMarket();
        for (int i = 0; i < GuiArbiter.goodsRegSize(); i++) {
            Good good = GuiArbiter.getGood(i);
            if (_localMarket.get(good) != null) {
                // spinner has initial value 0, minimum 0, maximum is the amount of the good in the _localMarket, goes up by 1
                SpinnerModel model = new SpinnerNumberModel(0, 0, GuiArbiter.getGoodQuantity(good), 1);
                JSpinner jsp = new JSpinner(model);
                jsp.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        JSpinner spinner = (JSpinner) e.getSource();
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
        GuiArbiter.updateCurrentPortInfo(currentPortLabel);
    }

    /**
     * Displays and formats the target port (to be removed)
     */
    public void updateTargetPortInfo() {
        GuiArbiter.updateTargetPortInfo(targetPortLabel);
    }


    public static void main(String[] args) {
        frame = new JFrame("Marketplace");
        frame.setContentPane(new MarketplaceScreen()._panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new CloseHandler());
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
        _panel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:d:noGrow,fill:max(d;4px):noGrow,left:d:noGrow,fill:m:noGrow,left:4dlu:noGrow,left:d:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:d:grow,fill:57px:noGrow,left:4dlu:noGrow,fill:35px:noGrow,left:4dlu:noGrow,fill:d:grow,fill:d:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,top:d:noGrow,top:3dlu:noGrow,center:57px:noGrow"));
        _panel.setEnabled(false);
        MarketplaceLabel = new JLabel();
        MarketplaceLabel.setText("MARKET!");
        CellConstraints cc = new CellConstraints();
        _panel.add(MarketplaceLabel, cc.xy(2, 1));
        playerInfoLabel = new JLabel();
        playerInfoLabel.setText("You have: coins");
        _panel.add(playerInfoLabel, cc.xyw(2, 3, 14));
        goods = new JLabel();
        goods.setText("Goods");
        _panel.add(goods, cc.xy(2, 5));
        inventoryLabel = new JLabel();
        inventoryLabel.setText("Inventory");
        _panel.add(inventoryLabel, cc.xyw(4, 5, 4));
        marketLabel = new JLabel();
        marketLabel.setText("Market");
        _panel.add(marketLabel, cc.xyw(11, 5, 3));
        sellButton = new JButton();
        sellButton.setText("Sell");
        _panel.add(sellButton, cc.xyw(4, 11, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        buyButton = new JButton();
        buyButton.setText("Buy");
        _panel.add(buyButton, cc.xyw(11, 11, 6));
        buySpinnerPanel = new JPanel();
        buySpinnerPanel.setLayout(new BorderLayout(0, 0));
        _panel.add(buySpinnerPanel, cc.xy(16, 9, CellConstraints.CENTER, CellConstraints.FILL));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("Label");
        _panel.add(currentPortLabel, cc.xyw(2, 13, 6, CellConstraints.DEFAULT, CellConstraints.TOP));
        targetPortLabel = new JLabel();
        targetPortLabel.setText("");
        _panel.add(targetPortLabel, cc.xyw(11, 13, 3, CellConstraints.LEFT, CellConstraints.TOP));
        final JLabel label1 = new JLabel();
        label1.setText("Quantity");
        _panel.add(label1, cc.xy(11, 7));
        final JLabel label2 = new JLabel();
        label2.setText("Price");
        _panel.add(label2, cc.xy(13, 7));
        final JLabel label3 = new JLabel();
        label3.setText("Quantity");
        _panel.add(label3, cc.xy(4, 7, CellConstraints.LEFT, CellConstraints.DEFAULT));
        final JLabel label4 = new JLabel();
        label4.setText("Price");
        _panel.add(label4, cc.xy(7, 7));
        sellSpinnerPanel = new JPanel();
        sellSpinnerPanel.setLayout(new BorderLayout(0, 0));
        _panel.add(sellSpinnerPanel, cc.xy(8, 9, CellConstraints.CENTER, CellConstraints.FILL));
        goodsLabel = new JLabel();
        goodsLabel.setText("Label");
        _panel.add(goodsLabel, cc.xy(2, 9, CellConstraints.LEFT, CellConstraints.TOP));
        invQuantityLabel = new JLabel();
        invQuantityLabel.setText("Label");
        _panel.add(invQuantityLabel, cc.xy(4, 9, CellConstraints.LEFT, CellConstraints.TOP));
        sellPriceLabel = new JLabel();
        sellPriceLabel.setText("Label");
        _panel.add(sellPriceLabel, cc.xy(7, 9, CellConstraints.DEFAULT, CellConstraints.TOP));
        buyPriceLabel = new JLabel();
        buyPriceLabel.setText("Label");
        _panel.add(buyPriceLabel, cc.xy(13, 9, CellConstraints.LEFT, CellConstraints.TOP));
        marketQuantityLabel = new JLabel();
        marketQuantityLabel.setText("Label");
        _panel.add(marketQuantityLabel, cc.xy(11, 9, CellConstraints.LEFT, CellConstraints.TOP));
        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to Main Screen");
        _panel.add(returnToMainButton, cc.xyw(2, 15, 6, CellConstraints.LEFT, CellConstraints.TOP));
        final Spacer spacer1 = new Spacer();
        _panel.add(spacer1, cc.xy(15, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer2 = new Spacer();
        _panel.add(spacer2, cc.xy(10, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return _panel;
    }
}
