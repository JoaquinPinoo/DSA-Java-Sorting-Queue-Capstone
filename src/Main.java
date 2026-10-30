import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String RED = "\u001B[31m";

        Scanner sc = new Scanner(System.in);

        ArrayList<String> bookTitles = new ArrayList<>();
        ArrayList<String> bookAuthors = new ArrayList<>();
        ArrayList<Integer> bookYears = new ArrayList<>();
        ArrayList<Integer> bookCopies = new ArrayList<>();

        ArrayList<Integer> reqNumbers = new ArrayList<>();
        ArrayList<String> reqStudents = new ArrayList<>();
        ArrayList<String> reqBooks = new ArrayList<>();
        ArrayList<String> reqStatus = new ArrayList<>();

        Queue<Integer> pendingQueue = new LinkedList<>();

        int nextRequestNo = 1001;

        String[] sampleTitles = {"Introduction to Algorithms", "Clean Code",
                "Java: The Complete Reference", "Data Structures and Algorithms", "Noli Me Tangere"};
        String[] sampleAuthors = {"Thomas Cormen", "Robert Martin", "Herbert Schildt",
                "Michael Goodrich", "Jose Rizal"};
        int[] sampleYears = {2009, 2008, 2018, 2014, 1887};
        int[] sampleCopies = {2, 1, 3, 1, 2};
        for (int i = 0; i < sampleTitles.length; i++) {
            bookTitles.add(sampleTitles[i]);
            bookAuthors.add(sampleAuthors[i]);
            bookYears.add(sampleYears[i]);
            bookCopies.add(sampleCopies[i]);
        }

        int choice = 0;
        int num = 0;
        String text = "";
        boolean running = true;

        try {
            while (running) {

                System.out.println("\n==============================================");
                System.out.println("   LIBRARY BORROWING REQUEST SYSTEM");
                System.out.println("==============================================");
                System.out.println(" BOOKS");
                System.out.println("  1. Display book catalog");
                System.out.println("  2. Add a book");
                System.out.println("  3. Search book by title");
                System.out.println("  4. Update number of copies");
                System.out.println(" REQUEST QUEUE (FIFO)");
                System.out.println("  5. Register borrowing request (Enqueue)");
                System.out.println("  6. Process next request (Dequeue)");
                System.out.println("  7. Peek at next request");
                System.out.println("  8. Display pending queue");
                System.out.println("  9. Check if queue is empty");
                System.out.println(" 10. Cancel a pending request");
                System.out.println(" SORTING / RECORDS");
                System.out.println(" 11. Sort books (title / publication year)");
                System.out.println(" 12. Sort borrowing records (student name / request no.)");
                System.out.println(" 13. Display all borrowing records");
                System.out.println("  0. Exit");
                System.out.println("----------------------------------------------");

                while (true) {
                    System.out.print("Enter choice: ");
                    try {
                        choice = Integer.parseInt(sc.nextLine().trim());
                        if (choice >= 0 && choice <= 13) break;
                        System.out.println(RED + "Please enter a number from 0 to 13." + RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                    }
                }
                System.out.println();

                switch (choice) {

                    case 1: {
                        System.out.println("--- BOOK CATALOG ---");
                        if (bookTitles.isEmpty()) {
                            System.out.println(RED + "No books in the catalog." + RESET);
                        } else {
                            System.out.printf("%-4s %-34s %-20s %-6s %-6s%n", "No.", "Title", "Author", "Year", "Copies");
                            for (int i = 0; i < bookTitles.size(); i++) {
                                System.out.printf("%-4d %-34s %-20s %-6d %-6d%n", (i + 1), bookTitles.get(i),
                                        bookAuthors.get(i), bookYears.get(i), bookCopies.get(i));
                            }
                        }
                        break;
                    }

                    case 2: {
                        System.out.println("--- ADD A BOOK ---");
                        String title;
                        while (true) {
                            System.out.print("Title: ");
                            title = sc.nextLine().trim();
                            if (!title.isEmpty()) break;
                            System.out.println(RED + "Input cannot be empty." + RESET);
                        }
                        boolean exists = false;
                        for (int i = 0; i < bookTitles.size(); i++) {
                            if (bookTitles.get(i).equalsIgnoreCase(title)) exists = true;
                        }
                        if (exists) {
                            System.out.println(RED + "That book already exists. Use option 4 to update its copies." + RESET);
                            break;
                        }
                        String author;
                        while (true) {
                            System.out.print("Author: ");
                            author = sc.nextLine().trim();
                            if (!author.isEmpty()) break;
                            System.out.println(RED + "Input cannot be empty." + RESET);
                        }
                        int year;
                        while (true) {
                            System.out.print("Publication year (1000-2100): ");
                            try {
                                year = Integer.parseInt(sc.nextLine().trim());
                                if (year >= 1000 && year <= 2100) break;
                                System.out.println(RED + "Please enter a number from 1000 to 2100." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        int copies;
                        while (true) {
                            System.out.print("Number of copies (0-100): ");
                            try {
                                copies = Integer.parseInt(sc.nextLine().trim());
                                if (copies >= 0 && copies <= 100) break;
                                System.out.println(RED + "Please enter a number from 0 to 100." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        bookTitles.add(title);
                        bookAuthors.add(author);
                        bookYears.add(year);
                        bookCopies.add(copies);
                        System.out.println(GREEN + "Book added successfully." + RESET);
                        break;
                    }

                    case 3: {
                        System.out.println("--- SEARCH BOOK ---");
                        while (true) {
                            System.out.print("Enter title or part of the title: ");
                            text = sc.nextLine().trim().toLowerCase();
                            if (!text.isEmpty()) break;
                            System.out.println(RED + "Input cannot be empty." + RESET);
                        }
                        boolean found = false;
                        for (int i = 0; i < bookTitles.size(); i++) {
                            if (bookTitles.get(i).toLowerCase().contains(text)) {
                                if (!found) System.out.println("Matching books:");
                                System.out.println("  - " + bookTitles.get(i) + " by " + bookAuthors.get(i)
                                        + " (" + bookYears.get(i) + ") | Copies available: " + bookCopies.get(i));
                                found = true;
                            }
                        }
                        if (!found) System.out.println(RED + "No book matched \"" + text + "\"." + RESET);
                        break;
                    }

                    case 4: {
                        System.out.println("--- UPDATE COPIES ---");
                        if (bookTitles.isEmpty()) {
                            System.out.println(RED + "No books in the catalog." + RESET);
                            break;
                        }
                        System.out.printf("%-4s %-34s %-20s %-6s %-6s%n", "No.", "Title", "Author", "Year", "Copies");
                        for (int i = 0; i < bookTitles.size(); i++) {
                            System.out.printf("%-4d %-34s %-20s %-6d %-6d%n", (i + 1), bookTitles.get(i),
                                    bookAuthors.get(i), bookYears.get(i), bookCopies.get(i));
                        }
                        while (true) {
                            System.out.print("Book number to update (1-" + bookTitles.size() + "): ");
                            try {
                                num = Integer.parseInt(sc.nextLine().trim());
                                if (num >= 1 && num <= bookTitles.size()) break;
                                System.out.println(RED + "Please enter a number from 1 to " + bookTitles.size() + "." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        int copies;
                        while (true) {
                            System.out.print("New number of copies (0-100): ");
                            try {
                                copies = Integer.parseInt(sc.nextLine().trim());
                                if (copies >= 0 && copies <= 100) break;
                                System.out.println(RED + "Please enter a number from 0 to 100." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        bookCopies.set(num - 1, copies);
                        System.out.println(GREEN + "Copies updated for \"" + bookTitles.get(num - 1) + "\"." + RESET);
                        break;
                    }

                    case 5: {
                        System.out.println("--- REGISTER BORROWING REQUEST ---");
                        if (bookTitles.isEmpty()) {
                            System.out.println(RED + "No books available to request." + RESET);
                            break;
                        }
                        String student;
                        while (true) {
                            System.out.print("Student name: ");
                            student = sc.nextLine().trim();
                            if (!student.isEmpty()) break;
                            System.out.println(RED + "Input cannot be empty." + RESET);
                        }
                        System.out.println("--- AVAILABLE BOOKS ---");
                        System.out.printf("%-4s %-34s %-20s %-6s %-6s%n", "No.", "Title", "Author", "Year", "Copies");
                        for (int i = 0; i < bookTitles.size(); i++) {
                            System.out.printf("%-4d %-34s %-20s %-6d %-6d%n", (i + 1), bookTitles.get(i),
                                    bookAuthors.get(i), bookYears.get(i), bookCopies.get(i));
                        }
                        while (true) {
                            System.out.print("Book number to borrow (1-" + bookTitles.size() + "): ");
                            try {
                                num = Integer.parseInt(sc.nextLine().trim());
                                if (num >= 1 && num <= bookTitles.size()) break;
                                System.out.println(RED + "Please enter a number from 1 to " + bookTitles.size() + "." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        if (bookCopies.get(num - 1) == 0) {
                            System.out.println(RED + "Note: no copies are currently available. The request is still queued\n"
                                    + "      and will be rejected if there is still no copy when it is processed." + RESET);
                        }
                        int reqNo = nextRequestNo++;
                        reqNumbers.add(reqNo);
                        reqStudents.add(student);
                        reqBooks.add(bookTitles.get(num - 1));
                        reqStatus.add("Pending");
                        pendingQueue.add(reqNo);
                        System.out.println(GREEN + "Request #" + reqNo + " registered for " + student + "." + RESET);
                        System.out.println("Position in queue: " + pendingQueue.size());
                        break;
                    }

                    case 6: {
                        System.out.println("--- PROCESS NEXT REQUEST ---");
                        if (pendingQueue.isEmpty()) {
                            System.out.println(RED + "The queue is empty. There are no requests to process." + RESET);
                            break;
                        }
                        int reqNo = pendingQueue.poll();
                        int r = reqNumbers.indexOf(reqNo);
                        int b = bookTitles.indexOf(reqBooks.get(r));
                        System.out.println("Processing request #" + reqNo + " from " + reqStudents.get(r)
                                + " for \"" + reqBooks.get(r) + "\"...");
                        if (b != -1 && bookCopies.get(b) > 0) {
                            bookCopies.set(b, bookCopies.get(b) - 1);
                            reqStatus.set(r, "Approved");
                            System.out.println(GREEN + "APPROVED. Copies left: " + bookCopies.get(b) + RESET);
                        } else {
                            reqStatus.set(r, "Rejected");
                            System.out.println(RED + "REJECTED. No copies available." + RESET);
                        }
                        System.out.println("Requests still pending: " + pendingQueue.size());
                        break;
                    }

                    case 7: {
                        System.out.println("--- NEXT REQUEST IN LINE ---");
                        if (pendingQueue.isEmpty()) {
                            System.out.println(RED + "The queue is empty." + RESET);
                            break;
                        }
                        int reqNo = pendingQueue.peek();
                        int r = reqNumbers.indexOf(reqNo);
                        System.out.println("Request #" + reqNo + " | " + reqStudents.get(r) + " | \"" + reqBooks.get(r) + "\"");
                        System.out.println("(The request stays in the queue.)");
                        break;
                    }

                    case 8: {
                        System.out.println("--- PENDING REQUESTS (FRONT -> BACK) ---");
                        if (pendingQueue.isEmpty()) {
                            System.out.println(RED + "The queue is empty." + RESET);
                            break;
                        }
                        System.out.printf("%-5s %-9s %-20s %-30s%n", "Pos", "Request#", "Student", "Book");
                        int pos = 1;
                        for (int reqNo : pendingQueue) {
                            int r = reqNumbers.indexOf(reqNo);
                            System.out.printf("%-5d %-9d %-20s %-30s%n", pos++, reqNo, reqStudents.get(r), reqBooks.get(r));
                        }
                        System.out.println("Total pending: " + pendingQueue.size());
                        break;
                    }

                    case 9: {
                        if (pendingQueue.isEmpty()) {
                            System.out.println("The queue is EMPTY.");
                        } else {
                            System.out.println("The queue is NOT empty. Pending requests: " + pendingQueue.size());
                        }
                        break;
                    }

                    case 10: {
                        System.out.println("--- CANCEL PENDING REQUEST ---");
                        if (pendingQueue.isEmpty()) {
                            System.out.println(RED + "The queue is empty. Nothing to cancel." + RESET);
                            break;
                        }
                        System.out.printf("%-5s %-9s %-20s %-30s%n", "Pos", "Request#", "Student", "Book");
                        int pos = 1;
                        for (int reqNo : pendingQueue) {
                            int r = reqNumbers.indexOf(reqNo);
                            System.out.printf("%-5d %-9d %-20s %-30s%n", pos++, reqNo, reqStudents.get(r), reqBooks.get(r));
                        }
                        while (true) {
                            System.out.print("Request number to cancel: ");
                            try {
                                num = Integer.parseInt(sc.nextLine().trim());
                                if (num >= 1 && num <= 999999) break;
                                System.out.println(RED + "Please enter a number from 1 to 999999." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        if (!pendingQueue.contains(num)) {
                            System.out.println(RED + "Request #" + num + " is not in the pending queue." + RESET);
                            break;
                        }
                        pendingQueue.remove(Integer.valueOf(num));
                        reqStatus.set(reqNumbers.indexOf(num), "Cancelled");
                        System.out.println(GREEN + "Request #" + num + " cancelled." + RESET);
                        break;
                    }

                    case 11:
                    case 12: {
                        boolean forBooks = (choice == 11);
                        int n = forBooks ? bookTitles.size() : reqNumbers.size();
                        if (n < 2) {
                            if (forBooks) {
                                System.out.println(RED + "Need at least 2 books to sort." + RESET);
                            } else {
                                System.out.println(RED + "Need at least 2 borrowing records to sort." + RESET);
                            }
                            break;
                        }
                        if (forBooks) {
                            System.out.println("--- SORT BOOKS ---");
                            System.out.println("Sort by:  1. Title (A-Z)   2. Publication year (oldest first)");
                        } else {
                            System.out.println("--- SORT BORROWING RECORDS ---");
                            System.out.println("Sort by:  1. Student name (A-Z)   2. Request number (lowest first)");
                        }
                        int criteria;
                        while (true) {
                            System.out.print("Choice: ");
                            try {
                                criteria = Integer.parseInt(sc.nextLine().trim());
                                if (criteria >= 1 && criteria <= 2) break;
                                System.out.println(RED + "Please enter a number from 1 to 2." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }
                        System.out.println("Algorithm:  1. Bubble Sort   2. Selection Sort   3. Insertion Sort");
                        int algo;
                        while (true) {
                            System.out.print("Choice: ");
                            try {
                                algo = Integer.parseInt(sc.nextLine().trim());
                                if (algo >= 1 && algo <= 3) break;
                                System.out.println(RED + "Please enter a number from 1 to 3." + RESET);
                            } catch (NumberFormatException e) {
                                System.out.println(RED + "Invalid input. Please enter a whole number." + RESET);
                            }
                        }

                        for (int stage = 0; stage < 2; stage++) {

                            if (stage == 1) {
                                boolean textKey = (criteria == 1);
                                ArrayList<Integer> order = new ArrayList<>();
                                ArrayList<String> keyText = new ArrayList<>();
                                ArrayList<Integer> keyNum = new ArrayList<>();
                                for (int i = 0; i < n; i++) {
                                    order.add(i);
                                    if (forBooks) {
                                        keyText.add(bookTitles.get(i));
                                        keyNum.add(bookYears.get(i));
                                    } else {
                                        keyText.add(reqStudents.get(i));
                                        keyNum.add(reqNumbers.get(i));
                                    }
                                }

                                if (algo == 1) {
                                    System.out.println("\n[Bubble Sort]");
                                    for (int pass = 0; pass < n - 1; pass++) {
                                        boolean swapped = false;
                                        for (int j = 0; j < n - 1 - pass; j++) {
                                            int ka = order.get(j);
                                            int kb = order.get(j + 1);
                                            int cmp = textKey ? keyText.get(ka).compareToIgnoreCase(keyText.get(kb))
                                                    : keyNum.get(ka) - keyNum.get(kb);
                                            if (cmp > 0) {
                                                int tmp = order.get(j);
                                                order.set(j, order.get(j + 1));
                                                order.set(j + 1, tmp);
                                                swapped = true;
                                            }
                                        }
                                        if (!swapped) break;
                                    }
                                } else if (algo == 2) {
                                    System.out.println("\n[Selection Sort]");
                                    for (int i = 0; i < n - 1; i++) {
                                        int min = i;
                                        for (int j = i + 1; j < n; j++) {
                                            int ka = order.get(j);
                                            int kb = order.get(min);
                                            int cmp = textKey ? keyText.get(ka).compareToIgnoreCase(keyText.get(kb))
                                                    : keyNum.get(ka) - keyNum.get(kb);
                                            if (cmp < 0) min = j;
                                        }
                                        if (min != i) {
                                            int tmp = order.get(i);
                                            order.set(i, order.get(min));
                                            order.set(min, tmp);
                                        }
                                    }
                                } else {
                                    System.out.println("\n[Insertion Sort]");
                                    for (int i = 1; i < n; i++) {
                                        int j = i;
                                        while (j > 0) {
                                            int ka = order.get(j - 1);
                                            int kb = order.get(j);
                                            int cmp = textKey ? keyText.get(ka).compareToIgnoreCase(keyText.get(kb))
                                                    : keyNum.get(ka) - keyNum.get(kb);
                                            if (cmp <= 0) break;
                                            int tmp = order.get(j - 1);
                                            order.set(j - 1, order.get(j));
                                            order.set(j, tmp);
                                            j--;
                                        }
                                    }
                                }

                                if (forBooks) {
                                    ArrayList<String> oldTitles = new ArrayList<>(bookTitles);
                                    ArrayList<String> oldAuthors = new ArrayList<>(bookAuthors);
                                    ArrayList<Integer> oldYears = new ArrayList<>(bookYears);
                                    ArrayList<Integer> oldCopies = new ArrayList<>(bookCopies);
                                    for (int k = 0; k < n; k++) {
                                        bookTitles.set(k, oldTitles.get(order.get(k)));
                                        bookAuthors.set(k, oldAuthors.get(order.get(k)));
                                        bookYears.set(k, oldYears.get(order.get(k)));
                                        bookCopies.set(k, oldCopies.get(order.get(k)));
                                    }
                                } else {
                                    ArrayList<Integer> oldNumbers = new ArrayList<>(reqNumbers);
                                    ArrayList<String> oldStudents = new ArrayList<>(reqStudents);
                                    ArrayList<String> oldBooks = new ArrayList<>(reqBooks);
                                    ArrayList<String> oldStatus = new ArrayList<>(reqStatus);
                                    for (int k = 0; k < n; k++) {
                                        reqNumbers.set(k, oldNumbers.get(order.get(k)));
                                        reqStudents.set(k, oldStudents.get(order.get(k)));
                                        reqBooks.set(k, oldBooks.get(order.get(k)));
                                        reqStatus.set(k, oldStatus.get(order.get(k)));
                                    }
                                }
                            }

                            System.out.println(stage == 0 ? "\n--- BEFORE SORTING ---" : "\n--- AFTER SORTING ---");
                            if (forBooks) {
                                System.out.printf("%-4s %-34s %-20s %-6s %-6s%n", "No.", "Title", "Author", "Year", "Copies");
                                for (int i = 0; i < bookTitles.size(); i++) {
                                    System.out.printf("%-4d %-34s %-20s %-6d %-6d%n", (i + 1), bookTitles.get(i),
                                            bookAuthors.get(i), bookYears.get(i), bookCopies.get(i));
                                }
                            } else {
                                System.out.printf("%-9s %-20s %-30s %-10s%n", "Request#", "Student", "Book", "Status");
                                for (int i = 0; i < reqNumbers.size(); i++) {
                                    System.out.printf("%-9d %-20s %-30s %-10s%n", reqNumbers.get(i), reqStudents.get(i),
                                            reqBooks.get(i), reqStatus.get(i));
                                }
                            }
                        }

                        if (forBooks) {
                            System.out.println(GREEN + "Books sorted successfully." + RESET);
                        } else {
                            System.out.println(GREEN + "Borrowing records sorted successfully." + RESET);
                            System.out.println("(The pending queue order is NOT affected by sorting. Use option 8 to verify.)");
                        }
                        break;
                    }

                    case 13: {
                        System.out.println("--- ALL BORROWING RECORDS ---");
                        if (reqNumbers.isEmpty()) {
                            System.out.println("No borrowing records yet.");
                        } else {
                            System.out.printf("%-9s %-20s %-30s %-10s%n", "Request#", "Student", "Book", "Status");
                            for (int i = 0; i < reqNumbers.size(); i++) {
                                System.out.printf("%-9d %-20s %-30s %-10s%n", reqNumbers.get(i), reqStudents.get(i),
                                        reqBooks.get(i), reqStatus.get(i));
                            }
                        }
                        break;
                    }

                    case 0: {
                        System.out.println("Thank you for using the Library Borrowing Request System. Goodbye!");
                        running = false;
                        break;
                    }
                }

                if (running) {
                    System.out.print("\nPress Enter to continue...");
                    sc.nextLine();
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nNo more input. Exiting.");
        }
    }
}