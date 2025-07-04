package Cart;

import Product.Product;

public class Items{
    private Product product;
    private int quantity;

    public Items(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public double getPrice(){
        return product.getPrice();
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
