package com.attractpay.admin.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DateTimeUtils {

    private static SimpleDateFormat sdf;

    /** @Description Parse time from date to offset time
    * @author Junji
    * @date 2019/10/30 15:20
    * @param date
    * @return java.lang.String
    * @exception
    */
    public static String parseOffsetTime(Date date) {
        OffsetDateTime dateTime = OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return dateTime.withNano(0).toString();
    }

    /** @Description set Timezone to CCT
    * @author Junji
    * @date 2019/10/30 15:27
    * @param dateStr
    * @return java.util.Date
    * @exception
    */

    public static Date parseToCCT(String dateStr) {
        OffsetDateTime dateTime = OffsetDateTime.parse(dateStr);
        Date date = Date.from(dateTime.toInstant());
        return date;
    }


}
