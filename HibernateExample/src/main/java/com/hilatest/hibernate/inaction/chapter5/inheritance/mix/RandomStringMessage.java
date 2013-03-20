
package com.hilatest.hibernate.inaction.chapter5.inheritance.mix;

public class RandomStringMessage extends Message {

    private String randomString;

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((randomString == null) ? 0 : randomString.hashCode());
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
        RandomStringMessage other = (RandomStringMessage)obj;
        if (randomString == null) {
            if (other.randomString != null)
                return false;
        } else if (!randomString.equals(other.randomString))
            return false;
        return true;
    }

}
