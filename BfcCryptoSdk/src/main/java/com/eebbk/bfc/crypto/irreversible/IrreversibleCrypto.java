package com.eebbk.bfc.crypto.irreversible;

import android.text.TextUtils;

import com.eebbk.bfc.crypto.Encrypt;
import com.eebbk.bfc.crypto.exception.ParameterException;
import com.eebbk.bfc.crypto.util.HexUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @author liuyewu
 * @company EEBBK
 * @function 不可逆加密算法
 * @date 2016/12/29
 */
class IrreversibleCrypto implements Encrypt {

    MessageDigest messageDigest;

//------------------------------------------------------------------------------------------------
//--------文件部分---------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
    /**
     * 对byte数组进行摘要加密
     * @param data 加密的byte数组
     * @return byte数组的摘要值
     */
    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        messageDigest.update(data);
        return messageDigest.digest();
    }

    /**
     * 对byte数组进行摘要加密
     * @param data 加密的byte数组
     * @return 转换后的字符串 ,默认为小写
     */
    @Override
    public String encryptToString(byte[] data) throws Exception {
        return HexUtils.byteToString(encrypt(data),true);
    }

    /**
     * 对byte数组进行摘要加密
     * @param data 加密的byte数组
     * @param isLower 大小写选择，true：小写；false：大写
     * @return 转换后的字符串
     */
    public String encryptToString(byte[] data ,boolean isLower) throws Exception {
        return HexUtils.byteToString(encrypt(data),isLower);
    }
    /**
     * 判断字符串的摘要校验码是否与一个已知的摘要码相匹配
     * @param data 原字符串
     * @param str 摘要值 字符串,注意摘要值的转换方式与这本类中的转换一致
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    boolean check(String data, String str) throws Exception  {
        String s = encryptToString(data.getBytes());
        return s.equals(str);
    }

    /**
     * 判断byte数组的摘要校验码是否与一个已知的摘要码相匹配
     * @param data 原byte数组
     * @param str 摘要值 字符串,注意摘要值的转换方式与这本类中的转换一致,默认以小写对比
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    boolean check(byte[] data, String str) throws Exception  {
        String s = encryptToString(data);
        return s.equals(str);
    }

    /**
     * 判断byte数组的摘要校验码是否与一个已知的摘要码相匹配
     * @param data 原byte数组
     * @param str 摘要值 字符串,注意摘要值的转换方式与这本类中的转换一致,默认以小写对比
     * @param isLower 大小写选择，true：小写；false：大写
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    boolean check(byte[] data, String str,boolean isLower) throws Exception  {
        String s = encryptToString(data,isLower);
        return s.equals(str);
    }

    /**
     * 判断byte数组的摘要校验码是否与一个已知的摘要 byte数组码相匹配
     * @param data 原数组
     * @param bt 摘要值 字节数组
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    boolean check(byte[] data, byte[] bt) throws Exception  {
        String s = encryptToString(data);
        return s.equals(HexUtils.byteToString(bt,true));
    }
//------------------------------------------------------------------------------------------------
//--------文件部分---------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
    /**
     * 对文件进行摘要加密
     * @param file 加密的文件
     * @return byte数组的摘要值
     * @throws Exception 可能出现文件异常和加密异常
     */
    public byte[] encryptFile(File file) throws Exception {
        InputStream fis=null;
        byte[] result=null;
        try{
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int numRead ;

            while ((numRead = fis.read(buffer)) > 0) {
                messageDigest.update(buffer, 0, numRead);
            }

            result=messageDigest.digest();
        }finally {
            if(fis!=null) {
                fis.close();
            }
        }

        return result;
    }

    /**
     * 对文件进行摘要加密
     * @param file 加密的文件
     * @return 转换后的字符串，默认小写
     * @throws Exception 可能出现文件异常和加密异常
     */
    public String encryptFileToString(File file) throws Exception {
        return HexUtils.byteToString(encryptFile(file),true);
    }

    /**
     * 对文件进行摘要加密
     * @param file 加密的文件
     * @param isLower  大小写选择，true：小写；false：大写
     * @return 转换后的字符串
     * @throws Exception 可能出现文件异常和加密异常
     */
    public String encryptFileToString(File file,boolean isLower) throws Exception {
        return HexUtils.byteToString(encryptFile(file),isLower);
    }

    /**
     * 对文件摘要值进行校验
     * @param file 加密的文件
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    boolean checkFile(File file, String fileString) throws Exception {
        boolean ret = false;

        String result = encryptFileToString(file);
        if (!TextUtils.isEmpty(result) && result.equals(fileString)) {
            ret = true;
        }

        return ret;
    }

    /**
     * 对文件摘要值进行校验
     * @param file 加密的文件
     * @return 是否校验成功：true成功；false失败
     * @param isLower  大小写选择，true：小写；false：大写
     * @throws Exception 可能出现文件异常和加密异常
     */
    boolean checkFile(File file, String fileString,boolean isLower) throws Exception {
        boolean ret = false;

        String result = encryptFileToString(file,isLower);
        if (!TextUtils.isEmpty(result) && result.equals(fileString)) {
            ret = true;
        }

        return ret;
    }

    /**
     * 对文件摘要值进行校验
     * @param file 加密的文件
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    boolean checkFile(File file, byte[] fileByte) throws Exception {
        return checkFile(file, HexUtils.byteToString(fileByte,true));
    }

}
