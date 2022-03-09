package com.zbc.practise.pattern.strategy;

/**
 * @author StormT1King
 * 选择排序 策略模式
 * Comparable接口，拥有compareTo方法所有需要排序的都去实现该方法
 */
public class Sorter2 {

    public static void sort(Comparable[] arr) {
        for(int i=0; i<arr.length - 1; i++) {
            int min = i;

            for(int j=i+1; j<arr.length; j++) {
                min = arr[j].compareTo(arr[min]) == -1 ? j : min;
            }

            swap(arr, i ,min);
        }
    }

    public static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
