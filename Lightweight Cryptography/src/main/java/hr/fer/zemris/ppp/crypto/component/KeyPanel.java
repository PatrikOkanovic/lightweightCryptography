package hr.fer.zemris.ppp.crypto.component;

import hr.fer.zemris.ppp.crypto.CryptoUtil;

import java.awt.*;

import javax.swing.*;

public class KeyPanel extends JPanel {

	private JTextField textField;
	private JButton button;
	
	private static final long serialVersionUID = 1L;

	public KeyPanel(String buttonText) {
        setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.GRAY));
        setBackground(CryptoUtil.LIGHT_BLUE);
        add(createPanel(buttonText));
	}

	private Component createPanel(String buttonText) {
		JPanel panel = new JPanel();
		panel.setBackground(CryptoUtil.LIGHT_BLUE);
		panel.add(createLabel(), JPanel.LEFT_ALIGNMENT);
		panel.add(createTextField());
		panel.add(createButton(buttonText));
		return panel;
	}

	private JLabel createLabel() {
		JLabel label = new JLabel("Kljuc:");
		label.setFont(CryptoUtil.PLAIN);
		label.setPreferredSize(new Dimension(50, 30));
		return label;
	}

	private JTextField createTextField() {
		textField = new JTextField(50);
		textField.setFont(CryptoUtil.PLAIN);
		textField.setEditable(false);
		return textField;
	}

	private JButton createButton(String buttonText) {
		button = new JButton(buttonText);
		button.setFont(CryptoUtil.PLAIN);
		return button;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getButton() {
		return button;
	}
}