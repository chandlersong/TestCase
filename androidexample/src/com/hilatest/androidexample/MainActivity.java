package com.hilatest.androidexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hilatest.androidexample.activity.AsyncTaskActivity;
import com.hilatest.androidexample.activity.DrawableMutationActivity;
import com.hilatest.androidexample.activity.GesturesActivity;
import com.hilatest.androidexample.aidl.AidlActivity;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /**
         * 会根据屏幕的分别率，来选择到底是显示values或者说values-mdpi的值
         * 如果屏幕是mdpi的话，会显示mdpi，否则则是hdpi
         */
        Toast.makeText(this,R.string.dpitest, Toast.LENGTH_LONG).show(); 
    }

	public void aidl(View view){
		this.startActivity(new Intent(this,AidlActivity.class));
	}
	
	public void drawableMutation(View view){
		this.startActivity(new Intent(this,DrawableMutationActivity.class));
	}

	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@SuppressWarnings("unused")
	@Override
	protected void onStart() {
		super.onStart();
		
		if(false){
			//Obtaining phone state information
			final TelephonyManager telMgr =
	            (TelephonyManager) this.getSystemService(
	                Context.TELEPHONY_SERVICE); 
	       PhoneStateListener phoneStateListener = 
	         new PhoneStateListener() {
	           public void onCallStateChanged(
	             int state, String incomingNumber) {
	        	   Log.i(C.Applicatin,"states changed"); 
	        	   Log.i(C.Applicatin,"incomingNumber"+incomingNumber);
	        	   Log.i(C.Applicatin,"state"+state);
	               Log.i(C.Applicatin,getTelephonyOverview(telMgr)); 
	           }
	       }; 
	       telMgr.listen(phoneStateListener,
	    		   PhoneStateListener.LISTEN_CALL_STATE);
	       
	       Log.i(C.Applicatin,"on start method"); 
	       Log.i(C.Applicatin,getTelephonyOverview(telMgr)); 
		}
       
	}
	
	public String getTelephonyOverview(TelephonyManager telMgr){
		 String callStateString = "NA";
	        int callState = telMgr.getCallState();
	        switch (callState) {
	        case TelephonyManager.CALL_STATE_IDLE:
	            callStateString = "IDLE";
	            break;
	        case TelephonyManager.CALL_STATE_OFFHOOK:
	            callStateString = "OFFHOOK";
	            break;
	        case TelephonyManager.CALL_STATE_RINGING:
	            callStateString = "RINGING";
	            break;
	        }
	        Log.i(C.Applicatin, "callStateString:"+callStateString);
	        
	        GsmCellLocation cellLocation = 
	          (GsmCellLocation) telMgr.getCellLocation();  
	        
	        if(cellLocation!=null){
		        String cellLocationString =cellLocation.getLac() + " " + cellLocation.getCid();
		        Log.i(C.Applicatin, "cellLocationString:"+cellLocationString);  
	        }else{
	        	 Log.i(C.Applicatin, "GsmCellLocation is null");
	        }
	        
	        String deviceId = telMgr.getDeviceId();
	        String deviceSoftwareVersion = 
	          telMgr.getDeviceSoftwareVersion();
	        String line1Number = telMgr.getLine1Number();
	        String networkCountryIso = telMgr.getNetworkCountryIso();
	        String networkOperator = telMgr.getNetworkOperator();
	        String networkOperatorName = telMgr.getNetworkOperatorName();
	        
	       
	        Log.i(C.Applicatin, "getPhoneType:"+telMgr.getPhoneType());
	        Log.i(C.Applicatin, "deviceId:"+deviceId);
	        Log.i(C.Applicatin, "deviceSoftwareVersion:"+deviceSoftwareVersion);
	        Log.i(C.Applicatin, "line1Number:"+line1Number);
	        Log.i(C.Applicatin, "networkCountryIso:"+networkCountryIso);
	        Log.i(C.Applicatin, "networkOperator:"+networkOperator);
	        Log.i(C.Applicatin, "networkOperatorName:"+networkOperatorName);
	        
	        String phoneTypeString = "NA";
	        int phoneType = telMgr.getPhoneType();
	        switch (phoneType) {
	        case TelephonyManager.PHONE_TYPE_GSM:
	            phoneTypeString = "GSM";
	            break;
	        case TelephonyManager.PHONE_TYPE_CDMA:
	            phoneTypeString = "CDMA";
	            break;
	        case TelephonyManager.PHONE_TYPE_NONE:
	            phoneTypeString = "NONE";
	            break;
	        }
	        
	        Log.i(C.Applicatin, "phoneTypeString:"+phoneTypeString);
	        
	        String simCountryIso = telMgr.getSimCountryIso();
	        String simOperator = telMgr.getSimOperator(); 
	        String simOperatorName = telMgr.getSimOperatorName();
	        String simSerialNumber = telMgr.getSimSerialNumber();
	        String simSubscriberId = telMgr.getSubscriberId();
	        String simStateString = "NA";
	        
	        Log.i(C.Applicatin, "simCountryIso:"+simCountryIso);
	        Log.i(C.Applicatin, "simOperator:"+simOperator);
	        Log.i(C.Applicatin, "simOperatorName:"+simOperatorName);
	        Log.i(C.Applicatin, "simSerialNumber:"+simSerialNumber);
	        Log.i(C.Applicatin, "simSubscriberId:"+simSubscriberId);
	        
	        int simState = telMgr.getSimState();
	        switch (simState) {
	        case TelephonyManager.SIM_STATE_ABSENT:
	            simStateString = "ABSENT";
	            break;
	        case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
	            simStateString = "NETWORK_LOCKED";
	            break;
	        // . . . other SIM states omitted for brevity
	        }
	        Log.i(C.Applicatin, "simStateString:"+simStateString);
	        
	        StringBuilder sb = new StringBuilder();
	        sb.append("telMgr - ");
	        sb.append("  \ncallState = " + callStateString);
	        // . . . remainder of appends omitted for brevity 
	        Log.i(C.Applicatin, "information:"+sb.toString());
	        return "finished";
	}
	
	public void gestures(View view){
		this.startActivity(new Intent(this,GesturesActivity.class));
	}
	
	public void asyncTaskDemo(View view){
		this.startActivity(new Intent(this,AsyncTaskActivity.class));
	}
}