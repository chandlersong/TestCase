package com.hilatest.security.chapter6.md5;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.hilatest.security.SuperTestCase;

public class md5example extends SuperTestCase {

    public static final String KEY_ALGORITHM = "MD5";

    @Test
    public void sunImplement() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        System.out.println("sun implment MD5");
        MessageDigest md = MessageDigest.getInstance(KEY_ALGORITHM);

        String input = "md5";

        byte[] result = md.digest(input.getBytes());
        Hex.encodeHexString(result);
        System.out.println(new String(result));
        System.out.println(Hex.encodeHexString(result));
    }

    @Test
    public void bouncyCastleImplement() throws NoSuchAlgorithmException, NoSuchProviderException {

        System.out.println("Bouncy Castle implment MD5");
        MessageDigest md = MessageDigest.getInstance(KEY_ALGORITHM, SuperTestCase.BC);

        String input = "md5";

        byte[] result = md.digest(input.getBytes());

        System.out.println(Hex.encodeHexString(result));
    }

    @Test
    public void apacheCodecImplement() {

        System.out.println("apache Codec implment MD5");

        String input = "md5";

        byte[] result = DigestUtils.md5(input);

        System.out.println(Hex.encodeHexString(result));
    }

    @Test
    public void apacheCodecImplement2() {

        System.out.println("apache Codec implment MD52");

        String input = "md5";

        System.out.println(DigestUtils.md5Hex(input.getBytes(StandardCharsets.UTF_8)));
    }
}
