package main.java.ru.clevertec.check.Service;

import java.io.IOException;
import java.io.PrintWriter;

public class LogError {
    private static final LogError INSTANCE = new LogError();
    private static final String ERROR_FILE = "ERROR.csv";

    private LogError() {}

    public static LogError getInstance() {
        return INSTANCE;
    }

    public void logErrorToCsv(String errorMessage) {
        try (PrintWriter writer = new PrintWriter(ERROR_FILE)) {
            writer.println("ERROR");
            writer.println(errorMessage);
        } catch (IOException e) {
            System.out.println("Не удалось записать ошибку в " + ERROR_FILE + ": " + e.getMessage());
        }
    }
}
