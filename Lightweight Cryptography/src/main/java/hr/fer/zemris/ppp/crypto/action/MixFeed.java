package hr.fer.zemris.ppp.crypto.action;

import algoritmi.mixfeed.MixFeedH;

public class MixFeed extends AbstractAlgorithm {

    private MixFeedH algorithm;

    public MixFeed() {
        algorithm = new MixFeedH();
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
        return "MixFeed";
    }
}
