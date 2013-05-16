
package com.hilatest.mock.staticmethod;

import com.hilatest.mock.RealObject;
import com.hilatest.mock.Target;

public class StaticMockFactory {

    public static Target getObject() {
        return new RealObject();
    }
}
