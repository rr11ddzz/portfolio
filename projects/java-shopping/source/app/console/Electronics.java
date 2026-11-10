/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.console;

public class Electronics extends Product {
    private double warranty;

    // Constructor for the Electronics class
    public Electronics(String brand, String model, String serialNumber, double price, double warranty) {
        super(brand, model, serialNumber, price);
        this.warranty = warranty;
    }

    // Getter and setter methods for the powerUsage attribute
    public double getWarranty() {
        return warranty;
    }

    public void setWarranty(double warranty) {
        this.warranty = warranty;
    }

    // Override the toString method to include electronics-specific information
    @Override
    public String toString() {
        return "Electronics{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", serialNumber='" + getSerialNumber() + '\'' +
                ", price=£" + getPrice() +
                ", warranty=" + warranty +
                '}';
    }
}
