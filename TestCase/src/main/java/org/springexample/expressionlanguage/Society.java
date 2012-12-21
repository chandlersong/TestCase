package org.springexample.expressionlanguage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Society {
	
	private String name;
	public static String Advisors = "advisors";
	public static String President = "president";
	
	private List<Inventor> members = new ArrayList<Inventor>();
	
	private Map<Object,Object> officers = new HashMap<Object,Object>();
	
	public List<Inventor> getMembers(){
		return members;
	}
	
	public Map<Object,Object> getOfficers(){
		return officers;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isMember(String name){
		boolean found = false;
		
		for (Inventor inventor : members) {
			if (inventor.getName().equals(name)){
				found = true;
				break;
			}
		}
		return found;
	}
}
