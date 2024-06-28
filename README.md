# Library Management System

## Overview

The Library Management System is a console-based Java application that allows users to perform various operations on a library's collection of books, members, and loans. The system uses JDBC to connect to a MySQL database, ensuring efficient data management and retrieval.

## Features

   * Add a New Book: Allows the user to add a new book to the library by providing details such as title, author, publisher, and year published.

   * Update Book Details: Enables the user to update the details of an existing book using its book ID.

   * Delete a Book: Provides the functionality to remove a book from the library using its book ID.

   * Search for a Book: Allows users to search for books by title, author, or year published.

   * Add a New Member: Enables the user to add a new member to the library by providing details such as name, email, and phone number.

   * Loan a Book: Records a new loan by associating a book with a member and updating the loans table with the loan date and due date.

   * Return a Book: Marks a book as returned by updating the return date in the loans table.



## Technologies Used

   * Java: Core programming language used to build the application.
   * JDBC: Java Database Connectivity for connecting and executing queries on a MySQL database.
   * MySQL: Relational database management system to store and manage library data.

## Database Schema

The MySQL database (library_db) includes the following tables:

`CREATE DATABASE library_db;`

`USE library_db;`

`CREATE TABLE books (`
    `book_id INT PRIMARY KEY AUTO_INCREMENT,`
    `title VARCHAR(255) NOT NULL,`
    `author VARCHAR(255) NOT NULL,`
    `publisher VARCHAR(255),`
    `year_published INT`
`);`

`CREATE TABLE members (`
    `member_id INT PRIMARY KEY AUTO_INCREMENT,`
    `name VARCHAR(255) NOT NULL,`
    `email VARCHAR(255) UNIQUE NOT NULL,`
    `phone VARCHAR(20)`
`);`

`CREATE TABLE loans (`
    `loan_id INT PRIMARY KEY AUTO_INCREMENT,`
    `book_id INT,`
    `member_id INT,`
    `loan_date DATE,`
    `return_date DATE,`
    `FOREIGN KEY (book_id) REFERENCES books(book_id),`
    `FOREIGN KEY (member_id) REFERENCES members(member_id)`
`);`


## Setup and Configuration

### 1. Download MySQL Connector/J:

* Download the MySQL Connector/J library from the MySQL website.
* Extract the archive and add the JAR file to your project's classpath.

### 2. Configure JDBC in IntelliJ IDEA:

* Open your project in IntelliJ IDEA.
* Right-click on your project in the Project Explorer and select 'Open Module Settings'.
* Go to 'Libraries', click the '+' icon, select 'Java', and navigate to the directory where you extracted the MySQL Connector/J archive.
* Select the JAR file and click 'OK', then apply the changes.

### 3. Database Configuration:

* Ensure your MySQL server is running.
* Create the 'library_db' database and the necessary tables using the provided schema.

## How to Run

### 1. Clone the repository:

     `git clone https://github.com/yourusername/library-management-system.git`

### 2. Open the project in IntelliJ IDEA.
### 3. Add the MySQL Connector/J JAR file to your project's classpath.
### 4. Ensure the MySQL server is running and the database is set up.
### 5. Run the 'LibraryManagementSystem' class.
