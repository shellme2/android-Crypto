package com.eebbk.bfc.crypto.secretkey.symmetry;

/**
 * @author liuyewu
 * @company EEBBK
 * @function DES secret key Generator
 * @date 2016/11/13
 */
public class DESKeyManager extends SymmetryKeyManager{

    public static final int DES_KEY_LENGTH = 64;
    private static final String ALGORITHM="DES";

    public DESKeyManager() throws Exception {
        this.keyLength=DES_KEY_LENGTH;
        this.init(ALGORITHM);
    }

}
