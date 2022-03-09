package com.zbc.practise.pattern.strategy;

/**
 * @author StormT1King
 * 选择排序
 */
public class Sorter {

    public static void sort(int[] arr) {
        for(int i=0; i<arr.length - 1; i++) {
            int min = i;

            for(int j=i+1; j<arr.length; j++) {
                min = arr[j] < arr[min] ? j : min;
            }

            swap(arr, i ,min);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
