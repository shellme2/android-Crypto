package com.eebbk.bfc.demo.crypto.util;


/**
 * @author liuyewu
 * @company EEBBK
 * @function
 * @date 2016/11/27
 */
public class StringUtils {

    public static byte[] getBytesFromString(String data){
        if(data==null||data.length()<2){
            return null;
        }

        String tempData=data.substring(1,(data.length()-1));
//        Log.d("liuyewu",tempData);
        String[] tempByteString=tempData.split(",");
        int len=tempByteString.length;
        byte[] result=new byte[len];
        byte tempByte;
        for (int i=0;i<len;i++) {
            tempByte=Byte.parseByte(tempByteString[i].trim());
            result[i]=tempByte;
        }

        return result;
    }
}
