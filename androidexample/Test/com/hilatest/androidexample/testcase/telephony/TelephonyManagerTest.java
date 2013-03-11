package com.hilatest.androidexample.testcase.telephony;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.testcase.CommonTest;

/**
 * Phone-related permission
 * 
 * android.permission.CALL_PHONE
 * Initiate a phone call without user confirmation in dialer
 * 
 * android.permission.CALL_PRIVILEGED
 * Call any number, including emergency, without confirmation in diale
 * 
 * android.permission.MODIFY_PHONE_STATE
 * Allow the application to modify the phone state; for example, to turn the radio on or off
 * 
 * android.permission.PROCESS_OUTGOING_CALLS
 * Allow application to receive broadcast for outgoing calls and modify
 * 
 * android.permission.READ_PHONE_STATE
 * Allow application to read the phone state
 * 
 * 
 * @author Chandler.Song
 *
 */
public class TelephonyManagerTest extends CommonTest{
	
	
	public TelephonyManagerTest(){
		super();
	}
	
	/**
	 * <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
	 */
	public void testBaisc(){
		final TelephonyManager telMgr= (TelephonyManager) this.getActivity().getSystemService(
				Context.TELEPHONY_SERVICE);
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
	}
	
	/**
	 * <uses-permission android:name="android.permission.CALL_PHONE"></uses-permission>
	 */
	public void testMakePhoneCall(){
		Intent intent = 
            new Intent(Intent.ACTION_CALL,
              Uri.parse("tel:" + "12345"));
        this.getActivity().startActivity(intent);
	}
	
	
	public void testDailerPhoneCall(){
		Intent intent = 
            new Intent(Intent.ACTION_DIAL,
              Uri.parse("tel:" + "12345"));
        this.getActivity().startActivity(intent);
	}
}
