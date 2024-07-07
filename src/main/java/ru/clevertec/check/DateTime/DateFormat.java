package main.java.ru.clevertec.check.DateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    public static String getDate (){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(formatter);
    }
}
