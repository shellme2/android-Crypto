package com.eebbk.bfc.demo.crypto.utilsmode;

import com.eebbk.bfc.crypto.symmetry.DESedeCrypto;
import com.eebbk.bfc.crypto.util.CharEncoding;

public class DESedeUtils {
    private static final String KEY = "010203040506070812345678";//16或者是24位

    private static final byte[] IV={1,22,55,22,78,66,8,9};//加解密偏移量，必须为8位

    private DESedeUtils(){}

    public static byte[] decrypt(byte[] data){
        byte[] result=null;

        try {
            new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8),DESedeCrypto.SymmetryMode.CBC,DESedeCrypto.SymmetryPadding.PKCS5_PADDING,IV).encrypt(data);
            result=new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8)).decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptToString(byte[] data){
        String result=null;

        try {
            result=new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8)).decryptToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decryptToString(byte[] data ,String charsetName){
        String result=null;

        try {
            result=new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8)).decryptToString(data,charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8)).encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data){
        String result=null;

        try {
            result=new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8)).encryptToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String encryptToString(byte[] data,String charsetName){
        String result=null;

        try {
            result=new DESedeCrypto(KEY.getBytes(CharEncoding.UTF_8)).encryptToString(data,charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
