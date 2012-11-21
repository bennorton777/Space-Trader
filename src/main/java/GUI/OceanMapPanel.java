package GUI;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/29/12
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */

import com.TableFlip.SpaceTrader.Model.Coordinates;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//import com.sun.javaws.util.GeneralUtil;

public class OceanMapPanel extends JPanel {

    public OceanMapPanel() {
        setBackground(Color.blue);
        setSize(500, 500);
    }

    public void paintComponent(Graphics g) {
        List<Coordinates> coords = GuiArbiter.getPortCoordinates();

        super.paintComponent(g);

        g.setColor(Color.yellow);
        for (Coordinates coord : coords) {
            int x = (coord.getxPos() * 5) - 2;
            int y = (coord.getyPos() * 5) - 2;
            g.fillRect(x, y, 5, 5);
        }

        Coordinates currentPort = GuiArbiter.getCurrentPort().getCoordinates();
        g.setColor(Color.green);
        g.fillRect((currentPort.getxPos() * 5) - 2, (currentPort.getyPos() * 5) - 2, 5, 5);
        g.drawRect((currentPort.getxPos() * 5) - 4, (currentPort.getyPos() * 5) - 4, 8, 8);

        int travelRadius = GuiArbiter.getCurrentSupplies()*5;
        g.drawOval((currentPort.getxPos()*5)-(travelRadius)-2, (currentPort.getyPos()*5)-travelRadius-2, travelRadius*2+4, travelRadius*2+4);

        Coordinates highlightedPort = GuiArbiter.getHighlightedPort().getCoordinates();
        if (highlightedPort != null) {
            g.setColor(Color.red);
            g.fillRect((highlightedPort.getxPos() * 5) - 2, (highlightedPort.getyPos() * 5) - 2, 5, 5);
            g.drawRect((highlightedPort.getxPos() * 5) - 4, (highlightedPort.getyPos() * 5) - 4, 8, 8);
        }
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}
