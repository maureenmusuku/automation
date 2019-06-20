package com.advalent.automation.impl.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getTodaysDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    public static String daysBeforeToday(int noOfDays, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now().minusDays(noOfDays);
        return formatter.format(now);
    }
}
