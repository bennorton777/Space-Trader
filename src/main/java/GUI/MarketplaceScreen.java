package GUI;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: DelRosario
 * Date: 10/23/12
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketplaceScreen {
    private JPanel panel1;
    private JLabel MarketplaceLabel;
    private JLabel playerInfoLabel;
    private JList goodsList;
    private JLabel goods;
    private JTable inventoryTable;
    private JLabel inventoryLabel;
    private JLabel marketLabel;
    private JTable table1;
    private JButton sellButton;
    private JButton buyButton;
    private JTextPane currentPortInfoPane;
    private JButton returnToMainButton;

    private static JPanel _panel;
    private static JFrame frame;


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
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
        MarketplaceLabel = new JLabel();
        MarketplaceLabel.setText("MARKETPLACE");
        CellConstraints cc = new CellConstraints();
        panel1.add(MarketplaceLabel, cc.xy(1, 1));
        playerInfoLabel = new JLabel();
        playerInfoLabel.setText("You have: coins");
        panel1.add(playerInfoLabel, cc.xy(1, 3));
        goods = new JLabel();
        goods.setText("Goods");
        panel1.add(goods, cc.xy(1, 5));
        goodsList = new JList();
        panel1.add(goodsList, cc.xy(1, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        inventoryTable = new JTable();
        inventoryTable.setShowHorizontalLines(true);
        panel1.add(inventoryTable, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.FILL));
        inventoryLabel = new JLabel();
        inventoryLabel.setText("Inventory");
        panel1.add(inventoryLabel, cc.xy(3, 5));
        marketLabel = new JLabel();
        marketLabel.setText("Market");
        panel1.add(marketLabel, cc.xy(5, 5));
        table1 = new JTable();
        panel1.add(table1, cc.xy(5, 7, CellConstraints.FILL, CellConstraints.FILL));
        sellButton = new JButton();
        sellButton.setText("Sell");
        panel1.add(sellButton, cc.xy(3, 9));
        buyButton = new JButton();
        buyButton.setText("Buy");
        panel1.add(buyButton, cc.xy(5, 9));
        currentPortInfoPane = new JTextPane();
        currentPortInfoPane.setText("");
        panel1.add(currentPortInfoPane, cc.xy(1, 11, CellConstraints.FILL, CellConstraints.FILL));
        final JTextPane textPane1 = new JTextPane();
        textPane1.setText("");
        panel1.add(textPane1, cc.xy(5, 11, CellConstraints.FILL, CellConstraints.FILL));
        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to Main Screen");
        panel1.add(returnToMainButton, cc.xy(3, 13));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
