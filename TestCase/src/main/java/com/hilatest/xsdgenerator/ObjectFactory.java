//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.06.16 时间 03:54:50 PM CST 
//


package com.hilatest.xsdgenerator;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hilatest.xsdgenerator package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnOptionalElementInSomeNamespace_QNAME = new QName("http://some/namespace", "anOptionalElementInSomeNamespace");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hilatest.xsdgenerator
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FooBar }
     * 
     */
    public FooBar createFooBar() {
        return new FooBar();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://some/namespace", name = "anOptionalElementInSomeNamespace")
    public JAXBElement<String> createAnOptionalElementInSomeNamespace(String value) {
        return new JAXBElement<String>(_AnOptionalElementInSomeNamespace_QNAME, String.class, null, value);
    }

}
