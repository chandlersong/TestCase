
package com.hilatest.mock.staticmethod;

import com.hilatest.mock.Target;

public class MockStaticMethod {

    public void run() {
        Target t = StaticMockFactory.getObject();

        t.doSomeThing();
    }
}
