package main.java.ru.clevertec.check.Model.Builder;

import main.java.ru.clevertec.check.Model.Product;

public class ProductBuilder {
    private int id;
    private String description;
    private double price;
    private int quantityInStock;
    private boolean isWholesale;

    public ProductBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
        return this;
    }

    public ProductBuilder setWholesale(boolean isWholesale) {
        this.isWholesale = isWholesale;
        return this;
    }

    public Product build() {
        return new Product(id, description, price, quantityInStock, isWholesale);
    }
}
