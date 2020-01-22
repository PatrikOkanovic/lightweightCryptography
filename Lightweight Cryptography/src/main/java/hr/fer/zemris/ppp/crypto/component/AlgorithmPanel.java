package hr.fer.zemris.ppp.crypto.component;

import hr.fer.zemris.ppp.crypto.CryptoAlgorithmLoader;
import hr.fer.zemris.ppp.crypto.CryptoUtil;
import hr.fer.zemris.ppp.crypto.action.AbstractAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AlgorithmPanel extends JPanel {

    private Properties properties;
    private JComboBox<AbstractAlgorithm> comboBox;
    private JTextArea descriptionTextArea;

    public AlgorithmPanel() {
        properties = initProperties();
        setLayout(new BorderLayout());
        add(createDescriptionPanel(), BorderLayout.CENTER);
        add(createChoicePanel(), BorderLayout.PAGE_START);
    }

    private Properties initProperties() {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("config\\algorithm.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    private JPanel createChoicePanel() {
        JPanel choicePanel = new JPanel();
        choicePanel.setBackground(CryptoUtil.LIGHT_BLUE);
        choicePanel.add(createAlgorithmLabel());
        choicePanel.add(createComboBox());
        return choicePanel;
    }

    private JLabel createAlgorithmLabel() {
        JLabel algorithmLabel = new JLabel("Algoritam: ");
        algorithmLabel.setFont(CryptoUtil.BOLD);
        return algorithmLabel;
    }

    private JComboBox<AbstractAlgorithm> createComboBox() {
        AbstractAlgorithm[] algorithms = CryptoAlgorithmLoader.load();
        comboBox = new JComboBox<>(algorithms);
        comboBox.setFont(CryptoUtil.PLAIN);
        comboBox.addActionListener(createComboBoxListener());
        comboBox.setSelectedIndex(0);
        return comboBox;
    }

    private ActionListener createComboBoxListener() {
        return e -> {
            AbstractAlgorithm algorithm = (AbstractAlgorithm) comboBox.getSelectedItem();
            String name = algorithm.toString();
            StringBuilder sb = new StringBuilder();
            sb.append("Velicina kljuca primarnog algoritma: ").append("\t").append(properties.get(name+"."+"primaryKeySize")).append("\n");
            sb.append("Alternativne velicine kljuceva:      ").append("\t").append(properties.get(name+"."+"otherKeySizes")).append("\n");
            sb.append("Velicina bloka:                      ").append("\t").append(properties.get(name+"."+"blockSize")).append("\n");
            sb.append("Metoda nadopune zadnjeg bloka:       ").append("\t").append(properties.get(name+"."+"padding")).append("\n");
            sb.append("Implementiran algoritam HASH:        ").append("\t").append(properties.get(name+"."+"hash")).append("\n");
            descriptionTextArea.setText(sb.toString());
        };
    }

    private JPanel createDescriptionPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(CryptoUtil.LIGHT_BLUE);
        panel.add(createDescriptionLabel(), JPanel.LEFT_ALIGNMENT);
        panel.add(createDescriptionTextArea());
        return panel;
    }

    private JLabel createDescriptionLabel() {
        JLabel label = new JLabel("Opis:");
        label.setFont(CryptoUtil.PLAIN);
        label.setPreferredSize(new Dimension(50, 30));
        return label;
    }

    private JScrollPane createDescriptionTextArea() {
        descriptionTextArea = new JTextArea(5, 45);
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setFont(CryptoUtil.PLAIN);
        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        JPanel panel = new JPanel();
        panel.setBackground(CryptoUtil.LIGHT_BLUE);
        panel.add(descriptionTextArea);
        return new JScrollPane(panel);
    }

    public JComboBox<AbstractAlgorithm> getComboBox() {
        return comboBox;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
}
