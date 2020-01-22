package algoritmi.locus_lotus;
public class LotusH{
 static{
 System.loadLibrary("nativeLotus");
 }
 public native byte[] encrypt(byte[] message, long messageLength,
 byte[] aData, long aDataLength,
 byte[] nonce, byte[] key);
 public native byte[] decrypt(byte[] cipher, long cipherLength,
 byte[] aData, long aDataLength,
 byte[] nonce, byte[] key);
}
