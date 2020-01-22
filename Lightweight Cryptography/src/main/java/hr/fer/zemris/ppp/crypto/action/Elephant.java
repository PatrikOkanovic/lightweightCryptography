package hr.fer.zemris.ppp.crypto.action;

import algoritmi.elephant.ElephantH;

public class Elephant extends AbstractAlgorithm {

    private ElephantH algorithm;

    public Elephant() {
        algorithm = new ElephantH();
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
        return "Elephant";
    }
}
