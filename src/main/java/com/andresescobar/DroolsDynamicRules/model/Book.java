package com.andresescobar.DroolsDynamicRules.model;

import org.json.JSONObject;
/*
 * Java bean that is linked with the rules.
 * It has three attributes:
 * Booknumber which is non-mandaotry but a primary key
 * Name : The name of the book
 * Price: The price in double of the book
 * 
 */
public class Book {
	private Integer bookNumber;
	private String name;
	private double price;

	public Integer getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(Integer bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book: "+this.name+ " "+ String.valueOf(this.price);
	}
	
	public JSONObject toJSON() {
		JSONObject bJSON = new JSONObject();
		bJSON = bJSON.put("ID", bookNumber);
		bJSON = bJSON.put("name", name);
		bJSON = bJSON.put("price", price);
		System.out.println("New json : "+bJSON.toString());
		return bJSON;
	}
	
}
