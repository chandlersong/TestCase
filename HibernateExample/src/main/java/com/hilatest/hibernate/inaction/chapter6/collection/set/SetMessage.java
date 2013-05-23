
package com.hilatest.hibernate.inaction.chapter6.collection.set;

import java.util.HashSet;
import java.util.Set;

import com.hilatest.hibernate.inaction.common.SimpleMessage;

public class SetMessage extends SimpleMessage {

    private Set<String> tags;

    public SetMessage() {
        tags = new HashSet<String>();
    }

    public SetMessage(String message) {
        this();
        this.setText(message);
    }

    public SetMessage(Set<String> tags) {
        this.tags = tags;
    }

    public void addTags(String tag) {
        this.tags.add(tag);
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
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
        SetMessage other = (SetMessage)obj;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

}
