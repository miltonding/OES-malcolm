package com.malcolm.oes.util;

public class Test {
    static int x(int x) {
        x++;
        return 1;
    }

    static String xy(Object str) {
        return str + "";
    }

    public static void main(String[] args) {
        System.out.println("/kogin/test".substring("/kogin".length() + 1));
        System.out.println(xy(null));
        System.out.println("oes/login.action".substring("oes/".length()));
    }
}
