package com.andresescobar.DroolsDynamicRules.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService{
	/*
	 * Service class for implementing the API methods
	 * Please notices that a call to executeRules is implied in every method.
	 */
	public BookService() {
		// TODO Auto-generated constructor stub
	}

	public void populateBooks() {
		BookRulesGenerator brg = BookRulesGenerator.getInstance();
		brg.executeRules();
		
	}

	@Override
	public void createBook(Book book) {
		
		BookRulesGenerator brg =  BookRulesGenerator.getInstance();
		brg.createBook(book);
	}
	
	
	@Override
	public List<JSONObject> getBooks() {
		BookRulesGenerator brg =  BookRulesGenerator.getInstance();
		ArrayList<JSONObject> currentLibrary = (ArrayList<JSONObject>) brg.getBooks();
		return currentLibrary;
	}

}
