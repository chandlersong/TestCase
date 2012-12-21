package com.hilatest.commA;

public class SupperObject {

    private interSuperClass c;
    
    public SupperObject(){

    }
    
    
    
    public interSuperClass getC() {
        return c;
    }



    public void setC(interSuperClass c) {
        this.c = c;
    }



    public void add(){
        c.value = c.value +2;
    }
    
    public void test(){
        System.out.println(c.value);
    }
    
    protected class interSuperClass{
        protected int value = 1;
    }
}
