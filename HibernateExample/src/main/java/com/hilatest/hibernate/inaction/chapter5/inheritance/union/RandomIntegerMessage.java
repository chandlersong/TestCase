
package com.hilatest.hibernate.inaction.chapter5.inheritance.union;

public class RandomIntegerMessage extends Message {

    private int RandomInteger;

    public int getRandomInteger() {
        return RandomInteger;
    }

    public void setRandomInteger(int randomInteger) {
        RandomInteger = randomInteger;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + RandomInteger;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RandomIntegerMessage other = (RandomIntegerMessage)obj;
        if (RandomInteger != other.RandomInteger)
            return false;
        return true;
    }

}
