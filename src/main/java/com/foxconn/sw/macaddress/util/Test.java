package com.foxconn.sw.macaddress.util;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        //hex_num2-hex_num1的最大值为1677,7216
        String hex_num1 = "BC9830686430";
        long dec_num1 = Long.parseLong(hex_num1, 16);
//        System.out.println(dec_num1);

        String hex_num2 = "BC98306864FF";
        long dec_num2 = Long.parseLong(hex_num2, 16);
//        System.out.println(dec_num2);

//        System.err.println(dec_num2 - dec_num1 + 1);

//        String hex_num3 = "FFFFFF";
//        long dec_num3 = Long.parseLong(hex_num3, 16);
//        System.out.println(dec_num3);

//        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
//        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);

        int[] a = {1, 2, 5, 4, 8, 66, 7, 11, 12};
//        System.out.println(sum(a, 4));

        List list = new ArrayList();
        List list2 = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(8);
        list.add(66);
        list.add(7);
        list.add(11);
        list.add(12);
        System.out.println("list ============= " + sum2(list, list.size()));

        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {

            map.put(i + 1, sum2(list, i + 1));
            list2.add(sum2(list, i + 1));
        }
        System.out.println("list2 = " + list2);
        index(list2, 113);
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

