package com.hilatest.androidexample.testcase.storage.sharereference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.testcase.CommonTest;

public class ShareReferenceTest extends CommonTest {

	
	public void testStoreAndRetrive(){
		
		/*
		 * Context.MODE_WORLD_READABLE,		
		 * Context.MODE_WORLD_WRITEABLE
		 * the other two variable of Context.MODE_PRIVATE
		 */
        //store
		SharedPreferences prefs =this.getActivity().
		 										getSharedPreferences(
						                                 "sharereferenctest",
						                                 Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		
		editor.putString("aa", "success");
		editor.commit();
		
		
		SharedPreferences secondprefs =this.getActivity().
											getSharedPreferences(
								                 "sharereferenctest",
								                 Context.MODE_PRIVATE);
		
		String result = secondprefs.getString("aa", "fail");
		Log.i(C.Applicatin, result);
		
	}
}
