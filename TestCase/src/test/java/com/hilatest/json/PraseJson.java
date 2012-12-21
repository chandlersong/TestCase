package com.hilatest.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * 
 * @author chandler
 *
 */
public class PraseJson {

	private String example="{ \"matches\" : [ {\"t\":\"ZMH\", \"n\":\"Zimmer Holdings, Inc.\", \"e\":\"NYSE\", \"id\":\"666999\", \"sugg\":[\"1,2\",\"ZMH\",\"Zimmer Holdings, Inc.\"]} ,{\"t\":\"\", \"n\":\"Zimmerman Lehman\", \"e\":\"\", \"id\":\"13476996\", \"sugg\":[\"1,2\",\"\",\"Zimmerman Lehman\"]} ,{\"t\":\"ZIM\", \"n\":\"ZIMMER HOLDINGS DL-,01\",\"e\":\"ETR\", \"id\":\"6977855\", \"sugg\":[\"1,2\",\"ZIM\",\"ZIMMER HOLDINGS DL-,01\"]} ,{\"t\":\"\", \"n\":\"Zimmerman Advertising LLC\",\"e\":\"\", \"id\":\"1501679\", \"sugg\":[\"1,2\",\"\",\"Zimmerman Advertising LLC\"]} ,{\"t\":\"ZIM\", \"n\":\"ZIMMER HOLDINGS DL-,01 (Frankfurt)\", \"e\":\"FRA\", \"id\":\"1763783\", \"sugg\":[\"1,2\",\"ZIM\",\"ZIMMER HOLDINGS DL-,01 (Frankfurt)\"]} ,{\"t\":\"ZIMR\", \"n\":\"ZIMMER CORP\", \"e\":\"PINK\", \"id\":\"10317925\", \"sugg\":[\"1,2\",\"ZIMR\",\"ZIMMER CORP\"]} ,{\"t\":\"\", \"n\":\"Zimmer Gunsul Frasca Architects LLP\", \"e\":\"\", \"id\":\"13155259\", \"sugg\":[\"1,2\",\"\",\"Zimmer Gunsul Frasca Architects LLP\"]} ,{\"t\":\"\", \"n\":\"The Day \\x26 Zimmermann Group, Inc.\", \"e\":\"\", \"id\":\"13695586\", \"sugg\":[\"1,2\",\"\",\"The Day \\x26 Zimmermann Group, Inc.\"]}],\"all\":true} ";

	@Test
	public void praseBasic() throws Exception{
		JSONObject json = new JSONObject(example);
		
		JSONArray matches = json.getJSONArray("matches");		
		System.out.println(matches);
		System.out.println("length:"+matches.length());
		int length = matches.length();
		for(int i=0;i<length;i++){
			JSONObject match = matches.getJSONObject(i);
			System.out.println(i+":"+match.toString());
			System.out.println("no:"+":"+match.getString("t"));
		}
		
	}
}
