# DroolsDynamicRules
Dynamic Template compilation of rules in Drools linked to a memory database

##Descritpion:
The aim of this mock is just to test, how dynamic rules via drools can be achieved using templates compilation.
Although the KieBases relies on a CSV file, this file is generated everytime the H2SQL Database on memory is changed.

##Database creation:
- First ensure that you have a connection created with Eclipse for H2 Database,
	the database is called Drools.
- Once you have connected with it, execute the populateBooks.sql under the src/main/resources

##How to execute:

- First import the project to Eclipse, you need *Maven installed*
- Once imported, right click on the project, and select "Run As> Maven Install"
- *Finally*, run *BookRulesDynamic.java* under "com.andreescobar.DroolsDynamicRules"
 
