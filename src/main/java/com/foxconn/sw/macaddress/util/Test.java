package com.foxconn.sw.macaddress.util;

public class Test {
    public static void main(String[] args) {
        String hex_num1 = "F4939F000000";
        long dec_num1 = Long.parseLong(hex_num1, 16);
        System.out.println(dec_num1);

        String hex_num2 = "F4939F31FFFF";
        long dec_num2 = Long.parseLong(hex_num2, 16);
        System.out.println(dec_num2);

        String hex_num3 = "FFFFFF";
        long dec_num3 = Long.parseLong(hex_num3, 16);
        System.out.println(dec_num3);

        System.err.println(dec_num1-dec_num2);

        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);

    }
}

