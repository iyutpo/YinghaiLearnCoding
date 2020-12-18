/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

Example 1:
    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], 
                therefore the length is 4.

Example 2:
    Input: nums = [0,1,0,3,2,3]
    Output: 4

Example 3:
    Input: nums = [7,7,7,7,7,7,7]
    Output: 1
*/

import java.util.Arrays;
public class Solution1 {
    // The first solution is Brute Force.
    // 最直观的方法当然是遍历后，返回最长上升子序列。我们可以定义一个recursive function（lengthofLIS)
    // 然后让该function返回可能的 最长上升子序列 的长度。每次调用该function时，我们要考虑两件事：
    // 1. 当前元素大于前一个元素。此时，我们将当前元素放到 最长上升子序列 中。
    // 2. 当前元素小于前一个元素。此时，我们不能将当前元素放到 最长上升子序列 中
    // Time: O(n^2), Space: O(n^2)
    public int LongestIncreasingSubsequence(int[] nums){
        return LongestIncreasingSubsequence(nums, Integer.MIN_VALUE, 0);
    }

    public int LongestIncreasingSubsequence(int[] nums, int prev, int curr){
        if (curr == nums.length)
            return 0;
        int taken = 0;
        if (nums[curr] > prev)
            taken = 1 + LongestIncreasingSubsequence(nums, nums[curr], curr+1);
        int nottaken = LongestIncreasingSubsequence(nums, prev, curr+1);
        return Math.max(taken, nottaken);
    }
}


class Solution2{
    // 在第一个方法中，有些参数在递归的过程中被反复调用。这种redundancy可以通过一定的方法来消除
    // 在该方法中，我们加入一个2-d memoization数组memo。memo[i][j]表示LIS的长度。
    // nums[i]表示前一个元素是否该加入LIS。nums[j]表示当前元素是否该加入LIS
    // Time: O(n^2), Space: O(n^2)
    public int LongestIncreasingSubsequence(int[] nums){
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] i : memo)
            return Arrays.fill(1, -1);
    }

    public int LongestIncreasingSubsequence(int[] nums, int prevIndex, int curr, int[] memo){
        if (curr == nums.length)
            return 0;
        if (memo[prevIndex + 1][curr] >= 0)
            return memo[prevIndex + 1][curr];
        int taken = 0;
        if (prevIndex < 0 || nums[curr] > nums[prevIndex])
            taken = 1 + LongestIncreasingSubsequence(nums, prevIndex, curr+1, memo);
        int nottaken = LongestIncreasingSubsequence(nums, prevIndex, curr+1, memo);
        memo[prevIndex+1][curr] = Math.max(taken, nottaken);
        return memo[prevIndex+1][curr];
    }
}


class Solution3{
    // 该方法取决于 “给定数组中 最长上升子序列 到第i个index元素 与之后的元素无关” 的事实。
    // 因此，如果我们知道了LIS的第i个index，就能通过第（i+1）个元素，找到LIS。
    // 利用dp矩阵，dp[i]表示到第i个元素为止的 最长上升子序列 的长度。为了找到dp[i]，需要将当前的元素nums[i]
    // append到dp中，并使得新增的 子序列 也是 上升子序列。因此我们很容易发现一个规律：
    // 对于所有的 0 ≤ j < i 且 nums[i] > nums[j]，有dp[i] = max(dp[j]) + 1
    // 在最后，所有dp[i]的最大值就是 要找的 最长上升子序列的长度了。
    // 即，LIS_length = max(dp[i]), ∀ 0 ≤ i < n
    // Time: O(n^2), Space: O(n)
    public int LongestIncreasingSubsequence(int[] nums){
        if (nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++){
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}


class Solution4{
    // 该方法中，我们从左到右scan整个数组。首先将dp数组的初始值设置为0。该dp数组用于存放 添加当前元素nums[i]后的 上升子序列
    // 当遍历nums数组时，我们将不断将当前为止遇到的 元素 添加到dp中。
    // nums中的第j个元素nums[j]，我们可以通过binary search来确定该元素nums[j]在dp中的位置。
    // 与此同时将nums[j]插入到正确的位置。需要注意的一点是，对于binary search，我们只需要考虑 我们需要进行更新插入的dp数组的那一部分
    // 由于我们每次插入时都确保了正确的插入位置，所以插入后形成的数组也是升序的。
    // 只要当前index i与当前 最长上升子序列的长度相等，就以位置我们需要将 其长度 len更新为 len+1
    // 另外要注意， dp 数组并不会生成最长上升子序列，但dp的长度却等于最长上升子序列的长度。
    // 请看下面例子：
    // input = [0, 8, 4, 12, 2]
    // dp: [0]
    // dp: [0, 8]
    // dp: [0, 4]
    // dp: [0, 4, 12]
    // dp: [0, 2, 12]  -->  该数组就是最后的dp。但并不是最长上升子序列（[0, 4, 12] or [0, 8, 12])
    // 但是它们的长度是相等的。
    // Time: O(n logn), Space: O(n)
    public int LongestIncreasingSubsequence(int[] nums){
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num: nums){
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0)
                i = -(i + 1);
            dp[i] = num;
            if (i == len)
                len++;
        }
        return len;
    }
}


class Main {
    public static void main(String[] args){
        Solution1 s1 = new Solution1();
        Solution1 s2 = new Solution2();
        Solution1 s3 = new Solution3();
        Solution1 s4 = new Solution4();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(Integer.toString(s1.LongestIncreasingSubsequence(nums)));
        System.out.println(Integer.toString(s2.LongestIncreasingSubsequence(nums)));
        System.out.println(Integer.toString(s3.LongestIncreasingSubsequence(nums)));
        System.out.println(Integer.toString(s4.LongestIncreasingSubsequence(nums)));
    }
}

