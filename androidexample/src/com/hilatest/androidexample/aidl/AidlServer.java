package com.hilatest.androidexample.aidl;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.aidl.aidlSeverExample.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlServer extends Service {

	
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		Log.i(C.Applicatin, "aidl servers starts");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(C.Applicatin, "run on bind");
		return new connectServer();
	}
	
	private static class  connectServer extends Stub{

		@Override
		public void testMethodIn(String a, String b) throws RemoteException {
			Log.i(C.Applicatin, "a:"+a);
			Log.i(C.Applicatin, "b:"+b);
			Log.i(C.Applicatin, "aidl success");
		}
		
	}

}
