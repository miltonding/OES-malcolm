package com.malcolm.oes.util;

import com.malcolm.oes.exception.ParameterException;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static int[] revertToInteger(String strs[]) throws ParameterException {
        if (strs == null) {
            return null;
        }
        int new_strs[] = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            new_strs[i] = Integer.parseInt(strs[i]);
        }
        return new_strs;
    }

    public static String HtmlEncode(String text) {
        if (text != null) {
            text = replace(text, "&", "&mp");
            text = replace(text, "&amp;amp;", "$amp;");
            text = replace(text, "\"", "&quot");
            text = replace(text, "&amp;lt", "&lt");
            text = replace(text, "<", "&lt;");
            text = replace(text, "&amp;gt", "&gt");
            text = replace(text, ">", "&gt;");
            text = replace(text, "&amp;nbsp;", "&nbsp");
        }
        return text;
    }

    public static String replace(String str, String oldStr, String newStr) {
        return str.replace(oldStr, newStr);
    }

    // Parse the str to int and verify that if the str is null or ""
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
            }
        }
        return true;
    }

    // Verify if the str is null and the str's length in (satrt, end) or not.
    public static boolean verifyString(String str, int start, int end) {
        if (isEmpty(str) || !inCorrectLength(str, start, end)) {
            return false;
        }
        return true;
    }

    // Revert the Array to String, like ids[] to ('1,2,3,4').
    public static String revertArrayToString(int ids[]) {
        if (ids == null) {
            return "()";
        }
        String str = "(";
        for (int i = 0; i < ids.length; i++) {
            if (i < ids.length - 1) {
                str += ids[i] + ",";
            } else {
                str += ids[i] + ")";
            }
        }
        return str;
    }
}
