package com.hilatest.androidexample.provider;

import com.hilatest.androidexample.C;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class SearchableProvider extends ContentProvider {

	 public static String AUTHORITY = "hilatestsearchDemo";
	 
	private static final int SEARCH_SUGGEST = 0;
    private static final int SHORTCUT_REFRESH = 1;
    
	 /**
     * The columns we'll include in our search suggestions.  There are others that could be used
     * to further customize the suggestions, see the docs in {@link SearchManager} for the details
     * on additional columns that are supported.
     */
    private static final String[] COLUMNS = {
            "_id",  // must include this column
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_TEXT_2,
            SearchManager.SUGGEST_COLUMN_INTENT_DATA,
            };
    
    private static final UriMatcher sURIMatcher = buildUriMatcher();
    
    /**
     * Sets up a uri matcher for search suggestion and shortcut refresh queries.
     */
    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher =  new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH_SUGGEST);
        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH_SUGGEST);
        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_SHORTCUT, SHORTCUT_REFRESH);
        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_SHORTCUT + "/*", SHORTCUT_REFRESH);
        return matcher;
    }
    
	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 
		 //print log
		 Log.i(C.Applicatin, "uri:"+uri.toString()); 
		 
		 if (!TextUtils.isEmpty(selection)) {
	          throw new IllegalArgumentException("selection not allowed for " + uri);
	      }
	      if (selectionArgs != null && selectionArgs.length != 0) {
	            throw new IllegalArgumentException("selectionArgs not allowed for " + uri);
	      }
	      if (!TextUtils.isEmpty(sortOrder)) {
	           throw new IllegalArgumentException("sortOrder not allowed for " + uri);
	      }
	      
	        switch (sURIMatcher.match(uri)) {
	            case SEARCH_SUGGEST:
	                String query = null;
	                if (uri.getPathSegments().size() > 1) {
	                    query = uri.getLastPathSegment().toLowerCase();
	                }
	                Log.i(C.Applicatin, "query string:"+query);
	                return getSuggestions(query, projection);
	            case SHORTCUT_REFRESH:
	                String shortcutId = null;
	                if (uri.getPathSegments().size() > 1) {
	                    shortcutId = uri.getLastPathSegment();
	                }
	                return refreshShortcut(shortcutId, projection);
	            default:
	                throw new IllegalArgumentException("Unknown URL " + uri);
	        }
	}

	private Cursor getSuggestions(String query, String[] projection) {

       MatrixCursor cursor = new MatrixCursor(COLUMNS);
     
       cursor.addRow(columnValuesOfWord(query));
    

        return cursor;
    }
	
	 private Object[] columnValuesOfWord(String word) {
	        return new String[] {
	        		word,           // _id
	        		word,           // text1
	        		word+"descrption",     // text2
	        		word,           // intent_data (included when clicking on item)
	        };
	    }

	    /**
	     * Note: this is unused as is, but if we included
	     * {@link SearchManager#SUGGEST_COLUMN_SHORTCUT_ID} as a column in our results, we
	     * could expect to receive refresh queries on this uri for the id provided, in which case we
	     * would return a cursor with a single item representing the refreshed suggestion data.
	     */
	    private Cursor refreshShortcut(String shortcutId, String[] projection) {
	        return null;
	    }
	    
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
