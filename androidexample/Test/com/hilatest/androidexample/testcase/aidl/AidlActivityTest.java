package com.hilatest.androidexample.testcase.aidl;

import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.aidl.AidlActivity;
import com.hilatest.androidexample.testcase.CommonTest;

public class AidlActivityTest extends CommonTest{

  

	public void testBasic(){
		Log.i(C.Applicatin, "start test aidl");
		this.staraidlActivity(AidlActivity.class);
		
		Log.i(C.Applicatin, this.getActivity().toString());
	
	}
}
