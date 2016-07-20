package com.szilardolah.webshop.szilard.olah.constraints;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class Util {

    private Util() {}
    
    public static long timeInMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }
    
    public static long systemTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }
    
    public static int differenceInYear(Date date) {
        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        cal.setTimeInMillis(timeInMillis(date));
        int birthYear = cal.get(Calendar.YEAR);
        return currYear - birthYear;
    }
    
    public static String generateUuidInString() {
        return UUID.randomUUID().toString();
    }
}
