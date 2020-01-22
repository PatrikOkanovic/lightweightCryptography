package hr.fer.zemris.ppp.crypto;

import hr.fer.zemris.ppp.crypto.component.FileLoadingPanel;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CryptoUtil {

    public static final Font BOLD = new Font("Verdana", Font.BOLD, 15);
    public static final Font PLAIN = new Font("Verdana", Font.PLAIN, 15);
    public static final Color LIGHT_BLUE = new Color(216, 235, 255);
    public static final Charset CHARSET = StandardCharsets.US_ASCII;

    public static void updatePanel(FileLoadingPanel panel, Path filePath) {
        panel.getPathTextArea().setText(filePath.toString());
        panel.getTextArea().updateBytes(readFile(filePath), -1);
    }

    private static byte[] readFile(Path filePath) {
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Greska pri citanju datoteke " + filePath + "!");
        }
    }
}

