package hr.fer.zemris.ppp.crypto.action;

import algoritmi.ace.AceH;

public class Ace extends AbstractAlgorithm {

    private AceH algorithm;

    public Ace() {
        this.algorithm = new AceH();
    }

    @Override
    public void encrypt() {
        byteResult = algorithm.encrypt(msgBytes, msgBytes.length, aData, aData.length, nonce, keyBytes);
    }

    @Override
    public void decrypt() {
        byteResult = algorithm.decrypt(msgBytes, msgBytes.length, aData, aData.length, nonce, keyBytes);
    }

    @Override
    public String toString() {
        return "Ace";
    }
}