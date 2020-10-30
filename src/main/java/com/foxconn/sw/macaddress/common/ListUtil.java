package com.foxconn.sw.macaddress.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtil {
    /**
     * 计算数组的前n项和
     *
     * @param a
     * @param n
     * @return
     */
    public static int sumArray(Integer a[], int n) {
        return (n < 1) ? 0 : sumArray(a, n - 1) + a[n - 1];
    }

    /**
     * 计算集合的前n项和
     *
     * @param a
     * @param n
     * @return
     */
    public static int sumList(List<Integer> a, int n) {
        return (n < 1) ? 0 : sumList(a, n - 1) + a.get(n - 1);
    }

    /**
     * 向集合中添加某个数，判断该数在该排序后的集合中的索引
     *
     * @param list 原集合
     * @param n    添加的数
     * @return
     */
    public static int index(List<Integer> list, int n) {
        list.add(n);
        Collections.sort(list);
        //list = [1, 30, 193, 1048769, 4048769, 7048769, 8825984]
        System.out.println("list = " + list);
        //list.indexOf(n) = 1
        System.out.println("list.indexOf(n) = " + list.indexOf(n));
        return list.indexOf(n);
    }

    /**
     * id放入list
     *
     * @param id
     *            id(多个已逗号分隔)
     * @return List集合
     */
    public static List<String> getList(String id) {
        List<String> list = new ArrayList<String>();
        String[] str = id.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return list;
    }
}
