package hr.fer.zemris.ppp.crypto.component;

import hr.fer.zemris.ppp.crypto.CryptoUtil;

import javax.swing.*;

public class CryptoTextArea extends JTextArea {

    private byte[] bytes;
    private String text;

    public CryptoTextArea(int rows, int columns) {
        super(rows, columns);
        this.bytes = new byte[] {};
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void updateBytes(byte[] bytes, int blockSize) {
        this.bytes = bytes;
        this.text = new String(bytes, CryptoUtil.CHARSET);

        if (blockSize == -1) {
            setText(text);
        } else {
            setText(createOutputString(text, blockSize));
        }
    }

    public void joinTextWithoutBlocks() {
        setText(text);
    }

    public void divideTextByBlocks(int blockSize){
        setText(createOutputString(text, blockSize));
    }

    private String createOutputString(String text, int blockSize) {
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i += blockSize) {
            int limit = length <= (i + blockSize) ? length : (i + blockSize);
            sb.append(text, i, limit);
            if (limit < length) {
                sb.append("    ");
            }
        }
        return sb.toString();
    }
}