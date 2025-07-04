package Service;

import Product.Shippable;
import Cart.Items;
import java.util.ArrayList;
import java.util.List;

public class Shipping {
    private static final double BASE_SHIPPING_RATE = 30.0;
    private static final double PER_GRAM_RATE = 0.05;

    public void processEachItem(List<Items> items) {
        List<Shippable> shippableItems = getShippableItems(items);
        if (!shippableItems.isEmpty()) {
            System.out.println("\n** Shipment notice **");
            for (Items item : items) {
                if (item.getProduct().isShippable()) {
                    double itemWeight = item.getProduct().getWeight() * item.getQuantity();
                    System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                            "\t" + (item.getProduct().getWeight() * item.getQuantity()) + "g");
                }

            }
            double totalWeight = calculateTotalWeight(items);
            System.out.println("Total package weight " + totalWeight/1000 + " kg");
            System.out.println("Shipping cost: " + calculateShippingCost(items));
            System.out.println("------------------------"+"\n");

        }
    }
    public List<Shippable> getShippableItems(List<Items> items) {
        List<Shippable> shippableItems = new ArrayList<>();
        for (Items item : items) {
            if (item.getProduct().isShippable()) {
                shippableItems.add(item.getProduct());
            }
        }
        return shippableItems;
    }

    public double calculateShippingCost(List<Items> items) {
        List<Shippable> shippableItems = getShippableItems(items);
        if (shippableItems.isEmpty()) {
            return 0.0;
        }

        double totalWeight = calculateTotalWeight(items);
        double weightCost = totalWeight * PER_GRAM_RATE;
        return BASE_SHIPPING_RATE + weightCost;
    }

    public double calculateTotalWeight(List<Items> items) {
        double totalWeight = 0.0;
        for (Items item : items) {
            if (item.getProduct().isShippable()) {
                totalWeight += item.getProduct().getWeight() * item.getQuantity();
            }
        }
        return totalWeight;
    }
}