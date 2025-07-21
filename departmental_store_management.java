import java.util.*;

class Product {
    int id;
    String name;
    float price;
    int quantity;

    Product(int id, String name, float price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

public class departmental_store_management {
    static final int MAX_PRODUCTS = 100;
    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Department Store Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Billing Product");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addProduct(sc);
                case 2 -> displayProducts();
                case 3 -> searchProduct(sc);
                case 4 -> updateProduct(sc);
                case 5 -> deleteProduct(sc);
                case 6 -> billingProduct(sc);
                case 7 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
    }

    static void addProduct(Scanner sc) {
        if (products.size() >= MAX_PRODUCTS) {
            System.out.println("Cannot add more products. Maximum limit reached.");
            return;
        }
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Product Price: ");
        float price = sc.nextFloat();

        System.out.print("Enter Product Quantity: ");
        int quantity = sc.nextInt();

        products.add(new Product(id, name, price, quantity));
        System.out.println("Product added successfully!");
    }

    static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\n--- Product List ---");
        System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Price", "Quantity");
        for (Product p : products) {
            System.out.printf("%-5d %-15s %-10.2f %-10d\n", p.id, p.name, p.price, p.quantity);
        }
    }

    static void searchProduct(Scanner sc) {
        System.out.print("Enter Product ID to search: ");
        int id = sc.nextInt();
        for (Product p : products) {
            if (p.id == id) {
                System.out.println("Product Found:");
                System.out.println("ID: " + p.id);
                System.out.println("Name: " + p.name);
                System.out.println("Price: " + p.price);
                System.out.println("Quantity: " + p.quantity);
                return;
            }
        }
        System.out.println("Product with ID " + id + " not found!");
    }

    static void updateProduct(Scanner sc) {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Product p : products) {
            if (p.id == id) {
                System.out.print("Enter new name: ");
                p.name = sc.nextLine();
                System.out.print("Enter new price: ");
                p.price = sc.nextFloat();
                System.out.print("Enter new quantity: ");
                p.quantity = sc.nextInt();
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product with ID " + id + " not found!");
    }

    static void deleteProduct(Scanner sc) {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.id == id) {
                iterator.remove();
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product with ID " + id + " not found!");
    } 

    static void billingProduct(Scanner sc) {
        float billAmount = 0;
        displayProducts();

        System.out.println("Enter product IDs to include in the bill (enter -1 to finish):");
        while (true) {
            System.out.print("Product ID: ");
            int proID = sc.nextInt();
            if (proID == -1) break;

            boolean found = false;
            for (Product p : products) {
                if (p.id == proID) {
                    billAmount += p.price * p.quantity;
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Product with ID " + proID + " not found!");
            }
        }
        System.out.printf("The total bill amount is: %.2f\n", billAmount);
    }
}
