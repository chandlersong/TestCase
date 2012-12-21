package com.hilatest.security.chapter8.dh;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DHCoder {

	public static final String KEY_ALGORITHM = "DH";
	
	public static final String SECERT_ALGORITHM="AES";
	
	private static final int KEY_SIZE=512;
	
	private static final String PUBLIC_KEY="DHPublicKey";
	
	private static final String PRIVATE_KEY="DHPrivate";
	
	/**
	 * 初始化甲方密钥
	 * @return 甲方密钥
	 * @throws Exception
	 */
	public static Map<String,Object> initKey() throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		generator.initialize(KEY_SIZE);
		KeyPair pair = generator.generateKeyPair();
	
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put(PRIVATE_KEY, pair.getPrivate());
		result.put(PUBLIC_KEY, pair.getPublic());
		
		return result;		
	}
	
	/**
	 * 产生乙方密钥对
	 * @param publickey
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> initKey(byte[] publickey) throws Exception{
		
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publickey);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		//generator public key
		PublicKey pubkey = factory.generatePublic(x509KeySpec);
		
		DHParameterSpec dhParamSpec = ((DHPublicKey)pubkey).getParams();
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance(factory.getAlgorithm());
		generator.initialize(dhParamSpec);
		
		KeyPair pair = generator.genKeyPair();
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put(PRIVATE_KEY, pair.getPrivate());
		result.put(PUBLIC_KEY, pair.getPublic());
		
		return result;
	}
	
	/**
	 * 解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrpt(byte[] data,byte[] keybyte) throws Exception{
		SecretKey k = new SecretKeySpec(keybyte,KEY_ALGORITHM);
		
		Cipher cipher = Cipher.getInstance(k.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, k);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data,byte[] keybyte) throws Exception{
		SecretKey k = new SecretKeySpec(keybyte,KEY_ALGORITHM);
		
		Cipher cipher = Cipher.getInstance(k.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, k);
		
		return cipher.doFinal(data);
	}
}
