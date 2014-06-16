
package com.hilatest.xsdgenerator;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://some/namespace" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="fooBar">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" default="requiredElementValue" name="requiredElement"/>
 *     &lt;xs:element type="xs:string" name="optionalElement" minOccurs="0"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="requiredAttribute"/>
 *   &lt;xs:attribute type="xs:string" name="optionalAttribute"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class FooBar
{
    private String requiredElement;
    private String optionalElement;
    private String requiredAttribute;
    private String optionalAttribute;

    /** 
     * Get the 'requiredElement' element value.
     * 
     * @return value
     */
    public String getRequiredElement() {
        return requiredElement;
    }

    /** 
     * Set the 'requiredElement' element value.
     * 
     * @param requiredElement
     */
    public void setRequiredElement(String requiredElement) {
        this.requiredElement = requiredElement;
    }

    /** 
     * Get the 'optionalElement' element value.
     * 
     * @return value
     */
    public String getOptionalElement() {
        return optionalElement;
    }

    /** 
     * Set the 'optionalElement' element value.
     * 
     * @param optionalElement
     */
    public void setOptionalElement(String optionalElement) {
        this.optionalElement = optionalElement;
    }

    /** 
     * Get the 'requiredAttribute' attribute value.
     * 
     * @return value
     */
    public String getRequiredAttribute() {
        return requiredAttribute;
    }

    /** 
     * Set the 'requiredAttribute' attribute value.
     * 
     * @param requiredAttribute
     */
    public void setRequiredAttribute(String requiredAttribute) {
        this.requiredAttribute = requiredAttribute;
    }

    /** 
     * Get the 'optionalAttribute' attribute value.
     * 
     * @return value
     */
    public String getOptionalAttribute() {
        return optionalAttribute;
    }

    /** 
     * Set the 'optionalAttribute' attribute value.
     * 
     * @param optionalAttribute
     */
    public void setOptionalAttribute(String optionalAttribute) {
        this.optionalAttribute = optionalAttribute;
    }
}
