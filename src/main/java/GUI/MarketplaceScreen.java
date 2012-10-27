package GUI;

import javax.swing.*;

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


    public MarketplaceScreen(){

    }

    public MarketplaceScreen(String[] goodsArray, int[] invQuantityArray, int[] sellPriceArray, int[] marketQuantityArray, int[] buyPriceArray){
        _goodsArray = goodsArray;
        _invQuantityArray = invQuantityArray;
        _sellPriceArray = sellPriceArray;
        _marketQuantityArray = marketQuantityArray;
        _buyPriceArray = buyPriceArray;


    }




    public static void main(String[] args) {
        frame = new JFrame("Main Screen");
        frame.setContentPane(new MarketplaceScreen()._panel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
