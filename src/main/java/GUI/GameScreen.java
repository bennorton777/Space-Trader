package GUI;

import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Model.Ship;
import com.TableFlip.SpaceTrader.Service.SaveMaker;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Game screen!
 */
public class GameScreen {
    private JTextPane playerInfo;
    private JButton toShipyardButton;
    private JButton toMarketplaceButton;
    private JButton toOceanMapButton;
    private JButton toTargetPortButton;

    private JPanel _panel;
    private JLabel currentPortLabel;
    private JLabel targetPortLabel;
    private JButton saveButton;
    private JLabel shipStatusLabel;
    private static JFrame frame;

    private String _name;
    private String _coins;

    static Player _player;
    static Ship _ship;

    public GameScreen(String name, String coins) {
        _name = name;
        _coins = coins;
        _player = Player.getInstance();
        _ship = _player.getShip();
        updatePlayerInfoPane();
        updateShipLabel();
        updatePortLabels();
        toShipyardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        toMarketplaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.out.print("Displaying Marketplace Screen");
                GuiArbiter.MarketplaceScreen();
            }
        });
        toOceanMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.SelectPortScreen();
            }
        });

        toTargetPortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                _ship.fly();

            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveMaker.getInstance().save();
            }
        });
    }

    public void updatePlayerInfoPane() {
        playerInfo.setText("Name: " + _name + " You have: " + _coins + " coins");
    }

    public void updateCredits(String coins) {
        _coins = coins;
        updatePlayerInfoPane();
    }

    public void updateShipLabel() {
        shipStatusLabel.setText("<html>Ship Status:<br />" +
                "<br />Name: " + _ship.getName() +
                "<br />Max Supplies Number: " + _ship.getSuppliesMax() +
                "<br />Weapons Slots: " + _ship.getWeaponSlots() +
                "<br />Armor Slots: " + _ship.getArmorSlots() +
                "<br />Crew Slots: " + _ship.getCrewSlots() +
                "<br />Tool Slots: " + _ship.getToolSlots() +
                "</html>");
    }

    public void updatePortLabels() {
        GuiArbiter.updateCurrentPortInfo(currentPortLabel);
        GuiArbiter.updateTargetPortInfo(targetPortLabel);
    }

    public static void close() {
        frame.dispose();
    }

    public static void main(String[] args) {
        frame = new JFrame("Main Screen");
        frame.setContentPane(new GameScreen(args[0], args[1])._panel);
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
        _panel.setLayout(new FormLayout("fill:173px:noGrow,left:4dlu:noGrow,fill:185px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:6dlu:noGrow,center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
        playerInfo = new JTextPane();
        playerInfo.setEditable(false);
        playerInfo.setText("Name: Pilot: Fighter: Trader: Engineer:  You have: coins.");
        CellConstraints cc = new CellConstraints();
        _panel.add(playerInfo, cc.xyw(1, 1, 3, CellConstraints.FILL, CellConstraints.TOP));
        toMarketplaceButton = new JButton();
        toMarketplaceButton.setText("Marketplace");
        _panel.add(toMarketplaceButton, cc.xy(1, 7));
        toOceanMapButton = new JButton();
        toOceanMapButton.setText("Ocean Map");
        _panel.add(toOceanMapButton, cc.xy(3, 7));
        toTargetPortButton = new JButton();
        toTargetPortButton.setText("Warp to Target Port");
        _panel.add(toTargetPortButton, cc.xy(3, 9));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("Label");
        _panel.add(currentPortLabel, cc.xy(1, 3, CellConstraints.LEFT, CellConstraints.TOP));
        targetPortLabel = new JLabel();
        targetPortLabel.setText("Label");
        _panel.add(targetPortLabel, cc.xy(3, 3, CellConstraints.LEFT, CellConstraints.TOP));
        toShipyardButton = new JButton();
        toShipyardButton.setText("Shipyard");
        _panel.add(toShipyardButton, cc.xy(1, 9));
        saveButton = new JButton();
        saveButton.setText("Save");
        _panel.add(saveButton, cc.xy(5, 1));
        shipStatusLabel = new JLabel();
        shipStatusLabel.setText("Label");
        _panel.add(shipStatusLabel, cc.xyw(1, 5, 3));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return _panel;
    }
}

