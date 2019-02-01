package com.eebbk.bfc.crypto.symmetry;


import com.eebbk.bfc.crypto.util.CharEncoding;

import java.io.UnsupportedEncodingException;

/**
 * @author liuyewu
 * @company EEBBK
 * @function aes encode and decrypt
 * @date 2016/11/13
 */
public class AESCrypto extends SymmetryCrypto{

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_ALGORITHM_PARAMETER_SPEC = "0102030405060708";

    /**
     * 默认加密方式为AES/CBC/PKCS5Padding，偏移量为0102030405060708
     * @param key 长度一定要是128,192,256中的一种
     */
    public AESCrypto(byte[] key)  {
        this.mAlgorithm=AES_ALGORITHM;
        try {
            this.mIv = AES_ALGORITHM_PARAMETER_SPEC.getBytes(CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.mTransformation = AES_ALGORITHM+"/"+SymmetryMode.CBC+"/"+SymmetryPadding.PKCS5_PADDING;
        this.mKey=key;
    }

    /**
     * 自定义加密方式和填充方式，已经偏移量
     * @param key 长度一定要是128,192,256中的一种
     * @param mode 建议使用{@link SymmetryMode}中的参数
     * @param padding 建议使用{@link SymmetryPadding}中的参数
     * @param iv 必须为16个字节长度，可以为null,若传null,则用默认值
     */
    public AESCrypto(byte[] key,String mode,String padding,byte[] iv) {
        if(SymmetryMode.ECB.equals(mode)){
            this.mIv = null;
        }else {
            this.mIv = iv;
        }

        this.mAlgorithm=AES_ALGORITHM;
        this.mTransformation = AES_ALGORITHM+"/"+mode+"/"+padding;
        this.mKey=key;
    }

    /**
     * AES加密方式
     */
    public interface SymmetryMode{
        String CBC="CBC";
        String ECB="ECB";
        String CTR="CTR";
        String CTS="CTS";
        String CFB="CFB";
        String OFB="OFB";
    }

    /**
     * AES填充方式方式
     */
    public interface SymmetryPadding{
        String PKCS5_PADDING="PKCS5Padding";
        String NO_PADDING="NoPadding";
        String ISO10126_PADDING="ISO10126Padding";
    }

}
