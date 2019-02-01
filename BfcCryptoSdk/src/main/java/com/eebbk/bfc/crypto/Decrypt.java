package com.eebbk.bfc.crypto;

/**
 * @author liuyewu
 * @company EEBBK
 * @function Decrypt interface
 * @date 2016/11/13
 */
public interface Decrypt {
    byte[] decrypt(byte[] data) throws Exception;
    String decryptToString(byte[] data) throws Exception;
}
