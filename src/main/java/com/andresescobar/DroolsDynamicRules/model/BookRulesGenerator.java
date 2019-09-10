package com.andresescobar.DroolsDynamicRules.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.andresescobar.DroolsDynamicRules.BookRulesDynamic;
import com.andresescobar.DroolsDynamicRules.Database.DatabaseConnector;

import ch.qos.logback.classic.Logger;

public class BookRulesGenerator {
	
	private static KieContainer kieContainer;
	private static final String KIE_BASE = "bookKieBase";
	private static final String CSV_FILE = "src/main/resources/BookData.csv";
	private  ArrayList<Book> bookCollection;
	private void writeToCSV() throws IOException {
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		if(dbc.createConnection()) {		
			String csvResults = dbc.printCSVFormat();
			System.out.println(csvResults);
			ClassLoader classLoader = BookRulesDynamic.class.getClassLoader();
			BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE));
		    writer.write(csvResults);
		    writer.flush();
		    writer.close();
		}
	}

	
	public BookRulesGenerator() {
		// TODO Auto-generated constructor stub
	}

	public List<JSONObject> getBooks() {
		ArrayList<JSONObject> bookLibrary = new ArrayList<JSONObject>();
		try {
			for(Book book: this.bookCollection) {
				System.out.println("This is my book "+ book);
				bookLibrary.add(book.toJSON());
			}
		} catch(Exception e) {
			JSONObject errorJson = new JSONObject();
			errorJson = (JSONObject) errorJson.put("message", "No books are aviliable");
			bookLibrary.add(errorJson);
		}
		return bookLibrary;
	}
	
	public void createBook(Book book) {
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		if(dbc.createConnection()) {		
			dbc.insertBook(book);
			dbc.closeConnection();
		}
		this.executeRules();
	}
	public void executeRules() {
		 try {
	        	KieBase kieBase = null;
				this.writeToCSV();
	            kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	            kieBase = kieContainer.getKieBase(KIE_BASE);
	            KieSession kieSession =  kieBase.newKieSession();
	            this.bookCollection = new ArrayList<>();
	            int bookSize = this.getBookSize();
	            for(int i=0; i < bookSize -1 ; i++) {
	                Book myBook = new Book();
	                myBook.setBookNumber(i+1);
	                kieSession.insert(myBook);
	                this.bookCollection.add(myBook);			
	            }
	            kieSession.fireAllRules();	    		
	            kieSession.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	}
	
	
	private int getBookSize() {
		int bookSize = 0;
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		if(dbc.createConnection()) {		
			bookSize = dbc.getCurrentBooksNumber();
			dbc.closeConnection();
		}
		return bookSize;
	}
}
