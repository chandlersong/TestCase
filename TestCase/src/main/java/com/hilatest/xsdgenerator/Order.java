
package com.hilatest.xsdgenerator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/** 
 * Order information.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://jibx.org/starter1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="order">
 *   &lt;xs:sequence>
 *     &lt;xs:element name="customer" minOccurs="0">
 *       &lt;!-- Reference to inner class Customer -->
 *     &lt;/xs:element>
 *     &lt;xs:element type="ns:address" name="billTo" minOccurs="0"/>
 *     &lt;xs:element name="shipping" minOccurs="0">
 *       &lt;xs:simpleType>
 *         &lt;!-- Reference to inner class Shipping -->
 *       &lt;/xs:simpleType>
 *     &lt;/xs:element>
 *     &lt;xs:element type="ns:address" name="shipTo" minOccurs="0"/>
 *     &lt;xs:element name="item" minOccurs="0" maxOccurs="unbounded">
 *       &lt;!-- Reference to inner class Item -->
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:long" use="required" name="orderNumber"/>
 *   &lt;xs:attribute type="xs:date" name="orderDate"/>
 *   &lt;xs:attribute type="xs:date" name="shipDate"/>
 *   &lt;xs:attribute type="xs:float" name="total"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Order
{
    private Customer customer;
    private Address billTo;
    private Shipping shipping;
    private Address shipTo;
    private List<Item> itemList = new ArrayList<Item>();
    private long orderNumber;
    private Date orderDate;
    private Date shipDate;
    private Float total;

    /** 
     * Get the 'customer' element value.
     * 
     * @return value
     */
    public Customer getCustomer() {
        return customer;
    }

    /** 
     * Set the 'customer' element value.
     * 
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /** 
     * Get the 'billTo' element value. Billing address information.
     * 
     * @return value
     */
    public Address getBillTo() {
        return billTo;
    }

    /** 
     * Set the 'billTo' element value. Billing address information.
     * 
     * @param billTo
     */
    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    /** 
     * Get the 'shipping' element value.
     * 
     * @return value
     */
    public Shipping getShipping() {
        return shipping;
    }

    /** 
     * Set the 'shipping' element value.
     * 
     * @param shipping
     */
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    /** 
     * Get the 'shipTo' element value. Shipping address information. If missing, the billing address is also
                          used as the shipping address.
     * 
     * @return value
     */
    public Address getShipTo() {
        return shipTo;
    }

    /** 
     * Set the 'shipTo' element value. Shipping address information. If missing, the billing address is also
                          used as the shipping address.
     * 
     * @param shipTo
     */
    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    /** 
     * Get the list of 'item' element items.
     * 
     * @return list
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /** 
     * Set the list of 'item' element items.
     * 
     * @param list
     */
    public void setItemList(List<Item> list) {
        itemList = list;
    }

    /** 
     * Get the 'orderNumber' attribute value.
     * 
     * @return value
     */
    public long getOrderNumber() {
        return orderNumber;
    }

    /** 
     * Set the 'orderNumber' attribute value.
     * 
     * @param orderNumber
     */
    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /** 
     * Get the 'orderDate' attribute value. Date order was placed with server.
     * 
     * @return value
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /** 
     * Set the 'orderDate' attribute value. Date order was placed with server.
     * 
     * @param orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /** 
     * Get the 'shipDate' attribute value. <![CDATA[Date order was shipped. This will be
          <code>null</code> if the order has not yet shipped.]]>
     * 
     * @return value
     */
    public Date getShipDate() {
        return shipDate;
    }

    /** 
     * Set the 'shipDate' attribute value. <![CDATA[Date order was shipped. This will be
          <code>null</code> if the order has not yet shipped.]]>
     * 
     * @param shipDate
     */
    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    /** 
     * Get the 'total' attribute value.
     * 
     * @return value
     */
    public Float getTotal() {
        return total;
    }

    /** 
     * Set the 'total' attribute value.
     * 
     * @param total
     */
    public void setTotal(Float total) {
        this.total = total;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://jibx.org/starter1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="customer" minOccurs="0">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="xs:string" name="firstName" minOccurs="0"/>
     *       &lt;xs:element type="xs:string" name="lastName" minOccurs="0"/>
     *       &lt;xs:element type="xs:string" name="middleName" minOccurs="0" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *     &lt;xs:attribute type="xs:long" use="required" name="customerNumber"/>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Customer
    {
        private String firstName;
        private String lastName;
        private List<String> middleNameList = new ArrayList<String>();
        private long customerNumber;

        /** 
         * Get the 'firstName' element value. Personal name.
         * 
         * @return value
         */
        public String getFirstName() {
            return firstName;
        }

        /** 
         * Set the 'firstName' element value. Personal name.
         * 
         * @param firstName
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /** 
         * Get the 'lastName' element value. Family name.
         * 
         * @return value
         */
        public String getLastName() {
            return lastName;
        }

        /** 
         * Set the 'lastName' element value. Family name.
         * 
         * @param lastName
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /** 
         * Get the list of 'middleName' element items.
         * 
         * @return list
         */
        public List<String> getMiddleNameList() {
            return middleNameList;
        }

        /** 
         * Set the list of 'middleName' element items.
         * 
         * @param list
         */
        public void setMiddleNameList(List<String> list) {
            middleNameList = list;
        }

        /** 
         * Get the 'customerNumber' attribute value.
         * 
         * @return value
         */
        public long getCustomerNumber() {
            return customerNumber;
        }

        /** 
         * Set the 'customerNumber' attribute value.
         * 
         * @param customerNumber
         */
        public void setCustomerNumber(long customerNumber) {
            this.customerNumber = customerNumber;
        }
    }
    /** 
     * Supported shipment methods. The "INTERNATIONAL" shipment methods
                              can only be used for orders with shipping addresses outside the U.S., and one of these
                              methods is required in this case.
     * 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:simpleType xmlns:xs="http://www.w3.org/2001/XMLSchema">
     *   &lt;xs:restriction base="xs:string">
     *     &lt;xs:enumeration value="STANDARD_MAIL"/>
     *     &lt;xs:enumeration value="PRIORITY_MAIL"/>
     *     &lt;xs:enumeration value="INTERNATIONAL_MAIL"/>
     *     &lt;xs:enumeration value="DOMESTIC_EXPRESS"/>
     *     &lt;xs:enumeration value="INTERNATIONAL_EXPRESS"/>
     *   &lt;/xs:restriction>
     * &lt;/xs:simpleType>
     * </pre>
     */
    public static enum Shipping {
        STANDARD_MAIL, PRIORITY_MAIL, INTERNATIONAL_MAIL, DOMESTIC_EXPRESS, INTERNATIONAL_EXPRESS
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://jibx.org/starter1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="item" minOccurs="0" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="xs:string" name="id" minOccurs="0"/>
     *       &lt;xs:element type="xs:string" name="description" minOccurs="0"/>
     *     &lt;/xs:sequence>
     *     &lt;xs:attribute type="xs:int" use="required" name="quantity"/>
     *     &lt;xs:attribute type="xs:float" use="required" name="price"/>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Item
    {
        private String id;
        private String description;
        private Integer quantity;
        private Float price;

        /** 
         * Get the 'id' element value. Stock identifier. This is expected to be 12 characters in
                                        length, with two leading alpha characters followed by ten decimal
                                        digits.
         * 
         * @return value
         */
        public String getId() {
            return id;
        }

        /** 
         * Set the 'id' element value. Stock identifier. This is expected to be 12 characters in
                                        length, with two leading alpha characters followed by ten decimal
                                        digits.
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /** 
         * Get the 'description' element value. Text description of item.
         * 
         * @return value
         */
        public String getDescription() {
            return description;
        }

        /** 
         * Set the 'description' element value. Text description of item.
         * 
         * @param description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /** 
         * Get the 'quantity' attribute value. Number of units ordered.
         * 
         * @return value
         */
        public Integer getQuantity() {
            return quantity;
        }

        /** 
         * Set the 'quantity' attribute value. Number of units ordered.
         * 
         * @param quantity
         */
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        /** 
         * Get the 'price' attribute value. Price per unit.
         * 
         * @return value
         */
        public Float getPrice() {
            return price;
        }

        /** 
         * Set the 'price' attribute value. Price per unit.
         * 
         * @param price
         */
        public void setPrice(Float price) {
            this.price = price;
        }
    }
}
