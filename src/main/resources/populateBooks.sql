/*
 * Script for creating the database BOOKS
 * where the events shall be stored
 */
DROP table BOOKS;
create table BOOKS ( Id VARCHAR(255) PRIMARY KEY AUTO_INCREMENT ,  name VARCHAR(255) NOT NULL,  price DOUBLE);