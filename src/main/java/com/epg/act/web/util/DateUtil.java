package com.epg.act.web.util;

import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public enum Pattern {
        FULL_PATTERN("yyyy-MM-dd HH:mm:ss"),
        DATE_PATTERN("yyyy-MM-dd"),
        DATE_PATTERN_NO_JOIN("yyyyMMdd"),
        TIME_PATTERN("HH:mm:ss"),;
        private String pattern;

        Pattern(String pattern) {
            this.pattern = pattern;
        }
    }

    public static Date parseDate(String text, Pattern pattern) {
        DateFormatter formatter = new DateFormatter(pattern.pattern);
        try {
            return formatter.parse(text, Locale.CHINA);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date date, Pattern pattern) {
        DateFormatter formatter = new DateFormatter(pattern.pattern);
        return formatter.print(date, Locale.CHINA);
    }

    public static void main(String[] args) {
        String a = "123733";
        System.out.println(Arrays.stream(a.split("")).distinct().reduce("", String::concat));
    }
}
