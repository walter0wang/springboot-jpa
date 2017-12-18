package com.epg.act.web.util;

import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public enum Pattern {
        FULL_PATTERN("yyyy-MM-dd HH:mm:ss");
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
}
