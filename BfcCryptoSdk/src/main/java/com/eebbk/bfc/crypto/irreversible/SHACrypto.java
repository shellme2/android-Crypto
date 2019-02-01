package com.eebbk.bfc.crypto.irreversible;

import java.io.File;
import java.security.MessageDigest;

/**
 * @author liuyewu
 * @company EEBBK
 * @function sha code 特殊说明：SHA224只在1-8,22+上有用，也就是说Android2.3到Android5.0上都不能用
 * @date 2016/11/13
 */
public class SHACrypto extends IrreversibleCrypto {

    /**
     * 默认为SHA-1加密方式
     * @throws Exception
     */
    public SHACrypto() throws Exception{
        messageDigest=MessageDigest.getInstance(SHAAlgorithm.SHA1);//与SAH一样,默认为该算法
    }

    /**
     * 根据需要选择特定的加密方式
     * @param algorithm 建议使用{@link SHAAlgorithm}中选择，传空会报空指针异常
     * @throws Exception 空指针异常，算法不存在异常
     */
    public SHACrypto(String algorithm) throws Exception {
        messageDigest=MessageDigest.getInstance(algorithm);
    }

//------------------------------------------------------------------------------------------------
//--------文件部分---------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------

    /**
     * 判断字符串的SHA校验码是否与一个已知的md5码相匹配
     * @param data 原字符串
     * @param SHAString SHA 字符串,注意SHAString的转换方式与这本类中的转换一致
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkSHA(String data, String SHAString) throws Exception  {
        return check(data,SHAString);
    }

    /**
     * 判断byte数组的md5校验码是否与一个已知的md5码相匹配
     * @param data 原byte数组
     * @param SHAString MD5 字符串,注意md5String的转换方式与这本类中的转换一致
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkSHA(byte[] data, String SHAString) throws Exception  {
        return check(data,SHAString);
    }

    /**
     * 判断byte数组的md5校验码是否与一个已知的md5码相匹配
     * @param data 原byte数组
     * @param SHAString MD5 字符串,注意md5String的转换方式与这本类中的转换一致
     * @param isLower 大小写选择，true：小写；false：大写
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkSHA(byte[] data, String SHAString,boolean isLower) throws Exception  {
        return check(data,SHAString,isLower);
    }

    /**
     * 判断byte数组的md5校验码是否与一个已知的md5 byte数组码相匹配
     * @param data 原数组
     * @param SHAByte MD5 byte数组,注意md5Byte是没被转换过的，若已经转换的，一定要确保还原
     * @return 匹配true；不匹配false
     * @throws Exception
     */
    public boolean checkSHA(byte[] data, byte[] SHAByte) throws Exception  {
        return check(data,SHAByte);
    }
//------------------------------------------------------------------------------------------------
//--------文件部分---------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------
    /**
     * 对文件SHA值进行校验
     * @param file 校验文件
     * @param fileSHAString 要校验的SHA值
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    public boolean checkFileSHA(File file, String fileSHAString) throws Exception{
        return checkFile(file,fileSHAString);
    }

    /**
     * 对文件SHA值进行校验
     * @param file 校验文件
     * @param fileSHAString 要校验的md5值
     * @param isLower  大小写选择，true：小写；false：大写
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    public boolean checkFileSHA(File file, String fileSHAString ,boolean isLower) throws Exception {
        return checkFile(file,fileSHAString,isLower);
    }

    /**
     * 对文件SHA值进行校验
     * @param file 校验文件
     * @param fileSHAByte 要校验的SHA值
     * @return 是否校验成功：true成功；false失败
     * @throws Exception 可能出现文件异常和加密异常
     */
    public boolean checkFileSHA(File file, byte[] fileSHAByte) throws Exception{
        return checkFile(file, fileSHAByte);
    }

    /**
     * 哈希算法的算法名称，其中SHA224只在1-8,22+上有用，也就是说Android2.3到Android5.0上都不能用
     */
    public interface SHAAlgorithm{
        String SHA1="SHA-1";
        /**
         * SHA224只在1-8,22+上有用，也就是说Android2.3到Android5.0上都不能用
         */
        String SHA224="SHA-224";
        String SHA256="SHA-256";
        String SHA384="SHA-384";
        String SHA512="SHA-512";
    }
}
