package com.eebbk.bfc.demo.crypto.utilsmode;

import com.eebbk.bfc.crypto.irreversible.SHACrypto;
import java.io.File;

public class SHAUtils {

    private SHAUtils(){}

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new SHACrypto().encrypt(data);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data){
        String result=null;

        try {
            result=new SHACrypto().encryptToString(data);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data,boolean isLower){
        String result=null;

        try {
            result=new SHACrypto().encryptToString(data,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkSHA(String data, String shaString){
        boolean result=false;

        try {
            result=new SHACrypto().checkSHA(data,shaString);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkSHA(byte[] data, String shaString){
        boolean result=false;

        try {
            result=new SHACrypto().checkSHA(data,shaString);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkSHA(byte[] data, String shaString,boolean isLower){
        boolean result=false;

        try {
            result=new SHACrypto().checkSHA(data,shaString,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkSHA(byte[] data, byte[] shaByte){
        boolean result=false;

        try {
            result=new SHACrypto().checkSHA(data,shaByte);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encryptFile(File file){
        byte[] result=null;

        try {
            result=new SHACrypto().encryptFile(file);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptFileToString(File file){
        String result=null;

        try {
            result=new SHACrypto().encryptFileToString(file);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptFileToString(File file,boolean isLower){
        String result=null;

        try {
            result=new SHACrypto().encryptFileToString(file,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkFileSHA(File file, String fileSHAString){
        boolean result=false;

        try {
            result=new SHACrypto().checkFileSHA(file,fileSHAString);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkFileSHA(File file, String fileSHAString ,boolean isLower){
        boolean result=false;

        try {
            result=new SHACrypto().checkFileSHA(file,fileSHAString,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkFileSHA(File file, byte[] fileSHAByte){
        boolean result=false;

        try {
            result=new SHACrypto().checkFileSHA(file,fileSHAByte);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }
}
