package com.eebbk.bfc.crypto.secretkey.symmetry;

/**
 * @author liuyewu
 * @company EEBBK
 * @function DESede secret key Generator
 * @date 2016/11/13
 */
public class DESedeKeyManager extends SymmetryKeyManager{
    private static final String ALGORITHM="DESede";

    /**
     * 默认密钥长度为192
     * @throws Exception
     */
    public DESedeKeyManager() throws Exception{
        this.keyLength=DESedeKeyLength.LENGTH_192;
        this.init(ALGORITHM);
    }

    /**
     * 自定义密钥长度
     * @param keyLength 密钥长度只能是128,192中的一种 ，建议使用{@link DESedeKeyLength}中的常量值
     * @throws Exception
     */
    public DESedeKeyManager(int keyLength) throws Exception{
        this.keyLength=keyLength;
        this.init(ALGORITHM);
    }

    /**
     * DESede密钥长度只能是以下几种
     */
    public interface DESedeKeyLength{
        int LENGTH_128 =128;
        int LENGTH_192 =192;
    }
}
