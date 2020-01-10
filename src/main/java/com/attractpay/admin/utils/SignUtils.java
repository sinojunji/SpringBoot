package com.attractpay.admin.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class SignUtils {

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	/** @Description RSA:SHA1WithRSA,RSA2:SHA256WithRSA
	* @author Junji
	* @date 2019/10/31 11:13
	* @param algorithm
	* @return java.lang.String
	* @exception
	*/
	private static String getAlgorithms(String algorithm) {
		return algorithm.equalsIgnoreCase("RSA2") ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
	}


	/** @Description RSA加密
	* @author Junji
	* @date 2019/10/31 10:38
	* @param content
		 * @param privateKey
		 * @param rsa2
	* @return java.lang.String
	* @exception
	*/
	public static String signRSA(String content, String privateKey, String rsa2, boolean isPath) {
		try {
//			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
//			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
//			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			PrivateKey priKey = getPrivateKey(privateKey,isPath);
			Signature signature = Signature.getInstance(getAlgorithms(rsa2));

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * RSA验签名检查 RSA sign verification
	 * @param content 待签名数据 pre-sign string
	 * @param sign 签名值 sign
	 * @param ali_public_key 支付宝公钥 Alipay's public key
	 * @param input_charset 编码格式 charset
	 * @return 布尔值 boolean
	 */
	public static boolean verifyRSA(String content, String sign, String ali_public_key, String input_charset)
	{
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			byte[] encodedKey = Base64.decodeBase64(ali_public_key);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );

			boolean bverify = signature.verify( Base64.decodeBase64(sign) );
			return bverify;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 解密 decrypt
	 * @param content 密文 cryptograph
	 * @param private_key 商户私钥 merchant's private key
	 * @param input_charset 编码格式 charset
	 * @return 解密后的字符串 string decrypted
	 */
	public static String decryptRSA(String content, String private_key, String input_charset,boolean isPath) throws Exception {
		PrivateKey prikey = getPrivateKey(private_key,isPath);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, prikey);

		InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content));
		ByteArrayOutputStream writer = new ByteArrayOutputStream();
		//rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
		//The maximum size of the bytes decrypted by rsa is 128, which will need to be decrypted and decrypted by 128 bits
		byte[] buf = new byte[128];
		int bufl;

		while ((bufl = ins.read(buf)) != -1) {
			byte[] block = null;

			if (buf.length == bufl) {
				block = buf;
			} else {
				block = new byte[bufl];
				for (int i = 0; i < bufl; i++) {
					block[i] = buf[i];
				}
			}

			writer.write(cipher.doFinal(block));
		}

		return new String(writer.toByteArray(), input_charset);
	}


	/**
	 * 得到私钥 get private key
	 * @param key 密钥字符串（经过base64编码） string of key(Base64 encoding)
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key,boolean isPath){
		PrivateKey privateKey = null;
		try {
			String privateKeyStr = "";

			if (isPath) {
				privateKeyStr = readKeyContentFromFile(key);
			} else {
				privateKeyStr = key;
			}

			byte[] keyBytes;
			keyBytes = Base64.decodeBase64(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			privateKey = keyFactory.generatePrivate(keySpec);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return privateKey;
	}

	/** @Description read private key from file
	* @author Junji
	* @date 2019/11/6 18:11
	* @param filePath
	* @return java.lang.String
	* @exception
	*/
	private static String readKeyContentFromFile(String filePath) throws Exception {
		try {
			InputStream in;
			if (filePath.startsWith("classpath:")) {
				in = SignUtils.class.getResourceAsStream("/" + filePath.replaceAll("classpath:", ""));
			} else {
				File file = new File(filePath);
				in = new FileInputStream(file);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
				}
			}
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("Key Content Read Fail");
		}
	}
}