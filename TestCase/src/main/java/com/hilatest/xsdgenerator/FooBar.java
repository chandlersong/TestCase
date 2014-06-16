//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.06.16 时间 03:54:50 PM CST 
//


package com.hilatest.xsdgenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>fooBar complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="fooBar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="optionalElement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="requiredAttribute" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="optionalAttribute" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fooBar", propOrder = {
    "aa",
    "optionalElement"
})
public class FooBar {

    @XmlElement(required = true, defaultValue = "requiredElementValue")
    protected String aa;
    protected String optionalElement;
    @XmlAttribute(name = "requiredAttribute", required = true)
    protected String requiredAttribute;
    @XmlAttribute(name = "optionalAttribute")
    protected String optionalAttribute;

    /**
     * 获取aa属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAa() {
        return aa;
    }

    /**
     * 设置aa属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAa(String value) {
        this.aa = value;
    }

    /**
     * 获取optionalElement属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionalElement() {
        return optionalElement;
    }

    /**
     * 设置optionalElement属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionalElement(String value) {
        this.optionalElement = value;
    }

    /**
     * 获取requiredAttribute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredAttribute() {
        return requiredAttribute;
    }

    /**
     * 设置requiredAttribute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredAttribute(String value) {
        this.requiredAttribute = value;
    }

    /**
     * 获取optionalAttribute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionalAttribute() {
        return optionalAttribute;
    }

    /**
     * 设置optionalAttribute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionalAttribute(String value) {
        this.optionalAttribute = value;
    }

}
