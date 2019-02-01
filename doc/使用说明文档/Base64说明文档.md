# Base64说明文档

## 整体介绍
Base64编解码，取自apache.commons.codec中的Base64编辑解密代码，使用也是一样的

### 包括的功能
- 1，对byte数组的编解码；
- 2，对String的编解密；
- 3，对数据流的编解码；

### 其中的工具方法
- 1，boolean isBase64(byte octet);
- 2，boolean isBase64(String base64)；
- 3，boolean isArrayByteBase64(byte[] arrayOctet)；
- 4，boolean isBase64(byte[] arrayOctet)；
- 5，byte[] encodeBase64(byte[] binaryData)；
- 6，String encodeBase64String(byte[] binaryData)；
- 7，byte[] encodeBase64URLSafe(byte[] binaryData)；
- 8，String encodeBase64URLSafeString(byte[] binaryData)；
- 9，byte[] encodeBase64Chunked(byte[] binaryData)；
- 10，byte[] encodeBase64(byte[] binaryData, boolean isChunked)；
- 11，byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe)；
- 12，byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe, int maxResultSize)；
- 13，byte[] decodeBase64(String base64String)；
- 14，byte[] decodeBase64(byte[] base64Data)；
- 15，BigInteger decodeInteger(byte[] pArray)；
- 16，byte[] encodeInteger(BigInteger bigInt)；

### 数据流，使用方法跟常用的数据流一样
- Base64InputStream
- Base64OutputStream

