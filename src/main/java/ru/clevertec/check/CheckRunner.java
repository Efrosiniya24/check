package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.Exception.BalanceDebitCardException;
import main.java.ru.clevertec.check.Exception.StartProject;
import main.java.ru.clevertec.check.Model.Check;
import main.java.ru.clevertec.check.Model.DiscountCard;
import main.java.ru.clevertec.check.Model.Item;
import main.java.ru.clevertec.check.Model.Product;
import main.java.ru.clevertec.check.Service.CheckService;
import main.java.ru.clevertec.check.Service.CsvReader;
import main.java.ru.clevertec.check.Service.LogError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.ru.clevertec.check.Service.Parser.*;

public class CheckRunner {
    public static void main(String[] args) {
        try {
            if (args.length < 2)
                throw new StartProject("\n\nНедостаточно аргументов( \nУкажите id и balanceDebitCard и, если имеется,discountCard\n");

            List<Product> products = CsvReader.readProducts("./src/main/resources/products.csv");
            List<DiscountCard> discountCards = CsvReader.readDiscountCards("./src/main/resources/discountCards.csv");
            List<Item> items = new ArrayList<>();
            String discountCardNumber = null;
            double balanceDebitCard = 0;

            for (String arg : args) {
                if (arg.startsWith("discountCard="))
                    discountCardNumber = parseDiscountCard(arg);
                else if (arg.startsWith("balanceDebitCard="))
                    balanceDebitCard = parseBalanceDebitCard(arg);
                else
                    items.add(parseItem(arg));
            }

            if (balanceDebitCard == 0)
                throw new BalanceDebitCardException("\nВы не указали сумму на счете(\n");

            CheckService checkService = new CheckService(new Check(items, products, discountCards, discountCardNumber, balanceDebitCard));

            checkService.print();
            checkService.saveToCsv("result.csv");
        } catch (StartProject | BalanceDebitCardException e) {
            LogError.getInstance().logErrorToCsv("BAD REQUEST");
            System.err.println(e);
        } catch (IOException e) {
            LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR");
            throw new RuntimeException(e);
        }
    }
}
