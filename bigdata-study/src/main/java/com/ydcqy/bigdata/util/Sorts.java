package com.ydcqy.bigdata.util;

import java.util.Arrays;

/**
 * @author xiaoyu
 */
public class Sorts {
    public static int[] bubbleSort(int[] originalArray) {
        int length = originalArray.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (originalArray[j] > originalArray[j + 1]) {
                    int tempValue = originalArray[j];
                    originalArray[j] = originalArray[j + 1];
                    originalArray[j + 1] = tempValue;
                }
            }
        }
        return originalArray;
    }

    public static int[] quickSort(int[] originalArray) {
        return null;
    }


    public static void main(String[] args) {
        long ss;
        int[] i = new int[]{3, 4, 7, 1, 3, 8, 2, 6};
        ss = System.currentTimeMillis();
        System.out.println("耗时: " + (System.currentTimeMillis() - ss) + "ms, " + Arrays.toString(bubbleSort(i)));
    }
}
