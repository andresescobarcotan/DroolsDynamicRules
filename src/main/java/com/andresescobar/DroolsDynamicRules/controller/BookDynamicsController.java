package com.andresescobar.DroolsDynamicRules.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresescobar.DroolsDynamicRules.model.Book;
import com.andresescobar.DroolsDynamicRules.model.BookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/books")
public class BookDynamicsController {


/*
 * Basic API:
 *  - There is only one endpoint with two methods:
 *  - Get: Returns the current books given by the library
 *  - Post:(w/o parameter) Populates the library with a small selection of books
 *  - Post: with a JSON specifying the book:
 *  	- Adds a book that is executed for the given rules.
 */
	@Autowired
	private BookService bookService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public String getBooks() {
		List<JSONObject> listBooks = bookService.getBooks();

        return listBooks.toString();
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public String populateBooks() {
	bookService.populateBooks();
	return bookService.getBooks().toString();
	}
	
	@PostMapping(consumes = {"application/json"}) 
	public String createBook(@RequestBody Book book) {
	bookService.createBook(book);
	return bookService.getBooks().toString();
	}
}
