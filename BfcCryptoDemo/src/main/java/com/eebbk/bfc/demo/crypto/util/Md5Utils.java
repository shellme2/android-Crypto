package com.eebbk.bfc.demo.crypto.util;

import com.eebbk.bfc.crypto.irreversible.MD5Crypto;

import java.io.File;

/**
 * @author liuyewu
 * @company EEBBK
 * @function md5 utils
 * @date 2016/11/24
 */
public class Md5Utils {

    private Md5Utils(){}

    public static String getMd5String(String data){
        String result=null;
        try {
            result= new MD5Crypto().encryptToString(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] getMd5(String data){
        byte[] result=null;
        try {
            result=  new MD5Crypto().encrypt(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] getFileMd5(File file){
        byte[] result=null;
        try {
            result=  new MD5Crypto().encryptFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getFileMd5String(File file){
        String result=null;
        try {
            result=  new MD5Crypto().encryptFileToString(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkMd5(String data,String md5){
        boolean result=false;
        try {
            result=  new MD5Crypto().checkMd5(data,md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkFileMd5(String filePath,String md5){
        boolean result=false;
        try {
            result=  new MD5Crypto().checkFileMD5(new File(filePath),md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
