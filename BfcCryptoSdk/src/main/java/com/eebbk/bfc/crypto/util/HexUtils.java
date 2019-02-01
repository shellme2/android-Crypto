package com.eebbk.bfc.crypto.util;

/**
 * @author liuyewu
 * @company EEBBK
 * @function string utils
 * @date 2016/11/13
 */
public class HexUtils {

    private static final char[] HEXDIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] HEXDIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void appendHexPair(byte bt, StringBuffer stringbuffer,boolean isLower) {
        char c0 ;
        char c1 ;
        if(isLower){
            c0 = HEXDIGITS_LOWER[(bt & 0xf0) >> 4]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
            c1 = HEXDIGITS_LOWER[bt & 0xf]; // 取字节中低 4 位的数字转换
        }else {
            c0 = HEXDIGITS_UPPER[(bt & 0xf0) >> 4]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
            c1 = HEXDIGITS_UPPER[bt & 0xf]; // 取字节中低 4 位的数字转换
        }

        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static String byteToString(byte bytes[],boolean isLower) {
        return bufferToHex(bytes, 0, bytes.length,isLower);
    }

    public static String bufferToHex(byte bytes[], int m, int n,boolean isLower) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer,isLower);
        }
        return stringbuffer.toString();
    }
}
