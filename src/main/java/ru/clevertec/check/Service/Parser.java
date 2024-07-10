package main.java.ru.clevertec.check.Service;

import main.java.ru.clevertec.check.Model.Item;

public class Parser {
    public static Item parseItem(String arg) {
        try {
            String[] parts = arg.split("-");
            if (parts.length != 2) {
                LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR", "result.csv");
                throw new IllegalArgumentException("Некорректный формат аргумента: " + arg);
            }

            int id = Integer.parseInt(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            return new Item(id, quantity);
        } catch (NumberFormatException e) {
            LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR", "result.csv");
            throw new IllegalArgumentException("Ошибка разбора числа в аргументе: " + arg);
        }
    }

    public static String parseStringArgument(String arg) {
        String[] parts = arg.split("=");
        return parts.length > 1 ? parts[1] : null;
    }

    public static double parseBalanceDebitCard(String arg) {
        return Double.parseDouble(arg.split("=")[1]);
    }
}
