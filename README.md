# Library Borrowing Request System

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
- Sort books by title in A-Z order.
- Sort books by publication year from oldest to newest.

**Sort Borrowing Records**
- Sort borrowing records by student name in A-Z order.
- Sort borrowing records by request number from lowest to highest.

**Display All Borrowing Records**
- Displays all borrowing requests along with their request number, student name, book, and current status.

### Input Validation and Color-Coded Messages

**Input Validation**
- Invalid menu choices, non-numeric input, numbers outside the allowed range, and empty text are rejected, and the program asks for the input again instead of closing.

**Color-Coded Display**
- Green messages indicate successful actions, such as an approved request or a successfully sorted list.
- Red messages indicate errors or failed actions, such as invalid input, an empty queue, or a rejected request.
- The main menu uses purple for the title and borders and blue for the section headings.

## Data Structure Explanation

### ArrayLists

The system uses multiple `ArrayList` objects to store and manage book and borrowing records.

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

The ArrayLists allow records to dynamically grow when new books or borrowing requests are added. Related information uses the same index, allowing the different pieces of information to stay connected.

The book ArrayLists are preloaded with the following eight books when the program starts:

- The Great Gatsby – F. Scott Fitzgerald (1925)
- Things Fall Apart – Chinua Achebe (1958)
- Hamlet – William Shakespeare (1603)
- Beloved – Toni Morrison (1987)
- Jane Eyre – Charlotte Bronte (1847)
- One Hundred Years of Solitude – Gabriel Garcia Marquez (1967)
- Les Miserables – Victor Hugo (1862)
- Noli Me Tangere – Jose Rizal (1887)

### Queue

The system uses a `Queue<Integer>` called `pendingQueue` to manage pending borrowing requests.

The queue follows the FIFO (First-In, First-Out) principle. This means the first borrowing request added to the queue is the first request that will be processed.

The queue uses the following operations:

- `add()` – adds a new request to the back of the queue.
- `poll()` – removes and processes the request at the front.
- `peek()` – views the next request without removing it.
- `isEmpty()` – checks whether the queue is empty.
- `contains()` – checks whether a request is still pending before it is canceled.
- `remove()` – removes a selected pending request.
- Looping through the queue – displays the pending requests from front to back without removing them.

The request number is stored in the queue, while the other details of the request are stored in the corresponding ArrayLists.

## Sorting Algorithm Explanation

The application implements three sorting algorithms: Bubble Sort, Selection Sort, and Insertion Sort.

### Bubble Sort

Bubble Sort repeatedly compares neighboring records and swaps them when they are in the wrong order.

In this system, Bubble Sort can be used to sort books by title or publication year and borrowing records by student name or request number.

### Selection Sort

Selection Sort searches for the smallest value in the unsorted portion of the records and places it in the correct position.

In this system, Selection Sort can be used to sort books by title or publication year and borrowing records by student name or request number.

### Insertion Sort

Insertion Sort builds the sorted list one record at a time by taking each record and inserting it into its proper position among the previously sorted records.

In this system, Insertion Sort can be used to sort books by title or publication year and borrowing records by student name or request number.

### Purpose of Sorting

Sorting makes the library records easier to organize, read, and manage. Users can arrange books alphabetically by title, by publication year, or organize borrowing records by student name or request number.

The sorting operation changes the order of the records in the corresponding ArrayLists. The pending queue itself is not affected by sorting, so its FIFO processing order remains unchanged.

## How to Run the Program

### Requirements

- Java Development Kit (JDK)
- Java-compatible IDE or Command Prompt/Terminal

A terminal that supports ANSI colors is recommended, such as Windows Terminal, macOS Terminal, a Linux terminal, or the VS Code terminal. Some IDE consoles may display color codes such as `[32m` as plain text instead of colors.

### Using Command Prompt or Terminal

Save the Java source code as:

```text
Main.java
```

Open Command Prompt or Terminal and navigate to the folder containing `Main.java`.

Compile the program using:

```bash
javac Main.java
```

After successfully compiling the program, run it using:

```bash
java Main
```

The Library Borrowing Request System menu will appear in the console.

Enter the number corresponding to the desired operation and follow the instructions displayed by the program.

To close the program, select the **Exit** option.

## Screenshots of the System

### 7.1 Main Menu

![Main Menu](screenshots/01%20-%20Main%20Menu.png)

*Main menu of the Library Borrowing Request System showing the available operations.*

### 7.2 Adding Records

![Adding Records](screenshots/02%20-%20Adding%20records%20and%20displaying%20stored%20records%201.png)

*Shows new borrowing records being added to the system.*

### 7.3 Displaying Stored Records

![Displaying Stored Records](screenshots/03%20-%20Adding%20records%20and%20displaying%20stored%20records%202.png)

*Displays the borrowing records currently stored in the system.*

### 7.4 Enqueue

![Enqueue](screenshots/04%20-%20Enqueue.png)

*Adds a new borrowing request to the back of the pending queue.*

### 7.5 Peek

![Peek](screenshots/05%20-%20Peek.png)

*Displays the next borrowing request without removing it from the queue.*

### 7.6 Dequeue and FIFO

![Dequeue and FIFO](screenshots/06%20-%20Dequeue%20-%20shows%20FIFO.png)

*Processes the first request added to the queue, demonstrating FIFO behavior.*

### 7.7 Displaying the Queue

![Displaying the Queue](screenshots/07%20-%20Displaying%20the%20queue.png)

*Displays all pending borrowing requests from the front to the back of the queue.*

### 7.8 Sorting Algorithm 1 - Bubble Sort

![Bubble Sort](screenshots/08%20-%20Bubble%20Sort%20-%20First%20Sorting%20Algorithm.png)

*Shows the records before and after applying Bubble Sort.*

### 7.9 Sorting Algorithm 2 - Selection Sort

![Selection Sort](screenshots/09%20-%20Selection%20Sort%20-%20Second%20Sorting%20Algorithm.png)

*Shows the records before and after applying Selection Sort.*

### 7.10 Input Validation and Error Messages

![Input Validation](screenshots/10%20-%20Input%20Validation.png)

*Invalid inputs are rejected and an appropriate error message is displayed.*

## Project Files

The project submission contains:

- `Main.java` – Java source code of the application.
- `README.md` – project documentation.
- `screenshots/` – folder containing screenshots of the actual application.

## Submission

The complete project, including the README file, Java source code, and required screenshots, should be submitted through the designated submission platform or project repository.
