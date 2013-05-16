
package com.hilatest.mock.mockstaticmethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hilatest.mock.MockObject;
import com.hilatest.mock.staticmethod.MockStaticMethod;
import com.hilatest.mock.staticmethod.StaticMockFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StaticMockFactory.class)
public class PowerMockImpl {

    @Test
    public void testBasic() {

        PowerMockito.mockStatic(StaticMockFactory.class);
        PowerMockito.when(StaticMockFactory.getObject()).thenReturn(new MockObject());

        MockStaticMethod object = new MockStaticMethod();
        object.run();
    }
}
