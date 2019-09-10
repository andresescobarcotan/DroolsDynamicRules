package com.andresescobar.DroolsDynamicRules.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.andresescobar.DroolsDynamicRules.BookRulesDynamic;
import com.andresescobar.DroolsDynamicRules.Database.DatabaseConnector;

public class BookRulesGenerator {
	
	private static KieContainer kieContainer;
	private static final String KIE_BASE = "bookKieBase";
	private static final String CSV_FILE = "src/main/resources/BookData.csv";
	private static KieBase kieBase;
	private static KieSession kieSession;
	private static ArrayList<Book> bookCollection;
	private static BookRulesGenerator brg;
	
	
	private void writeToCSV() throws IOException {
		/**
		 * @function: writeToCSV
		 * @description: Gets all the data stored in the database, and writes it into a csv file 
		 * in order to be available for Drools rules. 
		 */
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		if(dbc.createConnection()) {		
			String csvResults = dbc.printCSVFormat();
			BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE));
		    writer.write(csvResults);
		    writer.flush();
		    writer.close();
		}	}
	
	public static BookRulesGenerator getInstance() {
		/**
		 * getInstance
		 * @Description:
		 * - Implements the singleton pattern.
		 *- It creates only one instance of the DatabaseConnector class
		 * - If is already created then return it.
		 */
		if(brg == null) {
			brg = new BookRulesGenerator();
		}
		return brg;
	}

	
	private BookRulesGenerator() {
		// TODO Auto-generated constructor stub
	}

	public List<JSONObject> getBooks() {
		/**
		 * @function createBook
		 * @returns: bookLibrary, a json with the collection of current books if any.
		 * @description: Gathers the current collection of books in the system, if any.
		 * and returns it.
		 */
		this.executeRules();
		ArrayList<JSONObject> bookLibrary = new ArrayList<JSONObject>();
		try {
			for(Book book: bookCollection) {
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
		/**
		 * @function createBook
		 * @Params: Book, book to be inserted into the database 
		 * @description: Creates a book instance on the database
		 * and executes the rules
		 */
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		if(dbc.createConnection()) {		
			dbc.insertBook(book);
			dbc.closeConnection();
		}
		this.executeRules();
	}
	public void executeRules() {
		/**
		 * @function executeRules
		 * @description:  Executes the current rules specified in the base of knowledge
		 *  
		 */
		 try {
				this.writeToCSV();
	            kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	            kieBase = kieContainer.getKieBase(KIE_BASE);
	            kieSession =  kieBase.newKieSession();
	            bookCollection = new ArrayList<>();
	            int bookSize = this.getBookSize();
	            for(int i=0; i < bookSize -1 ; i++) {
	                Book myBook = new Book();
	                myBook.setBookNumber(i+1);
	                kieSession.insert(myBook);
	                bookCollection.add(myBook);			
	            }
	            kieSession.fireAllRules();	    		
	            kieSession.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				kieSession.dispose();
			}
	}
	
	
	private int getBookSize() {
		/**	
		 * @function getBookSize
		 * @return The current size of books on the database 
		 *  
		 */
		int bookSize = 0;
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		if(dbc.createConnection()) {		
			bookSize = dbc.getCurrentBooksNumber();
			dbc.closeConnection();
		}
		return bookSize;
	}
}
