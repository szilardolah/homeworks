package coma.szilardolah.webshop.szilard.olah.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class Util {

    private Util() {}
    
    public static int differenceInYear(Date date) {
        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        cal.setTimeInMillis(date.getTime());
        int birthYear = cal.get(Calendar.YEAR);
        return currYear - birthYear;
    }
    
    public static String generateUuidInString() {
        return UUID.randomUUID().toString();
    }
}
