package com.hilatest.commB;

import com.hilatest.commA.SupperObject;

public class SubObject extends SupperObject {

    
    
    public SubObject() {
        super();
        
        super.setC(new subinterclass().setValue(2) );
    }

    protected class subinterclass extends interSuperClass{
        
        protected subinterclass setValue(int value){
            super.value = value;
            return this;
        }
    }
}
