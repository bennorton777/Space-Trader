package GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 9/6/12
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class CharGen {
    private JSpinner _traderSpinner;
    private JSpinner _engineerSpinner;
    private JSpinner _fighterSpinner;
    private JSpinner _pilotSpinner;
    private int _pilotSkill = 0;
    private int _engineerSkill = 0;
    private int _fighterSkill = 0;
    private int _traderSkill = 0;
    private int _currentTotal = 20;
    private static JFrame frame;

    /**
     * Gui class that controls the Character Generation Dialog.
     * Never interacts with Game logic classes except through the GUI arbiter class.
     * Provides checks to make sure the user enters valid data.
     * Contains generated GUI code in method $$$setupUI$$$
     */
    public CharGen() {
        $$$setupUI$$$();
        _submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((_currentTotal != 0) || (_textField1.getText().length() == 0)) {
                    return;
                }
                GuiArbiter.newCharacter(_textField1.getText(), _pilotSkill, _fighterSkill, _traderSkill, _engineerSkill);
                frame.dispose();
            }
        });
        _pilotSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (((_currentTotal == 0) && (_pilotSkill - (Integer) _pilotSpinner.getValue() < 0)) || ((Integer) _pilotSpinner.getValue() < 0)) {
                    _pilotSpinner.setValue(_pilotSkill);
                } else {
                    _currentTotal += _pilotSkill - (Integer) _pilotSpinner.getValue();
                    _totalLabel.setText(((Integer) _currentTotal).toString());
                    _pilotSkill = (Integer) _pilotSpinner.getValue();
                }
            }
        });
        _fighterSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (((_currentTotal == 0) && (_fighterSkill - (Integer) _fighterSpinner.getValue() < 0)) || ((Integer) _fighterSpinner.getValue() < 0)) {
                    _fighterSpinner.setValue(_fighterSkill);
                } else {
                    _currentTotal += _fighterSkill - (Integer) _fighterSpinner.getValue();
                    _totalLabel.setText(((Integer) _currentTotal).toString());
                    _fighterSkill = (Integer) _fighterSpinner.getValue();
                }
            }
        });
        _traderSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (((_currentTotal == 0) && (_traderSkill - (Integer) _traderSpinner.getValue() < 0)) || ((Integer) _traderSpinner.getValue() < 0)) {
                    _traderSpinner.setValue(_traderSkill);
                } else {
                    _currentTotal += _traderSkill - (Integer) _traderSpinner.getValue();
                    _totalLabel.setText(((Integer) _currentTotal).toString());
                    _traderSkill = (Integer) _traderSpinner.getValue();
                }
            }
        });
        _engineerSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (((_currentTotal == 0) && (_engineerSkill - (Integer) _engineerSpinner.getValue() < 0)) || ((Integer) _engineerSpinner.getValue() < 0)) {
                    _engineerSpinner.setValue(_engineerSkill);
                } else {
                    _currentTotal += _engineerSkill - (Integer) _engineerSpinner.getValue();
                    _totalLabel.setText(((Integer) _currentTotal).toString());
                    _engineerSkill = (Integer) _engineerSpinner.getValue();
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuiArbiter.load();
                frame.dispose();
                GuiArbiter.GameScreen();
            }
        });
    }

    private JTextField _textField1;
    private JPanel _panel;
    private JButton _submitButton;
    private JLabel _totalLabel = new JLabel();
    private JSlider _slider1;
    private JButton loadButton;

    //Everything below was generated.
    public static void main(String[] args) {
        frame = new JFrame("CharGen");
        frame.setContentPane(new CharGen()._panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        _panel = new JPanel();
        _panel.setLayout(new GridLayoutManager(9, 6, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Name");
        _panel.add(label1, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(42, 15), null, 0, false));
        _engineerSpinner = new JSpinner();
        _panel.add(_engineerSpinner, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(35, -1), null, 0, false));
        _traderSpinner = new JSpinner();
        _panel.add(_traderSpinner, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(35, -1), null, 0, false));
        _fighterSpinner = new JSpinner();
        _panel.add(_fighterSpinner, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(35, -1), null, 0, false));
        _pilotSpinner = new JSpinner();
        _panel.add(_pilotSpinner, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(35, 17), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Pilot");
        _panel.add(label2, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(27, 17), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Fighter");
        _panel.add(label3, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Trader");
        _panel.add(label4, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Engineer");
        _panel.add(label5, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Points Remaining");
        _panel.add(label6, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        _totalLabel.setText("20");
        _panel.add(_totalLabel, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setHorizontalAlignment(0);
        label7.setHorizontalTextPosition(0);
        label7.setText("Yo Make a Character Man");
        _panel.add(label7, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(138, 9), null, 0, false));
        _textField1 = new JTextField();
        _panel.add(_textField1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        _submitButton = new JButton();
        _submitButton.setText("Submit");
        _panel.add(_submitButton, new GridConstraints(8, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Difficulty");
        _panel.add(label8, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        _slider1 = new JSlider();
        _slider1.setMajorTickSpacing(1);
        _slider1.setMaximum(4);
        _slider1.setPaintLabels(false);
        _slider1.setPaintTicks(true);
        _slider1.setSnapToTicks(true);
        _panel.add(_slider1, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadButton = new JButton();
        loadButton.setText("Load");
        _panel.add(loadButton, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        _panel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return _panel;
    }
}
