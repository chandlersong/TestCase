package com.hilatest.security.chapter7.aes;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.hilatest.security.SuperTestCase;

/**
 * AES 加密方法实现
 * @author chandler
 *
 */
public abstract class AESCoder extends SuperTestCase{

	//算法
	public static final String KEY_ALGORITHM = "AES";
	
	/*
	 * 加密算法/解密算法/工作模式/填充方式 
	 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	private static Key toKey(byte[] key) throws Exception{
		SecretKey secretKey = new SecretKeySpec(key,KEY_ALGORITHM);
		return secretKey;
	}
	
	/**
	 * 解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrpt(byte[] data,byte[] key) throws Exception{
		Key k = toKey(key);
		
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,"BC");
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
	public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
		Key k = toKey(key);
		
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		
		return cipher.doFinal(data);
	}
	
	public static byte[] initKey() throws Exception{
		
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		
		/**
		 * 书中说用第三方类库能够购完成这里。但是实际操作发觉不行。
		 * 必须引入Sun的无国界文件才行
		 * 
		 */
		kg.init(128);
		
		SecretKey secretkey = kg.generateKey();
		
		return secretkey.getEncoded();
	}
}
