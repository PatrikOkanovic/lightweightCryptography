package algoritmi.gift;

public class GiftH{ 
 
    static{         
        System.loadLibrary("nativeGift");     } 
 
        public native byte[] encrypt(byte[] message, long messageLength,                                  
        byte[] aData, long aDataLength,                                  
        byte[] nonce, byte[] key); 
 
        public native byte[] decrypt(byte[] cipher, long cipherLength,                                  
        byte[] aData, long aDataLength,                                  
        byte[] nonce, byte[] key);
}