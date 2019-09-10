package com.andresescobar.DroolsDynamicRules.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@Autowired
	private BookService bookService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBooks() {
		List<JSONObject> listBooks = bookService.getBooks();
        return new ResponseEntity<Object>(listBooks, HttpStatus.OK);

	}
	
	@PostMapping
	public void populateBooks() {
	bookService.populateBooks();
	}
	
	@PostMapping(consumes = {"application/json"}) 
	public void createBook(@RequestBody Book book) {
	bookService.createBook(book);
	}

}
