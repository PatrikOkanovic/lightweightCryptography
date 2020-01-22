package hr.fer.zemris.ppp.crypto.action;

import algoritmi.hyena.HyenaH;

public class Hyena extends AbstractAlgorithm {

    private HyenaH algorithm;

    public Hyena() {
        algorithm = new HyenaH();
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
        return "Hyena";
    }
}
