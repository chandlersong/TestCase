package com.hilatest.apachecommon3.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testStripStart(){
        
        System.out.println("StringUtils.stripStart(\"yxabc\", \"xyz\"):"+StringUtils.stripStart("yxabc  ", "xyz"));
        System.out.println("StringUtils.stripStart(\"yxabc\", \"y\"):"+StringUtils.stripStart("yxabc  ", "y"));
        System.out.println("StringUtils.stripStart(\"yxabc\", \"yz\"):"+StringUtils.stripStart("yxabc  ", "yz"));
        System.out.println("StringUtils.stripStart(\"zayxabc\", \"yz\"):"+StringUtils.stripStart("zayxabc  ", "yz"));
    }
}
