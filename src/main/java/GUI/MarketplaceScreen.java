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
}