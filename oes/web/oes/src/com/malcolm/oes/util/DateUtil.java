package com.malcolm.oes.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getRecentDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String today = sdf.format(date);
        return today.split(" ")[0];
    }
}
