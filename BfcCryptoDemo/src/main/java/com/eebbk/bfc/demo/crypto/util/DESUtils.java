package com.eebbk.bfc.demo.crypto.util;

import com.eebbk.bfc.crypto.symmetry.DESCrypto;

/**
 * @author liuyewu
 * @company EEBBK
 * @function md5 utils
 * @date 2016/11/24
 */
public class DESUtils {
    private static final String KEY="12345678";//8位

    //根据特殊需求填写不同的加密模式如“CBC”等，不同的填充模式如“PKCS5Padding”，偏移量为8位byte数组
    //new DESCrypto(KEY.getBytes(),"CBC","PKCS5Padding","12345678".getBytes());

    private DESUtils(){}

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new DESCrypto(KEY.getBytes()).encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encrypt(String data){
        String result=null;

        try {
            result=new DESCrypto(KEY.getBytes()).encryptToString(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decrypt(byte[] data){
        byte[] result=null;

        try {
            result=new DESCrypto(KEY.getBytes()).decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String data){
        String result=null;

        try {
            result=new DESCrypto(KEY.getBytes()).decryptToString(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
