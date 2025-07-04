package Service;

import Product.Category;
import Product.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {
    public static List<Product> loadProductsFromCSV(String filename) throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header
            if (line == null) {
                throw new IOException("CSV file is empty");
            }

            while ((line = br.readLine()) != null) {
                try {
                    String[] values = line.split(",");
                    if (values.length < 6) {
                        throw new IOException("Invalid CSV format");
                    }

                    String name = values[0];
                    double price = Double.parseDouble(values[1]);
                    int quantity = Integer.parseInt(values[2]);
                    Category category = Category.valueOf(values[3]);
                    boolean isShippable = Boolean.parseBoolean(values[4]);
                    double weight = Double.parseDouble(values[5]);

                    Product product = new Product(name, price, quantity, category, isShippable, weight);

                    if (values.length > 6 && !values[6].isEmpty()) {
                        product.setProductionDate(Date.valueOf(values[6]));
                    }
                    if (values.length > 7 && !values[7].isEmpty()) {
                        product.setExpirationDays(Integer.parseInt(values[7]));
                    }

                    products.add(product);
                } catch (Exception e) {
                    throw new IOException("Error parsing line: " + line, e);
                }
            }
        }
        return products;
    }
}