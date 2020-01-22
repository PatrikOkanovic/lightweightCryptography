package hr.fer.zemris.ppp.crypto.action;

import algoritmi.romulus.RomulusH;

public class Romulus extends AbstractAlgorithm {

    private RomulusH algorithm;

    public Romulus() {
        algorithm = new RomulusH();
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
        return "Romulus";
    }
}
