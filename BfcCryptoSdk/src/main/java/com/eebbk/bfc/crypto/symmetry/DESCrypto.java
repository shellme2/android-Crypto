package com.eebbk.bfc.crypto.symmetry;

import com.eebbk.bfc.crypto.util.CharEncoding;

import java.io.UnsupportedEncodingException;

/**
 * @author liuyewu
 * @company EEBBK
 * @function des encode and decrypt
 * @date 2016/11/13
 */
public class DESCrypto extends SymmetryCrypto {
    private static final String DES_ALGORITHM = "DES";
    private static final String DES_ALGORITHM_PARAMETER_SPEC = "12345678";

    /**
     * 默认加密方式为DES/CBC/PKCS5Padding，偏移量为12345678
     * @param key 长度一定要是64
     */
    public DESCrypto(byte[] key) {
        this.mAlgorithm=DES_ALGORITHM;
        try {
            this.mIv = DES_ALGORITHM_PARAMETER_SPEC.getBytes(CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.mTransformation = DES_ALGORITHM+"/"+SymmetryMode.CBC+"/"+SymmetryPadding.PKCS5_PADDING;
        this.mKey=key;
    }

    /**
     * 自定义加密方式和填充方式，已经偏移量
     * @param key 长度一定要是64
     * @param mode 建议使用{@link SymmetryMode}中的参数
     * @param padding 建议使用{@link SymmetryPadding}中的参数
     * @param iv 必须为8个字节长度，可以为null,若传null,则用默认值
     */
    public DESCrypto(byte[] key,String mode,String padding,byte[] iv) {
        if(SymmetryMode.ECB.equals(mode)){
            this.mIv = null;
        }else {
            this.mIv = iv;
        }
        this.mAlgorithm=DES_ALGORITHM;
        this.mTransformation = DES_ALGORITHM+"/"+mode+"/"+padding;
        this.mKey=key;
    }

    /**
     * DES加密方式
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
     * DES填充方式方式
     */
    public interface SymmetryPadding{
        String PKCS5_PADDING="PKCS5Padding";
        String NO_PADDING="NoPadding";
        String ISO10126_PADDING="ISO10126Padding";
    }
}
