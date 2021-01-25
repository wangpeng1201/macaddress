package com.foxconn.sw.macaddress.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //hex_num2-hex_num1的最大值为1677,7216
        String hex_num1 = "00016C06A625";
        long dec_num1 = Long.parseLong(hex_num1, 16);
        String tmp = StringUtils.leftPad(Long.toHexString(6107342373L).toUpperCase(), 12, '0');
        System.out.println(dec_num1);
        System.out.println("tmp = " + tmp);
        Integer amount = 3;
        long l = Long.parseLong(hex_num1, 16);
        System.out.println("l = " + l);

        Long avalue = l + amount - 1;
//        System.out.println("avalue = " + avalue);
//
//        String hex_num2 = "300ED5EC9EC5";
//        long dec_num2 = Long.parseLong(hex_num2, 16);
//        System.out.println(dec_num2);

//        System.err.println(dec_num2 - dec_num1 + 1);

//        System.out.println("Long.toHexString(dec_num2-10) = " + Long.toHexString(dec_num2-9).toUpperCase());

//        String hex_num3 = "FFFFFF";
//        long dec_num3 = Long.parseLong(hex_num3, 16);
//        System.out.println(dec_num3);

//        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
//        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);

        int[] a = {1, 2, 5, 4, 8, 66, 7, 11, 12};
//        System.out.println(sum(a, 4));

//        List list = new ArrayList();
//        List list2 = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(5);
//        list.add(4);
//        list.add(8);
//        list.add(66);
//        list.add(7);
//        list.add(11);
//        list.add(12);
//        System.out.println("list ============= " + sum2(list, list.size()));
//
//        Map<Integer, Integer> map = new TreeMap<>();
//        for (int i = 0; i < list.size(); i++) {
//
//            map.put(i + 1, sum2(list, i + 1));
//            list2.add(sum2(list, i + 1));
//        }
//        System.out.println("list2 = " + list2);
//        index(list2, 113);
    }

    public static int sum(int a[], int n) {
        return (n < 1) ? 0 : sum(a, n - 1) + a[n - 1];
    }

    public static int sum2(List<Integer> a, int n) {
        return (n < 1) ? 0 : sum2(a, n - 1) + a.get(n - 1);
    }

    public static int index(List<Integer> list, int n) {
        list.add(n);
        Collections.sort(list);
        System.out.println("list = " + list);
        System.out.println("list.indexOf(n) = " + list.indexOf(n));
        return list.indexOf(n);
    }
}

