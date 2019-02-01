package com.eebbk.bfc.demo.crypto.util;

import android.util.Log;

import com.eebbk.bfc.crypto.asymmetry.RSACrypto;

/**
 * @author liuyewu
 * @company EEBBK
 * @function RSA Utils
 * @date 2016/11/28
 */
public class RSAUtils {
    private RSAUtils(){}

//    private static RSACrypto rsaCrypto;
//    static {
//        rsaCrypto=new RSACrypto();
//    }

    public static byte[] encryptByPublicKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().encryptByPublicKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }

    public static String encryptByPublicKeyToBase64String(String key,byte[] data){
        String result=null;

        try {
            result=new RSACrypto().encryptByPublicKeyToBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }

    public static byte[] encryptByPrivateKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().encryptByPrivateKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }

    public static String encryptByPrivateKeyToBase64String(String key,byte[] data){
        String result=null;

        try {
            result=new RSACrypto().encryptByPrivateKeyToBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }



    public static byte[] decryptByPublicKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPublicKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }

    public static byte[] decryptByPublicKeyFromBase64String(String key,String data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPublicKeyFromBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }

    public static byte[] decryptByPrivateKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPrivateKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }

    public static byte[] decryptByPrivateKeyFromBase64String(String key,String data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPrivateKeyFromBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("liuyewu",e.getMessage());
        }
        return result;
    }
}
