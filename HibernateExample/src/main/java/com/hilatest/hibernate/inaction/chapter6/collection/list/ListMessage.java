
package com.hilatest.hibernate.inaction.chapter6.collection.list;

import java.util.LinkedList;
import java.util.List;

import com.hilatest.hibernate.inaction.common.SimpleMessage;

public class ListMessage extends SimpleMessage {

    private List<String> tags;

    public ListMessage() {
        tags = new LinkedList<String>();
    }

    public ListMessage(String message) {
        this();
        this.setText(message);
    }

    public ListMessage(List<String> tags) {
        this.tags = tags;
    }

    public void addTags(String tag) {
        this.tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
        ListMessage other = (ListMessage)obj;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

}
