package hr.fer.zemris.ppp.crypto.action;

import algoritmi.xoodyak.XoodyakH;

public class Xoodyak extends AbstractAlgorithm {

    private XoodyakH algorithm;

    public Xoodyak() {
        algorithm = new XoodyakH();
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
        return "Xoodyak";
    }
}
