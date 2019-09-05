package com.andresescobar.DroolsDynamicRules.model;

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

}
