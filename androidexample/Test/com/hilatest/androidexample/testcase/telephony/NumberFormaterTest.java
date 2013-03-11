package com.hilatest.androidexample.testcase.telephony;

import android.telephony.PhoneNumberUtils;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.testcase.CommonTest;

public class NumberFormaterTest extends CommonTest {

	public void testFormateNumber(){
		
		String example1 = "abcdefg";
		Log.i(C.Applicatin, "example1:"+example1);
		Log.i(C.Applicatin, "After Format:"+PhoneNumberUtils.formatNumber(example1));
		
		
		String example2 = "12345678901";
		String example2_1 = "1-234-567-8901";
		Log.i(C.Applicatin, "example2:"+example2);
		Log.i(C.Applicatin, "After Format:"+PhoneNumberUtils.formatNumber(example2));
		Log.i(C.Applicatin, "example2_1:"+example2_1);
		Log.i(C.Applicatin, "Compare example2 and example2_1:"+PhoneNumberUtils.compare(example2, example2_1));
		
		String example3 = "+860211234567891";
		Log.i(C.Applicatin, "example3:"+example3);
		Log.i(C.Applicatin, "example3 is a gobal phone:"+PhoneNumberUtils.isGlobalPhoneNumber(example2));
		Log.i(C.Applicatin, "example3 is a emergency phone:"+PhoneNumberUtils.isEmergencyNumber(example2));
		
		String example5="1-800-GOOG-411";
		Log.i(C.Applicatin, "example5:"+example5);
		Log.i(C.Applicatin, "convertKeypadLettersToDigits:"+PhoneNumberUtils.convertKeypadLettersToDigits(example5));
	}
}
