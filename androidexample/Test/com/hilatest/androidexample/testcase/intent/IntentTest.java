package com.hilatest.androidexample.testcase.intent;

import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.MainActivity;
import com.hilatest.androidexample.testcase.CommonTest;

/**
 * 其实这里想要做的一个目的是，比方有一个request。
 * hilatest://www.hilatest.com/Number。 number可以是1-100
 * 2-100的时候到Common的Activity。而1的时候则是到Special Activity中去。
 * 但是发觉如果这样，一定会弹出一个谈话窗口。让用户选择。
 * @author chandler
 * 
 * The order:
 * 
	 1. Android puts together a list of all the Intent Filters available from the installed packages.
	 2. Intent Filters that do not match the action or category associated with the Intent being resolved 
	are removed from the list. 
		  2.1. Action matches are made if the Intent Filter either includes the speciﬁ  ed action or has 
		no action speciﬁ  ed.
		An Intent Filter will only fail the action match check if it has one or more actions 
		deﬁ ned, where none of them match the action speciﬁ ed by the Intent.
		  2.2. Category matching is stricter. Intent Filters must include all the categories deﬁ  ned in the 
		resolving Intent. An Intent Filter with no categories speciﬁ ed only matches Intents with 
		no categories. 
	  3. Finally, each part of the Intent’s data URI is compared to the Intent Filter’s data tag. If Intent 
	Filter deﬁ nes the scheme, host/authority, path, or mime type, these values are compared to the 
	Intent’s URI. Any mismatches will remove the Intent Filter from the list.
	Specifying no data values in an Intent Filter will match with all Intent data values.
		  3.1. The mime type is the data type of the data being matched. When matching data types, 
		you can use wild cards to match subtypes (e.g., earthquakes/*). If the Intent Filter spec-
		iﬁ es a data type, it must match the Intent; specifying no data type resolves to all of them.
		  3.2. The scheme is the “protocol” part of the URI — for example, http:, mailto:, or tel:.
		  3.3. The host name or “data authority” is the section of the URI between the scheme and the 
		path (e.g., www.google.com). For a host name to match, the Intent Filter’s scheme must 
		also pass.
		  3.4. The data path is what comes after the authority (e.g., /ig). A path can only match if the 
		scheme and host-name parts of the data tag also match. 
	  4. If more than one component is resolved from this process, they are ordered in terms of priority, 
	with an optional tag that can be added to the Intent Filter node. The highest ranking component 
	is then returned.
 *
 */
public class IntentTest extends CommonTest {

	private final static String ACTION="com.hilastest.intentView";
	
	private final static String DATA_REGULAR="hilatest://www.hilatest.com/%1$s";
	
	private final static String specialNumber ="1";
	
	public void testRunCommon(){
		
		
		final MainActivity activity = this.getActivity();
		
		
		
		final Random r = new Random();
		
		final String number = String.valueOf(r.nextInt(100)+2);
	
		final Uri data = Uri.parse(
				             String.format(DATA_REGULAR, number)
				           );
		
		Log.i(C.Applicatin, "data:"+data.toString());
		final Intent i = new Intent(ACTION,data);
		activity.startActivity(i);
	}
	
	public void testRunSpecial(){
	
		MainActivity activity = this.getActivity();
			
		final Uri data = Uri.parse(
				             String.format(DATA_REGULAR, specialNumber)
				           );
		
		Log.i(C.Applicatin, "data:"+data.toString());
		final Intent i = new Intent(ACTION,data);
		activity.startActivity(i);
	}
	
	/**
	 * 判断在系统中，有多少个可以接受intent的activity
	 */
	public void testAvailableActivity(){
		final PackageManager pm = this.getActivity().getPackageManager();
		
		final Uri data = Uri.parse(
	             String.format(DATA_REGULAR, specialNumber)
	           );
		final Intent availableIntent = new Intent(ACTION,data);
		
		List<ResolveInfo> availablelist =
            pm.queryIntentActivities(availableIntent,
                    PackageManager.MATCH_DEFAULT_ONLY);
		Log.i(C.Applicatin, "available intent's activity:"+availablelist.size());
		
		
		final Intent unAvailableIntent = new Intent("xxxxx",data);
		
		List<ResolveInfo> unAvailablelist =
            pm.queryIntentActivities(unAvailableIntent,
                    PackageManager.MATCH_DEFAULT_ONLY);
		Log.i(C.Applicatin, "unavailable intent's activity:"+unAvailablelist.size());

	}
	
	/**
	 * 判断在系统中，有多少个可以接受intent的activity
	 */
	public void testResolveIntent(){
		final PackageManager pm = this.getActivity().getPackageManager();
		
		final Uri data = Uri.parse(
	             String.format(DATA_REGULAR, specialNumber)
	           );
		final Intent availableIntent = new Intent(ACTION,data);
		
		ResolveInfo rf =
            pm.resolveActivity(availableIntent,
                    PackageManager.MATCH_DEFAULT_ONLY);
		Log.i(C.Applicatin, "available intent's activity:"+rf.toString());
	
	}
}
