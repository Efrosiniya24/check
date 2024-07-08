package main.java.ru.clevertec.check.Model.Builder;

import main.java.ru.clevertec.check.Model.Check;
import main.java.ru.clevertec.check.Model.DiscountCard;
import main.java.ru.clevertec.check.Model.Item;
import main.java.ru.clevertec.check.Model.Product;

import java.util.List;

public class CheckBuilder {
    private List<Item> items;
    private List<Product> products;
    private List<DiscountCard> discountCards;
    private String discountCardNumber;
    private double balanceDebitCard;

    public CheckBuilder setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public CheckBuilder setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public CheckBuilder setDiscountCards(List<DiscountCard> discountCards) {
        this.discountCards = discountCards;
        return this;
    }

    public CheckBuilder setDiscountCardNumber(String discountCardNumber) {
        this.discountCardNumber = discountCardNumber;
        return this;
    }

    public CheckBuilder setBalanceDebitCard(double balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
        return this;
    }

    public Check build() {
        return new Check(items, products, discountCards, discountCardNumber, balanceDebitCard);
    }
}
