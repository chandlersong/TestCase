package com.hilatest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ArrayListTest {

    @SuppressWarnings("unchecked")
    public void replace(@SuppressWarnings("rawtypes") List list, String str) {
        list.add(str);
    }

    @Test
    public void testReplace() {
        List<Integer> list = new ArrayList<Integer>();
        this.replace(list, "123");
        this.replace(list, "abc");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    /**
     * reproduce bug 6260652,for more details, please check
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6260652
     */
    @Test
    public void testToArryBug() {
        String[] s = {"a", "b"};
        List<String> l = Arrays.asList(s);

        System.out.println(l.toArray());
        System.out.println(l.toArray(new Object[0]));
    }

    @Test
    public void testArrayListConstractionFunction() {

        String[] s = {"a", "b"};
        List<String> l = Arrays.asList(s);

        Object[] elementData = l.toArray();
        elementData = Arrays.copyOf(elementData, elementData.length, Object[].class);
        System.out.println(elementData);
    }

    @Test
    public void testToArray() {

        String[] s = {"a", "b", "d"};
        ArrayList<String> shortArray = new ArrayList<String>();
        shortArray.add("c");

        String[] news = shortArray.toArray(s);
        System.out.println("news " + news);
        System.out.println("s 1st element:" + s[0]);
        System.out.println("s 2nd element:" + s[1]);
        System.out.println("s 3rd element:" + s[2]);

        System.out.println("=========================================");
        String[] s2 = {"a", "b", "d"};
        ArrayList<String> longArray = new ArrayList<String>();
        longArray.add("1");
        longArray.add("2");
        longArray.add("3");
        longArray.add("4");
        longArray.add("5");
        String[] news2 = shortArray.toArray(s);
        System.out.println("news2 " + news2);
        System.out.println("s2 1st element:" + s2[0]);
        System.out.println("s2 2nd element:" + s2[1]);
        System.out.println("s2 3rd element:" + s2[2]);
    }

    @Test
    public void testInsertAtRandomIndex() {
        ArrayList<String> array = new ArrayList<String>();
        array.ensureCapacity(100);

        array.set(2, "a");
        System.out.println("array size:" + array.size());


    }

}
