package hr.fer.zemris.ppp.crypto.component;

import hr.fer.zemris.ppp.crypto.CryptoUtil;

import javax.swing.*;
import java.awt.*;

public class ModePanel extends JPanel {

    private AbstractButton encryptButton;
    private AbstractButton decryptButton;
    private JButton switchButton;

    public ModePanel() {
        JPanel modePanel = new JPanel(new BorderLayout());
        modePanel.setBackground(CryptoUtil.LIGHT_BLUE);
        modePanel.add(createModeLabel(), BorderLayout.PAGE_START);
        modePanel.add(createRadioButtonPanel(), BorderLayout.CENTER);
        modePanel.add(createSwitchButton(), BorderLayout.PAGE_END);

        add(modePanel);
        add(createHelpLabel());
        setBackground(CryptoUtil.LIGHT_BLUE);
    }

    private JButton createSwitchButton() {
        switchButton = new JButton("Zamijeni");
        switchButton.setFont(CryptoUtil.PLAIN);
        return switchButton;
    }

    private JLabel createHelpLabel() {
        JLabel helpLabel = new JLabel();
        helpLabel.setPreferredSize(new Dimension(30, 10));
        return  helpLabel;
    }

    private JLabel createModeLabel() {
        JLabel modeLabel = new JLabel("Nacin rada", JLabel.CENTER);
        modeLabel.setFont(CryptoUtil.BOLD);
        return modeLabel;
    }

    private JPanel createRadioButtonPanel() {
        JPanel radioButtonPanel = new JPanel(new GridLayout(2, 1));
        radioButtonPanel.setBackground(CryptoUtil.LIGHT_BLUE);

        createButtonGroup();
        radioButtonPanel.add(encryptButton);
        radioButtonPanel.add(decryptButton);
        radioButtonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(CryptoUtil.LIGHT_BLUE);
        helpPanel.add(radioButtonPanel, JPanel.CENTER_ALIGNMENT);

        return helpPanel;
    }

    private void createButtonGroup() {
        ButtonGroup buttonGroup = new ButtonGroup();

        encryptButton = new JRadioButton("enkriptiranje");
        encryptButton.setFont(CryptoUtil.PLAIN);
        encryptButton.setSelected(true);
        encryptButton.setBackground(CryptoUtil.LIGHT_BLUE);
        buttonGroup.add(encryptButton);

        decryptButton = new JRadioButton("dekriptiranje");
        decryptButton.setFont(CryptoUtil.PLAIN);
        decryptButton.setBackground(CryptoUtil.LIGHT_BLUE);
        buttonGroup.add(decryptButton);
    }

    public AbstractButton getEncryptButton() {
        return encryptButton;
    }

    public AbstractButton getDecryptButton() {
        return decryptButton;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }
}