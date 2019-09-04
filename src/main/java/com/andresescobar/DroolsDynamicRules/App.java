package com.andresescobar.DroolsDynamicRules;

import org.junit.Before;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Hello world!
 *
 */
public class App 
{
	private static KieContainer kieContainer;
	private static KieBase kieBase;
	private static final String KIE_BASE = "bookKieBase";
	
	
    public static void main( String[] args )
    {
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
		
    }
}
