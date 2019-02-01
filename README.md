<center><h4>中间件xxx库使用说明手册模板</h4>
<table>
	<tr>
		<td><b>版本</b></td>
		<td>V1.0</td>		
	</tr>
	<tr>
		<td><b>关键字</b></td>
		<td>中间件、加密解密库、使用手册、模板</td>
	</tr>
	<tr>
		<td><b>创建时间</b></td>
		<td>2017-01-03</td>
	</tr>
	<tr>
		<td><b>创建人</b></td>
		<td>曾井芳</td>
	</tr>
	<tr>
		<td><b>最新发布日期</b></td>
		<td>2017-01-03</td>
	</tr>
</table></center>
<center><h4>文档变更记录</h4>
<table>
	<tr>
		<td><b>更改人</b></td>
		<td><b>日期</b></td>
		<td><b>更改内容</b></td>		
	</tr>
	<tr>
		<td>曾井芳</td>
		<td>2017-01-03</td>
		<td>创建文件</td>		
	</tr>
	<tr>
		<td>&emsp;</td>
		<td>&emsp;</td>
		<td>&emsp;</td>		
	</tr>
</table></center> 

# 关于
加密解密库目前是基于主流的加密方案进行封装的工具类库，包括摘要加密（MD5，SHA等），Base64编解码，对称加解密（DES,3DES,AES）,非对称加解密（RSA），以及DES,3DES,AES，RSA的密钥生成器，涵盖目前应用中用到的加解密算法。仅对外发布jar包，支持后台以及android前端开发。

## 版本和项目名称
- 库版本： 2.0.5
- 项目英文名： Crypto
- API支持： > 15

## 功用（功能列表）
- 1,提供不可逆加密，也叫摘要算法、散列式算法，包括默认MD5，SHA-1，还可自定义扩展SHA-224，SHA-256，SHA-384，SHA-512.
- 2,提供对称加解密，目前包括DES，3DES（DESede），AES。
- 3,提供非对称加解密，目前只提供RSA。
- 4,提供Base64编解码。
- 5,提供密钥生成器，目前包括DES，3DES（DESede），AES的密钥生成器，RSA的密钥对生成器。


# 使用

## 注意事项
- 1，使用加密解密库选择相关算法时，要根据自身需求的效率和安全性综合考虑，不同算法在安全性和效率方面往往是相互冲突的，所有要折中考虑；
- 2，使用加密解密库，往往是在数据传输过程中使用，所有必须要考虑各个节点间的加密算法及参数配置要保持一致，不然会导致加密混乱；
- 3，选择某种算法的时候，一定要看说明文档，不同算法在有些API版本上是没有的，比如说SHA—224，在1-8，22+上才有，其余的API上没有；
- 4，使用加密解密库时，最好不要直接使用，需封装自己的加密解密工具类；

## 各个加解密算法说明
- 不可逆加密使用，注意事项等说明： [不可逆加密说明文档](http://172.28.2.93/bfc/BfcCrypto/blob/develop/doc/使用说明文档/不可逆加密说明文档.md)
- 对称加解密使用，注意事项等说明： [对称加解密说明文档](http://172.28.2.93/bfc/BfcCrypto/blob/develop/doc/使用说明文档/对称加解密说明文档.md)
- 非对称加解密使用，注意事项等说明： [非对称加解密说明文档](http://172.28.2.93/bfc/BfcCrypto/blob/develop/doc/使用说明文档/非对称加解密说明文档.md)
- 密钥生成器使用，注意事项等说明： [密钥生成器说明文档](http://172.28.2.93/bfc/BfcCrypto/blob/develop/doc/使用说明文档/密钥生成器说明文档.md)
- Base64使用，注意事项等说明： [Base64说明文档](http://172.28.2.93/bfc/BfcCrypto/blob/develop/doc/使用说明文档/Base64说明文档.md)

## 可扩展功能说明
- 根据需求可增加相应的加解密算法
- 可根据需求可用so库的形式添加自定义的一些算法

## 源码保存地址
http://172.28.2.93/bfc/BfcCrypto.git

## 升级清单文档
- 文档名称： [UPDATE.md ](http://172.28.2.93/bfc/BfcCrypto/blob/master/UPDATE.md)

# 最后
希望大家多多使用和提出宝贵意见，大家一起讨论进步，一起完善本库。
联系方式 ： 15992936593  RTX：  曾井芳（20252366）
参与开发人员： 曾井芳、刘业武
