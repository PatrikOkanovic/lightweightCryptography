package hr.fer.zemris.ppp.crypto.action;

import hr.fer.zemris.ppp.crypto.CryptoUtil;

import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Paths;

public abstract class AbstractAlgorithm {

    static {
        initJavaLibraryPath();
    }

    protected byte[] msgBytes;
    protected byte[] keyBytes;
    protected byte[] aData = "".getBytes(CryptoUtil.CHARSET);
    protected byte[] nonce = "000102030405060708090A0B0C0D0E0F".getBytes(CryptoUtil.CHARSET);
    protected byte[] byteResult;

    public abstract void encrypt();

    public abstract void decrypt();

    protected static void initJavaLibraryPath() {
        String string = Paths.get(".").toAbsolutePath().toString();
        String path = (string.substring(0, string.length() - 1) + "native\\").replaceAll("%20", " ");//jer sam genije koji foldere naziva "5. semestar" (s razmakom)
        System.setProperty("java.library.path", path);

        try {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            System.err.println("Native code error.");
        }
    }

    public byte[] getMsgBytes() {
        return msgBytes;
    }

    public void setMsgBytes(byte[] msgBytes) {
        this.msgBytes = msgBytes;
    }

    public byte[] getKeyBytes() {
        return keyBytes;
    }

    public void setKeyBytes(byte[] keyBytes) {
        this.keyBytes = keyBytes;
    }

    public byte[] getByteResult() {
        return byteResult;
    }
}
