package com.hilatest.googledata.finance;

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

/**
 * http://www.google.com/finance/match?matchtype=matchall&q=Zimme&basetkr=undefined&hl=zh-CN
*/
public class TestMarch{

    @Test
	public void testBasic() throws ClientProtocolException, IOException, URISyntaxException, JSONException{
    	System.out.println("Start test March basic");
		HttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("matchtype", "matchall"));
		qparams.add(new BasicNameValuePair("q","162102"));
		qparams.add(new BasicNameValuePair("basetkr","undefined"));
		qparams.add(new BasicNameValuePair("hl","zh-CN"));
		URI uri = URIUtils.createURI("http", "www.google.com", -1, "finance/match", 
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
        
        
        JSONObject json = new JSONObject(body);
		
		JSONArray matches = json.getJSONArray("matches");		
		System.out.println(matches);
		System.out.println("length:"+matches.length());
		int length = matches.length();
		for(int i=0;i<length;i++){
			JSONObject match = matches.getJSONObject(i);
			System.out.println(i+":"+match.toString());
			System.out.println("no:"+":"+match.getString("t"));
		}
		
        httpclient.getConnectionManager().shutdown();  
        
        System.out.println("finish test March basic");
	}
}
