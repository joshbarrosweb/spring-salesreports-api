package com.joshbarrosweb.springsalesapi.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final String BRAZILIAN_DATE_FORMAT = "dd/MM/yyyy";
    public static final Date DEFAULT_START_DATE;

    static {
        DEFAULT_START_DATE = DateUtils.fromString("01/01/1970", false);
    }

    public static Date fromString(String dateString, boolean atEndOfDay) {
        if(!StringUtils.hasText(dateString)) {
            return null;
        }

        var date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(BRAZILIAN_DATE_FORMAT));
        LocalDateTime dateTime;

        if(atEndOfDay) {
            dateTime = date.atTime(LocalTime.of(23, 59));
        } else {
            dateTime = date.atStartOfDay();
        }

        var instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date today(boolean atEndOfDay) {
        String formattedTodayDate = LocalDate.now().format(DateTimeFormatter.ofPattern(BRAZILIAN_DATE_FORMAT));
        return fromString(formattedTodayDate, atEndOfDay);
    }
}
