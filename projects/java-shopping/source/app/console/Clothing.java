/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.console;

public class Clothing extends Product {
    private String colour;
    private String size; // Adding size attribute

    // Constructor for the Clothing class
    public Clothing(String brand, String model, String serialNumber, double price, String colour, String size) {
        super(brand, model, serialNumber, price);
        this.colour = colour;
        this.size = size;
    }

    // Getter and setter methods for the colour attribute
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    // Getter and setter methods for the size attribute
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Override the toString method to include clothing-specific information
    @Override
    public String toString() {
        return "Clothing{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", serialNumber='" + getSerialNumber() + '\'' +
                ", price=£" + getPrice() +
                ", colour='" + colour + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
