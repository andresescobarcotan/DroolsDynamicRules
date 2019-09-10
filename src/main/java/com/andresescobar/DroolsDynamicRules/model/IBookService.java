package com.andresescobar.DroolsDynamicRules.model;

import java.util.List;

import org.json.JSONObject;
/**
 * Interface to uncouple the implementation from the Spring controller
 * 
 */

public interface IBookService {
		void populateBooks();
		void createBook(Book book);
		List<JSONObject> getBooks();
}


