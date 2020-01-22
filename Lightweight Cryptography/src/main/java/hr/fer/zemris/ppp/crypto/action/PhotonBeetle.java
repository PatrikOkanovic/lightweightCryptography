package hr.fer.zemris.ppp.crypto.action;

import algoritmi.photonbeetle.PhotonBeetleH;

public class PhotonBeetle extends AbstractAlgorithm {

	private PhotonBeetleH algorithm;

    public PhotonBeetle() {
        algorithm = new PhotonBeetleH();
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
        return "PHOTON-Beetle";
    }
}
