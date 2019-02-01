package com.eebbk.bfc.crypto.secretkey.symmetry;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author liuyewu
 * @company EEBBK
 * @function 对称加密基础类
 * @date 2016/12/30
 */
class SymmetryKeyManager {

    private SecretKey secretKey;
    int keyLength;

    void init(String algorithm ) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            kgen.init(keyLength);

            SecretKey key = kgen.generateKey();
            this.setSecretKey(key);
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
