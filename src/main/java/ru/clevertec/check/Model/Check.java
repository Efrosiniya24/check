package main.java.ru.clevertec.check.Model;

import java.util.List;

public class Check {
    private List<Item> items;
    private List<Product> products;
    private List<DiscountCard> discountCards;
    private String discountCardNumber;
    private double balanceDebitCard;

    public Check(List<Item> items, List<Product> products, List<DiscountCard> discountCards, String discountCardNumber, double balanceDebitCard) {
        this.items = items;
        this.products = products;
        this.discountCards = discountCards;
        this.discountCardNumber = discountCardNumber;
        this.balanceDebitCard = balanceDebitCard;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    public void setDiscountCards(List<DiscountCard> discountCards) {
        this.discountCards = discountCards;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public void setDiscountCardNumber(String discountCardNumber) {
        this.discountCardNumber = discountCardNumber;
    }

    public double getBalanceDebitCard() {
        return balanceDebitCard;
    }

    public void setBalanceDebitCard(double balanceDebitCard) {
        this.balanceDebitCard = balanceDebitCard;
    }

    public DiscountCard findDiscountCardByNumber(String cardNumber) {
        return discountCards.stream()
                .filter(card -> Integer.toString(card.getNumber()).equals(cardNumber))
                .findFirst()
                .orElse(null);
    }
}
