package com.andresescobar.DroolsDynamicRules.model;

import org.json.simple.JSONObject;

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
	
	public JSONObject toJSON() {
		JSONObject bookJSON = new JSONObject();
		bookJSON = (JSONObject) bookJSON.put("ID", bookNumber);
		bookJSON = (JSONObject) bookJSON.put("name", this.name);
		bookJSON =  (JSONObject) bookJSON.put("price", this.price);
		return bookJSON;
	}
	
}
