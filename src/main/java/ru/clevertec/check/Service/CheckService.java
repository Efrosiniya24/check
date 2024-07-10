package main.java.ru.clevertec.check.Service;

import main.java.ru.clevertec.check.DateTime.DateFormat;
import main.java.ru.clevertec.check.DateTime.TimeFormat;
import main.java.ru.clevertec.check.Model.Check;
import main.java.ru.clevertec.check.Model.DiscountCard;
import main.java.ru.clevertec.check.Model.Item;
import main.java.ru.clevertec.check.Model.Product;
import org.w3c.dom.ls.LSOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static main.java.ru.clevertec.check.Service.ProductService.findProductById;

public class CheckService {
    private final Check check;

    public CheckService(Check check) {
        this.check = check;
    }

    public void print() {
        printOrSave(null);
    }

    public void saveToCsv(String filePath) {
        printOrSave(filePath);
    }

    private void printOrSave(String filePath) {
        List<Item> items = mergeItems(check.getItems());
        Map<Integer, Product> productMap = check.getProducts().stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
        double personalDiscount = getPersonalDiscount(check.getDiscountCardNumber());
        double totalWithDiscount = 0.0;

        try (PrintWriter writer = filePath != null ? new PrintWriter(new FileWriter(filePath)) : new PrintWriter(System.out)) {
            writer.println("Date;Time");
            writer.println(DateFormat.getDate() + ";" + TimeFormat.getTime());
            writer.println("\nQTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL");

            double total = 0.0;
            double totalDiscount = 0.0;

            for (Item item : items) {
                Product product = productMap.get(item.getId());
                if (product != null) {
                    double price = product.getPrice();
                    double subtotal = price * item.getQuantity();
                    double discount = calculateDiscount(item, product, personalDiscount);
                    double totalPrice = subtotal - discount;

                    writer.println(formatLine(item.getQuantity(), product.getDescription(), price, discount, subtotal));

                    total += subtotal;
                    totalDiscount += discount;
                    totalWithDiscount += totalPrice;
                }
            }

            writer.println("\nTOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT");
            writer.println(formatLine(total, totalDiscount, totalWithDiscount));


        } catch (IOException e) {
            LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR","result.csv");
            System.err.println(e.getMessage());
        }
        if (check.getBalanceDebitCard() < totalWithDiscount) {
            LogError.getInstance().logErrorToCsv("NOT ENOUGH MONEY","result.csv");
            System.err.println("\nНедостаточно средств");
            System.exit(1);
        }
    }

    private String formatLine(Object... values) {
        return String.join(";",
                java.util.Arrays.stream(values)
                        .map(value -> value instanceof Double
                                ? String.format(Locale.US, "%.2f$", value)
                                : value.toString())
                        .toArray(String[]::new));
    }

    public double calculateDiscount(Item item, Product product, double personalDiscount) {
        double subtotal = product.getPrice() * item.getQuantity();
        double subtotalWithDiscount = 0.0;
        return product.isWholesale_product() && item.getQuantity() >= 5
                ? subtotal * 0.10
                : subtotal * (personalDiscount / 100);
    }

    public double getPersonalDiscount(String discountCardNumber) {
        if (discountCardNumber == null) return 0.0;
        return check.getDiscountCards().stream()
                .filter(card -> String.valueOf(card.getNumber()).equals(discountCardNumber))
                .mapToDouble(DiscountCard::getDiscount_amount)
                .findFirst()
                .orElse(0.02);
    }

    public List<Item> mergeItems(List<Item> items) {
        return items.stream()
                .collect(Collectors.toMap(Item::getId, item -> new Item(item.getId(), item.getQuantity()),
                        (existing, replacement) -> {
                            existing.setQuantity(existing.getQuantity() + replacement.getQuantity());
                            return existing;
                        }))
                .values().stream().toList();
    }
}