
package com.hilatest.hibernate.inaction.chapter4;

public class Component {

    private String componentText;

    public String getComponentText() {
        return componentText;
    }

    public void setComponentText(String componentText) {
        this.componentText = componentText;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((componentText == null) ? 0 : componentText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Component other = (Component)obj;
        if (componentText == null) {
            if (other.componentText != null)
                return false;
        } else if (!componentText.equals(other.componentText))
            return false;
        return true;
    }

}
