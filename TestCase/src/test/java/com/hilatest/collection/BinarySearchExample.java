package com.hilatest.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import junit.framework.Assert;

public class BinarySearchExample {

    @Test
    public void testMockWork() {

        int searchA = 88;

        List<Item> itemList = new ArrayList<Item>();
        Item specialItem = new Item(searchA, RandomStringUtils.randomAlphanumeric(10));
        itemList.add(specialItem);
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            itemList.add(new Item(r.nextInt(100), RandomStringUtils.randomAlphanumeric(10)));
        }

        ItemComparator c = new ItemComparator();
        Collections.sort(itemList, c);

        Item mockItem = new Item(searchA, null);

        int index = Collections.binarySearch(itemList, mockItem, c);
        Assert.assertEquals(specialItem, itemList.get(index));

        for (Item item : itemList) {
            System.out.println(item);
        }
        System.out.println("special Item");
        System.out.println("index:" + index);
        System.out.println(itemList.get(index));
    }
}

class Item {

    private int a;

    private String b;

    public Item(int a, String b) {
        super();
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Item [a=" + a + ", b=" + b + "]";
    }

}

class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        // TODO Auto-generated method stub
        return o1.getA() - o2.getA();
    }

}
