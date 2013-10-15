package com.hilatest.common;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class RangeTest {

    @Test
    public void testDate() throws ParseException {
        Date start = DateUtils.parseDate("2011-03-21 09:00", "YYYY-MM-DD HH:MM");

        Date startOK = DateUtils.parseDate("2011-03-21 13:00", "YYYY-MM-DD HH:MM");

        Date conflict = DateUtils.parseDate("2011-03-21 10:00", "YYYY-MM-DD HH:MM");

        Range<Date> dateRange = Range.between(start, DateUtils.addHours(start, 2));

        Range<Date> dateRangeConflict = Range.between(conflict, DateUtils.addHours(conflict, 1));
        Range<Date> dateRangeOK = Range.between(startOK, DateUtils.addHours(startOK, 1));

        System.out.println("confilct isOverlappedBy:" + dateRange.isOverlappedBy(dateRangeConflict));
        System.out.println("ok isOverlappedBy:" + dateRange.isOverlappedBy(dateRangeOK));
    }
}
