package com.hilatest.json;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class Jsonorg {

	@Test
	public void testJsonorg() throws JSONException{

	  
		JSONObject jo = new JSONObject();
		
		jo.put("name", "json");
		jo.put("bool", Boolean.TRUE);
		System.out.println( jo.toString() );  
	}
}
