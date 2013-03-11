package com.hilatest.androidexample.activity;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.R;

public class AddGesturesActivity extends Activity{

	private final File mStoreFile = new File(Environment.getExternalStorageDirectory(), "/androidexample/gestures");
	
	private static final float LENGTH_THRESHOLD = 120.0f;

    private Gesture mGesture;
    
	private static GestureLibrary sStore;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.create_gesture);
		
		if(!mStoreFile.exists()){
			try {
				Log.i(C.Applicatin, "create new gesture file:"+mStoreFile.createNewFile());
			} catch (IOException e) {
				Log.e(C.Applicatin, "can't create gesture file",e);
			}
		}
		
		sStore = GestureLibraries.fromFile(mStoreFile);
		
		Log.i(C.Applicatin, "load the library");
		
		GestureOverlayView gestureView = (GestureOverlayView)this.findViewById(R.id.gestures_overlay);
		gestureView.addOnGestureListener(new GesturesProcessor());
	}

	public void addGesture(View view){
		
	
		String gestureName = ((EditText)this.findViewById(R.id.gesture_name)).getText().toString();
		if(mGesture==null||(gestureName==null||gestureName.length()==0)){
			return;
		}
		
		sStore.addGesture(gestureName, mGesture);
		sStore.save();
		
		Toast.makeText(this, gestureName+ " has been saved at "+mStoreFile.getPath(), Toast.LENGTH_LONG).show();
	}
	
	public void cancelGesture(View view){
		
	}

	 private class GesturesProcessor implements GestureOverlayView.OnGestureListener {
	        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
	            mGesture = null;
	        }

	        public void onGesture(GestureOverlayView overlay, MotionEvent event) {
	        }

	        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
	            mGesture = overlay.getGesture();
	            if (mGesture.getLength() < LENGTH_THRESHOLD) {
	                overlay.clear(false);
	            }
	        }

	        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
	        }
	    }
}
