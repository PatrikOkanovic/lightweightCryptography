package hr.fer.zemris.ppp.crypto;

import hr.fer.zemris.ppp.crypto.action.AbstractAlgorithm;
import hr.fer.zemris.ppp.crypto.component.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class CryptoUI extends JFrame {

	private static final long serialVersionUID = 1L;
    private static final String ENCRYPT_TEXT = "Enkriptiraj";
    private static final String DECRYPT_TEXT = "Dekriptiraj";
    
	private AbstractButton encryptButton;
	private AbstractButton decryptButton;
	private JButton switchButton;
	private JComboBox<AbstractAlgorithm> comboBox;
	private JTextArea descriptionTextArea;
	private KeyPanel keyPanel;
	private FileLoadingPanel inputPanel;
	private FileLoadingPanel outputPanel;
	private JButton startButton;
	private JCheckBox blockCheckbox;

	public CryptoUI() {
        setLocation(150, 0);
		setSize(1100, 500);
        setTitle("Lightweight cryptography");
		initGUI();
		pack();
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.PAGE_START);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.PAGE_END);
        
        addListeners();
        initStartingValues();
    }

	private void initStartingValues() {
		keyPanel.getButton().getActionListeners()[0].actionPerformed(null);
		CryptoUtil.updatePanel(inputPanel, Paths.get("examples/example.txt").toAbsolutePath());
	}

	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.GRAY));
		topPanel.add(createAlgorithmPanel(), BorderLayout.CENTER);
		topPanel.add(createModePanel(), BorderLayout.LINE_END);
		return topPanel;
	}

	private JPanel createAlgorithmPanel() {
	    AlgorithmPanel algorithmPanel = new AlgorithmPanel();
	    comboBox = algorithmPanel.getComboBox();
	    descriptionTextArea = algorithmPanel.getDescriptionTextArea();
		return algorithmPanel;
	}

	private JPanel createModePanel() {
		ModePanel modePanel = new ModePanel();
		encryptButton = modePanel.getEncryptButton();
		decryptButton = modePanel.getDecryptButton();
		switchButton = modePanel.getSwitchButton();
		return modePanel;
	}

	private JPanel createMainPanel() {
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    mainPanel.add(createKeyPanel(), BorderLayout.PAGE_START);
	    mainPanel.add(createCryptoPanel(), BorderLayout.CENTER);
	    return mainPanel;
	}

	private JPanel createKeyPanel() {
        keyPanel = new KeyPanel("Generiraj");
        keyPanel.getButton().addActionListener(createButtonListener());
        return keyPanel;
	}

	private ActionListener createButtonListener() {
		return e -> {
			Random rand = new Random();
			StringBuilder sb = new StringBuilder();
			int keySize = parseNumberInRow(0) / 64;
			for (int i = 0; i < keySize; i++) {
				sb.append(Long.toHexString(rand.nextLong()));
			}
			keyPanel.getTextField().setText(sb.toString().toUpperCase());
		};
	}

	private int parseNumberInRow(int row) {
		return Integer.parseInt(descriptionTextArea.getText().split("\n")[row]
				.split("\t")[1].split(" ")[0]);
	}

	private JPanel createCryptoPanel() {
		CryptoPanel cryptoPanel = new CryptoPanel();
		inputPanel = cryptoPanel.getInputPanel();
		outputPanel = cryptoPanel.getOutputPanel();
		return cryptoPanel;
	}

	private JPanel createBottomPanel() {
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setBackground(CryptoUtil.LIGHT_BLUE);
	    bottomPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.GRAY));
	    bottomPanel.add(createStartButton());
	    bottomPanel.add(crateBlockCheckbox());
	    return bottomPanel;
	}

	private JCheckBox crateBlockCheckbox() {
		blockCheckbox = new JCheckBox("podijeli na blokove");
		return blockCheckbox;
	}

	private JButton createStartButton() {
		startButton = new JButton(ENCRYPT_TEXT);
		startButton.setFont(CryptoUtil.PLAIN);
		return startButton;
	}

	private void addListeners() {
		comboBox.addActionListener(createComboBoxListener());
		encryptButton.addActionListener(createEncryptButtonListener());
		decryptButton.addActionListener(createDecryptButtonListener());
		startButton.addActionListener(createStartButtonListener());
		inputPanel.getPathTextArea().getDocument().addDocumentListener(createDocumentListener());
		switchButton.addActionListener(createSwitchButtonListener());
		blockCheckbox.addActionListener(createBlockCheckboxListener());
	}

	private ActionListener createBlockCheckboxListener() {
		return e -> {
			if (blockCheckbox.isSelected()) {
				outputPanel.getTextArea().divideTextByBlocks(parseNumberInRow(2) / 8);
			} else {
				outputPanel.getTextArea().joinTextWithoutBlocks();
			}
		};
	}

	private ActionListener createComboBoxListener() {
		ActionListener l = comboBox.getActionListeners()[0];
		return e -> {
			l.actionPerformed(e);
			keyPanel.getButton().getActionListeners()[0].actionPerformed(e);
		};
	}

	private ActionListener createEncryptButtonListener() {
		return e -> {
			startButton.setText(ENCRYPT_TEXT);
			updateOutputFileName();
		};
	}

	private ActionListener createDecryptButtonListener() {
		return e -> {
			startButton.setText(DECRYPT_TEXT);
			updateOutputFileName();
		};
	}

	private ActionListener createStartButtonListener() {
		return e -> {
			AbstractAlgorithm algorithm = (AbstractAlgorithm) comboBox.getSelectedItem();
			assert algorithm != null;
			algorithm.setMsgBytes(inputPanel.getTextArea().getBytes());
			algorithm.setKeyBytes(keyPanel.getTextField().getText().getBytes(CryptoUtil.CHARSET));

			if (encryptButton.isSelected()) {
				algorithm.encrypt();
			} else {
				algorithm.decrypt();
			}

			byte[] byteResult = algorithm.getByteResult();
			int blockSize = blockCheckbox.isSelected() ? parseNumberInRow(2) / 8 : -1;
			outputPanel.getTextArea().updateBytes(byteResult, blockSize);
			writeToFile(byteResult);
		};
	}

	private DocumentListener createDocumentListener() {
		return new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				updateOutputFileName();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				updateOutputFileName();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				updateOutputFileName();
			}
		};
	}

	private ActionListener createSwitchButtonListener() {
		return e -> {
			Path filePath = Paths.get(outputPanel.getPathTextArea().getText());
			if (!Files.exists(filePath)) {
				JOptionPane.showMessageDialog(
						CryptoUI.this, "Datoteka " + filePath + " ne postoji.\nPrije zamjene spremite rezultat kriptiranja.",
						"Greska", JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			CryptoUtil.updatePanel(inputPanel, filePath);
		};
	}


	public void updateOutputFileName() {
		outputPanel.getPathTextArea().setText(getNewOutputFileName());
		outputPanel.getTextArea().updateBytes(new byte[] {}, -1);
	}

	private String getNewOutputFileName() {
		String inputFileName = getInputFileName(inputPanel.getPathTextArea().getText());
		String addOn = encryptButton.isSelected() ? "_encrypted.txt" : "_decrypted.txt";
		return  inputFileName + addOn;
	}

	private String getInputFileName(String text) {
		int lastIndex = text.lastIndexOf(".");
		if (lastIndex == -1) {
			return text;
		}
		return text.substring(0, lastIndex);
	}

	private void writeToFile(byte[] byteResult) {
		String pathString = outputPanel.getPathTextArea().getText();
		try {
			Files.write(Paths.get(pathString), byteResult);
		} catch (IOException e1) {
			throw new RuntimeException("Greska pri pisanju u datoteku " + pathString + "!");
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.err.println("Neuspjesna izmjena LookAndFeel-a!");
		}

		SwingUtilities.invokeLater(() ->
				new CryptoUI().setVisible(true)
		);
    }
}