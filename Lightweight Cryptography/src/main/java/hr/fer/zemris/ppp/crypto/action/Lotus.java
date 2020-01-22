package hr.fer.zemris.ppp.crypto.action;

import algoritmi.locus_lotus.LotusH;

public class Lotus extends AbstractAlgorithm {

    private LotusH algorithm;

    public Lotus() {
        algorithm = new LotusH();
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
        return "Locus-Lotus";
    }
}
