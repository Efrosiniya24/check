package main.java.ru.clevertec.check.Model.Builder;

import main.java.ru.clevertec.check.Model.Item;

public class ItemBuilder {
    private int id;
    private int quantity;

    public ItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ItemBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Item build() {
        return new Item(id, quantity);
    }
}