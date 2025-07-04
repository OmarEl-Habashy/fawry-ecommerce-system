package Service;
import Cart.*;
import Product.*;
import Customer.Customer;
public class Checkout {
    private Shipping shipping;

    public Checkout(){
        this.shipping = new Shipping();
    }
    public void processCheckout(Customer customer, Cart cart){
        if (cart.getItems().isEmpty()){
            throw new IllegalStateException("Cart is empty");
        }

        //validate the items in the cart
        ValidateItem(cart);


        double subtotal = calculateSubtotal(cart);
        double shippingCost = shipping.calculateShippingCost(cart.getItems());
        double total = subtotal + shippingCost;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance for checkout. Your balance is " + customer.getBalance() +
                    ", but the total amount is " + total);
        }

        //print the shipping details
        shipping.processEachItem(cart.getItems());

        //deduct the total amount from customer's balance
        customer.deductBalance(total);

        //print the receipt
        printReceipt(cart, subtotal, shippingCost, total);

        //update the product stock
        UpdateProductStock(cart);
        System.out.println("\n** Checkout successful! **");
        System.out.println("** Thank you for your purchase, " + customer.getName() + "! **");
        System.out.println("Your new balance is: " + customer.getBalance() + "\n\n");

    }
    private double calculateSubtotal(Cart cart){
        double subtotal =0;
        for(Items item : cart.getItems()){
            subtotal += item.getPrice() * item.getQuantity();
        }
        return subtotal;
    }

    private void printReceipt(Cart cart, double subtotal, double shippingCost, double total) {
        System.out.println("** Checkout receipt **");
        for (Items item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                    "\t" + item.getPrice()* item.getQuantity());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + shippingCost);
        System.out.println("Amount\t" + total + "\n");

    }

    private void UpdateProductStock(Cart cart) {
        for (Items item : cart.getItems()) {
            Product product = item.getProduct();
            int newStock = product.getQuantity() - item.getQuantity();
            if (newStock < 0) {
                throw new IllegalStateException("Insufficient stock for " + product.getName());
            }
            product.setQuantity(newStock);
        }
    }

    private void ValidateItem(Cart cart) {
        for (Items item : cart.getItems()) {
            Product product = item.getProduct();
            if (!product.isAvailable()) {
                throw new IllegalStateException("Product " + product.getName() +
                        (product.hasExpired() ? " has expired." : " is out of stock."));
            }
        }
    }
}
