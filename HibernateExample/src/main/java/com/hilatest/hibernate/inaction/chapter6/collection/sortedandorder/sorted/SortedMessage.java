
package com.hilatest.hibernate.inaction.chapter6.collection.sortedandorder.sorted;

import java.util.HashMap;
import java.util.Map;

import com.hilatest.hibernate.inaction.common.SimpleMessage;

/**
 * this case, I don't want write special test case. because it's similar to map <br>
 * just out put order will be different
 * 
 * @author chandler.song
 */
public class SortedMessage extends SimpleMessage {

    private Map<String, String> comments;

    public SortedMessage() {
        comments = new HashMap<String, String>();
    }

    public SortedMessage(String message) {
        this();
        this.setText(message);
    }

    public SortedMessage(Map<String, String> comments) {
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
        SortedMessage other = (SortedMessage)obj;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        return true;
    }

}
