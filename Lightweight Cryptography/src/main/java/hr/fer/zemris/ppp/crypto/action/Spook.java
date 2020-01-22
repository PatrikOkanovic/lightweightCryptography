package hr.fer.zemris.ppp.crypto.action;

import algoritmi.spook.SpookH;

public class Spook extends AbstractAlgorithm {

    private SpookH algorithm;

    public Spook() {
        algorithm = new SpookH();
    }

    public void encrypt() {
        byteResult = algorithm.encrypt(msgBytes, msgBytes.length, aData, aData.length, nonce, keyBytes);
    }

    @Override
    public void decrypt() {
        byteResult = algorithm.decrypt(msgBytes, msgBytes.length, aData, aData.length, nonce, keyBytes);
    }

    @Override
    public String toString() {
        return "Spook";
    }
}
