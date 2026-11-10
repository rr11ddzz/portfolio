/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.console;

public class Product implements Comparable<Product> {
    private String brand;
    private String model;
    private String serialNumber;
    private double price;

    // Constructor for the Product class
    public Product(String brand, String model, String serialNumber, double price) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.price = price;
    }

    // Getter methods for attributes
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    // Implement the compareTo method for sorting
    @Override
    public int compareTo(Product other) {
        return serialNumber.compareTo(other.serialNumber);
    }

    // Implement the toString method for displaying product information
    @Override
    public String toString() {
        return "Brand: " + brand + ", Model: " + model + ", Serial Number: " + serialNumber + ", Price: £" + price;
    }
}
