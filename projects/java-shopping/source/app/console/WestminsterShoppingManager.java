/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app.console;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WestminsterShoppingManager {
    private static final int MAX_PRODUCTS = 15;
    private static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        readProductsFromFile(); // Read from file if it exists

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    printProductList();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("------ Westminster Shopping Manager ------");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of products");
        System.out.println("4. Save products to file");
        System.out.println("5. Exit");
    }

    private static void addProduct() {
        if (products.size() >= MAX_PRODUCTS) {
            System.out.println("Error: maximum number of products reached.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product type (1 for electronics, 2 for clothing): ");
        int productType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product's brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter product's model: ");
        String model = scanner.nextLine();

        System.out.print("Enter product's serial number: ");
        String serialNumber = scanner.nextLine();

        double price = 0.0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter product's price: £");
            try {
                price = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        Product product;
        if (productType == 1) {
            System.out.print("Enter warrantyPeriod: ");
            double warranty = scanner.nextDouble();
            scanner.nextLine();
            product = new Electronics(brand, model, serialNumber, price, warranty);
        } else if (productType == 2) {
            System.out.print("Enter colour: ");
            String colour = scanner.nextLine();

            // Adding size for clothing
            System.out.print("Enter size: ");
            String size = scanner.nextLine();

            product = new Clothing(brand, model, serialNumber, price, colour, size);
        } else {
            System.out.println("Invalid product type.");
            return;
        }

        products.add(product);
        System.out.println("Product added successfully.");
    }

    private static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product's serial number: ");
        var serialNumber = scanner.nextLine();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getSerialNumber().equals(serialNumber)) {
                System.out.println("Product deleted: " + product);
                products.remove(i);
                return;
            }
        }
        System.out.println("Error: Product with serial number " + serialNumber + " not found.");
    }

    private static void printProductList() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("------ Product List ------");
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println("--------------------------");
        }
    }

    private static void saveToFile() {
        if (products.isEmpty()) {
            System.out.println("No products to save.");
            return;
        }

        try {
            FileWriter fw = new FileWriter("products.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Product product : products) {
                pw.println(product.getBrand());
                pw.println(product.getModel());
                pw.println(product.getSerialNumber());
                pw.println(product.getPrice());

                if (product instanceof Electronics) {
                    // If the product is an instance of Electronics, write warranty
                    pw.println(((Electronics) product).getWarranty());
                } else if (product instanceof Clothing) {
                    // If the product is an instance of Clothing, write colour and size
                    pw.println(((Clothing) product).getColour());
                    pw.println(((Clothing) product).getSize());
                }
            }

            pw.close();
            fw.close();
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save to file.");
        }
    }

    private static void readProductsFromFile() {
        try {
            Scanner scanner = new Scanner(new File("products.txt"));
            while (scanner.hasNextLine()) {
                String brand = scanner.nextLine();
                String model = scanner.nextLine();
                String serialNumber = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());

                int productType = Integer.parseInt(scanner.nextLine());
                Product product;

                if (productType == 1) {
                    // If productType is 1, it is an Electronics product
                    double warranty = Double.parseDouble(scanner.nextLine());
                    product = new Electronics(brand, model, serialNumber, price, warranty);
                } else if (productType == 2) {
                    // If productType is 2, it is a Clothing product
                    String colour = scanner.nextLine();

                    // Adding size for clothing
                    String size = scanner.nextLine();

                    product = new Clothing(brand, model, serialNumber, price, colour, size);
                } else {
                    // Handle other cases or throw an exception
                    System.out.println("Invalid product type in file.");
                    continue;
                }

                products.add(product);
            }
            scanner.close();
            System.out.println("Products read from file successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to read from file.");
        }
    }
}
