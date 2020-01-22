package hr.fer.zemris.ppp.crypto.component;

import hr.fer.zemris.ppp.crypto.CryptoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Path;

public class CryptoPanel extends JPanel {

    private FileLoadingPanel inputPanel;
    private FileLoadingPanel outputPanel;

    public CryptoPanel() {
        setLayout(new GridLayout(0, 1));
        add(createInputPanel());
        add(createOutputPanel());
    }

    private FileLoadingPanel createInputPanel() {
        inputPanel = new FileLoadingPanel("Ulaz:", "Odaberi ulaz");

        inputPanel.getTextArea().setRows(6);
        inputPanel.getButton().setAction(createOpenAction(inputPanel));

        return inputPanel;
    }

    private Action createOpenAction(FileLoadingPanel panel) {
        return new AbstractAction(panel.getButton().getText()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser("examples");
                jfc.setDialogTitle("Ucitaj ulaznu datoteku");
                if (jfc.showOpenDialog(CryptoPanel.this) != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                Path filePath = jfc.getSelectedFile().toPath();

                if (!Files.exists(filePath)) {
                    JOptionPane.showMessageDialog(
                            CryptoPanel.this, "Zeljena datoteka ne postoji.",
                            "Greska", JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                CryptoUtil.updatePanel(panel, filePath);
            }
        };
    }

    private FileLoadingPanel createOutputPanel() {
        outputPanel = new FileLoadingPanel("Izlaz:", "Odaberi izlaz");

        outputPanel.getTextArea().setRows(6);
        outputPanel.getButton().setAction(createSaveAction(outputPanel));

        return outputPanel;
    }

    private Action createSaveAction(FileLoadingPanel panel) {
        return new AbstractAction(panel.getButton().getText()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser("examples");
                jfc.setDialogTitle("Spremi u datoteku");
                if (jfc.showSaveDialog(CryptoPanel.this) != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                Path newPath = jfc.getSelectedFile().toPath();
                if (Files.exists(newPath)) {
                    if (getOverwriteConfirmation() == JOptionPane.NO_OPTION) {
                        return;
                    }
                }

                panel.getPathTextArea().setText(newPath.toString());
            }

            private int getOverwriteConfirmation() {
                return JOptionPane.showOptionDialog(
                        CryptoPanel.this,
                        "Ta datoteka vec postoji, prebrisati ju?",
                        "Prebrisati?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        new String[] { "Da", "Ne" },
                        JOptionPane.YES_OPTION
                );
            }
        };
    }

    public FileLoadingPanel getInputPanel() {
        return inputPanel;
    }

    public FileLoadingPanel getOutputPanel() {
        return outputPanel;
    }
}