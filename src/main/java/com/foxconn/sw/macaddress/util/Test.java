package com.foxconn.sw.macaddress.util;

public class Test {
    public static void main(String[] args) {
        //hex_num2-hex_num1的最大值为1677,7216
        String hex_num1 = "00016C06A625";
        long dec_num1 = Long.parseLong(hex_num1, 16);
        System.out.println(dec_num1);

        String hex_num2 = "00016C06A629";
        long dec_num2 = Long.parseLong(hex_num2, 16);
        System.out.println(dec_num2);

        System.err.println(dec_num2-dec_num1+1);

//        String hex_num3 = "FFFFFF";
//        long dec_num3 = Long.parseLong(hex_num3, 16);
//        System.out.println(dec_num3);



//        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
//        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);



    }


}

