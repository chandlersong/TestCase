
package com.hilatest.xsdgenerator;

/** 
 * Address information.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://jibx.org/starter1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="address">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="street1" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="street2" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="city" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="state" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="postCode" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="country" minOccurs="0"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Address
{
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postCode;
    private String country;

    /** 
     * Get the 'street1' element value. First line of street information (required).
     * 
     * @return value
     */
    public String getStreet1() {
        return street1;
    }

    /** 
     * Set the 'street1' element value. First line of street information (required).
     * 
     * @param street1
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    /** 
     * Get the 'street2' element value. Second line of street information (optional).
     * 
     * @return value
     */
    public String getStreet2() {
        return street2;
    }

    /** 
     * Set the 'street2' element value. Second line of street information (optional).
     * 
     * @param street2
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /** 
     * Get the 'city' element value.
     * 
     * @return value
     */
    public String getCity() {
        return city;
    }

    /** 
     * Set the 'city' element value.
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /** 
     * Get the 'state' element value. State abbreviation (required for the U.S. and Canada, optional
                          otherwise).
     * 
     * @return value
     */
    public String getState() {
        return state;
    }

    /** 
     * Set the 'state' element value. State abbreviation (required for the U.S. and Canada, optional
                          otherwise).
     * 
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /** 
     * Get the 'postCode' element value. Postal code (required for the U.S. and Canada, optional
                          otherwise).
     * 
     * @return value
     */
    public String getPostCode() {
        return postCode;
    }

    /** 
     * Set the 'postCode' element value. Postal code (required for the U.S. and Canada, optional
                          otherwise).
     * 
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /** 
     * Get the 'country' element value. Country name (optional, U.S. assumed if not
                          supplied).
     * 
     * @return value
     */
    public String getCountry() {
        return country;
    }

    /** 
     * Set the 'country' element value. Country name (optional, U.S. assumed if not
                          supplied).
     * 
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
