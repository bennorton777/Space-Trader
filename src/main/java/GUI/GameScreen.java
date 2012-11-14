package GUI;

import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Model.Ship;
import com.intellij.uiDesigner.core.Spacer;
import com.TableFlip.SpaceTrader.Service.SaveMaker;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Game screen!
 * <p/>
 * Contains information about the Player, his current Port, his Port
 * highlighted on the Ocean Map, his ship, and allows the player to
 * save the game.
 */
public class GameScreen {
    private JButton toShipyardButton;
    private JButton toMarketplaceButton;
    private JButton toOceanMapButton;
    private JButton toTargetPortButton;

    private JPanel _panel;
    private JLabel currentPortLabel;
    private JLabel targetPortLabel;
    private JButton saveButton;
    private JLabel shipStatusLabel;
    private JLabel playerInfo;
    private JButton _fillUpFuelTankButton;
    private JButton loadButton;
    private static JFrame frame;

    public GameScreen() {
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
                System.out.println("Displaying Marketplace Screen");
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
                GuiArbiter.fly();
                updatePlayerInfoPane();
                updateShipLabel();
                updatePortLabels();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.save();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.load();
                updatePlayerInfoPane();
                updateShipLabel();
                updatePortLabels();
            }
        });
        _fillUpFuelTankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(_panel, "Filling up your traveling supplies will cost " + (Player.getInstance().getShip().getSuppliesMax() - Player.getInstance().getShip().getSuppliesRemaining()) + " coins, are you sure you want to fill up?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (selectedOption == 1) {
                    GuiArbiter.popUp("Okay, your traveling supplies have not been filled up.");
                }
                if (selectedOption == 0) {
                    Player player = Player.getInstance();
                    Ship ship = player.getShip();
                    int missingSupplies = ship.getSuppliesMax() - ship.getSuppliesRemaining();
                    if (player.getCredits() < missingSupplies) {
                        JOptionPane.showMessageDialog(_panel, "You don't have enough coins!", "", JOptionPane.ERROR_MESSAGE);
                    } else {
                        player.setCredits(player.getCredits() - missingSupplies);
                        ship.setSuppliesRemaining(ship.getSuppliesMax());
                        GuiArbiter.popUp("You supplies have been replenished.");
                    }
                }
                updatePlayerInfoPane();
                GuiArbiter.updateShipStatus(shipStatusLabel);
            }
        });

    }

    public void popUp(String message) {
        JOptionPane.showMessageDialog(_panel, "You cannot travel to that port.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void updatePlayerInfoPane() {
        GuiArbiter.updatePlayerInfo(playerInfo);
    }

    public void updateShipLabel() {
        GuiArbiter.updateShipStatus(shipStatusLabel);
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
        frame.setContentPane(new GameScreen()._panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        _panel.setLayout(new FormLayout("fill:max(d;4px):noGrow,fill:173px:noGrow,left:4dlu:noGrow,fill:185px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:30px:noGrow,center:32px:noGrow,center:max(d;4px):noGrow,top:m:noGrow,center:d:noGrow,top:3dlu:noGrow,center:38px:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:m:noGrow,top:3dlu:noGrow,center:50px:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
        toMarketplaceButton = new JButton();
        toMarketplaceButton.setText("Marketplace");
        CellConstraints cc = new CellConstraints();
        _panel.add(toMarketplaceButton, cc.xy(2, 11, CellConstraints.CENTER, CellConstraints.TOP));
        toOceanMapButton = new JButton();
        toOceanMapButton.setText("Ocean Map");
        _panel.add(toOceanMapButton, cc.xy(4, 11, CellConstraints.CENTER, CellConstraints.TOP));
        toTargetPortButton = new JButton();
        toTargetPortButton.setText("Warp to Target Port");
        _panel.add(toTargetPortButton, cc.xy(4, 13, CellConstraints.CENTER, CellConstraints.TOP));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("Label");
        _panel.add(currentPortLabel, cc.xy(2, 5, CellConstraints.LEFT, CellConstraints.TOP));
        targetPortLabel = new JLabel();
        targetPortLabel.setText("Label");
        _panel.add(targetPortLabel, cc.xy(4, 5, CellConstraints.LEFT, CellConstraints.TOP));
        toShipyardButton = new JButton();
        toShipyardButton.setText("Shipyard");
        _panel.add(toShipyardButton, cc.xy(2, 13, CellConstraints.CENTER, CellConstraints.TOP));
        saveButton = new JButton();
        saveButton.setText("Save");
        _panel.add(saveButton, cc.xy(6, 1));
        shipStatusLabel = new JLabel();
        shipStatusLabel.setText("Label");
        _panel.add(shipStatusLabel, cc.xyw(2, 9, 3));
        final Spacer spacer1 = new Spacer();
        _panel.add(spacer1, cc.xy(2, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
        playerInfo = new JLabel();
        playerInfo.setText("Label");
        _panel.add(playerInfo, cc.xywh(2, 1, 3, 2, CellConstraints.DEFAULT, CellConstraints.TOP));
        _fillUpFuelTankButton = new JButton();
        _fillUpFuelTankButton.setText("Replenish Traveling Supplies");
        _panel.add(_fillUpFuelTankButton, cc.xyw(2, 15, 3, CellConstraints.DEFAULT, CellConstraints.TOP));
        loadButton = new JButton();
        loadButton.setText("Load");
        _panel.add(loadButton, cc.xy(6, 2, CellConstraints.FILL, CellConstraints.TOP));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return _panel;
    }
}

