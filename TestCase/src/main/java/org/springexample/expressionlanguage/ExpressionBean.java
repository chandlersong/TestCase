package org.springexample.expressionlanguage;

public class ExpressionBean {

	private String desc;
	
	private int number;

	private int filter;
	
	public ExpressionBean(String desc, int number) {
		super();
		this.desc = desc;
		this.number = number;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}
	
}
