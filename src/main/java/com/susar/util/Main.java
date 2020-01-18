package com.susar.util;

import com.susar.service.dateconverter.DateConverter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DateConverter dateConverter = new DateConverter();
        dateConverter.convertToDate("2019-11-12T23:00:00.000Z");
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeZone dateTimeZone = DateTimeZone.getDefault();
        DateTime dateTime = DateTime.now();
        System.out.println();
        System.out.println(currentDateAndTime);
        System.out.println(dateTime);
        System.out.println(dateTimeZone);
    }
}
