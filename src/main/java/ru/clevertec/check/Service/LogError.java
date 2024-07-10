package main.java.ru.clevertec.check.Service;

import java.io.IOException;
import java.io.PrintWriter;

public class LogError {
    private static LogError INSTANCE = new LogError();
    private static final String DEFAULT_ERROR_FILE = "result.csv";

    private LogError() {}

    public static LogError getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogError();
        }
        return INSTANCE;
    }

    public void logErrorToCsv(String errorMessage, String filePath) {
        logError(filePath != null ? filePath : DEFAULT_ERROR_FILE, errorMessage);
    }

    private void logError(String filePath, String errorMessage) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("ERROR");
            writer.println(errorMessage);
        } catch (IOException e) {
            System.out.println("Не удалось записать ошибку в " + filePath + ": " + e.getMessage());
        }
    }
}
