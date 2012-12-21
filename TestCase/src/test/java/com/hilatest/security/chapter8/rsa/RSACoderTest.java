/**
 * 2008-6-11
 */
package com.hilatest.security.chapter8.rsa;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;


/**
 * RSA校验
 * 
 * @author 梁栋
 * @version 1.0
 */
public class RSACoderTest {

	/**
	 * 公钥
	 */
	private byte[] publicKey;

	/**
	 * 私钥
	 */
	private byte[] privateKey;

	/**
	 * 初始化密钥
	 * 
	 * @throws Exception
	 */
	@Before
	public void initKey() throws Exception {

		// 初始化密钥
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		publicKey=Base64.decodeBase64("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLatx+ToAWC0tuclTfevy837Ruv8KgxEvFpzKAj/Tessu6tEH/8DjNILy6SUzLJDekW3rjPx8ebLc2xxvX83dTaILzUiQqtI4q5FRDeQsys2Hy/xgEzEFnLwh4TV8jB145bKkyFNv3BFz27hKLFe0X3zOninWRkN9RdPLX1Hi/kwIDAQAB");
		privateKey = Base64.decodeBase64("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAItq3H5OgBYLS25yVN96/LzftG6/wqDES8WnMoCP9N6yy7q0Qf/wOM0gvLpJTMskN6RbeuM/Hx5stzbHG9fzd1NogvNSJCq0jirkVEN5CzKzYfL/GATMQWcvCHhNXyMHXjlsqTIU2/cEXPbuEosV7RffM6eKdZGQ31F08tfUeL+TAgMBAAECgYAZuKZjWyJAbucEwm0sKemVAwSr5g8HUA/WsGkd1PFNYNk7YeRHTs978M1A5SMY2160oPgEEvIvz8jhf0yAw/dAkvNjEjO6PAtsH0+5/jTSYHa83QJUa0lIyAGUULnrSqN0eOBDF7GJ1jkwoL4AtIOZCxH1qvb4tkYAtb/BQIbxQQJBANTm1Vn+ruKQlMKkJ18wjNxOe2UjnVeCqSeEOuSFuB0PBvE6417zLVENrcmZU73LY6i5zbNT/xMM2L3oP/GQmPMCQQCno92ntx83I+JsOCNtnhTSX44ic5R00JxWWziue0h5/kBX4y6M0h8uEaI9KzTK5yDWDhu14FiD55+dWVW3KObhAkB0JkL7SBDNFieyULASJICUqFR1hwqN9YCnSGKAzdr2L36QGJp28KAmehn3xCiFgPKr2EI6tXaRRA7y/EP2W/a1AkEApT5JRwCirF+a2MGdanfgXl4CZMM5GsxmzliszpCHKHkOQ5OUVwrn4SlQmqr0Ju0c2OKfPfAeUCcIlrlwXFLdAQJAPDdVrOwHASHgakzqZog2x4w57+3VxCuW3JrnrirOSGVDzBHXvW5Wts9BnGv0hzYAyddYERD1nOGAAGEhyMr+EQ==");
		System.err.println("公钥: \n" + Base64.encodeBase64String(publicKey));
		System.err.println("私钥： \n" + Base64.encodeBase64String(privateKey));
	}

	/**
	 * 校验
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {

		System.err.println("\n---私钥加密——公钥解密---");

		String inputStr1 = "RSA加密算法";
		byte[] data1 = inputStr1.getBytes();
		System.err.println("原文:\n" + inputStr1);

		// 加密
		byte[] encodedData1 = RSACoder.encryptByPrivateKey(data1, privateKey);
		System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData1));

		// 解密
		byte[] decodedData1 = RSACoder.decryptByPublicKey(encodedData1,
				publicKey);
		String outputStr1 = new String(decodedData1);
		System.err.println("解密后:\n" + outputStr1);

		// 校验
		assertEquals(inputStr1, outputStr1);

		System.err.println("\n---公钥加密——私钥解密---");
		String inputStr2 = "RSA Encypt Algorithm";
		byte[] data2 = inputStr2.getBytes();
		System.err.println("原文:\n" + inputStr2);

		// 加密
		byte[] encodedData2 = RSACoder.encryptByPublicKey(data2, publicKey);
		System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData2));

		// 解密
		byte[] decodedData2 = RSACoder.decryptByPrivateKey(encodedData2,
				privateKey);
		String outputStr2 = new String(decodedData2);
		System.err.println("解密后: " + outputStr2);

		// 校验
		assertEquals(inputStr2, outputStr2);
	}
}
