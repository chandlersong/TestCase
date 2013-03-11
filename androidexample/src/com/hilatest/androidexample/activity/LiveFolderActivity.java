package com.hilatest.androidexample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.LiveFolders;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.R;

public class LiveFolderActivity extends Activity {

	public static final Uri CONTENT_URI = Uri.parse("content://hialtestLiveFolder/live_folders/books");
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		

		final Intent i = this.getIntent();
		
		Log.i(C.Applicatin, "is ACTION_CREATE_LIVE_FOLDER:"+LiveFolders.ACTION_CREATE_LIVE_FOLDER.equals(i.getAction()));
		Log.i(C.Applicatin, "ACTION_CREATE_LIVE_FOLDER:"+i.getAction());
		
		if (LiveFolders.ACTION_CREATE_LIVE_FOLDER.equals(i.getAction())) {
            setResult(RESULT_OK, createLiveFolder(this, CONTENT_URI,
                    "Live Folder example", R.drawable.icon_android));
            finish();
        }
	}

	private static Intent createLiveFolder(Context context, Uri uri, String name, int icon) {
        final Intent intent = new Intent();

        intent.setData(uri);
        intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_NAME, name);
        intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_ICON,
                Intent.ShortcutIconResource.fromContext(context, icon));
        intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_DISPLAY_MODE, LiveFolders.DISPLAY_MODE_LIST);

        return intent;
    }

	
}
