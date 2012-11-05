package GUI;

import com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper;
import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.GameEntity.Player;
import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.TableFlip.SpaceTrader.Model.Port;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/28/12
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectPortScreen {
    private JPanel panel1;
    private JPanel OceanMap;
    //private JPanel OceanMapPanel;
    private JButton backToMainScreenButton;
    private JButton downButton;
    private JButton upButton;
    private JButton rightButton;
    private JButton leftButton;
    private static JFrame frame;
    //private Player _player;
    //private Port _port;
    //  private Ocean _ocean;
    //private Port _targetPort;

    public SelectPortScreen() {
        $$$setupUI$$$();
        //_player = Player.getInstance();
        //_port = _player.getCurrentPort();
        //_targetPort = _player.getTargetPort();

        updateCurrentPortInfo();
        updateTargetPortInfo();
        updateFuelStatus();
        updateDistance(mantattanDistance(GuiArbiter.getTargetPort().getCoordinates(), GuiArbiter.getCurrentPort().getCoordinates()));
        OceanMap.setBackground(Color.cyan);


        backToMainScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Bootstrapper.displayGameScreen();
            }
        });
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.up();
                OceanMapP.repaint();
                updateTargetPortInfo();
            }
        });
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.down();
                OceanMapP.repaint();
                updateTargetPortInfo();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.right();
                OceanMapP.repaint();
                updateTargetPortInfo();
            }
        });
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.left();
                OceanMapP.repaint();
                updateTargetPortInfo();
            }
        });
        OceanMapP.addKeyListener(new KeyAdapter() {
        });
    }
/*
    public void paintComponent(Graphics g) {

        //super.paintComponent(g);

        g.setColor(Color.yellow);
        g.fillOval(0, 0, 80, 80);
    }*/

    public void updateCurrentPortInfo() {
        Port port = GuiArbiter.getCurrentPort();
        currentPortLabel.setText("<html>Current Port Details<br /><br />" +
                "Name: " + port.getName() + "<br />" +
                "Tech Level: " + port.getTechLevel() + "<br />" +
                "Resource: " + port.getResources() + "<br />" +
                "Coordinates: " + port.getCoordinates() + "<br /></html>");
    }

    public void updateTargetPortInfo() {
        Port targetPort = GuiArbiter.getTargetPort();
        if (targetPort != null) {
            targetPortLabel.setText("<html>Target Port Details<br /><br />" +
                    "Name: " + targetPort.getName() + "<br />" +
                    "Tech Level: " + targetPort.getTechLevel() + "<br />" +
                    "Resource: " + targetPort.getResources() + "<br />" +
                    "Coordinates: " + targetPort.getCoordinates() + "<br /></html>");
            updateDistance(mantattanDistance(targetPort.getCoordinates(), GuiArbiter.getCurrentPort().getCoordinates()));
        } else {
            targetPortLabel.setText("<html>Target Port Details<br /><br />" +
                    "Name: ---<br />" +
                    "Tech Level: ---<br />" +
                    "Resource: ---<br />" +
                    "Coordinates: ---<br /></html>");
        }

    }

    public void updateFuelStatus() {

        fuelStatusLabel.setText("You have supplies to travel " + GuiArbiter.getCurrentSupplies() + " leagues.");
    }

    public void updateDistance(int distance) {
        distanceLabel.setText("This island is " + distance + " leagues away.");
    }

    private void createUIComponents() {
        OceanMapP = new OceanMapPanel();
    }

    private int mantattanDistance(Coordinates a, Coordinates b) {
        return Math.abs(a.getxPos() - b.getxPos()) + Math.abs(a.getyPos() - b.getyPos());
    }

    public static void main(String[] args) {

        frame = new JFrame("SelectPortScreen");

        frame.setContentPane(new SelectPortScreen().panel1);
        //frame.getContentPane().add(new OceanMapPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTextPane currentPortTextPane;
    private JLabel selectDestinationLabel;
    private JLabel currentPortLabel;
    private JLabel targetPortLabel;
    private JLabel fuelStatusLabel;
    private JLabel distanceLabel;
    private JPanel OceanMapP;

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        OceanMap = new JPanel();
        OceanMap.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(OceanMap, new GridConstraints(1, 0, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(95, 24), null, 0, false));
        OceanMap.add(OceanMapP, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 500), new Dimension(500, 500), null, 0, false));
        backToMainScreenButton = new JButton();
        backToMainScreenButton.setText("Back to Main Screen");
        panel2.add(backToMainScreenButton, new GridConstraints(6, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(95, 29), null, 0, false));
        upButton = new JButton();
        upButton.setText("Up");
        panel2.add(upButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(95, 29), null, 0, false));
        downButton = new JButton();
        downButton.setText("Down");
        panel2.add(downButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(95, 29), null, 0, false));
        leftButton = new JButton();
        leftButton.setText("Left");
        panel2.add(leftButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(49, 29), null, 0, false));
        rightButton = new JButton();
        rightButton.setText("Right");
        panel2.add(rightButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(57, 29), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        selectDestinationLabel = new JLabel();
        selectDestinationLabel.setText("Ahoy matey, select destination port");
        panel2.add(selectDestinationLabel, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("change me!");
        panel2.add(currentPortLabel, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        targetPortLabel = new JLabel();
        targetPortLabel.setText("change me!");
        panel2.add(targetPortLabel, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fuelStatusLabel = new JLabel();
        fuelStatusLabel.setText("fuel stuff");
        panel2.add(fuelStatusLabel, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        distanceLabel = new JLabel();
        distanceLabel.setText("distance stuff");
        panel2.add(distanceLabel, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
