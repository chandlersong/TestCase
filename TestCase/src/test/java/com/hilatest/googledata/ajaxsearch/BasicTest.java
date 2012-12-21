package com.hilatest.googledata.ajaxsearch;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.hilatest.googledata.ajaxsearch.AJAXSearchBasic.KeyWord.CursorProperty;
import com.hilatest.googledata.ajaxsearch.AJAXSearchBasic.KeyWord.ResultProperty;
import com.hilatest.googledata.ajaxsearch.AJAXSearchBasic.KeyWord.CursorProperty.PagesProperty;


public class BasicTest extends AJAXSearchBasic {


	@Test
	public void testBasic() throws JSONException, URISyntaxException, ClientProtocolException, IOException{
		System.out.println("Start test Ajax search");
		HttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("v", "1.0"));
		qparams.add(new BasicNameValuePair("key",KEY));
		qparams.add(new BasicNameValuePair("q", "Hello world"));
		//qparams.add(new BasicNameValuePair("start","1"));
		//qparams.add(new BasicNameValuePair("userip",this.getIP()));
		
		URI uri = URIUtils.createURI("https", "ajax.googleapis.com", -1, "ajax/services/search/web", 
			    URLEncodedUtils.format(qparams, "UTF-8"), null); 
		
		System.out.println("url"+uri.toString());
		HttpGet httpget = new HttpGet(uri);
		    
        HttpResponse response = httpclient.execute(httpget);
        
        String body = EntityUtils.toString(response.getEntity());
        
        Header[] headers =response.getAllHeaders();
        
        System.out.println("status:"+response.getStatusLine());
        System.out.println("/////////////////////////////////////");
        for(Header h:headers){
        	System.out.println(h.getName()+":"+h.getValue());
        }
        System.out.println("/////////////////////////////////////");
        System.out.println("result:");
        System.out.println(body);
        // When HttpClient instance is no longer needed, 
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        
        
    
		
        httpclient.getConnectionManager().shutdown();  
        
        System.out.println("finish test ajax search");
	}
	
	@Test
	public void TestElement() throws JSONException{
		

		 
		JSONObject json = this.doSearch();
		 
		JSONArray result = json.getJSONArray(KeyWord.RESULT);
		 
		int length = result.length();
		 
		System.out.println("result:");
		System.out.println("total size:"+length);
		
		for(int i=0;i<length;i++){
			JSONObject record = result.getJSONObject(i);
			System.out.println(i+":"+record.toString());
			System.out.println("title:"+":"+record.getString(ResultProperty.title));
		}
		
		System.out.println("cursor:");
		
		JSONObject cursor = json.getJSONObject(KeyWord.CURSOR);
		System.out.println("pages:");
		
		JSONArray pages = cursor.getJSONArray(CursorProperty.pages);
		System.out.println("page size:"+pages.length());
		
		for(int i=0;i<pages.length();i++){
			JSONObject pageitem = pages.getJSONObject(i);
			System.out.println(i+":"+pageitem.toString());
			System.out.println("start:"+pageitem.getString(PagesProperty.start));
			System.out.println("label:"+pageitem.getString(PagesProperty.label));
		}
		
		System.out.println("estimatedResultCount:"+cursor.getString(CursorProperty.estimatedResultCount));
		System.out.println("currentPageIndex:"+cursor.getString(CursorProperty.currentPageIndex));
	}
	
	@Test
	public void TestPrint() throws JSONException{
		

		 
		JSONObject json = this.doSearch();
		 
        this.printResult(json);
	}
}
