package com.andresescobar.DroolsDynamicRules;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.andresescobar.DroolsDynamicRules.Book;
public class BookTemplateTest {
	
	private KieContainer kieContainer;
	private KieBase kieBase;
	private static final String KIE_BASE = "bookKieBase";
	
	@Before
	public void init() {
		kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		kieBase = kieContainer.getKieBase(KIE_BASE);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSimpleBook() {
		Book myBook = new Book();
		myBook.setName("Treasure Island");
		KieSession kieSession = kieBase.newKieSession();
		kieSession.insert(myBook);
		kieSession.fireAllRules();
		Assert.assertEquals((double) 1000.1, myBook.getPrice(), 0.01);
		
	}
	
}
