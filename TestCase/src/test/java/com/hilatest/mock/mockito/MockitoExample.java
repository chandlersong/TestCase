package com.hilatest.mock.mockito;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoExample {

    @Test
    public void testVerify() {
        // mock creation

        @SuppressWarnings("unchecked")
        List<String> mockedList = mock(List.class);

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

    @Test
    public void testSpy(){
        RealObject object = new RealObject();
        RealObject spy = Mockito.spy(object);

        when(spy.test()).thenReturn("mock");

        System.out.println(spy.test());
        spy.print();
    }

    @Test
    public void testNewObject(){
        RealObject mockObject = mock(RealObject.class);
        when(mockObject.getStrings()).thenReturn(new ArrayList<String>());

        mockObject.getStrings().add("aa");


        System.out.println(mockObject.getStrings().get(0));

    }


}

class RealObject{

    private List<String> strings;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public String test(){
        return "realMethod";
    }

    public void print(){
        System.out.println(test());
    }

}