package com.luhan.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h2>排序工具类</h2>
 *
 * @author luHan
 * @create 2020/4/22 10:16
 * @since 1.0.0
 */
public class SortUtil {
    private SortUtil(){}

    /**
     * 按照自然语言顺序排序
     * @param list 待排序的集合
     */
    public static void naturalSort(List<String> list) {
        Collections.sort(list, (o1, o2) -> {
            if(o1.equals(o2)){
                return 0;
            }
            int i = 0, j = 0;
            String temp1, temp2;
            int num1, num2;
            int length = Math.min(o1.length(), o2.length());
            while (i < length && j < length) {
                temp1 = "";
                temp2 = "";
                if (o1.charAt(i) > '9' || o1.charAt(i) < '0' || o2.charAt(j) > '9' || o2.charAt(j) < '0') {
                    if (o1.charAt(i) == o2.charAt(j)) {
                        i++;
                        j++;
                        continue;
                    } else if (o1.charAt(i) > o2.charAt(j)) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                while (i < o1.length() && o1.charAt(i) <= '9' && o1.charAt(i) >= '0') {
                    temp1 += o1.charAt(i);
                    i++;
                }
                while (j < o2.length() && o2.charAt(j) <= '9' && o2.charAt(j) >= '0') {
                    temp2 += o2.charAt(j);
                    j++;
                }
                num1 = atoi(temp1);
                num2 = atoi(temp2);
                if (num1 == num2) {
                    if (temp1.length() < temp2.length()) {
                        return 1;
                    } else if (temp1.length() > temp2.length()) {
                        return -1;
                    } else {
                        continue;
                    }
                } else if (num1 > num2) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.length() > o2.length() ? 1 : -1;
        });
    }

    public static int atoi(String str) {
        //这里要小心，需要判断有效性
        if (str == null || str.length() == 0) {
            return 0;
        }
        int nlen = str.length();
        double sum = 0;
        int sign = 1;
        int j = 0;
        //剔除空格
        while (str.charAt(j) == ' ') {
            j++;
        }
        //判断正数和负数
        if (str.charAt(j) == '+') {
            sign = 1;
            j++;
        } else if (str.charAt(j) == '-') {
            sign = -1;
            j++;
        }

        for (int i = j; i < nlen; i++) {
            char current = str.charAt(i);
            if (current >= '0' && current <= '9') {
                sum = sum * 10 + (int) (current - '0');
            } else {
                break;//碰到非数字，退出转换
            }
        }
        sum = sum * sign;
        //这里要小心，需要判断范围
        if (sum > Integer.MAX_VALUE) {
            sum = Integer.MAX_VALUE;
        } else if (sum < Integer.MIN_VALUE) {
            sum = Integer.MIN_VALUE;
        }
        return (int) sum;
    }
}
