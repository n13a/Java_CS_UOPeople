import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create predefined products
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Men Cargo Pants", 100.0));
        products.add(new Product("P2", "Jeans Pants", 120.0));
        products.add(new Product("P3", "Crew T-Shirts", 50.0));
        products.add(new Product("P4", "Loose Fit T-Shirt", 72.0));
        products.add(new Product("P5", "Ultra Cotton T-shirt", 91.0));
        products.add(new Product("P6", "Long Sleeve Dress Shirt", 60.0));
        products.add(new Product("P7", "Business Dress Shirt", 55.0));

        // Scanner object for user input.
        Scanner scanner = new Scanner(System.in);

        // Ask for the customer's name
        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();

        // Instance of a customer
        Customer customer = new Customer("C1", customerName);

        while (true) {
            // Display the products
            System.out.println("\nProducts:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getName() + " - $" + products.get(i).getPrice());
            }

            System.out.println(
                    "\nEnter the number of the product you want to add to the shopping cart,\ntype 'cart' to view your shopping cart, type 'remove' to remove\na product from your shopping cart, or type 'order' to place your order:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("order")) {
                break;
            } else if (input.equalsIgnoreCase("cart")) {
                System.out.println("\nYour shopping cart:");
                for (Product product : customer.getShoppingCart()) {
                    System.out.println("- " + product.getName() + " - $" + product.getPrice());
                }
                System.out.println("-----".repeat(15));
                continue;
            } else if (input.equalsIgnoreCase("remove")) {
                List<Product> customerProducts = customer.getShoppingCart();
                for (int i = 0; i < customerProducts.size(); i++) {
                    System.out.println(
                            (i + 1) + ". " + customerProducts.get(i).getName() + " - $"
                                    + customerProducts.get(i).getPrice());
                }
                System.out.println("\nEnter the number of the product you want to remove from your shopping cart:");
                input = scanner.nextLine();
                try {
                    int productNumber = Integer.parseInt(input);
                    if (productNumber > 0 && productNumber <= customer.getShoppingCart().size()) {
                        System.out.println(customer.getShoppingCart().get(productNumber - 1).getName()
                                + " has been removed from your shopping cart.");
                        customer.removeProductFromCart(customer.getShoppingCart().get(productNumber - 1));
                        System.out.println("-----".repeat(15));
                    } else {
                        System.out.println("Invalid product number. Please try again.");
                        System.out.println("-----".repeat(15));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a product number.");
                    System.out.println("-----".repeat(15));
                }
                continue;
            }

            try {
                int productNumber = Integer.parseInt(input);
                if (productNumber > 0 && productNumber <= products.size()) {
                    if (customer.hasProductInCart(products.get(productNumber - 1))) {
                        System.out.println("This product has already been added to your shopping cart.");
                    } else {
                        customer.addProductToCart(products.get(productNumber - 1));
                        System.out.println(
                                products.get(productNumber - 1).getName() + " has been added to your shopping cart.");

                        System.out.println("-----".repeat(15));
                    }
                } else {
                    System.out.println("Invalid product number. Please try again.");
                    System.out.println("-----".repeat(15));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a product number, 'cart', 'remove', or 'order'.");
                System.out.println("-----".repeat(15));
            }
        }

        // Place an order
        Order order = new Order("O1", customer, customer.getShoppingCart());

        // Display the order
        System.out.println("\nOrder:");
        System.out.println(order.generateOrderSummary());

        scanner.close();
    }
}
