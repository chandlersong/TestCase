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

import com.hilatest.googledata.GoogleBasicTestCase;


public class TestPortfolios extends GoogleBasicTestCase{

	
	
	public static final String SERVICE="finance";
	
	@Override
	protected String getServicename() {
		return SERVICE;
	}
		

	public void testBasic() throws ClientProtocolException, IOException, URISyntaxException{
		
		System.out.println("Start test Portfolios basic");
		HttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("returns", Boolean.TRUE.toString()));
		qparams.add(new BasicNameValuePair("positions", Boolean.TRUE.toString()));
		qparams.add(new BasicNameValuePair("transactions", Boolean.TRUE.toString()));
		URI uri = URIUtils.createURI("http", "finance.google.com", -1, "finance/feeds/default/portfolios", 
		    URLEncodedUtils.format(qparams, "UTF-8"), null);
		
		System.out.println("url"+uri.toString());
		HttpGet httpget = new HttpGet(uri);
		
		httpget = this.sethead(httpget);
		
		Header[] getheaders =httpget.getAllHeaders();
	        
	     System.out.println("/////////////////////////////////////");
	     for(Header h:getheaders){
	        	System.out.println(h.getName()+":"+h.getValue());
	    }
	    System.out.println("/////////////////////////////////////");   
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
        
        System.out.println("finish test Portfolios basic");
	}
	
}
