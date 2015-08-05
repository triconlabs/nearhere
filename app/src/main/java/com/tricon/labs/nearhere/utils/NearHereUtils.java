package com.tricon.labs.nearhere.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gautam on 7/29/2015.
 */
public class NearHereUtils {

    public static String getDate(long timestamp, String format) {
        Date date = new Date(timestamp);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format, Locale.getDefault());
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setAmPmStrings(new String[]{"am", "pm"});
        dateFormatter.setDateFormatSymbols(symbols);
        return dateFormatter.format(date);
    }

}
