package com.eebbk.bfc.crypto.irreversible;

import java.io.File;
import java.security.MessageDigest;

/**
 * @author liuyewu
 * @company EEBBK
 * @function md5 code
 * @date 2016/11/13
 */
public class MD5Crypto extends IrreversibleCrypto {
    private static final String KEY_MD5 = "MD5";

    /**
     * MD5构造器
     */
    public MD5Crypto() throws Exception {
        messageDigest=MessageDigest.getInstance(KEY_MD5);
    }

//------------------------------------------------------------------------------------------------
//--------文件部分---------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     * @param data 原字符串
     * @param md5String MD5 字符串,注意md5String的转换方式与这本类中的转换一致
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkMd5(String data, String md5String) throws Exception  {
        return check(data,md5String);
    }

    /**
     * 判断byte数组的md5校验码是否与一个已知的md5码相匹配
     * @param data 原byte数组
     * @param md5String MD5 字符串,注意md5String的转换方式与这本类中的转换一致
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkMd5(byte[] data, String md5String) throws Exception  {
        return check(data,md5String);
    }

    /**
     * 判断byte数组的md5校验码是否与一个已知的md5码相匹配
     * @param data 原byte数组
     * @param md5String MD5 字符串,注意md5String的转换方式与这本类中的转换一致
     * @param isLower 大小写选择，true：小写；false：大写
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkMd5(byte[] data, String md5String,boolean isLower) throws Exception  {
        return check(data,md5String,isLower);
    }
    /**
     * 判断byte数组的md5校验码是否与一个已知的md5 byte数组码相匹配
     * @param data 原数组
     * @param md5Byte MD5 byte数组,注意md5Byte是没被转换过的，若已经转换的，一定要确保还原
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkMd5(byte[] data, byte[] md5Byte) throws Exception  {
        return check(data,md5Byte);
    }
//------------------------------------------------------------------------------------------------
//--------文件部分---------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
    /**
     * 对文件MD5值进行校验
     * @param file 校验文件
     * @param fileMd5String 要校验的md5值
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    public boolean checkFileMD5(File file, String fileMd5String) throws Exception {
        return checkFile(file,fileMd5String);
    }

    /**
     * 对文件MD5值进行校验
     * @param file 校验文件
     * @param fileMd5String 要校验的md5值
     * @param isLower  大小写选择，true：小写；false：大写
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    public boolean checkFileMD5(File file, String fileMd5String ,boolean isLower) throws Exception {
        return checkFile(file,fileMd5String,isLower);
    }

    /**
     * 对文件MD5值进行校验
     * @param file 校验文件
     * @param fileMd5Byte 要校验的md5 byte数组
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    public boolean checkFileMD5(File file, byte[] fileMd5Byte) throws Exception{
        return checkFile(file,fileMd5Byte);
    }

}
