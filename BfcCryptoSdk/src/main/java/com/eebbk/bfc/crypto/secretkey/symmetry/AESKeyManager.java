package com.eebbk.bfc.crypto.secretkey.symmetry;

/**
 * @author liuyewu
 * @company EEBBK
 * @function AES secret key generator
 * @date 2016/11/13
 */
public class AESKeyManager extends SymmetryKeyManager{

    private static final String ALGORITHM="AES";

    /**
     * 默认密钥长度为128
     * @throws Exception
     */
    public AESKeyManager() throws Exception{
        this.keyLength=AESKeyLength.LENGTH_128;
        this.init(ALGORITHM);
    }

    /**
     * 自定义密钥长度
     * @param keyLength 密钥长度只能是128,192,256中的一种 ，建议使用{@link AESKeyLength}中的常量值
     * @throws Exception
     */
    public AESKeyManager(int keyLength) throws Exception {
        this.keyLength=keyLength;
        this.init(ALGORITHM);
    }

    /**
     * AES密钥长度只能是以下几种
     */
    public interface AESKeyLength{
        int LENGTH_128 =128;
        int LENGTH_192 =192;
        int LENGTH_256 =256;
    }
}
