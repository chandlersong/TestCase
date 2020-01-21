package me.study.java;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SneakyThrowsThrowsDemos {

    @Test(expected = NullPointerException.class)
    public void testOtherException() {
        raiseOtherException(null);

    }

    @Test(expected = IOException.class)
    public void testSpecialException() {
        raiseSpecialException();
    }

    @Test(expected = Throwable.class)
    public void testAllException() {
        raiseAllException();
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    public String raiseOtherException(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows(IOException.class)
    public String raiseSpecialException() {
        throw new IOException();
    }


    @SneakyThrows
    public String raiseAllException() {
        throw new Throwable();
    }
}


