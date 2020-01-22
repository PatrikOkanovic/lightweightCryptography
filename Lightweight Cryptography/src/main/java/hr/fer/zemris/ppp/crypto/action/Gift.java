package hr.fer.zemris.ppp.crypto.action;

import algoritmi.gift.GiftH;

public class Gift extends AbstractAlgorithm {

    private GiftH algorithm;

    public Gift() {
        this.algorithm = new GiftH();
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
        return "Gift";
    }
}
