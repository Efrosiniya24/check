package main.java.ru.clevertec.check.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {
    public static String getTime (){
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss:mm:HH");
        return localTime.format(formatter);
    }
}
