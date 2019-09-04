package com.andresescobar.DroolsDynamicRules.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class DatabaseConnector {

	private static Logger LOGGER = (Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
	private static String dbRoute = "jdbc:h2:~/drools"; // Connection string
	private static String user = "sa";
	private static String password = "";
	private static String TABLE_NAME = "BOOKS"; // Name of the table
	private static Connection conn = null; // Object for managing the connection.
	private static DatabaseConnector singletonConnector = null; // Singleton pattern is used here.
	private static boolean isConnected = false; // Indicates if the object is connected to the database or not.
	private static int ID_POSITION = 1; // Position where the ID column is located in the results.
	
	public DatabaseConnector() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean createConnection() {
		/**
		 * createConnection
		 * @Description Creates a connection with the database
		 */
		if(!isConnected) {
			try {
				LOGGER.debug("Creating connection with the database");
				// 	Create database connection
				conn = DriverManager.getConnection(dbRoute, user, password);
				isConnected = true;
				LOGGER.info("Connection with the database was created successfully");
			} catch (SQLException e) {
				LOGGER.error("Database not connected: " +e.getMessage());
				isConnected = false;
			}
		}
		return isConnected;
	}
	
	public boolean closeConnection() {
		/**
		 * closeConnection
		 * @Description Closes the current connection with the database
		 */
		if(isConnected) {
			try {
				LOGGER.debug("Closing connection with the database");
				conn.close();
				isConnected = false;
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				isConnected = true;
			}
		}
		return isConnected;
	}
	
	public static DatabaseConnector getInstance() {
		/**
		 * getInstance
		 * @Description:
		 * - Implements the singleton pattern.
		 *- It creates only one instance of the DatabaseConnector class
		 * - If is already created then return it.
		 */
		if(singletonConnector == null) {
			singletonConnector = new DatabaseConnector();
		}
		return singletonConnector;
	}
	
	private boolean checkExists(String id) {
		/**
		 * checkExists 
		 * @param id: The ID of the book to be checked
		 * @return true if the book already exists or false if not.
		 * @Description: 
		 * - Executes an SQL Statement just to check if the event is already on the database.
		 */
		boolean repeated = true;
		
		if(isConnected) {
			try {		 
				String statement = "SELECT * from "+TABLE_NAME+ " WHERE ID="+"'"+id+"'";
				Statement stm = conn.createStatement();
				stm.execute(statement);
				ResultSet resultRepeated = stm.getResultSet();
				if(resultRepeated.next()) {
					LOGGER.error("A member already exists on database "+resultRepeated.getString(ID_POSITION));
					repeated = true;
				} else {
					repeated = false;
				}
				
			} catch(Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return repeated;
	}
	
	public String printResults() {
		/**
		 * printResults
		 * @return A String with the results found on the database
		 * @Description Executes a basic statement to fetch all the events that are on the database.
		 */
		String results = " Results found on the database : \n";
		if(isConnected) {
			try {		 
				String statement = "SELECT * from "+TABLE_NAME;
				Statement stm = conn.createStatement();
				stm.execute(statement);
				ResultSet resultDatabase = stm.getResultSet();
				ResultSetMetaData rsmd = resultDatabase.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while(resultDatabase.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) results +=",  ";
				        String columnValue = resultDatabase.getString(i);
				        results += rsmd.getColumnName(i)+":"+columnValue;
				    }
					results += "\n";
				} 
				
			} catch(Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return results;
	}
	
	public String printCSVFormat() {
		/**
		 * printCSVFormat
		 * @return A String with the results found on the database in csv format
		 * @Description Executes a basic statement to fetch all the events that are on the database.
		 */
		String results = " Results found on the database : \n";
		if(isConnected) {
			try {		 
				String statement = "SELECT * from "+TABLE_NAME;
				Statement stm = conn.createStatement();
				stm.execute(statement);
				ResultSet resultDatabase = stm.getResultSet();
				ResultSetMetaData rsmd = resultDatabase.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while(resultDatabase.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
				        String columnValue = resultDatabase.getString(i);
				        results+= columnValue;
				        if(i < columnsNumber) results += ',';
				    }
					results += "\n";
				} 
				
			} catch(Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return results;

		
	}

}