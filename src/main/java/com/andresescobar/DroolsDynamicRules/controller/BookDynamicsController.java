package com.andresescobar.DroolsDynamicRules.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping
	public void populateBooks() {
	bookService.populateBooks();
	}
	
	@PostMapping(consumes = {"application/json"}) 
	public void createBook(@RequestBody Book book) {
	bookService.createBook(book);
	}

}
