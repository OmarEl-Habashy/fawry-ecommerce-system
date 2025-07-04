package Cart;
import Product.Product;
import Service.Shipping;

public class Cart {
    private java.util.List<Items> items;

    public Cart() {
        this.items = new java.util.ArrayList<>();
    }

    public boolean addItem(Product product, int quantity) {
        if (!product.isAvailable()) {
            System.out.println("Product " + product.getName() +
                    (product.hasExpired() ? " has expired." : " is out of stock."));
            return false;
        }

        int existingQuantity = 0;
        for (Items existingItem : items) {
            if (existingItem.getProduct().getName().equals(product.getName())) {
                existingQuantity = existingItem.getQuantity();
                break;
            }
        }

        if (existingQuantity + quantity > product.getQuantity()) {
            int remainingStock = product.getQuantity() - existingQuantity;
            System.out.println("Cannot add " + quantity + " items. You already have " +
                    existingQuantity + " in cart. Available stock: " + remainingStock);
            return false;
        }

        for (Items existingItem : items) {
            if (existingItem.getProduct().getName().equals(product.getName())) {
                existingItem.setQuantity(existingQuantity + quantity);
                return true;
            }
        }

        items.add(new Items(product, quantity));
        return true;
    }

    public java.util.List<Items> getItems() {
        return items;
    }

    private double calculateSubtotal() {
        double subtotal = 0;
        for(Items item : items) {
            subtotal += item.getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    public void previewOrder() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double subtotal = calculateSubtotal();
        Shipping shipping = new Shipping();
        double shippingCost = shipping.calculateShippingCost(items);
        double total = subtotal + shippingCost;

        System.out.println("** Order Preview **");
        for (Items item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                    "\t" + (item.getPrice() * item.getQuantity()));
        }

        System.out.println("----------------------");
        System.out.println("Subtotal:\t" + subtotal);
        System.out.println("Shipping:\t" + shippingCost);
        System.out.println("Total:\t\t" + total);
        System.out.println("----------------------");

        if (shippingCost > 0) {
            System.out.println("\nEstimated Shipping Details:");
            shipping.processEachItem(items);
        }
    }}