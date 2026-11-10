/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WestminsterShoppingGui {

    private JFrame frame;
    private JComboBox<String> categoryDropdown;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private List<Product> products;
    private List<Product> shoppingCart;

    public WestminsterShoppingGui() {
        frame = new JFrame("Shopping Cart App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        initializeProducts();

        // Category dropdown
        String[] categories = {"All", "Electronics", "Clothes"};
        categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.addActionListener(e -> updateTable());
        frame.add(categoryDropdown, BorderLayout.NORTH);

        // Product table
        String[] columnNames = {"Name", "Category", "Availability", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getSelectionModel().addListSelectionListener(e -> showProductDetails());
        JScrollPane scrollPane = new JScrollPane(productTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Product details panel
        productDetailsTextArea = new JTextArea(5, 20);
        productDetailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(productDetailsTextArea);
        frame.add(detailsScrollPane, BorderLayout.SOUTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> addToCart());
        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> viewCart());
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame properties
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        shoppingCart = new ArrayList<>();
        updateTable();
    }

    private void initializeProducts() {
        // Initialize product data (replace with your actual product data)
        products = new ArrayList<>();
        products.add(new Product("Product A", "Electronics", 5, 50.0));
        products.add(new Product("Product B", "Clothes", 2, 30.0));
        // Add more products as needed
    }

    private void updateTable() {
        String selectedCategory = categoryDropdown.getSelectedItem().toString();

        tableModel.setRowCount(0);

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if ("All".equals(selectedCategory) || product.getCategory().equals(selectedCategory)) {
                filteredProducts.add(product);
            }
        }

        // Sort the list alphabetically by product name
        Collections.sort(filteredProducts, Comparator.comparing(Product::getName));

        for (Product product : filteredProducts) {
            Object[] rowData = {product.getName(), product.getCategory(), product.getAvailability(), product.getPrice()};
            tableModel.addRow(rowData);
        }

        // Apply red color to rows with reduced availability
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int availability = (int) tableModel.getValueAt(i, 2);
            if (availability < 3) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    tableModel.setValueAt("<html><font color='red'>" + tableModel.getValueAt(i, j) + "</font></html>", i, j);
                }
            }
        }
    }

    private void showProductDetails() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String details = "Name: " + tableModel.getValueAt(selectedRow, 0) + "\n"
                    + "Category: " + tableModel.getValueAt(selectedRow, 1) + "\n"
                    + "Availability: " + tableModel.getValueAt(selectedRow, 2) + "\n"
                    + "Price: " + tableModel.getValueAt(selectedRow, 3);
            productDetailsTextArea.setText(details);
        }
    }

    private void addToCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String category = (String) tableModel.getValueAt(selectedRow, 1);
            int availability = (int) tableModel.getValueAt(selectedRow, 2);
            double price = (double) tableModel.getValueAt(selectedRow, 3);

            Product selectedProduct = new Product(name, category, availability, price);
            shoppingCart.add(selectedProduct);

            JOptionPane.showMessageDialog(frame, "Product added to cart.");
        }
    }

    private void viewCart() {
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Category", "Availability", "Price"};
        DefaultTableModel cartTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartFrame.add(cartScrollPane, BorderLayout.CENTER);

        for (Product product : shoppingCart) {
            Object[] rowData = {product.getName(), product.getCategory(), product.getAvailability(), product.getPrice()};
            cartTableModel.addRow(rowData);
        }

        double totalCost = shoppingCart.stream().mapToDouble(Product::getPrice).sum();

        // Apply discount if buying at least three products of the same category
        long categoryCount = shoppingCart.stream().filter(p -> p.getAvailability() >= 3).map(Product::getCategory).distinct().count();
        if (categoryCount >= 3) {
            double discount = 0.2 * totalCost;
            totalCost -= discount;
            JOptionPane.showMessageDialog(frame, "Discount applied! Final cost: £" + totalCost);
        } else {
            JOptionPane.showMessageDialog(frame, "Final cost: £" + totalCost);
        }

        cartFrame.setSize(400, 300);
        cartFrame.setLocationRelativeTo(null);
        cartFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WestminsterShoppingGui::new);
    }
}

class Product {
    private String name;
    private String category;
    private int availability;
    private double price;

    public Product(String name, String category, int availability, double price) {
        this.name = name;
        this.category = category;
        this.availability = availability;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getAvailability() {
        return availability;
    }

    public double getPrice() {
        return price;
    }
}
