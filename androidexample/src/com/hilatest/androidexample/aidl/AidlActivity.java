package com.hilatest.androidexample.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.C.IntentCont.ActionCont;
import com.hilatest.androidexample.R;

public class AidlActivity extends Activity {

    private aidlSeverExample service;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            Log.i(C.Applicatin, "ComponentName:" + arg0.getShortClassName());
            Log.i(C.Applicatin, "bind success");

            service = aidlSeverExample.Stub.asInterface(arg1);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.i(C.Applicatin, "ComponentName:" + arg0);
            Log.i(C.Applicatin, "bind fail");

        }

    };

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(C.Applicatin, "start aidl test");

        this.setContentView(R.layout.aidl_example);
    }

    /**
     * 
     * 
     * the log when bind service after bind services
     * 05-17 13:56:54.446: ERROR/androidexample(1947): service is null
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): FATAL EXCEPTION: main
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947):
     * java.lang.IllegalStateException: Could not execute method of the activity
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.view.View$1.onClick(View.java:2144)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.view.View.performClick(View.java:2485)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.view.View$PerformClick.run(View.java:9080)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.os.Handler.handleCallback(Handler.java:587)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.os.Handler.dispatchMessage(Handler.java:92)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.os.Looper.loop(Looper.java:123)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.app.ActivityThread.main(ActivityThread.java:3683)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * java.lang.reflect.Method.invokeNative(Native Method)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * java.lang.reflect.Method.invoke(Method.java:507)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * com.android.internal.os
     * .ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:839)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * com.android.internal.os.ZygoteInit.main(ZygoteInit.java:597)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * dalvik.system.NativeStart.main(Native Method)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): Caused by:
     * java.lang.reflect.InvocationTargetException
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * java.lang.reflect.Method.invokeNative(Native Method)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * java.lang.reflect.Method.invoke(Method.java:507)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.view.View$1.onClick(View.java:2139)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): ... 11 more
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): Caused by:
     * java.lang.IllegalArgumentException: Service not registered:
     * com.hilatest.androidexample.aidl.AidlActivity$1@405329c0
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.app.LoadedApk.forgetServiceDispatcher(LoadedApk.java:891)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.app.ContextImpl.unbindService(ContextImpl.java:890)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * android.content.ContextWrapper.unbindService(ContextWrapper.java:352)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): at
     * com.hilatest.androidexample
     * .aidl.AidlActivity.unBindServiceexample(AidlActivity.java:74)
     * 05-17 13:57:06.952: ERROR/AndroidRuntime(1947): ... 14 more
     * 05-17 13:57:09.395: ERROR/InputDispatcher(61): channel '4082e450
     * com.hilatest.androidexample/com.hilatest.androidexample.MainActivity
     * (server)' ~ Consumer closed input channel or an error occurred.
     * events=0x8
     * 05-17 13:57:09.395: ERROR/InputDispatcher(61): channel '4082e450
     * com.hilatest.androidexample/com.hilatest.androidexample.MainActivity
     * (server)' ~ Channel is unrecoverably broken and will be disposed!
     * 05-17 13:57:09.515: ERROR/InputDispatcher(61): Received spurious receive
     * callback for unknown input channel. fd=153, events=0x8
     * 
     * @param view
     */
    public void runbindSevice(View view) {
        if (this.service != null) {
            try {
                service.testMethodIn("chandler", "awesome");
            } catch (RemoteException e) {
                Log.e(C.Applicatin, "remote erorr", e);
            }
        } else {
            Log.e(C.Applicatin, "service is null");
        }
    }

    public void bindServiceexample(View view) {
        Log.i(C.Applicatin, "start bind service");
        Intent i = new Intent(ActionCont.AidlServer);
        Log.i(C.Applicatin, "bind services:" + this.bindService(i, connection, Context.BIND_AUTO_CREATE));
    }

    public void unBindServiceexample(View view) {
        Log.i(C.Applicatin, "unbind service");
        this.unbindService(connection);
    }

}
