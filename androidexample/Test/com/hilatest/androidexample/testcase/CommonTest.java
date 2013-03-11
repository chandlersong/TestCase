package com.hilatest.androidexample.testcase;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.hilatest.androidexample.MainActivity;

public class CommonTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public CommonTest() {
		super(MainActivity.class);
	}

	public void staraidlActivity(Class<?> activityclass){
    	Activity activity = this.getActivity();
    	Intent i = new Intent(activity,activityclass);
    	activity.startActivity(i);
	    	
    	this.getActivity();
    }
}
