package com.eebbk.bfc.crypto.symmetry;

import com.eebbk.bfc.crypto.util.CharEncoding;

import java.io.UnsupportedEncodingException;

/**
 * @author liuyewu
 * @company EEBBK
 * @function des encode and decrypt
 * @date 2016/11/13
 */
public class DESedeCrypto extends SymmetryCrypto {

    private static final String DESede_ALGORITHM = "DESede";
    private static final String DESede_TRANSFORMATION = "DESede/CBC/PKCS5Padding";
    private static final String DESede_ALGORITHM_PARAMETER_SPEC = "12345678";

    /**
     * 默认加密方式为AES/CBC/PKCS5Padding，偏移量为12345678
     * @param key 长度一定要是128,192中的一种
     */
    public DESedeCrypto(byte[] key) {
        this.mAlgorithm=DESede_ALGORITHM;
        try {
            this.mIv = DESede_ALGORITHM_PARAMETER_SPEC.getBytes(CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.mTransformation = DESede_TRANSFORMATION;
        this.mKey=key;
    }

    /**
     * 自定义加密方式和填充方式，已经偏移量
     * @param key 长度一定要是128,192中的一种
     * @param mode 建议使用{@link SymmetryMode}中的参数
     * @param padding 建议使用{@link SymmetryPadding}中的参数
     * @param iv 必须为8个字节长度，可以为null,若传null,则用默认值
     */
    public DESedeCrypto(byte[] key, String mode, String padding, byte[] iv) {
        if(SymmetryMode.ECB.equals(mode)){
            this.mIv = null;
        }else {
            this.mIv = iv;
        }

        this.mAlgorithm=DESede_ALGORITHM;
        this.mTransformation = DESede_ALGORITHM+"/"+mode+"/"+padding;
        this.mKey=key;
    }

    /**
     * DESede加密方式
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
     * DESede填充方式方式
     */
    public interface SymmetryPadding{
        String PKCS5_PADDING="PKCS5Padding";
        String NO_PADDING="NoPadding";
        String ISO10126_PADDING="ISO10126Padding";
    }

}
