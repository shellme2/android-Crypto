package com.eebbk.bfc.crypto.asymmetry;

import com.eebbk.bfc.crypto.base64.binary.Base64;
import com.eebbk.bfc.crypto.exception.ParameterException;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author liuyewu
 * @company EEBBK
 * @function rsa encode and decrypt
 * @date 2016/11/13
 */
public class RSACrypto {

    private static final String RSA_ALGORITHM = "RSA";

    private String mTransformation;

    /**
     * 默认构造函数，算法为RSA/ECB/PKCS1Padding
     */
    public RSACrypto() {
        mTransformation = RSA_ALGORITHM+"/"+AsymmetryMode.ECB+"/"+AsymmetryPadding.PKCS1_PADDING;
    }

    /**
     * 自定义构造函数，可以自定义加密方式和填充方式 ,参数都不能传空
     * @param mode 加密方式，建议使用{@link AsymmetryMode}中静态参数
     * @param padding 填充方式，建议使用{@link AsymmetryPadding}中静态参数
     */
    public RSACrypto(String mode, String padding) {
        this.mTransformation = RSA_ALGORITHM+"/"+mode+"/"+padding;
    }

    /**
     * 私钥解密，解密数据为Base64编码后的数据
     * @param key 密钥
     * @param data Base64编码后的数据，一次性长度不可超过密钥的长度
     * @return 返回解密后的byte数组
     * @throws Exception
     */
    public byte[] decryptByPrivateKeyFromBase64String(String key, String data) throws Exception {
        return decryptByPrivateKey(key, Base64.decodeBase64(data));
    }

    /**
     * 私钥解密
     * @param key 密钥
     * @param data 一次性长度不可超过密钥的长度
     * @return 返回解密后的byte数组
     * @throws Exception
     */
    public byte[] decryptByPrivateKey(String key, byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher;
        if (mTransformation == null) {
            cipher = Cipher.getInstance(RSA_ALGORITHM);
        } else {
            cipher = Cipher.getInstance(mTransformation);
        }

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥解密，数据源直接是Base64编码的数据
     * @param key 公钥
     * @param data 一次性长度不可超过密钥的长度
     * @return 返回解密后的byte数组
     * @throws Exception
     */
    public byte[] decryptByPublicKeyFromBase64String(String key, String data) throws Exception {
        return decryptByPublicKey(key, Base64.decodeBase64(data));
    }

    /**
     * 公钥解密
     * @param key 公钥
     * @param data 一次性长度不可超过密钥的长度
     * @return 返回解密后的byte数组
     * @throws Exception
     */
    public byte[] decryptByPublicKey(String key, byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        X509EncodedKeySpec e = new X509EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(e);

        Cipher cipher;
        if (mTransformation == null) {
            cipher = Cipher.getInstance(RSA_ALGORITHM);
        } else {
            cipher = Cipher.getInstance(mTransformation);
        }

        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥加密 ，加密后数据直接加密成utf-8的Base64编码的字符串
     * @param key 密钥
     * @param data 一次性加密长度不能超过密钥长度
     * @return 加密后数据直接加密成utf-8的Base64编码的字符串
     * @throws Exception
     */
    public String encryptByPublicKeyToBase64String(String key, byte[] data) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(key, data));
    }

    /**
     * 公钥加密 ，加密后数据直接加密成utf-8的Base64编码的字符串
     * @param key 密钥
     * @param data 一次性加密长度不能超过密钥长度
     * @param charsetName 返回字符串格式，建议使用{@link com.eebbk.bfc.crypto.util.CharEncoding}中常量
     * @return 加密后数据直接加密成utf-8的Base64编码的字符串
     * @throws Exception
     */
    public String encryptByPublicKeyToBase64String(String key, byte[] data ,String charsetName) throws Exception {
        return new String(Base64.encodeBase64(encryptByPublicKey(key, data)),charsetName);
    }

    /**
     * 公钥加密
     * @param key 密钥
     * @param data 一次性加密长度不能超过密钥长度
     * @return 加密后的byte数组
     * @throws Exception
     */
    public byte[] encryptByPublicKey(String key, byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher;
        if (mTransformation == null) {
            cipher = Cipher.getInstance(RSA_ALGORITHM);
        } else {
            cipher = Cipher.getInstance(mTransformation);
        }
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }


    /**
     * 私钥加密，加密后数据直接加密成utf-8的Base64编码的字符串
     * @param key 密钥
     * @param data 一次性加密长度不能超过密钥长度
     * @return 加密后数据直接加密成utf-8的Base64编码的字符串
     * @throws Exception
     */
    public String encryptByPrivateKeyToBase64String(String key, byte[] data) throws Exception {
        return Base64.encodeBase64String(encryptByPrivateKey(key, data));
    }

    /**
     * 私钥加密，加密后数据直接加密成utf-8的Base64编码的字符串
     * @param key 密钥
     * @param data 一次性加密长度不能超过密钥长度
     * @param charsetName 返回字符串格式，建议使用{@link com.eebbk.bfc.crypto.util.CharEncoding}中常量
     * @return 加密后数据直接加密成utf-8的Base64编码的字符串
     * @throws Exception
     */
    public String encryptByPrivateKeyToBase64String(String key, byte[] data,String charsetName) throws Exception {
        return new String(Base64.encodeBase64(encryptByPrivateKey(key, data)),charsetName);
    }

    /**
     * 私钥加密
     * @param key 密钥
     * @param data 一次性加密长度不能超过密钥长度
     * @return 加密后byte数组
     * @throws Exception
     */
    public byte[] encryptByPrivateKey(String key, byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher;
        if (mTransformation == null) {
            cipher = Cipher.getInstance(RSA_ALGORITHM);
        } else {
            cipher = Cipher.getInstance(mTransformation);
        }

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * RSA非对称加密的加密模式
     */
    public interface AsymmetryMode{
        String ECB="ECB";
        String NONE="NONE";
    }

    /**
     * RSA非对称加密的填充方式
     */
    public interface AsymmetryPadding{
        String NO_PADDING="NOPadding";
        String PKCS1_PADDING="PKCS1Padding";
        String OAEP_PADDING="OAEPPadding";
        String OAEP_WITH_SHA1_AND_MGF1_PADDING="OAEPwithSHA-1andMGF1Padding";
        String OAEP_WITH_SHA256_AND_MGF1_PADDING="OAEPwithSHA-256andMGF1Padding";
    }

}
