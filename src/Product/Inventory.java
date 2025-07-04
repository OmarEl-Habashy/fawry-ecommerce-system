package Product;

import java.util.Scanner;

public class Inventory {
    private java.util.List<Product> products;

    public Inventory() {
        this.products = new java.util.ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public java.util.List<Product> getProducts() {
        return new java.util.ArrayList<>(products);
    }

    public Product selectProduct(Scanner scanner) {
        while (true) {
            System.out.println("\nAvailable products:");
            System.out.println("0. Return to main menu");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.println(i + 1 + ". " + product.getName() + " - Price: $" + product.getPrice());
            }

            System.out.print("Enter product number: ");
            int productNumber = scanner.nextInt();

            if (productNumber == 0) {
                return null;
            }
            if (productNumber >= 1 && productNumber <= products.size()) {
                return products.get(productNumber - 1);
            }
            System.out.println("Invalid product number! Please try again.");
        }
    }
}
