
package com.hilatest.hibernate.inaction.chapter5.customertype.usertypeExample;

public class StringSize {

    private int size;

    public StringSize() {

    }

    public StringSize(String target) {
        this.setSize(target);
    }

    public void setSize(String target) {
        this.size = target.length();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
