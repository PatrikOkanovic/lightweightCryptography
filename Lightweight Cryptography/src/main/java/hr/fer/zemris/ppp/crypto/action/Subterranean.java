package hr.fer.zemris.ppp.crypto.action;

import algoritmi.subterranean.SubterraneanH;

public class Subterranean extends AbstractAlgorithm {

    private SubterraneanH algorithm;

    public Subterranean() {
        algorithm = new SubterraneanH();
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
        return "subterranean";
    }
}
