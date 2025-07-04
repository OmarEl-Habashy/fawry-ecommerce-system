import Cart.*;
import Customer.Customer;
import Product.*;
import Service.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer("Omar", 10000.0);


        Inventory inventory = new Inventory();
        List<Product> products = ProductLoader.loadProductsFromCSV("products.csv");
        for (Product product : products) {
            inventory.addProduct(product);
        }
        Cart cart = new Cart();

        System.out.println("Welcome to the e-commerce Beta Console App!");
        while (true) {
            System.out.println("Enter the number of the operation you want to perform:");
            System.out.println("1. Add product to cart");
            System.out.println("2. View cart");
            System.out.println("3. Checkout");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    Product selectedProduct = inventory.selectProduct(sc);
                    if (selectedProduct != null) {
                        int quantity = 0;
                        while (true) {
                            try {
                                System.out.print("Enter quantity: ");
                                quantity = sc.nextInt();
                                if (quantity <= 0) {
                                    System.out.println("Quantity must be a positive number. Please try again.");
                                } else {
                                    break;
                                }
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid number.");
                                sc.next();
                            }
                        }
                        boolean added = cart.addItem(selectedProduct, quantity);
                        if (added){
                            System.out.println(quantity + " " + selectedProduct.getName() + "(s) added to cart.");
                        }
                    }
                    break;

                case 2:
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        System.out.println("Items in your cart:");
                        for (Items item : cart.getItems()) {
                            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                                    " - Price: $" + item.getPrice() * item.getQuantity());
                        }
                        cart.previewOrder();
                    }
                    break;

                case 3:
                    Checkout checkout = new Checkout();
                    try {
                        checkout.processCheckout(customer, cart);
                        cart.getItems().clear();
                    } catch (IllegalStateException e) {
                        System.out.println("Checkout failed: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("** Account Details **");
                    System.out.println("Name: " + customer.getName() + ",\nBalance: $" + customer.getBalance());
                    break;

                case 5:
                    System.out.println("Exiting the application. Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }


    }
}
