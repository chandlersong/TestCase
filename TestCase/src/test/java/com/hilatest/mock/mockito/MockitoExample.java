package com.hilatest.mock.mockito;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class MockitoExample {

    @Test
    public void testVerify() {
        // mock creation

        @SuppressWarnings("unchecked")
        List<String> mockedList = Mockito.mock(List.class);

        // using mock object
        /*
         * 注释这句之后。 Mockito.verify(mockedList).add("one");会报错。
         * 这个用处不错
         */
        mockedList.add("one");

        mockedList.clear();

        // verification
        Mockito.verify(mockedList).add("one");

        Mockito.verify(mockedList).clear();

    }
}
