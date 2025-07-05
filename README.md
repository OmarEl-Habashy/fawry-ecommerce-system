# E-Commerce Console Application

A Java console-based e-commerce system that handles product management, shopping cart operations, and checkout processes with shipping calculations.

## Features

- Product management with various attributes (name, price, quantity)
- Support for expirable products (e.g., Cheese, Biscuits)
- Shipping calculation for physical products
- Shopping cart functionality
- Customer balance management
- Checkout process with detailed receipts
- CSV-based product inventory loading

## Architecture

The application follows a layered architecture pattern:

- **Domain Layer**: Contains the core business entities
    - Product classes represent the available items for purchase
    - Customer class manages user information and balance
    - Cart classes handle shopping cart operations

- **Service Layer**: Contains business logic
    - Checkout service processes transactions
    - Shipping service calculates shipping costs
    - ProductLoader service handles data import from CSV

- **Presentation Layer**: Handled by Main.java
    - Provides the console interface for user interaction
    - Processes user commands and displays results

This separation of concerns allows for maintainable code and clear responsibility boundaries between components.

## Project Structure

```
src/
├── Cart/
│   ├── Cart.java
│   └── Items.java
├── Customer/
│   └── Customer.java
├── Product/
│   ├── Category.java
│   ├── Inventory.java
│   ├── Product.java
│   └── Shippable.java
├── Service/
│   ├── Checkout.java
│   ├── ProductLoader.java
│   └── Shipping.java
└── Main.java
```

## Getting Started


-> Make sure you place the `products.csv` file in the project root directory


## Main Menu

The application provides an interactive console menu with the following options:

1. Add product to cart
2. View cart
3. Checkout
4. View Account Details
5. Exit

## Sample Product CSV Format

```csv
name,price,quantity,category,isShippable,weight,productionDate,expirationDays
TV,999.99,10,NON_ORGANIC,true,7000,,
Cheese,5.0,100,ORGANIC,true,200,2023-10-01,30
```

## Features Implementation

- **Product Types**: Supports both organic (expirable) and non-organic products
- **Shipping**: Calculates shipping costs based on weight and quantity
- **Stock Management**: Prevents overselling by tracking inventory
- **Input Validation**: Handles invalid inputs and edge cases
- **Error Handling**: Proper error messages for various scenarios

## Error Handling

The system handles various error cases including:
- Empty cart checkout attempts
- Insufficient customer balance
- Out of stock products
- Expired products
- Invalid input data

## Challenge Requirements Met

| Requirement | Implementation |
|-------------|----------------|
| ✅ Product definition with attributes | Implemented with name, price, quantity, category |
| ✅ Expiration handling | Organic products track production date and expiration days |
| ✅ Shipping weight calculations | Shipping costs calculated based on product weight |
| ✅ Cart quantity validation | Validates available inventory before checkout |
| ✅ Comprehensive checkout process | Complete flow with balance check and receipt generation |
| ✅ Error handling | Robust handling of edge cases and invalid scenarios |
| ✅ Shipping service integration | Shipping service calculates costs for physical products |

## Dependencies

- Java SE 8 or higher
- No external libraries required
