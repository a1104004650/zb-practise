package com.zbc.practise.leetcode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Leetcode<main> {

    // leetCode 第一题
    // 算出数组中任意2个组合成为target
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    // leetCode 第一题
    // 算出数组中任意2个组合成为target
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = target - nums[i];
            if(map.containsKey(num)) {
                return new int[] {i ,map.get(num)};
            }
            map.put(nums[i], i);
        }
        return null;
    }


    /**
     * Definition for singly-linked list.
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
     public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null;
            int carry = 0;
            while (l1 != null || l2 != null) {
                int n1 = l1 != null ? l1.val : 0;
                int n2 = l2 != null ? l2.val : 0;
                int sum = n1 + n2 + carry;
                if (head == null) {
                    head = tail = new ListNode(sum % 10);
                } else {
                    tail.next = new ListNode(sum % 10);
                    tail = tail.next;
                }
                carry = sum / 10;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (carry > 0) {
                tail.next = new ListNode(carry);
            }
            return head;
        }
    }

    // leetCode 第三题
    public static int lengthOfLongestSubstring(String s) {
        if("".equals(s)) {
            return 0;
        }
        char[] charArray = s.toCharArray();
        int maxNum = 0;
        int nowNum = 0;
        StringBuilder str = new StringBuilder();
        for (char c : charArray) {
            if(str.indexOf(String.valueOf(c)) == -1) {
                str.append(c);
                nowNum++;
                continue;
            }
            str.delete(0, str.length());
            str.append(c);
            maxNum = nowNum > maxNum ? nowNum :maxNum;
            nowNum = 1;
        }
        maxNum = nowNum > maxNum ? nowNum :maxNum;
        return maxNum;
    }

    /**
     * leetcode 504题 七进制数
     */
    public static String convertToBase7(int num) {
       return Integer.toString(num, 7);
    }

    // 如果堆中有 4 块石头，那么你永远不会赢得比赛；
    // 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }

    // 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void findStr(){
        String[] names = { "张三", "张三", "李四", "李四", "李四", "李四", "王五", "A", "B", "C", "C" };
        Map<String, Long> finalMap = Stream.of(names)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (m1, m2) -> m2, LinkedHashMap::new));
        System.out.println(finalMap);
    }


    /** 给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。
      * 请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。
      * 如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。
      * 如果 searchWord 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。
      * 如果 searchWord 不是任何单词的前缀，则返回 -1 。
      */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    // 给你字符串 s 和整数 k 。
    // 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
    // 英文中的 元音字母 为（a, e, i, o, u）。

    // 先算第一个字符串的值
    // 右移一位如果被移走的一位是原音则 -1 如果增加的那个是原音则 + 1 每次取最大的
    public static int maxVowels(String s, int k) {
        int maxNum = 0;
        char[] array = s.substring(0, k).toCharArray();
        char[] sArray = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'a' || array[i] == 'e' || array[i] == 'i' || array[i] == 'o' || array[i] == 'u') {
                maxNum++;
            }
        }

        int maxNow = maxNum;
        for (int i = 0; i < sArray.length - k; i++) {
            if (sArray[i] == 'a' || sArray[i] == 'e' || sArray[i] == 'i' || sArray[i] == 'o' || sArray[i] == 'u') {
                maxNow--;
            }

            if (sArray[i + k] == 'a' || sArray[i + k] == 'e' || sArray[i + k] == 'i' || sArray[i + k] == 'o'
                    || sArray[i + k] == 'u') {
                maxNow++;
            }

            maxNum = maxNow > maxNum ? maxNow : maxNum;
        }
        return maxNum;
    }

    /** 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *  算法的时间复杂度应该为 O(log (m+n)) 。
     *  4. 寻找两个正序数组的中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ints = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).toArray();
        Arrays.sort(ints);
        if(ints.length % 2 == 0){
            return new BigDecimal(ints[ints.length / 2 - 1]).add(new BigDecimal(ints[(ints.length / 2)])).divide(new BigDecimal(2)).doubleValue();
        }else{
            return ints[ints.length / 2];
        }
    }

    /** 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *  如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     *   假设环境不允许存储 64 位整数（有符号或无符号）。
     *   7.整数反转
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

    /** 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
     *  leetcode 1523. 在区间范围内统计奇数数目
     */
    public int countOdds(int low, int high) {
        return pre(high) - pre(low - 1);
    }

    public int pre(int x) {
        return (x + 1) >> 1;
    }

    /**
     * 给定n个字符串，求每个字符串出现的次数并且按出现次数从大到小排序输出
     */
    public static void groupStr() {
        // Arrays的 stream方法
        String[] strs = new String[] {"12", "add", "ccc", "ffff", "12", "ss", "ff", "add", "add", "f"};
        Map<String, Long> collect = Arrays.stream(strs)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }


    public static void main(String[] args) {
        groupStr();
    }

}
