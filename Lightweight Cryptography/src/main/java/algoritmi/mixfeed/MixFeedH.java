package algoritmi.mixfeed;
public class MixFeedH{
 static{
 System.loadLibrary("nativeMixfeed");
 }
 public native byte[] encrypt(byte[] message, long messageLength,
 byte[] aData, long aDataLength,
 byte[] nonce, byte[] key);
 public native byte[] decrypt(byte[] cipher, long cipherLength,
 byte[] aData, long aDataLength,
 byte[] nonce, byte[] key);
}
