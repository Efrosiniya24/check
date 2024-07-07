package main.java.ru.clevertec.check.Service;

import main.java.ru.clevertec.check.Exception.ProductNotFoundException;
import main.java.ru.clevertec.check.Model.Product;

import java.util.List;

public class ProductService {
    public static Product findProductById(int id, List<Product> products) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    new ProductNotFoundException("\nНет товара с id " + id+"\n");
                    LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR");
                    return null;
                });
    }
}
