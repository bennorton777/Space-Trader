package GUI;

import com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper;
import com.TableFlip.SpaceTrader.GameEntity.Ocean;
import com.TableFlip.SpaceTrader.Model.Port;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
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
    private JButton backToMainScreenButton;
    private JButton downButton;
    private JButton upButton;
    private JButton rightButton;
    private JButton leftButton;
    private static JFrame frame;

    public SelectPortScreen() {
        $$$setupUI$$$();

        updateCurrentPortInfo();
        updateHighlightedPortInfo();
        updateFuelStatus();
        updateDistance(GuiArbiter.calculateDistance(GuiArbiter.getTargetPort().getCoordinates(), GuiArbiter.getCurrentPort().getCoordinates()));
        updatePortSelected();
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
                updateHighlightedPortInfo();
            }
        });
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.down();
                OceanMapP.repaint();
                updateHighlightedPortInfo();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.right();
                OceanMapP.repaint();
                updateHighlightedPortInfo();
            }
        });
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.left();
                OceanMapP.repaint();
                updateHighlightedPortInfo();
            }
        });
        selectPortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = GuiArbiter.setTargetPort();
                if(value==1){
                    JOptionPane.showMessageDialog(panel1, "You cannot travel to that port.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(value==2){
                    JOptionPane.showMessageDialog(panel1, "You cannot select your current port to be your target port.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updatePortSelected();
            }
        });
        OceanMapP.addKeyListener(new KeyAdapter() {
        });
        OceanMapP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               /* super.mouseClicked(e);
                Port close=GuiArbiter.getClosestPort(e.getX(), e.getY());
                Ocean.getInstance().setHighlightedPort(close);
                OceanMapP.repaint();
                updateHighlightedPortInfo();*/
            }
        });
    }

    public void updateCurrentPortInfo() {
        Port port = GuiArbiter.getCurrentPort();
        currentPortLabel.setText("<html>Current Port Details<br /><br />" +
                "Name: " + port.getName() + "<br />" +
                "Tech Level: " + port.getTechLevel() + "<br />" +
                "Resource: " + port.getResources() + "<br />" +
                "Coordinates: " + port.getCoordinates() + "<br /></html>");
    }

    public void updateHighlightedPortInfo() {
        Port highlightedPort = GuiArbiter.getHighlightedPort();
        if (highlightedPort != null) {
            highlightedPortLabel.setText("<html>Highlighted Port Details<br /><br />" +
                    "Name: " + highlightedPort.getName() + "<br />" +
                    "Tech Level: " + highlightedPort.getTechLevel() + "<br />" +
                    "Resource: " + highlightedPort.getResources() + "<br />" +
                    "Coordinates: " + highlightedPort.getCoordinates() + "<br /></html>");
            updateDistance(GuiArbiter.calculateDistance(highlightedPort.getCoordinates(), GuiArbiter.getCurrentPort().getCoordinates()));
            updatePortSelected();
        } else {
            highlightedPortLabel.setText("<html>Target Port Details<br /><br />" +
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

    public void updatePortSelected() {
        if (GuiArbiter.getTargetPort().equals(GuiArbiter.getHighlightedPort())){
            portSelectedLabel.setText("<html>This port has been<br /> selected as your target.</html>");
        }
        else {
            portSelectedLabel.setText("<html>This port has not been<br /> selected as your target.</html>");
        }
    }

    private void createUIComponents() {
        OceanMapP = new OceanMapPanel();
    }

    public static void main(String[] args) {

        frame = new JFrame("SelectPortScreen");

        frame.setContentPane(new SelectPortScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new CloseHandler());
        frame.pack();
        frame.setVisible(true);
    }

    private JTextPane currentPortTextPane;
    private JLabel selectDestinationLabel;
    private JLabel currentPortLabel;
    private JLabel highlightedPortLabel;
    private JLabel fuelStatusLabel;
    private JLabel distanceLabel;
    private JPanel OceanMapP;
    private JButton selectPortButton;
    private JLabel portSelectedLabel;

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
        highlightedPortLabel = new JLabel();
        highlightedPortLabel.setText("change me!");
        panel2.add(highlightedPortLabel, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
