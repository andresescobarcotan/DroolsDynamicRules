package com.andresescobar.DroolsDynamicRules.model;

import java.util.List;

import org.json.simple.JSONObject;

public interface IBookService {
		void populateBooks();
		void createBook(Book book);
		List<JSONObject> getBooks();
}


