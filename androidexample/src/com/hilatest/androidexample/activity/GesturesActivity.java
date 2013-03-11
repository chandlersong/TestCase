package com.hilatest.androidexample.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.R;

public class GesturesActivity extends Activity implements OnGesturePerformedListener{

	private static GestureLibrary sStore;
	private final File mStoreFile = new File(Environment.getExternalStorageDirectory(), "/androidexample/gestures");
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.gestures);
		GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gesturesView);
		gestures.addOnGesturePerformedListener(this);
		
		sStore = GestureLibraries.fromFile(mStoreFile);
		
		Log.i(C.Applicatin, "library load:"+sStore.load());
		Log.i(C.Applicatin, "library size:"+sStore.getGestureEntries().size());
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		
		Log.i(C.Applicatin, "GesturesActivity resume");
		sStore = GestureLibraries.fromFile(mStoreFile);
		Log.i(C.Applicatin, "library load:"+sStore.load());
		Log.i(C.Applicatin, "library size:"+sStore.getGestureEntries().size());
		
		super.onResume();
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		 	ArrayList<Prediction> predictions = sStore.recognize(gesture);
		 	
		    Log.i(C.Applicatin, "Prediction size:"+predictions.size());
		    // We want at least one prediction
		    if (predictions.size() > 0) {
		        Prediction prediction = predictions.get(0);
		        // We want at least some confidence in the result
		        if (prediction.score > 1.0) {
		            // Show the spell
		            Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
		        }
		    }

		
	}
	
	
	public void addGestures(View view){
		Intent i = new Intent(this,AddGesturesActivity.class);
		this.startActivity(i);
	}

}
