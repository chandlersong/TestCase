package com.hilatest.googledata.finance;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.hilatest.googledata.GoogleBasicTestCase;

public class TestPostion extends GoogleBasicTestCase {

	@Override
	protected String getServicename() {
		return TestPortfolios.SERVICE;
	}
	
	public void testbasic() throws ClientProtocolException, IOException{
		System.out.println("Start test Postion basic");
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://finance.google.com/finance/feeds/default/portfolios/1/positions?returns=true");
		
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
        
        System.out.println("finish test Postion basic");
	}

}
