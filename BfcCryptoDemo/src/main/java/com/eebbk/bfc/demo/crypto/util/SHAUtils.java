package com.eebbk.bfc.demo.crypto.util;

import com.eebbk.bfc.crypto.irreversible.SHACrypto;

import java.io.File;

/**
 * @author liuyewu
 * @company EEBBK
 * @function md5 utils
 * @date 2016/11/24
 */
public class SHAUtils {

    private SHAUtils(){}

    public static String getSHAString(String mode,String data){
        String result=null;
        try {
            result= new SHACrypto(mode).encryptToString(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] getSHA(String mode,String data){
        byte[] result=null;
        try {
            result= new SHACrypto(mode).encrypt(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] getFileSHA(String mode,File file){
        byte[] result=null;
        try {
            result= new SHACrypto(mode).encryptFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getFileSHAString(String mode,File file){
        String result=null;
        try {
            result= new SHACrypto(mode).encryptFileToString(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkSHA(String mode,String data,String SHA){
        boolean result=false;
        try {
            result= new SHACrypto(mode).checkSHA(data,SHA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkFileSHA(String mode,String filePath,String SHA){
        boolean result=false;
        try {
            result= new SHACrypto(mode).checkFileSHA(new File(filePath),SHA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
