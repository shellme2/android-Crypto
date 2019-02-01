package com.eebbk.bfc.crypto.exception;

import static com.eebbk.bfc.crypto.config.CryptoConstant.CRYPTO_MAX_DATA_LENGTH;

/**
 * @author liuyewu
 * @company EEBBK
 * @function Parameter Exception
 * @date 2016/11/24
 */
public class ParameterException  extends Exception{
    public static final String CRYPTO_MAX_DATA_LENGTH_EXEPTION_DES =
            "the data must less 1M,please check and split !";

    public ParameterException() {
        super();
    }

    public ParameterException(String message) {
        super(message);
    }

    public static void checkDataLength(byte[] data) throws ParameterException{
        if(data.length>CRYPTO_MAX_DATA_LENGTH){
            throw new ParameterException(ParameterException.CRYPTO_MAX_DATA_LENGTH_EXEPTION_DES);
        }
    }
}
