package com.tspoon.googlefinance.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class Utils {

    private static final NumberFormat FORMAT_PERCENT = NumberFormat.getNumberInstance();
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormat.forPattern("yyyy-MM-dd");

    static {
        FORMAT_PERCENT.setMinimumFractionDigits(2);
        FORMAT_PERCENT.setMaximumFractionDigits(2);
    }

    public static double getPercent(double numerator, double denominator) {
        if (denominator == 0) {
            return 0;
        }
        return (numerator / denominator) * 100;
    }

    public static String getPercentString(double numerator, double denominator) {
        if (denominator == 0) {
            return "0.00%";
        }
        return FORMAT_PERCENT.format((numerator / denominator) * 100) + "%";
    }

    public static String toPercent(double percent) {
        return FORMAT_PERCENT.format(percent) + "%";
    }

    public static String toDateString(DateTime date) {
        return FORMAT_DATE.print(date);
    }

    public static DateTime dateFromString(String date) {
        return FORMAT_DATE.parseDateTime(date);
    }

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
