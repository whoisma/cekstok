package com.ma.display.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by MA on 10/01/2018.
 */

public class Helper {

    public static String rupiah(String string){
        double value = Double.parseDouble(string);

        Locale localeID = new Locale("in", "ID");
        NumberFormat curr = NumberFormat.getCurrencyInstance(localeID);

        return curr.format(value);
    }
}
