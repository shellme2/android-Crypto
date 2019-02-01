package com.eebbk.bfc.crypto.symmetry;

import com.eebbk.bfc.crypto.Decrypt;
import com.eebbk.bfc.crypto.Encrypt;
import com.eebbk.bfc.crypto.base64.binary.Base64;
import com.eebbk.bfc.crypto.exception.ParameterException;
import com.eebbk.bfc.crypto.util.CharEncoding;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author liuyewu
 * @company EEBBK
 * @function 对称加密基础类
 * @date 2016/12/29
 */
class SymmetryCrypto implements Decrypt,Encrypt {
    String mAlgorithm;
    String mTransformation;
    byte[] mIv ;
    byte[] mKey;

    /**
     * 对字节数组进行加密操作
     * @param data 待加密字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        SecretKeySpec secretKey=new SecretKeySpec(mKey,mAlgorithm);

        Cipher cipher = Cipher.getInstance(this.mTransformation);
        if(this.mIv == null) {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } else {
            IvParameterSpec bytes = new IvParameterSpec(this.mIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, bytes);
        }

        return cipher.doFinal(data);
    }

    /**
     * 将base64字节数组解密成String明文
     * @param data base64字节数组
     * @return 默认UTF-8 编码模式的字符串
     * @throws Exception
     */
    @Override
    public String decryptToString(byte[] data) throws Exception {
        return new String(decrypt(Base64.decodeBase64(data)), CharEncoding.UTF_8);
    }

    /**
     * 将base64字节数组解密成String明文
     * @param data base64字节数组
     * @param charsetName 编码方式，建议使用{@link CharEncoding}中的静态常数
     * @return 编码模式的字符串
     * @throws Exception
     */
    public String decryptToString(byte[] data ,String charsetName) throws Exception {
        return new String(decrypt(Base64.decodeBase64(data)), charsetName);
    }

    /**
     * 加密字节数组
     * @param data 待加密的字节数组
     * @return 加密后字节数组
     * @throws Exception
     */
    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        //检测数据是否超过1M大小
        ParameterException.checkDataLength(data);

        SecretKeySpec secretKey=new SecretKeySpec(mKey,mAlgorithm);

        Cipher cipher = Cipher.getInstance(this.mTransformation);
        if(this.mIv == null) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } else {
            IvParameterSpec bytes = new IvParameterSpec(this.mIv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, bytes);
        }

        return cipher.doFinal(data);
    }

    /**
     * 加密后直接转换成Base64编码
     * @param data 加密的字节数组
     * @return Base64编码的，默认UTF-8 编码模式的字符串
     * @throws Exception
     */
    @Override
    public String encryptToString(byte[] data) throws Exception {
        return new String(Base64.encodeBase64(encrypt(data)), CharEncoding.UTF_8);
    }

    /**
     * 加密后直接转换成Base64编码
     * @param data 加密的字节数组
     * @param charsetName 编码方式，建议使用{@link CharEncoding}中的静态常数
     * @return Base64编码的
     * @throws Exception
     */
    public String encryptToString(byte[] data,String charsetName) throws Exception {
        return new String(Base64.encodeBase64(encrypt(data)), charsetName);
    }
}
