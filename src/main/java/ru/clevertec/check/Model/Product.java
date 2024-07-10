package main.java.ru.clevertec.check.Model;

public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity_in_stock;
    private boolean wholesale_product;

    public Product(int id, String description, double price, int quantity_in_stock, boolean wholesale_product) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity_in_stock = quantity_in_stock;
        this.wholesale_product = wholesale_product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public boolean isWholesale_product() {
        return wholesale_product;
    }

    public void setWholesale_product(boolean wholesale_product) {
        this.wholesale_product = wholesale_product;
    }
}