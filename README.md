# Library Borrowing Request System

## Project Title

Library Borrowing Request System

## Project Description

The Library Borrowing Request System is a Java console-based application designed to manage books and student borrowing requests in a library.

The system allows users to view and manage the book catalog, search for books, update the number of available copies, and register borrowing requests. Borrowing requests are placed in a FIFO (First-In, First-Out) queue, ensuring that requests are processed in the order they were submitted.

The system also provides sorting functions for books and borrowing records, making it easier to organize and manage library information.

## Objectives

- To manage and display a library book catalog.
- To allow users to add new books to the system.
- To search for books by title or part of a title.
- To update the number of available book copies.
- To register and manage student borrowing requests.
- To use a queue to process borrowing requests in FIFO order.
- To allow users to view, cancel, and process pending requests.
- To organize book and borrowing records using sorting algorithms.
- To demonstrate the use of ArrayList, Queue, and sorting algorithms in Java.

## Use Cases and Features

### Book Management

**Display Book Catalog**
- Displays all books currently stored in the system.
- Shows the book title, author, publication year, and number of copies.

**Add a Book**
- Allows the user to add a new book.
- Requires the title, author, publication year, and number of copies.
- Prevents duplicate book titles.

**Search Book**
- Allows users to search for a book using its title or part of its title.
- Displays matching books and their available copies.

**Update Number of Copies**
- Allows the user to change the number of copies available for a selected book.

### Borrowing Request Management

**Register Borrowing Request**
- Allows a student to request a book.
- Each request receives a unique request number.
- The request is added to the pending queue.

**Process Next Request**
- Processes the request at the front of the queue.
- If a copy is available, the request is approved and the number of copies decreases.
- If no copy is available, the request is rejected.

**Peek at Next Request**
- Displays the next request that will be processed without removing it from the queue.

**Display Pending Queue**
- Shows all pending requests from the front of the queue to the back.

**Check if Queue is Empty**
- Checks whether there are currently any pending borrowing requests.

**Cancel a Pending Request**
- Allows the user to cancel a request that is still waiting in the queue.

### Sorting and Records

**Sort Books**
- Books can be sorted by title, A-Z.
- Books can be sorted by publication year, oldest first.

**Sort Borrowing Records**
- Borrowing records can be sorted by student name, A-Z.
- Borrowing records can be sorted by request number, lowest first.

**Display All Borrowing Records**
- Displays all borrowing requests along with their request number, student name, book, and current status.

## Data Structure Explanation

### ArrayLists

The system uses multiple ArrayList objects to store and manage records.

For books, the following ArrayLists are used:

- `bookTitles` – stores book titles.
- `bookAuthors` – stores book authors.
- `bookYears` – stores publication years.
- `bookCopies` – stores the number of available copies.

For borrowing records, the system uses:

- `reqNumbers` – stores request numbers.
- `reqStudents` – stores student names.
- `reqBooks` – stores requested book titles.
- `reqStatus` – stores the status of each request.

The ArrayLists allow records to dynamically grow when new books or borrowing requests are added. Related information uses the same index, so the records can be connected together.

For example, the book at index 0 in `bookTitles`, `bookAuthors`, `bookYears`, and `bookCopies` represents the same book.

### Queue

The system uses a `Queue<Integer>` called `pendingQueue` to manage borrowing requests.

The queue follows FIFO (First-In, First-Out). This means the first borrowing request added to the queue is the first request that will be processed.

The queue uses:

- `add()` – adds a new request to the back of the queue.
- `poll()` – removes and processes the request at the front.
- `peek()` – views the next request without removing it.
- `isEmpty()` – checks whether there are no pending requests.
- `remove()` – removes a selected pending request.

The request number is stored in the queue, while the other details of the request are stored in the corresponding ArrayLists.

## Sorting Algorithm Explanation

The application provides three sorting algorithms:

### Bubble Sort

Bubble Sort repeatedly compares neighboring records and swaps them when they are in the wrong order.

In this system, Bubble Sort can be used to sort books by title or publication year and borrowing records by student name or request number.

### Selection Sort

Selection Sort searches for the smallest value in the unsorted portion of the records and places it in the correct position.

In this system, Selection Sort can be used to sort books by title or publication year and borrowing records by student name or request number.

### Insertion Sort

Insertion Sort builds the sorted list one record at a time by taking each new record and inserting it into its proper position among the previously sorted records.

In this system, Insertion Sort can be used to sort books by title or publication year and borrowing records by student name or request number.

### Purpose of Sorting

Sorting makes the library records easier to organize, read, and manage. For example, users can arrange books alphabetically by title or arrange borrowing requests by request number.

The sorting operation changes the order of the records in the corresponding ArrayLists. The pending queue itself is not affected by sorting, so its FIFO processing order remains unchanged.

## How to Run the Program

### Requirements

- Java Development Kit (JDK)
- Java-compatible IDE or Command Prompt/Terminal

### Using Command Prompt or Terminal

Save the Java source code as:

```text
Main.java
```

Open Command Prompt or Terminal and navigate to the folder containing the file.

Compile the program:

```bash
javac Main.java
```

Run the program:

```bash
java Main
```

The Library Borrowing Request System menu will appear. Enter the number corresponding to the desired operation.

Choose `0` to exit the program.
