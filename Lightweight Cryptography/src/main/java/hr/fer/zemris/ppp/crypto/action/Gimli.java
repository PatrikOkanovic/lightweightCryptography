package hr.fer.zemris.ppp.crypto.action;

import algoritmi.gimli.GimliH;

public class Gimli extends AbstractAlgorithm {

    private GimliH algorithm;

    public Gimli() {
        algorithm = new GimliH();
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
        return "Gimli";
    }
}
