
package com.hilatest.hibernate.inaction.chapter5.customertype.compositeusertype;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 645310973047862150L;

    private String Content;

    private Timestamp time;

    public Comment() {
    };

    public String getContent() {
        return Content;
    }

    public Comment(String content, Timestamp time) {
        super();
        Content = content;
        this.time = time;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Content == null) ? 0 : Content.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        Comment other = (Comment)obj;
        if (Content == null) {
            if (other.Content != null)
                return false;
        } else if (!Content.equals(other.Content))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

}
