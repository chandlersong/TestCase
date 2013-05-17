
package com.hilatest.hibernate.inaction.chapter5.customertype.compositeusertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

public class CommentType implements CompositeUserType {

    private static final int CONTENT_INDEX = 0;

    private static final int TIMESTAMP_INDEX = 1;

    /**
     * A CompositeUserTypeexposes the properties of the value type through getPropertyNames()
     */
    public String[] getPropertyNames() {

        return new String[] {
                "Content", "timestamp"
        };
    }

    /**
     * he properties each have their own type, as defined by getPropertyTypes(). The types of the SQLcolumns are now implicit from this
     * method.
     */
    public Type[] getPropertyTypes() {
        return new Type[] {
                StandardBasicTypes.STRING, StandardBasicTypes.TIMESTAMP
        };
    }

    /**
     * The getPropertyValue()method returns the value of an individual property of the MonetaryAmount.<br>
     * I think the property is kind of index
     */
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        Comment comment = (Comment)component;

        switch (property) {
            case CONTENT_INDEX: {
                return comment.getContent();
            }
            case TIMESTAMP_INDEX: {
                return comment.getTime();
            }

        }

        return null;
    }

    /**
     * The setPropertyValue()method sets the value of an individual property of the MonetaryAmount<br>
     * I don't know when the method will be called and what's the usage.<br>
     * but compare with get method. I think it just need to set value to object
     */
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
        throw new UnsupportedOperationException("Comment MonetaryAmount!");

    }

    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
        return Comment.class;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null || y == null) {
            return false;
        }
        return x.equals(y);
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    /**
     * Loading a value now is straightforward: You transform two column values in the result set to two property values in a new
     * MonetaryAmountinstance.
     */
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        if (rs.wasNull()) {
            return null;
        }

        Comment comment = new Comment();
        comment.setContent(rs.getString(names[CONTENT_INDEX]));
        comment.setTime(rs.getTimestamp(names[TIMESTAMP_INDEX]));
        return comment;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, StandardBasicTypes.STRING.sqlType());
            st.setNull(index + 1, StandardBasicTypes.TIMESTAMP.sqlType());
        } else {
            Comment comment = (Comment)value;
            st.setString(index, comment.getContent());
            st.setTimestamp(index + 1, comment.getTime());
        }

    }

    public Object deepCopy(Object value) throws HibernateException {
        Comment comment = new Comment();

        if (value instanceof Comment) {

            Comment origin = (Comment)value;

            comment.setContent(origin.getContent());
            comment.setTime(origin.getTime());

            return comment;
        }
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
        return ((Comment)value);
    }

    public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
        if (cached instanceof Comment) {
            Comment ss = new Comment();
            return ss;
        }

        return cached;
    }

    public Object replace(Object original, Object target, SessionImplementor session, Object owner)
            throws HibernateException {
        return this.deepCopy(original);
    }

}
