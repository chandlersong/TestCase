package com.hilatest.googledata.finance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
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

/*
 * http://www.google.com/finance/info?oe=utf8&infotype=infoquoteall&q=SHA:000001,SHE:399001&zx=1296541030912
 * 
 * http://www.google.com/ig/api?stock=AAPL
 */
public class TestStock {

	 @Test
		public void testBasic() throws ClientProtocolException, IOException, URISyntaxException, JSONException{
	    	System.out.println("Start test March basic");
			HttpClient httpclient = new DefaultHttpClient();
			
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("oe", "utf8"));
			qparams.add(new BasicNameValuePair("infotype","infoquoteall"));
			qparams.add(new BasicNameValuePair("q","SHA:000001,SHE:399001"));
			
			qparams.add(new BasicNameValuePair("zx",String.valueOf(new Date().getTime())));
			URI uri = URIUtils.createURI("http", "www.google.com", -1, "finance/info", 
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
	        
	        int begin = body.indexOf("[");
	        System.out.println("json:");
	        System.out.println(body.substring(begin));
	        JSONArray json = new JSONArray((body.substring(begin)));
	        System.out.println("json size:"+":"+json.length());
	        int length = json.length();
	        for(int i=0;i<length;i++){
				JSONObject match = json.getJSONObject(i);
				System.out.println(i+" price:"+match.getString("lo"));
				System.out.println(i+":"+match.toString());
				System.out.println("no:"+":"+match.getString("t"));
			}
	        httpclient.getConnectionManager().shutdown();  
	        
	        System.out.println("finish test March basic");
	        
	        Date now = new Date();
	        System.out.println(now.getTime());
		}
}
