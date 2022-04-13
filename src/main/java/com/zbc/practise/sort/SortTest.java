package com.zbc.practise.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * 各种排序
 * @author StormT1King
 */
public class SortTest {

    public static void main(String[] args) {
        int[] num = { 9 , 2 , 3 , 1 , 6 , 7 , 13 , 4 , 14 , 10 };
        // 冒泡排序
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(num);
        printInts(num);

        num = new int[]{9, 2, 3, 1, 6, 7, 13, 4, 14, 10};
        // 选择排序
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(num);
        printInts(num);

        num = new int[]{9, 2, 3, 1, 6, 7, 13, 4, 14, 10};
        // 选择排序
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(num);
        printInts(num);

        num = new int[]{9, 2, 3, 1, 6, 7, 13, 4, 14, 10};
        // 选择排序
        QuickSort quickSort = new QuickSort();
        quickSort.sort(num, 0 , num.length - 1);
        printInts(num);

    }

    /**
     * 快速排序
     * 选择一个轴，分别分为左侧和右侧，左侧比轴小，右侧比轴大。 左侧继续选轴，
     * 两边同时移动，如果发现比新轴大的话交换位置。直到左右到一个指针相撞
     *  例如：  9, 2, 3, 1, 6, 7, 13, 4, 14, 10
     *  假如 7是轴，那么left 就是 9 right 6
     *  left一直往右边找，直到比轴大的
     *  right一直往左边找 直到比轴小的
     *  如果此时left比right小，则换位置
     */
    static class QuickSort {
        void sort(int[] num, int leftBound, int rightBound) {
            // 说明数组就一个元素，就不需要移动直接返回
            if(leftBound >= rightBound) {
                return;
            }
            int mid = partition(num , leftBound , rightBound);
            sort(num , leftBound, mid - 1);
            sort(num , mid + 1, rightBound);
        }

        // 分区 pivot轴 left左边分区开始位置 right左边分区最后一个元素（不能是轴本身）。
        // 返回轴的位置递归继续分区
        int partition(int[] arr, int leftBound, int rightBound) {
            int pivot = arr[rightBound];
            int left = leftBound;
            int right = rightBound - 1;

            // 不写 = 会少一次交换
            while(left <= right) {
                // left < right 极端情况需要把所有测放到轴的一边
                while(left <= right && arr[left] <= pivot) {
                    left++;
                }

                while(left <= right && arr[right] > pivot) {
                    right--;
                }

                System.out.println("left ->" + left + "right -> " + right);

                if(left < right) {
                    swap(arr, left, right);
                }
            }

            swap(arr , left, rightBound); // 轴和指针碰撞的位置交换位置
            return left;
        }

        void swap(int[] num, int i, int j) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    /**
     * 插入排序
     * 保障每一次交换前面保持顺序
     * 从 下标1 开始 与下标0比较 找到最小的。
     * 第二波开始从下标2开始 下标2 与下标1比较，小的换到前面去，然后继续从下标1 与 下标0进行比较 小的换到前面去 以此类推
     */
    static class InsertionSort {
        void sort(int[] num) {
            for (int i = 1 ; i < num.length ; i++ ) {
                for (int j = i ; j > 0 ; j --) {
                    if(num[j] < num[j - 1]) {
                        swap(num, j , j - 1);
                    }
                }
            }
        }

        void swap(int[] num, int i, int j) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    /**
     * 选择排序
     * 先循环找到最小的数值 然后跟第一个交换
     * 依次类推
     */
    static class SelectionSort{
        void sort(int[] num) {
            for (int i = 0 ; i < num.length - 1 ; i ++) {
                int index = i;
                for (int j = i + 1; j < num.length; j++) {
                    if (num[j] < num[index]) {
                        index = j;
                    }
                }
                swap(num , i, index);
            }

        }

        void swap(int[] num, int i, int j) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    /**
     * 冒泡排序
     * 冒泡排序会将最大的冒泡到数组最后面
     * 所以我这边外层循环使用数组长度 - 1的下标开始循环。
     * 每一次循环找到大的数值，然后依次交换这样就可以得到最大的数值
     */
    static class BubbleSort{
        void sort(int[] num) {
            for (int i = num.length - 1 ; i > 0 ; i--) {
                findMax(num , i);
            }
        }

        // num整个数组
        // i 哪个下标之前最大的。
        void findMax(int[] num, int i) {
            for(int j = 0 ; j < i ; j++) {
                if(num[j] > num[j + 1]) {
                    swap(num, j, j+1);
                }
            }
        }

        void swap(int[] num, int i, int j) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }


    public static void printInts(int[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println("");
    }


}
