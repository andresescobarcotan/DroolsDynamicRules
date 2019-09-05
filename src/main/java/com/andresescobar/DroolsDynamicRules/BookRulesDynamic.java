package com.andresescobar.DroolsDynamicRules;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andresescobar.DroolsDynamicRules.Database.DatabaseConnector;
import com.andresescobar.DroolsDynamicRules.model.Book;

@SpringBootApplication
public class BookRulesDynamic 
{
	
    public static void main( String[] args )
    {	
    	SpringApplication.run(BookRulesDynamic.class, args);
    }
}
