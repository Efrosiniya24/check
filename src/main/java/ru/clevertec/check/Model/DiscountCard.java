
package main.java.ru.clevertec.check.Model;

public class DiscountCard {
    private int id;
    private int number;
    private int discount_amount;

    public DiscountCard(int id, int number, int discount_amount) {
        this.id = id;
        this.number = number;
        this.discount_amount = discount_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(int discount_amount) {
        this.discount_amount = discount_amount;
    }
}
