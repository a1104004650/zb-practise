package com.zbc.practise.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode {

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

    // 给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。
    // 请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。
    // 如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。
    // 如果 searchWord 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。
    // 如果 searchWord 不是任何单词的前缀，则返回 -1 。
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith(searchWord))
                return i + 1;
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

}
