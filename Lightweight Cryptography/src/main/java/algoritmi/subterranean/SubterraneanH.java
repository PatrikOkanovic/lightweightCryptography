package algoritmi.subterranean;

public class SubterraneanH {
	
	static{
		System.loadLibrary("nativeSubterranean");
	}
	
	public native byte[] encrypt(
		byte[] message, long messageLength,
		byte[] aData, long aDataLength,
		byte[] nonce, byte[] key
	);
	
	public native byte[] decrypt(
		byte[] cipher, long cipherLength,
		byte[] aData, long aDataLength,
		byte[] nonce, byte[] key
	);

}