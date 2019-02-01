package com.eebbk.bfc.demo.crypto.utilsmode;

import com.eebbk.bfc.crypto.irreversible.MD5Crypto;
import java.io.File;

public class Md5Utils {

    private Md5Utils(){}

    public static byte[] encrypt(byte[] data){
        byte[] result=null;

        try {
            result=new MD5Crypto().encrypt(data);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data){
        String result=null;

        try {
            result=new MD5Crypto().encryptToString(data);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptToString(byte[] data,boolean isLower){
        String result=null;

        try {
            result=new MD5Crypto().encryptToString(data,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkMd5(String data, String md5String){
        boolean result=false;

        try {
            result=new MD5Crypto().checkMd5(data,md5String);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkMd5(byte[] data, String md5String){
        boolean result=false;

        try {
            result=new MD5Crypto().checkMd5(data,md5String);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkMd5(byte[] data, String md5String,boolean isLower){
        boolean result=false;

        try {
            result=new MD5Crypto().checkMd5(data,md5String,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkMd5(byte[] data, byte[] md5Byte){
        boolean result=false;

        try {
            result=new MD5Crypto().checkMd5(data,md5Byte);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encryptFile(File file){
        byte[] result=null;

        try {
            result=new MD5Crypto().encryptFile(file);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptFileToString(File file){
        String result=null;

        try {
            result=new MD5Crypto().encryptFileToString(file);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static String encryptFileToString(File file,boolean isLower){
        String result=null;

        try {
            result=new MD5Crypto().encryptFileToString(file,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkFileMD5(File file, String fileMd5String){
        boolean result=false;

        try {
            result=new MD5Crypto().checkFileMD5(file,fileMd5String);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkFileMD5(File file, String fileMd5String ,boolean isLower){
        boolean result=false;

        try {
            result=new MD5Crypto().checkFileMD5(file,fileMd5String,isLower);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }

    public static boolean checkFileMD5(File file, byte[] fileMd5Byte){
        boolean result=false;

        try {
            result=new MD5Crypto().checkFileMD5(file,fileMd5Byte);
        } catch (Exception e) {
            //相应处理
            e.printStackTrace();
        }

        return result;
    }
}
