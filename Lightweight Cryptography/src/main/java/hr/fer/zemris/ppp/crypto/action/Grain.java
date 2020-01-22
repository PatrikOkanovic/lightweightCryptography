package hr.fer.zemris.ppp.crypto.action;

import algoritmi.grain.GrainH;

public class Grain extends AbstractAlgorithm {

    private GrainH algorithm;

    public Grain() {
        algorithm = new GrainH();
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
        return "Grain";
    }
}
