package main.java.ru.clevertec.check.Model.Builder;

import main.java.ru.clevertec.check.Model.DiscountCard;

public class DiscountCardBuilder {
    private int id;
    private int number;
    private int discountAmount;

    public DiscountCardBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public DiscountCardBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public DiscountCardBuilder setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public DiscountCard build() {
        return new DiscountCard(id, number, discountAmount);
    }
}