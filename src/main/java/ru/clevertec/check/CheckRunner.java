package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.Exception.BalanceDebitCardException;
import main.java.ru.clevertec.check.Exception.NoArgument;
import main.java.ru.clevertec.check.Exception.StartProjectException;
import main.java.ru.clevertec.check.Model.Builder.CheckBuilder;
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
            if (args.length < 3)
                throw new StartProjectException("\n\nНедостаточно аргументов(");

            List<Item> items = new ArrayList<>();
            String discountCardNumber = null;
            double balanceDebitCard = 0;
            String pathToFile = null;
            String saveToFile = null;

            for (String arg : args) {
                if (arg.startsWith("discountCard="))
                    discountCardNumber = parseStringArgument(arg);
                else if (arg.startsWith("balanceDebitCard="))
                    balanceDebitCard = parseBalanceDebitCard(arg);
                else if (arg.startsWith("pathToFile="))
                    pathToFile = parseStringArgument(arg);
                else if (arg.startsWith("saveToFile="))
                    saveToFile = parseStringArgument(arg);
                else
                    items.add(parseItem(arg));
            }

            if (balanceDebitCard == 0)
                throw new BalanceDebitCardException("\nВы не указали сумму на счете(\n");

            if (saveToFile == null) {
                LogError.getInstance().logErrorToCsv("BAD REQUEST","result.csv");
                throw new NoArgument("\nНе указан saveToFile\n");
            }

            if (pathToFile == null) {
                LogError.getInstance().logErrorToCsv("BAD REQUEST",saveToFile);
                throw new NoArgument("\nNo pathToFile\n");
            }

            List<Product> products = CsvReader.readProducts(pathToFile);
            List<DiscountCard> discountCards = CsvReader.readDiscountCards("./src/main/resources/discountCards.csv");

            Check check = new CheckBuilder()
                    .setItems(items)
                    .setProducts(products)
                    .setDiscountCards(discountCards)
                    .setDiscountCardNumber(discountCardNumber)
                    .setBalanceDebitCard(balanceDebitCard)
                    .build();
            CheckService checkService = new CheckService(check);

            checkService.print();
            checkService.saveToCsv(saveToFile);
        } catch (StartProjectException | BalanceDebitCardException e) {
            LogError.getInstance().logErrorToCsv("BAD REQUEST", "result.csv");
            System.err.println(e);
        } catch (NoArgument e) {
            System.err.println(e);
        } catch (IOException e) {
            LogError.getInstance().logErrorToCsv("INTERNAL SERVER ERROR","result.csv");
            throw new RuntimeException(e);
        }
    }
}
