package com.hilatest.googledata.ajaxsearch;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hilatest.googledata.ajaxsearch.AJAXSearchBasic.KeyWord.CursorProperty;
import com.hilatest.googledata.ajaxsearch.AJAXSearchBasic.KeyWord.ResultProperty;
import com.hilatest.googledata.ajaxsearch.AJAXSearchBasic.KeyWord.CursorProperty.PagesProperty;

public class AJAXSearchBasic {

	public static final String KEY ="ABQIAAAAsnirEq5OVQ0pYbrdHP0XzxRvVOhiXqeISfEVwn3tyiaSihzoHBRNRxvfuPbfraddxMvVKtRBVgxNsQ";
	
	public static interface KeyWord{
		public static final String responseData="responseData";
		public static final String RESULT="results";
		
		public static interface ResultProperty{
			public static final String GsearchResultClass="GsearchResultClass";
			public static final String unescapedUrl="unescapedUrl";
			public static final String url="url";
			public static final String visibleUrl="visibleUrl";
			public static final String cacheUrl="cacheUrl";
			public static final String title="title";
			public static final String titleNoFormatting="titleNoFormatting";
			public static final String content="content";
		}
		
		public static final String CURSOR ="cursor";
		
		public static  interface CursorProperty{
			public static final String pages ="pages";
			
			public static interface PagesProperty{
				public static final String start ="start";
				public static final String label ="label";
			}
			
			public static final String estimatedResultCount ="estimatedResultCount";
			
			public static final String currentPageIndex ="currentPageIndex";
			
			public static final String moreResultsUrl ="moreResultsUrl";
		}
		
		public static final String responseDetails ="responseDetails";
		
		public static final String responseStatus ="responseStatus";
	}
	
	public String getIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "127.0.0.1";
		}
	}
	
	public JSONObject doSearch(String q, String start){
		try {
			System.out.println("Start test Ajax search");
			HttpClient httpclient = new DefaultHttpClient();
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("v", "1.0"));
			qparams.add(new BasicNameValuePair("key", KEY));
			qparams.add(new BasicNameValuePair("q", q));
			qparams.add(new BasicNameValuePair("start",start));
			//qparams.add(new BasicNameValuePair("userip",this.getIP()));		
			qparams.add(new BasicNameValuePair("rsz","8"));
			
			
			URI uri = URIUtils.createURI("https", "ajax.googleapis.com", -1,
					"ajax/services/search/web",
					URLEncodedUtils.format(qparams, "UTF-8"), null);
			
			System.out.println("url" + uri.toString());
			
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			String body = EntityUtils.toString(response.getEntity());
		
	        
			// When HttpClient instance is no longer needed, 
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
			
			return new JSONObject(body).getJSONObject(KeyWord.responseData);
		} catch (Exception e) {
			return new JSONObject();
		}
	}
	
	public JSONObject doSearch(String start){
		return this.doSearch("hello world", start);
	}
	
	public JSONObject doSearch(){
		return this.doSearch("hello world","0");
	}
	
	public void printResult(JSONObject json){
		
		try {
			JSONArray result = json.getJSONArray(KeyWord.RESULT);
			int length = result.length();
			System.out.println("result:");
			System.out.println("total size:" + length);
			for (int i = 0; i < length; i++) {
				JSONObject record = result.getJSONObject(i);
				System.out.println(i + ":" + record.toString());
				System.out.println("title:" + ":"
						+ record.getString(ResultProperty.title));
			}
			System.out.println("cursor:");
			JSONObject cursor = json.getJSONObject(KeyWord.CURSOR);
			System.out.println("pages:");
			JSONArray pages = cursor.getJSONArray(CursorProperty.pages);
			System.out.println("page size:" + pages.length());
			for (int i = 0; i < pages.length(); i++) {
				JSONObject pageitem = pages.getJSONObject(i);
				System.out.println(i + ":" + pageitem.toString());
				System.out.println("start:"
						+ pageitem.getString(PagesProperty.start));
				System.out.println("label:"
						+ pageitem.getString(PagesProperty.label));
			}
			System.out.println("estimatedResultCount:"
					+ cursor.getString(CursorProperty.estimatedResultCount));
			System.out.println("currentPageIndex:"
					+ cursor.getString(CursorProperty.currentPageIndex));
		} catch (Exception e) {

		}
		
	}
}
