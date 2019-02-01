package com.eebbk.bfc.crypto.secretkey.asymmetry;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author liuyewu
 * @company EEBBK
 * @function RSA secret key Generator
 * @date 2016/11/13
 */
public class RSAKeyManager {

    private static final int RSA_KEY_LENGTH = 1024;
    private static final String ALGORITHM="RSA";

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    private int keyLength;

    /**
     * 默认密钥长度为1024
     */
    public RSAKeyManager() throws Exception{
        this.keyLength =RSA_KEY_LENGTH;
        this.init(ALGORITHM);
    }

    /**
     * 自定义密钥长度
     * @param keyLength 密钥长度为384~16384之间的任意值，建议使用{@link RASKeyLength}中的参数
     */
    public RSAKeyManager(int keyLength) throws Exception{
        this.keyLength =keyLength;
        this.init(ALGORITHM);
    }

    private void init(String algorithm) throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
        kpg.initialize(keyLength);
        KeyPair e = kpg.genKeyPair();

        this.setPrivateKey((RSAPrivateKey) e.getPrivate());
        this.setPublicKey((RSAPublicKey) e.getPublic());
    }

    public RSAPublicKey getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * 密钥长度为384~16384之间的任意值，建议使用以下值
     */
    public interface RASKeyLength{
        int LENGTH_1024=1024;
        int LENGTH_2048=2048;
        int LENGTH_3072=3072;
        int LENGTH_4096=4096;
        int LENGTH_5120=5120;
        int LENGTH_6144=6144;
        int LENGTH_7168=7168;
        int LENGTH_8192=8192;
        int LENGTH_9216=9216;
        int LENGTH_10240=10240;
    }
}
