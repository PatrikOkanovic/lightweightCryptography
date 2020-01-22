package hr.fer.zemris.ppp.crypto.component;

import hr.fer.zemris.ppp.crypto.CryptoUtil;

import java.awt.*;

import javax.swing.*;

public class FileLoadingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

    private JTextArea pathTextArea;
    private JButton button;
    private CryptoTextArea textArea;

    public FileLoadingPanel(String labelText, String buttonText) {
        setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.GRAY));
        setBackground(CryptoUtil.LIGHT_BLUE);
        add(createMainPanel(labelText, buttonText));
    }

    private JPanel createMainPanel(String labelText, String buttonText) {
        JPanel helpPanel = new JPanel();
        helpPanel.setBackground(CryptoUtil.LIGHT_BLUE);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(50, 30));
        label.setFont(CryptoUtil.PLAIN);
        helpPanel.add(label, JPanel.LEFT_ALIGNMENT);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createPathPanel(buttonText), BorderLayout.PAGE_START);
        mainPanel.add(createTextPanel(), BorderLayout.CENTER);
        helpPanel.add(mainPanel);

        return helpPanel;
    }

    private JScrollPane createTextPanel() {
        textArea = createTextArea();
        textArea.setColumns(50);
        textArea.setFont(CryptoUtil.PLAIN);
        JScrollPane textPanel = new JScrollPane(textArea);
        textPanel.setBackground(CryptoUtil.LIGHT_BLUE);
        return textPanel;
    }

    private JPanel createPathPanel(String buttonText) {
        JPanel pathPanel = new JPanel();
        pathPanel.setBackground(CryptoUtil.LIGHT_BLUE);
        pathTextArea = createTextArea();
        pathTextArea.setEditable(false);
        pathPanel.add(new JScrollPane(pathTextArea));
        button = new JButton(buttonText);
        button.setFont(CryptoUtil.PLAIN);
        pathPanel.add(button);
        return pathPanel;
    }

	private CryptoTextArea createTextArea() {
        CryptoTextArea jTextArea = new CryptoTextArea(1, 50);
        jTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jTextArea.setLineWrap(true);
        jTextArea.setFont(CryptoUtil.PLAIN);
        jTextArea.setEnabled(false);
        return jTextArea;
    }

    public JTextArea getPathTextArea() {
        return pathTextArea;
    }

    public JButton getButton() {
        return button;
    }

    public CryptoTextArea getTextArea() {
        return textArea;
    }
}