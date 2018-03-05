package com.changhong.people.common.utils.crypt;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AesCBC {
	
	private static Logger logger = LoggerFactory.getLogger(AesCBC.class);
	
	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private static String sKey = "00000bicycle0000";
	private static String ivParameter = "bicycle000000000";
	/**
	 * 算法
	 */
	private static final String ALGORITHMSTR = "AES/CBC/PKCS7Padding"; 
	
	private static AesCBC instance = null;

	private AesCBC() {

	}

	public static AesCBC getInstance() {
		if (instance == null) {
			instance = new AesCBC();
		}
		return instance;
	}

	/**
	 * 
	 * @MethodName：encrypt
	 * @param sSrc 明文
	 * @param sKey 秘钥
	 * @param ivParameter iv
	 * @return
	 * @throws Exception
	 * @ReturnType：String
	 * @Description：加密
	 * @Creator：xiedong
	 * @CreateTime：2017年7月11日下午1:57:31
	 * @Modifier：
	 * @ModifyTime：
	 */
	public String encrypt(String sSrc,String ivParameter) throws Exception {
		//添加PKCS7Padding加密
		Security.addProvider(new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return BASE64Coder.encode(encrypted);// 此处使用BASE64做转码。
	}

	/**
	 * 
	 * @MethodName：decrypt
	 * @param sSrc 密文
	 * @param sKey 秘钥
	 * @param ivParameter iv
	 * @return
	 * @throws Exception
	 * @ReturnType：String
	 * @Description：解密
	 * @Creator：xiedong
	 * @CreateTime：2017年7月11日下午1:55:55
	 * @Modifier：
	 * @ModifyTime：
	 */
	public String decrypt(String sSrc,String ivParameter) throws Exception {
		try {
			//添加PKCS7Padding解密
			Security.addProvider(new BouncyCastleProvider());
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = BASE64Coder.decodeb(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception e){
			logger.error("-------------解密失败-------------------", e);
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1; i++) {
			
			System.out.println("----------------------第-"+ i +"-次----------------------------");
			
			// 需要加密的字串
			String cSrc = "bicycle2016,/sys/initParam";
			System.out.println(cSrc);
			// 加密
			long lStart = System.currentTimeMillis();
			String enString = AesCBC.getInstance().encrypt(cSrc,ivParameter);
			System.out.println("加密后的字串是：" + enString);
			
			long lUseTime = System.currentTimeMillis() - lStart;
			System.out.println("加密耗时：" + lUseTime + "毫秒");
			// 解密
			lStart = System.currentTimeMillis();
			String deString = AesCBC.getInstance().decrypt(enString,ivParameter);
			System.out.println("解密后的字串是：" + deString);
			lUseTime = System.currentTimeMillis() - lStart;
			System.out.println("解密耗时：" + lUseTime + "毫秒");
		}
	}
}
