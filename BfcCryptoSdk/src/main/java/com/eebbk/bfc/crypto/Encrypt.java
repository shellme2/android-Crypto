package com.eebbk.bfc.crypto;

/**
 * @author liuyewu
 * @company EEBBK
 * @function Encrypt interface
 * @date 2016/11/13
 */
public interface Encrypt {
    byte[] encrypt(byte[] data) throws Exception;
    String encryptToString(byte[] data) throws Exception;
}
