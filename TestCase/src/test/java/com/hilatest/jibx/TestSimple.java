package com.hilatest.jibx;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.Test;

public class TestSimple {
    static String BINDING_NAME = "binding";
    public final static String URL_ENCODING = "UTF-8";
    public final static String STRING_ENCODING = "UTF8";

    @Test
    public void marshall() {

        Field[] fields = FooRequest.class.getDeclaredFields();

        for (Field filed : fields) {
            System.out.println(filed.getName());
        }

        try {
            // IBindingFactory jc = BindingDirectory.getFactory(BINDING_NAME, PACKAGE_NAME); // OSGi does not like this
            IBindingFactory jc = BindingDirectory.getFactory(FooRequest.class);
            IMarshallingContext marshaller = jc.createMarshallingContext();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FooRequest fooRequest = new FooRequest();
            fooRequest.setRequest("ok");
            marshaller.marshalDocument(fooRequest, URL_ENCODING, null, out);
            String xml = out.toString(STRING_ENCODING);
            System.out.println(xml);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JiBXException e) {
            e.printStackTrace();
        }

    }
}
