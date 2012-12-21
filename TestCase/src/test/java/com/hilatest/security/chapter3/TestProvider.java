package com.hilatest.security.chapter3;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.hilatest.security.SuperTestCase;

public class TestProvider extends SuperTestCase{

	/**
	 * list provider
	 */
	@Test
	public void listProvider(){
		
		Provider[] ps = Security.getProviders();
		Set<Entry<Object, Object>> prop;
		for(Provider p:ps){
			System.out.println(p);
			
			prop = p.entrySet();
			
			for(Entry<Object,Object> e:prop){
				System.out.println("\t"+e.getKey());
			}
		}
		
	}
	
	/**
	 * list provider
	 */
	@Test
	public void testBCProvider(){
		
		Provider bc = Security.getProvider("BC");
			

			Set<Service> services = bc.getServices();
			
			for(Service s:services){
				System.out.println(s.getAlgorithm());
			}
	/*		Set<Entry<Object, Object>> prop;
	        prop = bc.entrySet();
			
			for(Entry<Object,Object> e:prop){
				System.out.println("\t"+e.getKey());
			}*/
	}
	
}

