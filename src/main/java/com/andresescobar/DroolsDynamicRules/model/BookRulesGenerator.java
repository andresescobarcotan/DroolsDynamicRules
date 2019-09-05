package com.andresescobar.DroolsDynamicRules.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	private static void writeToCSV() throws IOException {
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
				writeToCSV();
	            kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	            kieBase = kieContainer.getKieBase(KIE_BASE);
	            KieSession kieSession =  kieBase.newKieSession();
	            Book[] bookCollection = new Book[15];
	            for(int i=0; i < bookCollection.length; i++) {
	                Book myBook = new Book();
	                myBook.setBookNumber(i+1);
	                kieSession.insert(myBook);
	                bookCollection[i] = myBook;			
	            }
	            kieSession.fireAllRules();
	            for(Book myBook: bookCollection) {
	                System.out.println("Libro: "+ myBook.getName()+ " Precio: "+String.valueOf(myBook.getPrice()));
	            }
	    		
	            kieSession.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
