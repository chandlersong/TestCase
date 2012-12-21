package org.springexample.beanwarpper;

import org.junit.Test;
import org.springexample.beanwrapper.Company;
import org.springexample.beanwrapper.Employee;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

/*
 * Table 5.1. Examples of properties
	Expression Explanation
	name 
	Indicates the property name corresponding to the methods getName() or isName() and setName(..)
	account.name 
	Indicates the nested property name of the property account corresponding
	e.g. to the methods getAccount().setName() orgetAccount().getName()
	account[2] 
	Indicates the third element of the indexed property account. Indexed properties can be of type array, list or other naturally ordered collection
	account[COMPANYNAME] I
	ndicates the value of the map entry indexed by the key COMPANYNAME of the Map property account
 */
public class TestWrapperBean {

	@Test
	public void testBasic(){
		BeanWrapper company = new BeanWrapperImpl(new Company());
		// setting the company name..
		company.setPropertyValue("name", "Some Company Inc.");
		// ... can also be done like this:
		PropertyValue value = new PropertyValue("name", "Some Company Inc.");
		company.setPropertyValue(value);
		// ok, let's create the director and tie it to the company:
		BeanWrapper jim = new BeanWrapperImpl(new Employee());
		jim.setPropertyValue("name", "Jim Stravinsky");
		company.setPropertyValue("managingDirector", jim.getWrappedInstance());
		// retrieving the salary of the managingDirector through the company
		Float salary = (Float) company.getPropertyValue("managingDirector.salary");
		
		System.out.println(salary);
	}
}
