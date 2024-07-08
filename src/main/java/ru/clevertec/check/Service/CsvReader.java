package main.java.ru.clevertec.check.Service;

import main.java.ru.clevertec.check.Exception.QuantityExceotion;
import main.java.ru.clevertec.check.Model.Builder.ProductBuilder;
import main.java.ru.clevertec.check.Model.DiscountCard;
import main.java.ru.clevertec.check.Model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<Product> readProducts(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1)
                .map(CsvReader::parseProduct)
                .collect(Collectors.toList());
    }

    public static Product parseProduct(String line) throws QuantityExceotion {
        String[] parts = line.split(";");

        try {
            if (parts.length != 5)
                throw new QuantityExceotion("\nНедостаточно элементов в строке файла products\n" +line);

            int id = Integer.parseInt(parts[0]);
            String description = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantityInStock = Integer.parseInt(parts[3]);
            boolean isWholesale = Boolean.parseBoolean(parts[4]);

            Product product = new ProductBuilder()
                    .setId(id)
                    .setDescription(description)
                    .setPrice(price)
                    .setQuantityInStock(quantityInStock)
                    .setWholesale(isWholesale)
                    .build();

            return product;
        } catch (QuantityExceotion e) {
            LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR");
            System.err.println(e);
            System.exit(1);
        }
        return null;
    }

    public static List<DiscountCard> readDiscountCards(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1)
                .map(CsvReader::parseDiscountCard)
                .collect(Collectors.toList());
    }

    private static DiscountCard parseDiscountCard(String line) {
        String[] parts = line.split(";");

        try {
            if (parts.length != 3)
                throw new QuantityExceotion("\nНедостаточно элементов в строке файла discountCards\n" +line);
            int id = Integer.parseInt(parts[0]);
            int number = Integer.parseInt(parts[1]);
            int discountAmount = Integer.parseInt(parts[2]);
            return new DiscountCard(id, number, discountAmount);
        } catch (QuantityExceotion e) {
            LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR");
            System.err.println(e);
            System.exit(1);
        }
        return null;
    }
}
