package com.epg.act.web.util;

import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class test {
    public static void main(String[] args) {
        DateFormatter formatter = new DateFormatter("yyyyMMdd HH:mm:ss");
        try {
            Date date = formatter.parse("20170309 13:12:22", Locale.CHINA);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
