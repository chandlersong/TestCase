package com.hilatest.androidexample.testcase.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.testcase.CommonTest;

public class BasicNetTest extends CommonTest{

	/**
	 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
	 */
	public void testStatus(){
		ConnectivityManager cMgr =  (ConnectivityManager)this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
		
		Log.i(C.Applicatin, "net info toString:"+netInfo.toString());
		Log.i(C.Applicatin, "net info is available:"+netInfo.isAvailable());
		Log.i(C.Applicatin, "net info is connected:"+netInfo.isConnected());
		Log.i(C.Applicatin, "net info is connected or connecting:"+netInfo.isConnectedOrConnecting());
		Log.i(C.Applicatin, "net info state:"+netInfo.getState());
	}
}
