package com.eebbk.bfc.demo.crypto.utilsmode;

import com.eebbk.bfc.crypto.symmetry.AESCrypto;
import com.eebbk.bfc.crypto.util.CharEncoding;

public class AESUtils {
    private static final String KEY="12345678abcdefgh";//16,24,32中的一种

    private static final byte[] IV={1,22,55,22,78,66,8,9,1,2,3,4,5,6,7,87};//加解密偏移量，必须为16位

    private AESUtils(){}

    public static byte[] decrypt(byte[] data){
        byte[] result=null;

        try {
            new AESCrypto(KEY.getBytes(CharEncoding.UTF_8),AESCrypto.SymmetryMode.CBC,AESCrypto.SymmetryPadding.PKCS5_PADDING,IV).encrypt(data);
            result=new AESCrypto(KEY.getBytes(CharEncoding.UTF_8)).decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptToString(byte[] data){
        String result=null;

        try {
            result=new AESCrypto(KEY.getBytes(CharEncoding.UTF_8)).decryptToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptToString(byte[] data ,String charsetName){
        String result=null;

        try {
            result=new AESCrypto(KEY.getBytes(CharEncoding.UTF_8)).decryptToString(data,charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new AESCrypto(KEY.getBytes(CharEncoding.UTF_8)).encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data){
        String result=null;

        try {
            result=new AESCrypto(KEY.getBytes(CharEncoding.UTF_8)).encryptToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String encryptToString(byte[] data,String charsetName){
        String result=null;

        try {
            result=new AESCrypto(KEY.getBytes(CharEncoding.UTF_8)).encryptToString(data,charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
