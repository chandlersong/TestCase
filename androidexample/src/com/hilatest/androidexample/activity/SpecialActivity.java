package com.hilatest.androidexample.activity;

import com.hilatest.androidexample.C;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SpecialActivity extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		Log.i(C.Applicatin, "Special Activity has been load");
	}
}
