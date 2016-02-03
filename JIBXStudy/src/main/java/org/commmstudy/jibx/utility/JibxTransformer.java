package org.commmstudy.jibx.utility;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class JibxTransformer<Entity> {

    private IBindingFactory bfact;

    public JibxTransformer(Class<Entity> cls) throws JiBXException {
        bfact = BindingDirectory.getFactory(cls);
    }

    @SuppressWarnings("unchecked")
    public Entity unmarshalling(String fileName) throws JiBXException, FileNotFoundException {
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        FileInputStream in = new FileInputStream(fileName);
        return (Entity) uctx.unmarshalDocument(in, null);
    }

    public String marshalling(Entity entity) throws JiBXException, FileNotFoundException {

        IMarshallingContext mctx = bfact.createMarshallingContext();
        mctx.setIndent(2);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mctx.marshalDocument(entity, "UTF-8", null, baos);
        return baos.toString();
    }

}
