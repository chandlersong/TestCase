
package com.hilatest.hibernate.inaction.chapter6.collection.map;

import java.util.HashMap;
import java.util.Map;

import com.hilatest.hibernate.inaction.common.SimpleMessage;

public class MapMessage extends SimpleMessage {

    private Map<String, String> comments;

    public MapMessage() {
        comments = new HashMap<String, String>();
    }

    public MapMessage(String message) {
        this();
        this.setText(message);
    }

    public MapMessage(Map<String, String> comments) {
        this.comments = comments;
    }

    public void addComments(String key, String comment) {
        this.comments.put(key, comment);
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public void setComments(Map<String, String> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
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
        MapMessage other = (MapMessage)obj;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        return true;
    }

}
