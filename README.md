# Library App

This is a simple library application built with Java and MySQL using the Data Access Layer (DAL) pattern. The app allows users to manage a collection of books stored in a local MySQL database. Before running the application, make sure you have MySQL server installed on your machine and create the required database and table.

## Prerequisites

1. Install MySQL server on your machine.
2. Create a database named "library" with the following credentials:
   - Database Name: library
   - Username: root
   - Password: password
     
## Database Schema

The database should have a table named "books" with the following columns:

1. id (INT): Unique identifier for each book.
2. title (VARCHAR): Title of the book.
3. author (VARCHAR): Name of the book's author.
4. isbn (VARCHAR): International Standard Book Number of the book.

## How to Run the Application

1. Clone the repository or download the project files.
2. Open the project in your Java IDE (e.g., Eclipse, IntelliJ, etc.).
3. Make sure you have the MySQL JDBC driver in your project's classpath.
4. Open the src/main/java directory and locate the Main.java file.
5. Run the Main.java file to start the application.

## Using the Library App
1. When the application starts, you will see a simple console-based user interface.
2. You can use the menu options to perform various actions like adding a new book, searching for a book, updating book information, and deleting a book from the library.
