package com.eebbk.bfc.demo.crypto.util;

import com.eebbk.bfc.crypto.symmetry.AESCrypto;

/**
 * @author liuyewu
 * @company EEBBK
 * @function md5 utils
 * @date 2016/11/24
 */
public class AESUtils {
    private static final String KEY="12345678abcdefgh";//16位

    //根据特殊需求填写不同的加密模式如“CBC”等，不同的填充模式如“PKCS5Padding”，偏移量为16位byte数组
    //desCrypto=new AESCrypto(KEY.getBytes(),"CBC","PKCS5Padding","1234567812345678".getBytes());

    private AESUtils(){}

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new AESCrypto(KEY.getBytes()).encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encrypt(String data){
        String result=null;

        try {
            result=new AESCrypto(KEY.getBytes()).encryptToString(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decrypt(byte[] data){
        byte[] result=null;

        try {
            result=new AESCrypto(KEY.getBytes()).decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String data){
        String result=null;

        try {
            result=new AESCrypto(KEY.getBytes()).decryptToString(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
