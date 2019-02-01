package com.eebbk.bfc.demo.crypto.utilsmode;

import com.eebbk.bfc.crypto.asymmetry.RSACrypto;

public class RSAUtils {
    private RSAUtils(){}

    public static byte[] decryptByPrivateKeyFromBase64String(String key,String data){
        byte[] result=null;

        try {
            //new RSACrypto(RSACrypto.AsymmetryMode.ECB, RSACrypto.AsymmetryPadding.NO_PADDING)
            result=new RSACrypto().decryptByPrivateKeyFromBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] decryptByPrivateKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPrivateKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decryptByPublicKeyFromBase64String(String key,String data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPublicKeyFromBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptByPublicKeyToBase64String(String key,byte[] data){
        String result=null;

        try {
            result=new RSACrypto().encryptByPublicKeyToBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] decryptByPublicKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().decryptByPublicKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptByPrivateKeyToBase64String(String key,byte[] data){
        String result=null;

        try {
            result=new RSACrypto().encryptByPrivateKeyToBase64String(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encryptByPrivateKey(String key,byte[] data){
        byte[] result=null;

        try {
            result=new RSACrypto().encryptByPrivateKey(key,data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
