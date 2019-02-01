package com.eebbk.bfc.demo.crypto.utilsmode;

import com.eebbk.bfc.crypto.symmetry.DESCrypto;
import com.eebbk.bfc.crypto.util.CharEncoding;

public class DESUtils {
    private static final String KEY="12345678";//加解密密钥，必须为8位
    private static final byte[] IV={1,22,55,22,78,66,8,9};//加解密偏移量，必须为8位

    //根据特殊需求填写不同的加密模式如“CBC”等，不同的填充模式如“PKCS5Padding”，偏移量为8位byte数组
    //new DESCrypto(KEY.getBytes(),"CBC","PKCS5Padding","12345678".getBytes());

    private DESUtils(){}

    public static byte[] decrypt(byte[] data){
        byte[] result=null;

        try {
            new DESCrypto(KEY.getBytes(CharEncoding.UTF_8),DESCrypto.SymmetryMode.CBC,DESCrypto.SymmetryPadding.PKCS5_PADDING,IV).encrypt(data);
            result=new DESCrypto(KEY.getBytes(CharEncoding.UTF_8)).decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptToString(byte[] data){
        String result=null;

        try {
            result=new DESCrypto(KEY.getBytes(CharEncoding.UTF_8)).decryptToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptToString(byte[] data ,String charsetName){
        String result=null;

        try {
            result=new DESCrypto(KEY.getBytes(CharEncoding.UTF_8)).decryptToString(data,charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new DESCrypto(KEY.getBytes(CharEncoding.UTF_8)).encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data){
        String result=null;

        try {
            result=new DESCrypto(KEY.getBytes(CharEncoding.UTF_8)).encryptToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String encryptToString(byte[] data,String charsetName){
        String result=null;

        try {
            result=new DESCrypto(KEY.getBytes(CharEncoding.UTF_8)).encryptToString(data,charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
