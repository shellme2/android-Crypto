/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eebbk.bfc.crypto.util;

import java.io.UnsupportedEncodingException;

/**
 * Converts String to and from bytes using the encodings required by the Java specification. These encodings are specified in <a
 * href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
 * 
 * @see CharEncoding
 * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
 * @author <a href="mailto:ggregory@seagullsw.com">Gary Gregory</a>
 * @version $Id: HexUtils.java 950460 2010-06-02 09:43:02Z sebb $
 * @since 1.4
 */
public class StringUtils {

    private StringUtils(){

    }

    public static byte[] getBytesIso8859_1(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.ISO_8859_1);
    }

    public static byte[] getBytesUsAscii(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.US_ASCII);
    }

    public static byte[] getBytesUtf16(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16);
    }


    public static byte[] getBytesUtf16Be(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16BE);
    }


    public static byte[] getBytesUtf16Le(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16LE);
    }


    public static byte[] getBytesUtf8(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_8);
    }

    public static byte[] getBytesUnchecked(String string, String charsetName) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
        return new IllegalStateException(charsetName + ": " + e);
    }

    public static String newString(byte[] bytes, String charsetName) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    public static String newStringIso8859_1(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.ISO_8859_1);
    }


    public static String newStringUsAscii(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.US_ASCII);
    }


    public static String newStringUtf16(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_16);
    }

    public static String newStringUtf16Be(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_16BE);
    }


    public static String newStringUtf16Le(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_16LE);
    }

    public static String newStringUtf8(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_8);
    }

}
