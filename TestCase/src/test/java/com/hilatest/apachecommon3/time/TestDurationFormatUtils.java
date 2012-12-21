package com.hilatest.apachecommon3.time;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.Test;

public class TestDurationFormatUtils {

	@Test
	public void testBasic(){
		Random r = new Random();
		Long duration = Math.abs(r.nextLong());
		Date startDate = new Date();
		
		Date endDate = new Date(startDate.getTime()+duration);
		
		System.out.println("formatDurationHMS:"+DurationFormatUtils.formatDurationHMS(duration));
		System.out.println("formatDurationISO:"+DurationFormatUtils.formatDurationISO(duration));
		System.out.println("formatPeriodISO:"+DurationFormatUtils.formatPeriodISO(startDate.getTime(),endDate.getTime()));
	}
}
