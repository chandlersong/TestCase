package com.hilatest.hibernate.inaction.chapter5.customertype.usertypeExample;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

public class StringSizeUserType implements UserType {

    /**
     * The sqlTypes()method tells Hibernate what SQLcolumn types to use for DDLschema generation. Notice that this method returns an array
     * of type codes. A UserTypemay map a single property to multiple columns, but this legacy data model has only a single numeric column.
     * By using the Hiber-nate.BIG_DECIMAL.sqlType()method, you let Hibernate decide the exact SQL datatype for the given database dialect.
     * Alternatively, return a constant from java.sql.Types.
     */
    public int[] sqlTypes() {
        return new int[] {
                StandardBasicTypes.INTEGER.sqlType()
        };
    }

    /**
     * The returnedClass()method tells Hibernate what Java value type class is mapped by this UserType
     */
    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
        return StringSize.class;
    }

    /**
     * The UserTypeis responsible for dirty checking property values. The equals() method compares the current property value to a previous
     * snapshot and deter-mines whether the property is dirty and must by saved to the database. The hash-Code()of two equal value typed
     * instances has to be the same. We usually delegate this method to the actual value type class—in this case, the hashCode()method of
     * the given MonetaryAmountobject
     */
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
     * The nullSafeGet()method retrieves the property value from the JDBC Result-Set. You can also access the owner of the component if you
     * need it for the conversion. All database values are in USD, so you convert it to the currency the user has currently set in his
     * preferences. (Note that it’s up to you to implement this con-version and preference handling.)
     */
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Integer size = rs.getInt(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        StringSize ss = new StringSize();
        ss.setSize(size);
        return ss;
    }

    /**
     * The nullSafeSet()method writes the property value to the JDBC Prepared-Statement. This method takes whatever currency is set and
     * converts it to a simple BigDecimal USDamount before saving.
     */
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType());
        } else {
            StringSize ss = (StringSize) value;
            st.setInt(index, ss.getSize());
        }

    }

    /**
     * The UserTypeis also partially responsible for <B>creating a snapshot of a value </B> in the first place. Because MonetaryAmountis an
     * immutable class, the deepCopy() method returns its argument. In the case of a mutable type, it would need to return a copy of the
     * argument to be used as the snapshot value
     */
    public Object deepCopy(Object value) throws HibernateException {
        StringSize ss = new StringSize();

        if (value instanceof StringSize) {
            ss.setSize(((StringSize) value).getSize());
            return ss;
        }
        return value;
    }

    /**
     * Hibernate can make some minor performance optimizations for immutable types like this one, for example, when comparing snapshots
     * during dirty checking. The isMutable()method tells Hibernate that this type is immutable.
     */
    public boolean isMutable() {
        return false;
    }

    /**
     * The disassemble()method is called when Hibernate puts a <B>value into the second-level </B>. As you’ll learn later, this is a cache
     * of data that stores information in a serialized form.
     */
    public Serializable disassemble(Object value) throws HibernateException {

        return ((StringSize) value).getSize();
    }

    /**
     * The assemble()method does the opposite of disassembly: It can transform cached data into an instance of MonetaryAmount. As you can
     * see, implementation of both routines is easy for immutable types.
     */
    public Object assemble(Serializable cached, Object owner) throws HibernateException {

        if (cached instanceof Integer) {
            StringSize ss = new StringSize();
            ss.setSize((Integer) cached);
            return ss;
        }

        return cached;
    }

    /**
     * Implement replace()to handle merging of detached object state. As you’ll see later in the book, the process of merging involves an
     * original and a target object, whose state must be combined. Again, for immutable value types, return the first argument. For mutable
     * types, at least return a deep copy of the first argument. For mutable types that have component fields, you probably want to apply a
     * recursive merging routine.
     */
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return this.deepCopy(original);
    }

    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {
        // TODO Auto-generated method stub

    }

}
