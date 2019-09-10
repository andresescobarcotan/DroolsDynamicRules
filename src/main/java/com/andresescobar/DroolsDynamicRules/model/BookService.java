package com.andresescobar.DroolsDynamicRules.model;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService{

	public BookService() {
		// TODO Auto-generated constructor stub
	}

	public void populateBooks() {
		BookRulesGenerator brg = new BookRulesGenerator();
		brg.executeRules();
		
	}

	@Override
	public void createBook(Book book) {
		
		BookRulesGenerator brg = new BookRulesGenerator();
		brg.createBook(book);
	}
	
	
	@Override
	public List<JSONObject> getBooks() {
		BookRulesGenerator brg = new BookRulesGenerator();
		return brg.getBooks();
	}

}
