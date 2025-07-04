package Product;

import java.time.LocalDate;

public class Product implements Shippable{
    private String name;
    private double price;
    private int quantity;
    private Category category;
    private LocalDate productionDate;
    private Integer expirationDays;
    private boolean isShippable;
    private double weight;

    public Product(String name, double price, int quantity, Category category,
                   boolean isShippable, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.productionDate = null;
        this.expirationDays = null;
        this.isShippable = isShippable;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isShippable() {
        return isShippable;
    }

    @Override
    public double getWeight() {
        return weight;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductionDate(java.sql.Date date) {
        if (date != null) {
            this.productionDate = date.toLocalDate();
        }
    }

    public void setExpirationDays(Integer days) {
        if (category == Category.ORGANIC && days != null) {
            this.expirationDays = days;
        }
    }

    public boolean hasExpired() {
        if (category == Category.NON_ORGANIC || expirationDays == null) {
            return false;
        }
        return productionDate.plusDays(expirationDays).isBefore(LocalDate.now());
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public Integer getExpirationDays() {
        return expirationDays;
    }

    public boolean isAvailable() {
        return quantity > 0 && !hasExpired();
    }



    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                ", isShippable=" + isShippable +
                ", weight=" + weight +
                '}';
    }
}