/* Write a Java program that accomplishes the following tasks:

1. Implement the following options:

    Add Books
    Borrow Books
    Return Books
    Exit

2.     For "Add Books":

    Prompt the user to enter the book title, author, and quantity.
    If the book already exists in the library, update the quantity.
    If the book is new, add it to the library.

3.     For "Borrow Books":

    Prompt the user to enter the book title and the number of books to borrow.
    Check if the requested number of books is available in the library.
    If the books are available, update the quantity and display a success message.
    If not, display an error message.

4.     For "Return Books":

    Prompt the user to enter the book title and the number of books to return.
    Check if the books being returned belong to the library system.
    If they do, update the quantity and display a success message.
    If not, display an error message.

5.     Handle invalid input and display appropriate error messages.

6.     Implement an exit option to allow the user to exit the program. */


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Class representing a Book
class Book {
    String title;
    String author;
    int quantity;

    // Constructor to initialize Book properties
    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Method to get a string representation of the book details
    public String tell() {
        return "Book name: " + title + " | author: " + author + " | quantity: " + quantity;
    }
}

// Class representing a Library
public class Library {
    private List<Book> books;

    // Constructor to initialize the Library with an empty list of books
    public Library() {
        this.books = new ArrayList<>();
    }

    // Method to add a new book to the library
    public void addBook(Scanner scanner) {
        System.out.println("Enter the details of the book:");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        // Check if the book with the same title already exists
        for (Book existingBook : books) {
            if (existingBook.title.equalsIgnoreCase(title)) {
                // Book with the same title exists, increase quantity
                System.out.print("This book already exists. Enter the quantity to add: ");
                int quantityToAdd = scanner.nextInt();
                existingBook.quantity += quantityToAdd;
                System.out.println("Updated quantity!\n");
                return; // Exit the method
            }
        }

        // If the book doesn't exist, get author and quantity details and add it to the library
        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book newBook = new Book(title, author, quantity);
        books.add(newBook);

        System.out.println("Book added successfully!\n");
    }

    // Method to borrow a book from the library
    public void borrowBook(Scanner scanner) {
        System.out.println("What book do you want to borrow?");
        String title = scanner.nextLine();
        for (Book existingBook : books) {
            if (existingBook.title.equalsIgnoreCase(title)) {
                System.out.println("How many of this book do you want to borrow?");
                int quantityToReduce = scanner.nextInt();
                if (quantityToReduce > existingBook.quantity) {
                    System.out.println("Sorry, We don't have that many.");
                    return;
                }
                existingBook.quantity -= quantityToReduce;
                System.out.println("Borrowed book successfully.");
                return;
            }
        }
        System.out.println("Sorry. We don't have this book.");
    }

    // Method to return a book to the library
    public void returnBook(Scanner scanner) {
        System.out.println("What book do you want to return?");
        String title = scanner.nextLine();
        for (Book existingBook : books) {
            if (existingBook.title.equalsIgnoreCase(title)) {
                System.out.println("How many of this book do you want to return?");
                int quantityToAdd = scanner.nextInt();
                existingBook.quantity += quantityToAdd;
                System.out.println("Returned book successfully.");
                return;
            }
        }
        System.out.println("Sorry. We never had this book.");
    }

    // Method to display all books in the library
    public void displayBooks() {
        if(books.size() == 0) {
          System.out.println("There are no books, yet.");
          return;
        }
        System.out.println("Library Books:\n");
        for (Book book : books) {
            System.out.println(book.tell());
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean stateOfProgram = true;
        while (stateOfProgram) {
            System.out.println("What operation do you want to do? \n 1. Add book \n 2. Borrow book \n 3. Return book\n 4. Display all the books \n 5. Quit\n");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Switch statement to perform different actions based on user input
                switch (choice) {
                    case 1 -> library.addBook(scanner);
                    case 2 -> library.borrowBook(scanner);
                    case 3 -> library.returnBook(scanner);
                    case 4 -> library.displayBooks();
                    case 5 -> stateOfProgram = false;
                    default -> {
                        System.out.println("Wrong operation. Try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        System.out.println("Goodbye...");
    }
}

