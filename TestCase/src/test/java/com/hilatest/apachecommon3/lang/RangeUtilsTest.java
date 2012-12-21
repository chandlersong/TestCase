package com.hilatest.apachecommon3.lang;

import org.apache.commons.lang3.Range;
import org.junit.Assert;
import org.junit.Test;

public class RangeUtilsTest {
    
    /**
     * java.lang.IllegalArgumentException 如果不存在交集时候的Exception
     * 
     */
    @Test
    public void testIntersectionWith(){
        
        Range<Integer> range = Range.between(new Integer(1), new Integer(10));
        Range<Integer> rangeIntersection = Range.between(new Integer(3), new Integer(11));
        
        Range<Integer> rangetestIntersectionWith = range.intersectionWith(rangeIntersection);
        
        Assert.assertTrue(rangetestIntersectionWith.contains(9));
        Assert.assertTrue(!rangetestIntersectionWith.contains(2));
    }
}
