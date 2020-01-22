package hr.fer.zemris.ppp.crypto.action;

import algoritmi.orange.OrangeH;

public class Orange extends AbstractAlgorithm {

    private OrangeH algorithm;

    public Orange() {
        algorithm = new OrangeH();
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
        return "Orange";
    }
}
