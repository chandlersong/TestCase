package com.hilatest.androidexample.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.LiveFolders;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.R;

public class LiveFolderProvider extends ContentProvider {

	private static final String AUTHORITY = "hialtestLiveFolder";
	private static final int BOOKS = 1;
	private static final int BOOK_ID = 2;
	private static final String LIVE_DATA="hilatest://www.hilatest.com/livefolder";


	// Set of columns needed by a live folder
	// This is the live-folder contract
	private static final String[] CURSOR_COLUMNS = new String[] {
			LiveFolders._ID, LiveFolders.NAME, LiveFolders.DESCRIPTION,
			LiveFolders.INTENT, LiveFolders.ICON_PACKAGE,
			LiveFolders.ICON_RESOURCE };
	private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "live_folders/books", BOOKS);
        URI_MATCHER.addURI(AUTHORITY, "live_folders/books/#", BOOK_ID);
    }
	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.i(C.Applicatin, "provider Live Folder run query");
		Log.i(C.Applicatin, "uri:"+uri.toString());	
		Log.i(C.Applicatin, "match:"+URI_MATCHER.match(uri));
		
		final MatrixCursor stringCursor = new MatrixCursor(CURSOR_COLUMNS);
		
		Object row[];
		for(int i = 0;i<5;i++){
			row = new Object[]{
					String.valueOf(i),                            //id
					"book"+String.valueOf(i),                     //name         
					"description"+String.valueOf(i),              //descprtion
					Uri.parse(LIVE_DATA), //the uri of intent,
					this.getContext().getPackageName(),
					R.drawable.icon_android_red
			};
			stringCursor.addRow(row);
		}
		return stringCursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.i(C.Applicatin, "provider Live Folder run insert");
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.i(C.Applicatin, "provider Live Folder run delete");
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		Log.i(C.Applicatin, "provider Live Folder run update");
		return 0;
	}

}
