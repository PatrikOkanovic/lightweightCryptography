package hr.fer.zemris.ppp.crypto;

import hr.fer.zemris.ppp.crypto.action.AbstractAlgorithm;
import hr.fer.zemris.ppp.crypto.action.Ace;
import hr.fer.zemris.ppp.crypto.action.Elephant;
import hr.fer.zemris.ppp.crypto.action.Gift;
import hr.fer.zemris.ppp.crypto.action.Gimli;
import hr.fer.zemris.ppp.crypto.action.Grain;
import hr.fer.zemris.ppp.crypto.action.Hyena;
import hr.fer.zemris.ppp.crypto.action.Lotus;
import hr.fer.zemris.ppp.crypto.action.MixFeed;
import hr.fer.zemris.ppp.crypto.action.Orange;
import hr.fer.zemris.ppp.crypto.action.PhotonBeetle;
import hr.fer.zemris.ppp.crypto.action.Romulus;
import hr.fer.zemris.ppp.crypto.action.Spook;
import hr.fer.zemris.ppp.crypto.action.Subterranean;
import hr.fer.zemris.ppp.crypto.action.Xoodyak;

public class CryptoAlgorithmLoader {
	
	public static AbstractAlgorithm[] load() {
		return new AbstractAlgorithm[] {
			new Ace(),
			new Elephant(),
			new Gift(),
			new Gimli(),
			new Grain(),
			new Hyena(),
			new Lotus(),
			new MixFeed(),
			new Orange(),
			new PhotonBeetle(),
			new Romulus(),
			new Spook(),
			new Subterranean(),
			new Xoodyak()
		};
	}

}
