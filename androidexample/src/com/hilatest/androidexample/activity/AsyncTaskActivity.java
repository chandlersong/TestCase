package com.hilatest.androidexample.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.R;

/**
 * 按了同步任务的按钮，Toast还可以按。
 * 按了不同步的按钮，Toast不可以按了。
 * @author Chandler.Song
 *
 */
public class AsyncTaskActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.asynctask_demo);
	}

	public void showToast(View view){
		final Toast t = Toast.makeText(this, "button has been clicked", Toast.LENGTH_LONG);
		t.show();
	}
	
	public void notRunAsyncMode(View view){
		final TextView nonAsyncTaskTextView = (TextView)this.findViewById(R.id.nonasyncTaskView);
		
		final Toast t = Toast.makeText(this, "process text", Toast.LENGTH_LONG);
		t.show();
		this.highCostTask();
		
		nonAsyncTaskTextView.setText("task complete");
	}
	
	public void RunAsyncMode(View view){
		new AsyncTaskDemo().execute("run");
	}
	
	public void highCostTask(){
		
		int count = 5000000;
		
		for(int i=0;i<count;i++){
			Math.sqrt(i);
		}
		
	}
	
	private class AsyncTaskDemo extends AsyncTask<String,String,String>{
		
		@Override
		protected String doInBackground(String... params) {
			Log.i(C.Applicatin, "run at backend:");
			this.publishProgress("public process");
			highCostTask();
			return "ok";
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */
		@Override
		protected void onProgressUpdate(String... values) {
			Log.i(C.Applicatin, "onProgressUpdate:"+values[0]);
			super.onProgressUpdate(values);
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			Log.i(C.Applicatin, "finish execute result:"+result);
			final TextView asyncTaskTextView = (TextView)findViewById(R.id.asyncTaskView);
			asyncTaskTextView.setText("task finish");
		}
		
		
	}


}
