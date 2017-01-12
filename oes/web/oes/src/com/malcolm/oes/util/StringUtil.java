package com.malcolm.oes.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.malcolm.oes.exception.ParameterException;

public class StringUtil {

    private static final Logger log = Logger.getLogger(StringUtil.class);

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    // This method will revert the string array to the int array.
    public static int[] revertStringArrayToIntegerArray(String strs[]) throws ParameterException, RuntimeException {
        if (strs == null) {
            return null;
        }
        int new_strs[] = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            new_strs[i] = Integer.parseInt(strs[i]);
        }
        return new_strs;
    }

    // This method will revert the special param.
    public static String htmlEncode(String text) {
        if (text != null) {
            text = text.replace("&", "&amp;");
            text = text.replace("\\", "&#92;");
            text = text.replace("'", "&#39;");
            text = text.replace("&amp;amp;", "$amp;");
            text = text.replace("\"", "&quot;");
            text = text.replace("&amp;lt", "&lt");
            text = text.replace("<", "&lt;");
            text = text.replace("&amp;gt", "&gt");
            text = text.replace(">", "&gt;");
            text = text.replace("&amp;nbsp;", "&nbsp;");
            text = text.replace("%", "&#37;");
            text = text.replace("_", "&#95;");
        }
        return text;
    }

    // Parse the str to int And verify that if the str is null or ""
    public static int myParseInt(String str) {
        if (str == null || str.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    // Verify the str's length is in (satrt, end) or not.
    public static boolean inCorrectLength(String str, int start, int end) {
        if (str != null) {
            if (str.length() < start || str.length() > end) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // Revert the Array to String, like ids[] to "1, 2, 3, 4".
    public static String revertArrayToString(int ids[]) {
        if (null == ids || ids.length == 0) {
            return "(0)";
        }
        StringBuilder sb = new StringBuilder();
        int length = ids.length;
        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                sb.append(ids[i]);
                sb.append(", ");
            } else {
                sb.append(ids[i]);
            }
        }
        return sb.toString();
    }

    // Revert the Array to String, like a list<Integer> to "1, 2, 3, 4".
    public static String revertListToString(List<Integer> ids) {
        if (null == ids || ids.size() == 0) {
            return "(0)";
        }
        StringBuilder sb = new StringBuilder();
        int length = ids.size();
        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                sb.append(ids.get(i));
                sb.append(", ");
            } else {
                sb.append(ids.get(i));
            }
        }
        return sb.toString();
    }

    // The method will judge the number is number or not.
    public static boolean isNumber(String str) {
        return str.matches("[0-9]+");
    }

    // The method will judge the str is in ("ASC", "DESC") or not.
    public static String isOrder(String str) {
        if (str.equals("ASC") || str.equals("DESC")) {
            return str;
        } else {
            return "ASC";
        }
    }

    // This is a parameter encode filter.
    public static void obecjtParamFilter(Object o) {
        Field field[] = o.getClass().getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            String methodName = field[i].getName();
            methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            String methodType = field[i].getGenericType().toString();
            if (methodType.equals("class java.lang.String")) {
                try {
                    Method getMethod = o.getClass().getMethod("get" + methodName);
                    Method setMethod = o.getClass().getMethod("set" + methodName, String.class);
                    String getmethodValue = (String) getMethod.invoke(o);
                    setMethod.invoke(o, StringUtil.htmlEncode(getmethodValue));
                } catch (Exception e) {
                    log.error("obecjtParamFilter : [Exception] : " + e.getMessage());
                }
            }
        }
    }

    public static Date revertStringToDate(String str) {
        // month(M) day(d) 12hour(h) 24hour(H) minute(m) second(s) week(E) quarter(q)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            log.error("revertStringToDate : [ParseException] : " + e.getMessage());
        }
        return date;
    }

}