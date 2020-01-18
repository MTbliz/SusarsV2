package com.susar.service.dateconverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateConverter {

    public void convertToDate(String javaScriptDate){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(javaScriptDate, inputFormatter);
        String formattedDate = outputFormatter.format(date);
        System.out.println(formattedDate); // prints 10-04-2018
    }
}
