package com.hilatest.androidexample.testcase.sms;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.hilatest.androidexample.activity.CommonActivity;
import com.hilatest.androidexample.testcase.CommonTest;

public class SMSTest extends CommonTest {

	/**
	 * NOT WORK
	 * <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>
	 */
	public void testSMS(){
		final PendingIntent sentIntent =
	          PendingIntent.getActivity(
	        		  this.getActivity(), 0, new Intent(this.getActivity(),
	            		CommonActivity.class), 0);
		SmsManager smsManager = SmsManager.getDefault();
		String dest = "1234567890";
        if (PhoneNumberUtils.
             isWellFormedSmsAddress(dest)) {
               smsManager.sendTextMessage(
            		   "test", null,
            		   "test", 
                 sentIntent, null);
             Toast.makeText(this.getActivity(),
               "SMS message sent", 
                Toast.LENGTH_LONG).show();
        } else {
           Toast.makeText(this.getActivity(),
            "SMS destination invalid - try again",
              Toast.LENGTH_LONG).show();
        }
	}
}
