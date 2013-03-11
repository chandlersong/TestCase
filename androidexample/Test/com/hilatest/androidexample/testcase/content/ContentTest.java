package com.hilatest.androidexample.testcase.content;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.testcase.CommonTest;

public class ContentTest extends CommonTest {

	
	public void testFindOtherContext(){
		Context otherAppsContext =null;
		try {
		   otherAppsContext = 
			    this.getActivity().createPackageContext("com.hilatest.androidexample",
			      Context.MODE_WORLD_WRITEABLE);
		} catch (NameNotFoundException e) {
			Log.e(C.Applicatin, "context not found", e);
		}
		
		if(otherAppsContext!=null){
			Log.i(C.Applicatin, "success");
		}
	}
}
