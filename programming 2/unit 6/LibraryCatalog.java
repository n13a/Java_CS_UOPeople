import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LibraryItem<T> {
  private String author;
  private String title;
  private String itemID;

  public LibraryItem(String title, String author, String itemID) {
    this.title = title;
    this.author = author;
    this.itemID = itemID;
  }

  // Getters
  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getItemID() {
    return itemID;
  }

  @Override
  public String toString() {
    return "Title: " + title + ", Author: " + author + ", Item ID: " + itemID;
  }
}

class Catalog<T extends LibraryItem<?>> {
  public List<T> items;

  public Catalog() {
    items = new ArrayList<>();
  }

  public void addItem(T item) {
    items.add(item);
  }

  public boolean removeItem(String itemID) {
    // Can't remove an item from empty catalog
    if (items.size() == 0) {
      System.out.println("Catalog is empty.");
      return false;
    }
    displayCatalog();
    for (T item : items) {
      if (item.getItemID().equals(itemID)) {
        items.remove(item);
        return true;
      }
    }
    return false;
  }

  public void displayCatalog() {
    if (items.size() == 0) {
      System.out.println("Catalog is empty.");

    }
    for (T item : items) {
      System.out.println(item);
    }
  }
}

public class LibraryCatalog {
  public static void main(String[] args) {
    Catalog<LibraryItem<?>> catalog = new Catalog<>();
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("Menu");
      System.out.println("1. Add Item");
      System.out.println("2. Display Catalog");
      System.out.println("3. Remove Item");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          System.out.println("Enter item details:");
          System.out.print("Title: ");
          String title = scanner.nextLine();
          System.out.print("Author: ");
          String author = scanner.nextLine();
          System.out.print("Item ID: ");
          String itemID = scanner.nextLine();

          LibraryItem<?> newItem = new LibraryItem<>(title, author, itemID);
          catalog.addItem(newItem);
          System.out.println("Item added to catalog.");
          break;
        case 2:
          System.out.println("Current Catalog:");
          catalog.displayCatalog();
          break;
        case 3:
          if (catalog.items.size() == 0) {
            System.out.println("Catalog is empty.");
            break;
          }

          System.out.print("Enter Item ID to remove: ");
          String removeID = scanner.nextLine();
          boolean removed = catalog.removeItem(removeID);
          if (removed) {
            System.out.println("Item removed successfully.");
          } else {
            System.out.println("Item not found.");
          }
          break;
        case 4:

          System.out.println("Bye...");
          break;
        default:
          System.out.println("Something went wrong. Try again.");
      }
    } while (choice != 4);

    scanner.close();
  }
}
